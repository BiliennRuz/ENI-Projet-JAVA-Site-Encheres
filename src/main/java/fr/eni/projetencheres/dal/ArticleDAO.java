package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;

public interface ArticleDAO {

	public List<ArticleVendu> getArticle() throws SQLException;
	public void addArticle(ArticleVendu article) throws SQLException;
	public void updateArticle(ArticleVendu article) throws SQLException;

}
