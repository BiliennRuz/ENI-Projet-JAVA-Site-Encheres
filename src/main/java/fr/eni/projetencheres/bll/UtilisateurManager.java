package fr.eni.projetencheres.bll;

// --- IMPORTS ---
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

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
		// Code Postal :
		if(verifierCodePostal(utilisateur.getCodePostal())) {
			nouvelUtilisateur.setCodePostal(utilisateur.getCodePostal());
		}
		else {
			throw new BusinessException("Le CP est trop court");
		}
		// Téléphone :
		if(verifierTelephone(utilisateur.getTelephone())) {
			nouvelUtilisateur.setTelephone(utilisateur.getTelephone());
		}
		else {
			throw new BusinessException("Le numéro de téléphone est trop court");
		}
		// Email :
		if(verifierEmail(utilisateur.getEmail())) {
			nouvelUtilisateur.setEmail(utilisateur.getEmail());
		}
		else {
			throw new BusinessException("L'Email est trop court");
		}
		
		// On alloue un crédit de 100 points au nouvel utilisateur
		nouvelUtilisateur.setCredit(100);
		
		nouvelUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
		
		// On envoie le nouvel utilisateur à la DAO
		utilisateurDAO.addUser(nouvelUtilisateur);
		
	}
	
	// Vérifier la conformité du Pseudo
	private boolean verifierPseudo(String pseudo) throws BusinessException, SQLException {
		Pattern p;
		Matcher m;
		int compteur = 0;
		
		// Pour avoir uniquement des valeurs alphanumériques
		p = Pattern.compile("^[a-zA-Z0-9]+$");
		m = p.matcher(pseudo);
		
		if (!m.find()) {
			// Si les caractères ne correspondent pas, on lance une exception
			throw new BusinessException("Le pseudo ne doit comporter que des caractères alphanumériques");
		} 
		
		// On vérifie qu'il n'existe pas de pseudo identique
		if(verifierString(pseudo)) {
			
			List <Utilisateur> utilisateurs = utilisateurDAO.getUser();
					
			for(Utilisateur utilisateur : utilisateurs) {
				if(utilisateur.getPseudo().equals(pseudo)) {
					compteur++;
				}
			}
			
			if(compteur > 1) {
				throw new BusinessException("Ce pseudo est déjà pris !");
			}
		}
			
		return true;
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
	// Vérification Email
	private boolean verifierEmail(String email) throws SQLException {
		Pattern p;
		Matcher m;
		int compteur = 0;
				
		// Pour avoir le format de l'Email
		p = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}$");
		m = p.matcher(email);
				
		if(verifierString(email)) {
				
			List <Utilisateur> utilisateurs = utilisateurDAO.getUser();
						
			for(Utilisateur utilisateur : utilisateurs) {
				if(utilisateur.getEmail().equals(email)) {
					compteur++;
				}
			}	
		}
		if(compteur > 1 || !m.find()) {
			return false;
		}	
		return true;
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
	
	// Vérifier le code postal
	private boolean verifierCodePostal(String codePostal) {
		if(codePostal.length() < 5) {
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
	

	// Vérifier que les 2 mots de passes correspondent (MDP et MDP confirmation)
	public boolean verifierMotsDePasse(String password, String passwordConfirm) throws BusinessException, SQLException {
		
		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			throw new BusinessException("Les mots de passe ne concordent pas");
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
