package fr.eni.projetencheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.User;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();

	private Utilisateur trouverUtilisateur(String login, String password) throws BusinessException, SQLException {
		
		Utilisateur utilisateur = new Utilisateur();
		return utilisateur;
	}
	
	private void verifierMotDePasseEtLogin(String login, String password) throws BusinessException, SQLException {
		
		
		
		if(login.length() < 3) {
			throw new BusinessException("Le login doit comporter au moins 3 lettres");
		}
		if(password.length() < 3) {
			throw new BusinessException("Le mot de passe doit comporter au moins 3 lettres");
		}
	}
	
	
	
	
	
	
	
	
	
//	private void validationConnexion(String login, String password) throws BusinessException, SQLException {
//
//		try {
//			Utilisateur utilisateur = new Utilisateur();
//			
//			if( login != null && password != null ) {
//				this.utilisateurDAO.check(utilisateur);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new BusinessException("erreur SQL lors de l'insertion en base de donnée");
//		}		
//	}
}
