package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.EnchereDAO;
import fr.eni.projetencheres.dal.RetraitDAO;
import fr.eni.projetencheres.dal.UtilisateurDAO;


public class VenteManager {
	// attribut qui contient la référence vers notre couche DAL (ajout base de donnée)
	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	private RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
	private EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	
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
	
	public ArticleVendu getArticleById(int id) {
		try {
			
			return this.articleDAO.getArticleById(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * SearchArticleVente() : retourne la liste des articles en vente depuis la couche DAL
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
					// on recupere le retrait associé si pas null
					System.out.println("DEBUG VenteManager articleVendu.getIdArticle() : " + articleVendu.getIdArticle());
					try {
						Retrait adresseRetrait = this.retraitDAO.getRetraitById(articleVendu.getIdArticle());
						System.out.println("DEBUG VenteManager adresseRetrait : " + adresseRetrait);
						articleVendu.setLieuRetrait(adresseRetrait);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					// on recupere les encheres associé
//					Enchere encheres = this.enchereDAO.getEnchereById(articleVendu.getIdArticle());
//					articleVendu.setEnchere(encheres);
					// on ajoute l'article a la liste
					listeArticlesEnVente.add(articleVendu);
					System.out.println("DEBUG VenteManager SearchArticleEnVente, add : " + articleVendu);
				}
			}
			return listeArticlesEnVente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais il y a une exception on retournera null
	}
	

	/**
	 * SearchArticleAchat() : retourne la liste des articles en achat depuis la couche DAL
	 * et filtre selon :
	 * - le mot clé dans le nom
	 * - la categorie selectionnée
	 * - le status de l'achat
	 */
	public List<ArticleVendu> SearchArticleAchat(String nom, int categorie, String statusAchat) {
		try {
			// on recupere la liste de tous les articles
			List<ArticleVendu> listeArticles = this.articleDAO.getArticle();
			
			List<ArticleVendu> listeArticlesEnVente = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVendu : listeArticles) {
				// on filtre sur les vente en cour et avec la categorie selectionnée
				if ((articleVendu.getDateDebutEncheres().compareTo(LocalDate.now()) <= 0)
						&& (articleVendu.getDateFinEncheres().compareTo(LocalDate.now()) >= 0)
						&& ((articleVendu.getIdCategorie() == categorie) || (categorie == 0))
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
