package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Categorie;

/**

 * Implémentation des fonctionnalités de mon interface CategorieDAO avec JDBC (en base de donnée)

 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_CATEGORIE = "select * from CATEGORIES;";
	private final static String INSERT_CATEGORIE = "insert into CATEGORIES(libelle) values(?);";
		
	
	
	/**
	 * getUser() : recupère la liste des categories depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Categorie> getCategorie() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_CATEGORIE);
		
		// 3 - j'initialise la liste des categories que je vais renvoyer
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des categorie que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le categorie correspondant à ma liste
			Categorie categorie = new Categorie(
					rs.getInt("no_categorie"),
					rs.getString("libelle")
					);
			listeCategories.add(categorie); // une fois le categorie créé je l'ajoute à ma liste
		}
		
		return listeCategories; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * add(Categorie categorie) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : CategorieManager)
	 */
	@Override
	public void addCategorie(Categorie categorie) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_CATEGORIE,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Categorie		
		// "insert into CATEGORIES(libelle) values(?);";
		pStmt.setString(1, categorie.getLibelle());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet Categorie
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			categorie.setIdCategorie(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
