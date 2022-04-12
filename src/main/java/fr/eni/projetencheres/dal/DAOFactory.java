package fr.eni.projetencheres.dal;

public class DAOFactory {
	/**
	 * Cette méthode sert à éviter le : RepasDAO repasDAO = new RepasDAOJdbcImpl() dans la couche BLL
	 */
	public static UtilisateurDAO getRepasDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
}
