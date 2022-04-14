package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Retrait;

/**

 * Implémentation des fonctionnalités de mon interface RetraitDAO avec JDBC (en base de donnée)

 */
public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_RETRAIT = "select * from RETRAITS;";
	private final static String INSERT_RETRAIT = "insert into RETRAITS(no_article, rue, code_postal, ville) values(?,?,?,?);";
		
	
	/**
	 * getUser() : recupère la liste des retraits depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Retrait> getRetrait() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_RETRAIT);
		
		// 3 - j'initialise la liste des retraits que je vais renvoyer
		List<Retrait> listeRetraits = new ArrayList<Retrait>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des retrait que je vais renvoyer
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
	 * add(Retrait retrait) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : RetraitManager)
	 */
	@Override
	public void addRetrait(Retrait retrait) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_RETRAIT,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Retrait		
		// "insert into RETRAITS(no_article, rue, code_postal, ville) values(?,?,?,?);"; 
		pStmt.setInt(1, retrait.getIdArticle());
		pStmt.setString(2, retrait.getRue());
		pStmt.setString(3, retrait.getCodePostal());
		pStmt.setString(4, retrait.getVille());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
//		// 4 -  je recupère l'id genéré et je met à jour l'objet Retrait
//		ResultSet rs = pStmt.getGeneratedKeys();
//		if (rs.next()) { // si jamais il y a un resultat
//			retrait.setIdRetrait(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
//		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
