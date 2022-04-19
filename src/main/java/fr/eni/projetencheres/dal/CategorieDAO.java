package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;

public interface CategorieDAO {

	public List<Categorie> getCategorie() throws SQLException;
	public Categorie getCategorieById(int idCategorie) throws SQLException;
	public void addCategorie(Categorie categorie) throws SQLException;

}
