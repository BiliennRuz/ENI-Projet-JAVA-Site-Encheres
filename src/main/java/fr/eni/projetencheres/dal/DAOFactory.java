package fr.eni.projetencheres.dal;

public class DAOFactory {
	/**
	 * Cette méthode sert à éviter le : RepasDAO repasDAO = new RepasDAOJdbcImpl() dans la couche BLL
	 */
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static UtilisateurDAO getUtilisateurDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
