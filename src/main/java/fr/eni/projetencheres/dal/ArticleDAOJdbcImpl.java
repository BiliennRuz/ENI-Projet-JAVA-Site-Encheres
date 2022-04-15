package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;

/**

 * Implémentation des fonctionnalités de mon interface ArticleVenduDAO avec JDBC (en base de donnée)

 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_ARTICLE = "select * from ARTICLES_VENDUS;";
	private final static String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(prix_initial, prix_vente, no_utilisateur, no_categorie, nom_article, description, date_debut_encheres, date_fin_encheres, status_vente) values(?,?,?,?,?,?,?,?,?);";
	private final static String UPDATE_ARTICLE = "update ARTICLES_VENDUS set prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, status_vente=? WHERE no_utilisateur=?";
	
	
	
	/**
	 * getUser() : recupère la liste des articles depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<ArticleVendu> getArticle() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_ARTICLE);
		
		// 3 - j'initialise la liste des articles que je vais renvoyer
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des article que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le article correspondant à ma liste
			ArticleVendu article = new ArticleVendu(
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"),
					rs.getInt("no_utilisateur"),
					rs.getInt("no_categorie"),
					rs.getString("nom_article"),
					rs.getString("description"),
					rs.getDate("date_debut_encheres").toLocalDate(),
					rs.getDate("date_fin_encheres").toLocalDate(),
					rs.getString("status_vente")
					);
			listeArticleVendus.add(article); // une fois le article créé je l'ajoute à ma liste
		}
		
		return listeArticleVendus; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * add(ArticleVendu article) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : ArticleVenduManager)
	 */
	@Override
	public void addArticle(ArticleVendu article) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ARTICLE,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet ArticleVendu		
		// "insert into ARTICLES(prix_initial, prix_vente, no_utilisateur, no_categorie, nom_article, description, date_debut_encheres, date_fin_encheres, status_vente) values(?,?,?,?,?,?,?,?,?);";
		pStmt.setInt(1, article.getPrixInitial());
		pStmt.setInt(2, article.getPrixVente());
		pStmt.setInt(3, article.getIdUtilisateur());
		pStmt.setInt(4, article.getIdCategorie());
		pStmt.setString(5, article.getNomArticle());
		pStmt.setString(6, article.getDescription());
		pStmt.setDate(7, Date.valueOf(article.getDateDebutEncheres()));
		pStmt.setDate(8, Date.valueOf(article.getDateFinEncheres()));
		pStmt.setString(9, article.getStatusVente());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet ArticleVendu
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			article.setIdArticle(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
	/**
	 * update(ArticleVendu article) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : ArticleVenduManager)
	 */
	@Override
	public void updateArticle(ArticleVendu article) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ARTICLE,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet ArticleVendu		
		//"update ARTICLES set prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, status_vente=? WHERE no_utilisateur=?";
		pStmt.setInt(1, article.getPrixInitial());
		pStmt.setInt(2, article.getPrixVente());
		pStmt.setInt(3, article.getIdUtilisateur());
		pStmt.setInt(4, article.getIdCategorie());
		pStmt.setString(5, article.getNomArticle());
		pStmt.setString(6, article.getDescription());
		pStmt.setDate(7, Date.valueOf(article.getDateDebutEncheres()));
		pStmt.setDate(8, Date.valueOf(article.getDateFinEncheres()));
		pStmt.setString(9, article.getStatusVente());
		pStmt.setInt(10, article.getIdArticle());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
				
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}

}
