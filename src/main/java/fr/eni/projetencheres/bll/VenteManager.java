package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;


public class VenteManager {
	// attribut qui contient la référence vers notre couche DAL (ajout base de donnée)
	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	
	/**
	 * getCategorie() : retourne la liste des categories depuis la couche DAL
	 */
	public List<Categorie> getCategorie() {
		try {
			return this.categorieDAO.getCategorie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais y'a une exception onretournera null
	}
	
	/**
	 * getCategorie() : retourne la liste des articles en vente depuis la couche DAL
	 * et filtre selon :
	 * - le mot clé dans le nom
	 * - la categorie selectionnée
	 * - le status de la vente
	 */
	public List<ArticleVendu> SearchArticleVente(String nom, int categorie, String statusVente) {
		try {
			// on recupere la liste de tous les articles
			List<ArticleVendu> listeArticles = this.articleDAO.getArticle();
			List<ArticleVendu> listeArticlesEnVente = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVendu : listeArticles) {
				// on filtre sur les vente en cour et avec la categorie selectionnée
				if ((articleVendu.getStatusVente().equals(statusVente))
						&& (articleVendu.getIdCategorie() == categorie)
						&& (articleVendu.getNomArticle().contains(nom))
						) {
					listeArticlesEnVente.add(articleVendu);
					System.out.println("DEBUG SearchArticleEnVente, add : " + articleVendu);
				}
			}
			return listeArticlesEnVente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais y'a une exception onretournera null
	}
	

	/**
	 * getCategorie() : retourne la liste des articles en vente depuis la couche DAL
	 * et filtre selon :
	 * - le mot clé dans le nom
	 * - la categorie selectionnée
	 * - le status de la vente
	 */
	public List<ArticleVendu> SearchArticleAchat(String nom, int categorie, String statusAchat) {
		try {
			// on recupere la liste de tous les articles
			List<ArticleVendu> listeArticles = this.articleDAO.getArticle();
			List<ArticleVendu> listeArticlesEnVente = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVendu : listeArticles) {
				// on filtre sur les vente en cour et avec la categorie selectionnée
				if ((articleVendu.getStatusVente().equals(statusAchat))
						&& (articleVendu.getIdCategorie() == categorie)
						&& (articleVendu.getNomArticle().contains(nom))
						) {
					listeArticlesEnVente.add(articleVendu);
					System.out.println("DEBUG SearchArticleEnVente, add : " + articleVendu);
				}
			}
			return listeArticlesEnVente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais il y a une exception on retournera null
	}
	
}
