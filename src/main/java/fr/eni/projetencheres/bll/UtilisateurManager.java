package fr.eni.projetencheres.bll;

// --- IMPORTS ---
import java.sql.SQLException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	
	// --- CONNEXION --- (#1001)
	
	// Méthode pour la Connexion 
	public Utilisateur trouverUtilisateur(String login, String password) throws BusinessException, SQLException {
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateur = utilisateurDAO.checkConnectUser(login, password);
			
		} catch(SQLException e) {
			throw new BusinessException("Utilisateur non trouvé...");
		}	
		
		return utilisateur;
	}
	
	
	// --- INSCRIPTION --- (#1003)
	
	// Méthode principale pour vérifier la conformité de tous les champs inscrits par l'utilisateur
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException, SQLException {
		
		Utilisateur nouvelUtilisateur = new Utilisateur();
		
		// Pseudo :
		if(verifierPseudo(utilisateur.getPseudo())) {
			nouvelUtilisateur.setPseudo(utilisateur.getPseudo());
		}
		else {
			throw new BusinessException("Le pseudo est trop court");
		}
		// Nom :
		if(verifierNom(utilisateur.getNom())) {
			nouvelUtilisateur.setNom(utilisateur.getNom());
		}
		else {
			throw new BusinessException("Le nom est trop court");
		}
		// Prénom :
		if(verifierPrenom(utilisateur.getPrenom())) {
			nouvelUtilisateur.setPrenom(utilisateur.getPrenom());
		}
		else {
			throw new BusinessException("Le prénom est trop court");
		}
		// Ville :
		if(verifierVille(utilisateur.getVille())) {
			nouvelUtilisateur.setVille(utilisateur.getVille());
		}
		else {
			throw new BusinessException("La ville est trop courte");
		}
		// Rue :
		if(verifierRue(utilisateur.getRue())) {
			nouvelUtilisateur.setRue(utilisateur.getRue());
		}
		else {
			throw new BusinessException("La rue est trop courte");
		}
		// Rue :
		if(verifierTelephone(utilisateur.getTelephone())) {
			nouvelUtilisateur.setTelephone(utilisateur.getTelephone());
		}
		else {
			throw new BusinessException("Le numéro de téléphone est trop court");
		}
		
		// On alloue un crédit de 100 points au nouvel utilisateur
		nouvelUtilisateur.setCredit(100);
		
		// On envoie le nouvel utilisateur à la DAO
		utilisateurDAO.addUser(nouvelUtilisateur);
		
	}
	
	// Vérifier la conformité du Pseudo
	private boolean verifierPseudo(String pseudo) {
		if(verifierString(pseudo)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Vérifier la conformité du Nom
	private boolean verifierNom(String nom) {
		if(verifierString(nom)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Vérifier la conformité du Prénom
	private boolean verifierPrenom(String prenom) {
		if(verifierString(prenom)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Vérifier la conformité de la Ville
	private boolean verifierVille(String ville) {
		if(verifierString(ville)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Vérifier la conformité de la Rue
	private boolean verifierRue(String rue) {
		if(verifierString(rue)) {
			return true;
		}
		else {
			return false;
		}
	}
	// Vérifier la conformité de : Pseudo, Nom, Prénom, Ville, Rue
	private boolean verifierString(String string) {
		if(string.length() < 2) {
			return false;
		}
		return true;		
	}
	
	// Vérifier que le numéro de téléphone à au moins 10 numéros
	private boolean verifierTelephone(String telephone) {
		if(telephone.length() < 10) {
			return false;
		}
		return true;		
	}
	
	// Vérification Email qui va être utilisée pour l'Inscription
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
	
	// Vérifier que les 2 mots de passes correspondent (MDP et MDP confirmation)
	private void verifierMotDePasseAvecMotDePasseConfirmation(String password, String passwordConfirm) throws BusinessException, SQLException {
		
		if(password == passwordConfirm) {
		// Les mots de passes sont les mêmes
		} else {
			throw new BusinessException("Les mots de passes ne correspondent pas");
		}
	}
	
	// Vérification Login et Mot de Passe qui va être utilisée pour l'Inscription
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
