package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Retrait;

/**
 * Implémentation des fonctionnalités de mon interface ArticleVenduDAO avec JDBC (en base de donnée)
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite
	private final static String SELECT_ARTICLES = "select * from ARTICLES_VENDUS AS av INNER JOIN UTILISATEURS AS u ON av.no_utilisateur = u.no_utilisateur;";
	private final static String SELECT_ARTICLE = "select * from ARTICLES_VENDUS;";
	private final static String SELECT_ARTICLE_BY_ID = "select * from ARTICLES_VENDUS where no_article=?;";
	private final static String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(prix_initial, prix_vente, no_utilisateur, no_categorie, nom_article, description, date_debut_encheres, date_fin_encheres, status_vente) values(?,?,?,?,?,?,?,?,?);";
	private final static String UPDATE_ARTICLE = "update ARTICLES_VENDUS set prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, status_vente=? WHERE no_utilisateur=?";
	
	
	/**
	 * getArticle() : recupère la liste des articles depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<ArticleVendu> getArticle() throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_ARTICLE);
		// 4 - j'initialise la liste des articles que je vais renvoyer
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();
		// 5 - je parcours mes resultats pour remplir ma liste des article que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			ArticleVendu article = new ArticleVendu(
					rs.getInt("no_article"),
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"),
					rs.getInt("no_utilisateur"),
					rs.getInt("no_categorie"),
					rs.getString("nom_article"),
					rs.getString("description"),
					rs.getDate("date_debut_encheres").toLocalDate(), // .format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",Locale.FRENCH))
					rs.getDate("date_fin_encheres").toLocalDate()
					);
			article.getVendeur();
			// pour chaque ligne , j'ajoute le article correspondant à ma liste			
			listeArticleVendus.add(article); // une fois le article créé je l'ajoute à ma liste
		}
		// 6 - our finir je renvoie ma liste remplie precédemment
		return listeArticleVendus;
	}
	
	/**
	 * getRetraitById() : recupère le retrait a partir de IdArticle depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public ArticleVendu getArticleById(int idArticle) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 3 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
		pStmt.setInt(1, idArticle);
		// 4 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		// 5 - je parcours mes resultats pour remplir mon retrait
		rs.next();
		ArticleVendu article = new ArticleVendu(
				rs.getInt("no_article"),
				rs.getInt("prix_initial"),
				rs.getInt("prix_vente"),
				rs.getInt("no_utilisateur"),
				rs.getInt("no_categorie"),
				rs.getString("nom_article"),
				rs.getString("description"),
				rs.getDate("date_debut_encheres").toLocalDate(),
				rs.getDate("date_fin_encheres").toLocalDate()
				);
		// 6 - je renvoie le retrait
		return article;		
	}
	
	/**
	 * addArticle(ArticleVendu article) : ajout d'un article dans la base de données
	 * @throws SQLException 
	 */
	@Override
	public void addArticle(ArticleVendu article) throws SQLException {
		// 1 - On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ARTICLE,Statement.RETURN_GENERATED_KEYS);
		// 3 - je remplace les ? de ma requête par les valeurs correspondantes
		// "insert into ARTICLES(prix_initial, prix_vente, no_utilisateur, no_categorie, nom_article, description, date_debut_encheres, date_fin_encheres, status_vente) values(?,?,?,?,?,?,?,?,?);";
		pStmt.setInt(1, article.getPrixInitial());
		pStmt.setInt(2, article.getPrixVente());
		pStmt.setInt(3, article.getIdUtilisateur());
		pStmt.setInt(4, article.getIdCategorie());
		pStmt.setString(5, article.getNomArticle());
		pStmt.setString(6, article.getDescription());
		pStmt.setDate(7, Date.valueOf(article.getDateDebutEncheres()));
		pStmt.setDate(8, Date.valueOf(article.getDateFinEncheres()));
		// 4 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		// 5 -  je recupère l'id genéré et je met à jour l'objet ArticleVendu
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			article.setIdArticle(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		// 6 - on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
	
	/**
	 * updateArticle(ArticleVendu article) : mise à jour d'un article dans la base de données
	 * @throws SQLException 
	 */
	@Override
	public void updateArticle(ArticleVendu article) throws SQLException {
		// 1 -On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ARTICLE,Statement.RETURN_GENERATED_KEYS);
		// 3 - je remplace les ? de ma requête par les valeurs correspondantes	
		//"update ARTICLES set prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, status_vente=? WHERE no_utilisateur=?";
		pStmt.setInt(1, article.getPrixInitial());
		pStmt.setInt(2, article.getPrixVente());
		pStmt.setInt(3, article.getIdUtilisateur());
		pStmt.setInt(4, article.getIdCategorie());
		pStmt.setString(5, article.getNomArticle());
		pStmt.setString(6, article.getDescription());
		pStmt.setDate(7, Date.valueOf(article.getDateDebutEncheres()));
		pStmt.setDate(8, Date.valueOf(article.getDateFinEncheres()));
		pStmt.setInt(10, article.getIdArticle());
		// 4 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		// 5 - on ferme la connexion quand tout a été ajouté
		cnx.close();
	}

}
