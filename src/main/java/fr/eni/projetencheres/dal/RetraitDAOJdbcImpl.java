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

import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Implémentation des fonctionnalités de mon interface RetraitDAO avec JDBC (en base de donnée)
 */
public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	// instanciation du logger
	Logger logger = LoggerFactory.getLogger(RetraitDAOJdbcImpl.class);
	
	private final static String SELECT_RETRAIT = "select * from RETRAITS;";
	private final static String SELECT_RETRAIT_BY_ID_ARTICLE = "select * from RETRAITS where no_article=?;";
	private final static String INSERT_RETRAIT = "insert into RETRAITS(no_article, rue, code_postal, ville) values(?,?,?,?);";
		
	
	/**
	 * getRetrait() : recupère la liste des retraits depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Retrait> getRetrait() throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_RETRAIT);
		// 4 - j'initialise la liste des retraits que je vais renvoyer
		List<Retrait> listeRetraits = new ArrayList<Retrait>();
		// 5 - je parcours mes resultats pour remplir ma liste des retrait que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le retrait correspondant à ma liste
			// "insert into RETRAITS(no_article, rue, code_postal, ville) values(?,?,?,?);";
			Retrait retrait = new Retrait(
					rs.getInt("no_article"),
					rs.getString("rue"),
					rs.getString("code_postal"),
					rs.getString("ville")
					);
			listeRetraits.add(retrait); // une fois le retrait créé je l'ajoute à ma liste
		}
		return listeRetraits; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * getRetraitById() : recupère le retrait a partir de IdArticle depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public Retrait getRetraitById(int idArticle) throws SQLException {
		logger.debug("Retrait getRetraitById(int idArticle)");
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID_ARTICLE);
		pStmt.setInt(1, idArticle);
		// 4 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		// 5 - je parcours mes resultats pour remplir mon retrait
		Retrait retrait = new Retrait();
		if (rs.next()) {
			retrait.setIdArticle(rs.getInt("no_article"));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
		}
		// 6 - je renvoie le retrait
		return retrait;		
	}
	
	
	/**
	 * addRetrait(Retrait retrait) : Ajout d'un retrait dans la base de données
	 * @throws SQLException 
	 */
	@Override
	public void addRetrait(Retrait retrait) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_RETRAIT);
		// 3 - je remplace les ? de ma requête par les valeurs correspondantes	
		// "insert into RETRAITS(no_article, rue, code_postal, ville) values(?,?,?,?);"; 
		pStmt.setInt(1, retrait.getIdArticle());
		pStmt.setString(2, retrait.getRue());
		pStmt.setString(3, retrait.getCodePostal());
		pStmt.setString(4, retrait.getVille());
		// 4 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données	
		// 5 - on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
