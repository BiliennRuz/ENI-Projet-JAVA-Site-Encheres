package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.jee.bo.Repas;

public interface UserDAO {
	public void add(User user) throws SQLException;
	public List<User> getAll() throws SQLException;
	public List<String> getIngredients(int idRepas) throws SQLException;
}
