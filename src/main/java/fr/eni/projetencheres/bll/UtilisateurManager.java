package fr.eni.projetencheres.bll;

import java.sql.SQLException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	
	// --- CONNEXION ----
	
	public Utilisateur trouverUtilisateur(String login, String password) throws BusinessException, SQLException {
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateur = utilisateurDAO.checkConnectUser(login, password);
			return utilisateur;
			
		} catch(SQLException e) {
			throw new BusinessException("Utilisateur non trouvé...");
		}	
	}
	
	// --- INSCRIPTION ---
	
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException, SQLException {
		Utilisateur nouvelUtilisateur = new Utilisateur();
		
		if(verifierPseudo(utilisateur.getPseudo())) {
			nouvelUtilisateur.setPseudo(utilisateur.getPseudo());
		}
		else {
			throw new BusinessException("Le pseudo est trop court");
		}
		
		if(verifierNom(utilisateur.getNom())) {
			nouvelUtilisateur.setPseudo(utilisateur.getNom());
		}
		else {
			throw new BusinessException("Le nom est trop court");
		}

		return nouvelUtilisateur;
	}
	
	private boolean verifierPseudo(String pseudo) {
		if(verifierString(pseudo)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean verifierNom(String nom) {
		if(verifierString(nom)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean verifierString(String string) {
		
		if(string.length() < 2) {
			return false;
		}
		return true;	
		
	}
	// Méthode de vérification Email qui va être utilisée pour l'inscription
	private boolean verifierEmail(String email) {	
		Pattern p;
		Matcher m;
		
		p = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$");
		m = p.matcher(email);
		if (m.find()) {
		  return true;
		} else {
		  return false;
		}
	}
	
	// Méthode de vérification Login et Mot de Passe qui va être utilisée pour l'inscription
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
