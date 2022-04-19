package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;


public class VenteManager {
	// attribut qui contient la référence vers notre couche DAL (ajout base de donnée)
	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	
	/**
	 * getCategorie() : retourne la liste des categories depuis la couche DAL
	 */
	public List<Categorie> getCategorie() {
		try {
			return this.categorieDAO.getCategorie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais il y a une exception on retournera null
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
				// on filtre sur les vente en cour (date actuelle comprise ente date début et fin d'enchere)
				// et avec la categorie selectionnée et l'element du nom selectionné

				if ((articleVendu.getDateDebutEncheres().compareTo(LocalDate.now()) <= 0)
						&& (articleVendu.getDateFinEncheres().compareTo(LocalDate.now()) >= 0)
						&& ((articleVendu.getIdCategorie() == categorie) || (categorie == 0))
						&& (articleVendu.getNomArticle().contains(nom))
						) {
					// on recupere le vendeur associé
					Utilisateur vendeur = this.utilisateurDAO.getUserById(articleVendu.getIdUtilisateur());
					articleVendu.setVendeur(vendeur);
					// on recupere la categorie associé
					Categorie libelleCategorie = this.categorieDAO.getCategorieById(articleVendu.getIdCategorie());
					articleVendu.setCategorieArticle(libelleCategorie);
					// on ajoute l'article a la liste
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
			
//			// on recupere les vendeurs associés
//			for (ArticleVendu articleVendu : listeArticles) {
//				articleVendu.setVendeur(this.articleDAO.getArticle());
//			}
			
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
