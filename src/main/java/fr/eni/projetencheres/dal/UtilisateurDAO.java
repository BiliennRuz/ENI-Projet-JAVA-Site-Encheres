package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public List<Utilisateur> getUsers() throws SQLException;
	public void add(Utilisateur user) throws SQLException;
	public void update(Utilisateur user) throws SQLException;

}
