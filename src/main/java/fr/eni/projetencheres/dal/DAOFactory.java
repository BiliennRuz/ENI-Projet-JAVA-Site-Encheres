package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.bo.Enchere;

public class DAOFactory {
	/**
	 * Cette méthode sert à éviter le : RepasDAO repasDAO = new RepasDAOJdbcImpl() dans la couche BLL
	 */
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
	

}
