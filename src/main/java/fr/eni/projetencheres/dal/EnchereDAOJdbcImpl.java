package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Enchere;

/**
 * Implémentation des fonctionnalités de mon interface EnchereDAO avec JDBC (en base de donnée)
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	// instanciation du logger
	Logger logger = LoggerFactory.getLogger(EnchereDAOJdbcImpl.class);
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_ENCHERE = "select * from ENCHERES;";
	private final static String SELECT_ENCHERE_BY_ID_ARTICLE = "select * from ENCHERES where no_article=?;";
	private final static String INSERT_ENCHERE = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
	private final static String UPDATE_ENCHERE = "update ENCHERES set date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur=? WHERE no_enchere=?";
	
	
	
	/**
	 * getEnchere() : recupère la liste des encheres depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Enchere> getEnchere() throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_ENCHERE);
		// 4 - j'initialise la liste des encheres que je vais renvoyer
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		// 5 - je parcours mes resultats pour remplir ma liste des enchere que je vais renvoyer
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
		// 6 - pour finir je renvoie ma liste remplie precédemment
		return listeEncheres;
	}
	
	/**
	 * getEnchereByIdArticle(int idArticle) : recupère le user à partir de IdArticle depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Enchere> getEnchereByIdArticle(int idArticle) throws SQLException {
		logger.debug("Categorie getCategorieById(int idCategorie)");
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ENCHERE_BY_ID_ARTICLE);
		pStmt.setInt(1, idArticle);
		// 4 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		// 5 - j'initialise la liste des encheres que je vais renvoyer
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		// 6 - je parcours mes resultats pour remplir ma liste des enchere que je vais renvoyer
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
		// 7 - pour finir je renvoie ma liste remplie precédemment	
		return listeEncheres;
	}
	
	
	/**
	 * addEnchere(Enchere enchere) : ajout d'une enchere
	 * @throws SQLException 
	 */
	@Override
	public void addEnchere(Enchere enchere) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ENCHERE,Statement.RETURN_GENERATED_KEYS);
		// 3 - je remplace les ? de ma requête par les valeurs correspondantes	
		// "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
		System.out.println("DEBUG EnchereDAOJdbcImpl addEnchere convert LocalDateTime to SQL DateTime : " + Date.valueOf(enchere.getDateEnchere().toLocalDate()));
		
		pStmt.setDate(1, Date.valueOf(enchere.getDateEnchere().toLocalDate()));
//		pStmt.setTimestamp(1, Date.valueOf(enchere.getDateEnchere()));
		pStmt.setInt(2, enchere.getMontantEnchere());
		pStmt.setInt(3, enchere.getIdArticle());
		pStmt.setInt(4, enchere.getIdUtilisateur());
		// 4 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		// 5 -  je recupère l'id genéré et je met à jour l'objet Enchere
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			enchere.setIdEnchere(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		// 6 - on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
