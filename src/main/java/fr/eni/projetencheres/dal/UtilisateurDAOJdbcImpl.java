package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Implémentation des fonctionnalités de mon interface UserDAO avec JDBC (en base de donnée)
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite
	private final static String INSERT_REPAS = "insert into User(date, heure) values(?,?);";
	private final static String INSERT_ALIMENT = "insert into Ingredient(nom, id_user) values(?,?);";
	private final static String SELECT_REPAS = "select * from User;";
	private final static String SELECT_INGREDIENTS = "select * from Ingredient where id_user=?;";
	
	/**
	 * getIngredients() : recupère la liste des ingredients pour un user donné
	 * @throws SQLException 
	 */
	@Override
	public List<String> getIngredients(int idUser) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête paramétrée" car on a besoin de remplacer des ?
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_INGREDIENTS);
		
		// 2 - je remplace le ? de ma requête par l'id du user
		pStmt.setInt(1, idUser);
		
		// 3 - j'execute la requête et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		
		// 4 - j'initialise la liste des ingredients que je vais renvoyer
		List<String> listeIngredients = new ArrayList<String>();
		
		// 5 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le nom de l'ingredient à ma liste
			String nomIngredient = rs.getString("nom");
			listeIngredients.add(nomIngredient);
		}
		
		return listeIngredients; // pour finir je renvoie ma liste remplie precédemment
	}

	
	/**
	 * getAll() : recupère la liste des user depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<User> getAll() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_REPAS);
		
		// 3 - j'initialise la liste des user que je vais renvoyer
		List<User> listeUser = new ArrayList<User>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le user correspondant à ma liste
			// je suis obligé de convertir Date => LocalDate et Time and LocalTime
			User user = new User(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getTime("heure").toLocalTime());
			listeUser.add(user); // une fois le user créé je l'ajoute à ma liste
		}
		
		return listeUser; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * add(User user) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : UserManager)
	 */
	@Override
	public void add(User user) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_REPAS,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet User		
		// setDate() a besoin d'une date au format java.sql.Date :  je dois convertir ma LocalDate en java.sql.Date (possible avec Date.valueOf())
		pStmt.setDate(1, Date.valueOf(user.getDate())); // ATTENTION, les paramètres commencent à l'index : 1
		
		// je remplace le deuxième ? de ma requête par l'heure  de mon objet User
		// setTime() a besoin d'une heure au format java.sql.Time :  je dois convertir mon LocalTime en java.sql.Time (possible avec Time.valueOf())
		pStmt.setTime(2, Time.valueOf(user.getHeure()));
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet User
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			user.setId(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		/**** REPAS Ajouté, je dois maintenant ajouter les aliments également ***/
		for (String aliment : user.getAliments()) {
			addAliment(user, aliment, cnx); // pour chaque aliment de mon user, j'appelle addAliment() avec la connexion en paramètre (pour utiliser la meême pour ajout user et aliment)
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}

	
	
	/**
	 * addAliment() : ajoute un aliment à un user 
	 */
	private void addAliment(User user, String aliment, Connection cnx)  throws SQLException {
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ALIMENT);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par le nom de mon aliment
		pStmt.setString(1, aliment); // ATTENTION, les paramètres commencent à l'index : 1
		
		// je remplace le deuxième ? de ma requête par l'id de mon user
		pStmt.setInt(2, user.getId());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données		
	}
}
