package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Implémentation des fonctionnalités de mon interface CategorieDAO avec JDBC (en base de donnée)
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_CATEGORIE = "select * from CATEGORIES;";
	private final static String SELECT_CATEGORIE_BY_ID = "select * from CATEGORIES where no_categorie=?;";
	private final static String SELECT_CATEGORIE_BY_NAME = "select * from CATEGORIES where libelle=?;";
	private final static String INSERT_CATEGORIE = "insert into CATEGORIES(libelle) values(?);";
		
	
	/**
	 * getCategorie() : recupère la liste des categories depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Categorie> getCategorie() throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_CATEGORIE);
		// 4 - j'initialise la liste des categories que je vais renvoyer
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		// 5 - je parcours mes resultats pour remplir ma liste des categorie que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le categorie correspondant à ma liste
			Categorie categorie = new Categorie(
					rs.getInt("no_categorie"),
					rs.getString("libelle")
					);
			listeCategories.add(categorie); // une fois le categorie créé je l'ajoute à ma liste
		}
		// 6 - pour finir je renvoie ma liste remplie precédemment
		return listeCategories;
	}
	
	/**
	 * getCategorieById(int idCategorie) : recupère la categorie depuis la base de donnée a partir de l'id
	 * @throws SQLException 
	 */
	@Override
	public Categorie getCategorieById(int idCategorie) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
		pStmt.setInt(1, idCategorie);
		// 4 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		// 5 - je parcours mes resultats pour remplir ma categorie
		Categorie categorie = new Categorie();
		if (rs.next()) { // si jamais il y a un resultat
			categorie.setLibelle(rs.getString("libelle")); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
			categorie.setIdCategorie(idCategorie);
		}
		// 6 - pour finir je renvoie ma categorie precédemment
		return categorie;
	}
	
	public Categorie getCategorieByName(String name) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		Statement stmt = cnx.createStatement();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_NAME);
		pStmt.setString(1, name);
		ResultSet rs = pStmt.executeQuery();
		Categorie categorie = new Categorie();
		if (rs.next()) { // si jamais il y a un resultat
			categorie.setLibelle(rs.getString("libelle")); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
			categorie.setIdCategorie(rs.getInt("no_categorie"));
		}
		// 6 - pour finir je renvoie ma categorie precédemment
		return categorie;
	}
	
	
	/**
	 * addCategorie(Categorie categorie) : ajoute une categorie dans la base de données
	 * @throws SQLException 
	 */
	@Override
	public void addCategorie(Categorie categorie) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_CATEGORIE,Statement.RETURN_GENERATED_KEYS);
		// 3 - je remplace les ? de ma requête par les valeurs correspondantes	
		// "insert into CATEGORIES(libelle) values(?);";
		pStmt.setString(1, categorie.getLibelle());
		// 4 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		// 5 -  je recupère l'id genéré et je met à jour l'objet Categorie
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			categorie.setIdCategorie(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		// 6 - on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
