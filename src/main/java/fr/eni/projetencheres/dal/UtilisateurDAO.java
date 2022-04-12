package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public void add(Utilisateur user) throws SQLException;
	public List<Utilisateur> getAll() throws SQLException;
	public List<String> getIngredients(int idRepas) throws SQLException;
}
