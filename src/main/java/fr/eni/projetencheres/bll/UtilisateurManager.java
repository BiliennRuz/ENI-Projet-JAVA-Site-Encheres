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
	
	
	private void validation(Utilisateur utilisateur) throws BusinessException, SQLException {
		if( utilisateur.getPseudo() != null ) {
			this.utilisateurDAO.add(utilisateur);
		}	
	}
}
