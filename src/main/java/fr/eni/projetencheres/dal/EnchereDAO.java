package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;

public interface EnchereDAO {

	public List<Utilisateur> getUser() throws SQLException;
	public void add(Utilisateur enchere) throws SQLException;
	public void update(Utilisateur enchere) throws SQLException;

}
