package fr.eni.projetencheres.dal;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Enchere;

/**

 * Implémentation des fonctionnalités de mon interface EnchereDAO avec JDBC (en base de donnée)

 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_ENCHERE = "select * from ENCHERES;";
	private final static String INSERT_ENCHERE = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
	private final static String UPDATE_ENCHERE = "update ENCHERES set date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE no_enchere=?";
	
	
	
	/**
	 * getUser() : recupère la liste des encheres depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Enchere> getEnchere() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_ENCHERE);
		
		// 3 - j'initialise la liste des encheres que je vais renvoyer
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des enchere que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le enchere correspondant à ma liste
			// "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
			Enchere enchere = new Enchere(
					rs.getInt("montant_enchere"),
					rs.getInt("no_article"),
					rs.getInt("no_utilisateur"),
					rs.getTimestamp("date_enchere").toLocalDateTime()
					);
			listeEncheres.add(enchere); // une fois le enchere créé je l'ajoute à ma liste
		}
		
		return listeEncheres; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * add(Enchere enchere) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : EnchereManager)
	 */
	@Override
	public void addEnchere(Enchere enchere) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ENCHERE,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Enchere		
		// "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
//		pStmt.setTimestamp(1, Date.valueOf(enchere.getDateEnchere()));
		pStmt.setInt(2, enchere.getMontantEnchere());
		pStmt.setInt(3, enchere.getIdArticle());
		pStmt.setInt(4, enchere.getIdUtilisateur());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet Enchere
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			enchere.setIdEnchere(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
	/**
	 * update(Enchere enchere) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : EnchereManager)
	 */
	@Override
	public void updateEnchere(Enchere enchere) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ENCHERE,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Enchere		
		// "update ENCHERES set date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE no_enchere=?";
//		pStmt.setTimestamp(1, Date.valueOf(enchere.getDateEnchere()));
		pStmt.setInt(2, enchere.getMontantEnchere());
		pStmt.setInt(3, enchere.getIdArticle());
		pStmt.setInt(4, enchere.getIdUtilisateur());
		pStmt.setInt(10, enchere.getIdEnchere());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
				
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}

}
