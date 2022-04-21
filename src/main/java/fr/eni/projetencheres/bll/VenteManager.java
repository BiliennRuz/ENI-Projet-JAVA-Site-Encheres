package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.projetencheres.bll.BusinessException;
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
	
	// initialisation du logger
	Logger logger = LoggerFactory.getLogger(VenteManager.class);
	
	// attribut qui contient la référence vers notre couche DAL (ajout base de donnée)
	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	private RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
	private EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	
	public List<ArticleVendu> getArticle() {
		logger.info("Appel de getArticle()");
		try {
			return this.articleDAO.getArticle();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
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
	
	public Categorie getCategorieByName(String name) {
		try {
			return this.categorieDAO.getCategorieByName(name);
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
	public Categorie getCategorieById(int id) {
		try {
			
			return this.categorieDAO.getCategorieById(id);
			
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
	 * @throws BusinessException 
	 */
	
	public List<ArticleVendu> SearchArticleVente(String nom, int categorie, boolean venteNonDebutee, boolean venteEnCours, boolean venteTerminee) { //throws BusinessException {
		try {
			// on recupere la liste de tous les articles
			List<ArticleVendu> listeArticles = this.articleDAO.getArticle();
			List<ArticleVendu> listeArticlesFiltreCatNom = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVendu : listeArticles) {
				// on filtre sur les vente avec la categorie selectionnée et l'element du nom selectionné			
				if (((articleVendu.getIdCategorie() == categorie) || (categorie == 0))
						&& (articleVendu.getNomArticle().contains(nom))
						) {
					// on recupere le vendeur associé
					Utilisateur vendeur = this.utilisateurDAO.getUserById(articleVendu.getIdUtilisateur());
					articleVendu.setVendeur(vendeur);
					// on recupere la categorie associé
					Categorie libelleCategorie = this.categorieDAO.getCategorieById(articleVendu.getIdCategorie());
					articleVendu.setCategorieArticle(libelleCategorie);
					// on recupere le retrait associé si pas null
					try {
						Retrait adresseRetrait = this.retraitDAO.getRetraitById(articleVendu.getIdArticle());
						articleVendu.setLieuRetrait(adresseRetrait);
					} catch (SQLException e) {
						logger.error("Pas de retrait à récuperer dans la base !!!");
						e.printStackTrace();
						//throw new BusinessException("erreur SQL lors de la récupération du retrait de l'article en base de donnée");
					}
					// on recupere les encheres associé

					try {
						List<Enchere> encheres = this.enchereDAO.getEnchereByIdArticle(articleVendu.getIdArticle());
						// recuperation de la dernière enchère de l'article si il y en a
						if (encheres.size() > 0) {
					        LocalDateTime maxDateTime = LocalDateTime.of(1900, 1, 1, 0, 0);
					        Enchere lastEnchere = new Enchere();
							for (Enchere enchere : encheres) {
								// Si la date de l'enchere est apres la derniere mémorisée
								if (enchere.getDateEnchere().isAfter(maxDateTime)) {
									// On memorise la date la plus élévée
									maxDateTime = enchere.getDateEnchere();
									// On sauvegarde l'encère
									lastEnchere = enchere;
								}
							}
							// On ajoute la dernière enchere a l'article
							articleVendu.setLastEnchere(lastEnchere);
						}
					} catch (SQLException e) {
						logger.error("Pas d'enchère à récuperer dans la base");
						e.printStackTrace();
						//throw new BusinessException("erreur SQL lors de la récupération de l'enchère en base de donnée");
					}
					// on ajoute l'article a la liste
					listeArticlesFiltreCatNom.add(articleVendu);
				}
			}
			
			// on filtre en fonction du type de vente
			logger.debug("venteNonDebutee : " + venteNonDebutee + " / venteEnCours : " + venteEnCours + " / venteTerminee : " + venteTerminee);
			List<ArticleVendu> listeArticlesFiltreVente = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVenduFiltreCatNom : listeArticlesFiltreCatNom) {
//				logger.debug("getDateDebutEncheres().compareTo : " + articleVenduFiltreCatNom.getDateDebutEncheres().compareTo(LocalDate.now()));
//				logger.debug("getDateFinEncheres().compareTo : " + articleVenduFiltreCatNom.getDateFinEncheres().compareTo(LocalDate.now()));
				// cas "Vente non débuté"
				if (
						(venteNonDebutee)
						&& (articleVenduFiltreCatNom.getDateDebutEncheres().compareTo(LocalDate.now()) > 0)
					) {
					logger.debug("add Vente non débutée :" + articleVenduFiltreCatNom);
					listeArticlesFiltreVente.add(articleVenduFiltreCatNom);
				}
				// cas "Vente en cours"
				if (
						(venteEnCours)
						&& (articleVenduFiltreCatNom.getDateDebutEncheres().compareTo(LocalDate.now()) <= 0)
						&& (articleVenduFiltreCatNom.getDateFinEncheres().compareTo(LocalDate.now()) >= 0)
					) {
					logger.debug("add Vente en cours : " + articleVenduFiltreCatNom);
					listeArticlesFiltreVente.add(articleVenduFiltreCatNom);
				}
				// cas "Vente terminée"
				if (
						(venteTerminee)
						&& (articleVenduFiltreCatNom.getDateFinEncheres().compareTo(LocalDate.now()) < 0)
					) {
					logger.debug("Vente terminée : " + articleVenduFiltreCatNom);
					listeArticlesFiltreVente.add(articleVenduFiltreCatNom);
				}
			}

			return listeArticlesFiltreVente;
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new BusinessException("erreur SQL lors de la récupération de l'article en base de donnée");
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
			
			List<ArticleVendu> listeArticlesFiltreCatNom = new ArrayList<ArticleVendu>();
			for (ArticleVendu articleVendu : listeArticles) {
				// on filtre sur les vente en cour et avec la categorie selectionnée
				if ((articleVendu.getDateDebutEncheres().compareTo(LocalDate.now()) <= 0)
						&& (articleVendu.getDateFinEncheres().compareTo(LocalDate.now()) >= 0)
						&& ((articleVendu.getIdCategorie() == categorie) || (categorie == 0))
						&& (articleVendu.getNomArticle().contains(nom))
						) {				
					listeArticlesFiltreCatNom.add(articleVendu);
					logger.debug("SearchArticleEnVente, add : " + articleVendu);
				}
			}
			return listeArticlesFiltreCatNom;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // si jamais il y a une exception on retournera null
	}
	
}
