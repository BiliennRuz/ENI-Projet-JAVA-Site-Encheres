package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public List<Utilisateur> getUser() throws SQLException;
	public void addUser(Utilisateur user) throws SQLException;
	public void updateUser(Utilisateur user) throws SQLException;
	public Utilisateur checkConnectUser(String login, String password) throws SQLException;
	public void deleteUser(String pseudo) throws SQLException;
}
