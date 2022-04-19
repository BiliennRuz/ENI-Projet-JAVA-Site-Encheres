package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Enchere;

public interface EnchereDAO {

	public List<Enchere> getEnchere() throws SQLException;
	public List<Enchere> getEnchereByIdArticle(int idArticle) throws SQLException;
	public void addEnchere(Enchere enchere) throws SQLException;
	public void updateEnchere(Enchere enchere) throws SQLException;

}
