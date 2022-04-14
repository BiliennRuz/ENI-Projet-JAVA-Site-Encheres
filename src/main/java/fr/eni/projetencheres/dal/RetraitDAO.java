package fr.eni.projetencheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetencheres.bo.Retrait;

public interface RetraitDAO {

	public List<Retrait> getRetrait() throws SQLException;
	public void addRetrait(Retrait retrait) throws SQLException;

}
