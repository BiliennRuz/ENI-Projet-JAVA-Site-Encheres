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

 * Implémentation des fonctionnalités de mon interface UtilisateurDAO avec JDBC (en base de donnée)

 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_UTILISATEUR = "select * from UTILISATEURS;";
	private final static String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	
	private final static String INSERT_ALIMENT = "insert into Ingredient(nom, id_user) values(?,?);";
	private final static String SELECT_INGREDIENTS = "select * from Ingredient where id_user=?;";
	
	/**
	 * getAll() : recupère la liste des user depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Utilisateur> getUsers() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_UTILISATEUR);
		
		// 3 - j'initialise la liste des user que je vais renvoyer
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le user correspondant à ma liste
			Utilisateur user = new Utilisateur(
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("rue"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("no_utilisateur"),
					rs.getInt("telephone"),
					rs.getInt("code_postal"),
					rs.getFloat("credit"),
					rs.getBoolean("administrateur")
					);
			listeUtilisateurs.add(user); // une fois le user créé je l'ajoute à ma liste
		}
		
		return listeUtilisateurs; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * add(Utilisateur user) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : UtilisateurManager)
	 */
	@Override
	public void add(Utilisateur user) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_UTILISATEUR,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Utilisateur		
		//pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur
		pStmt.setString(1, user.getPseudo());
		pStmt.setString(2, user.getNom());
		pStmt.setString(3, user.getPrenom());
		pStmt.setString(4, user.getEmail());
		pStmt.setInt(5, user.getTelephone());
		pStmt.setString(6, user.getRue());
		pStmt.setInt(7, user.getCodePostal());
		pStmt.setString(8, user.getVille());
		pStmt.setString(9, user.getMotDePasse());
		pStmt.setFloat(10, user.getCredit());
		pStmt.setBoolean(11, user.isAdministrateur());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet Utilisateur
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			user.setNoUtilisateur(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
	
	
	
	/**
	 * getIngredients() : recupère la liste des ingredients pour un user donné
	 * @throws SQLException 
	 */
	@Override
	public List<String> getIngredients(int idUtilisateur) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête paramétrée" car on a besoin de remplacer des ?
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_INGREDIENTS);
		
		// 2 - je remplace le ? de ma requête par l'id du user
		pStmt.setInt(1, idUtilisateur);
		
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
	 * addAliment() : ajoute un aliment à un user 
	 */
	private void addAliment(Utilisateur user, String aliment, Connection cnx)  throws SQLException {
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
