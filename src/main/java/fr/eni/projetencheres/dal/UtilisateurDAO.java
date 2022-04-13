package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public List<Utilisateur> getUser() throws SQLException;
	public void add(Utilisateur utilisateur) throws SQLException;
	public void update(Utilisateur utilisateur) throws SQLException;

}
