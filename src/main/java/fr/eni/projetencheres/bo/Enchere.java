package fr.eni.projetencheres.bo;

import java.time.LocalTime;

public class Enchere {

	private int idEnchere, montantEnchere, idArticle, idUtilisateur;
	private LocalTime dateEnchere;
	
	public Enchere(int montantEnchere, int idArticle, int idUtilisateur, LocalTime dateEnchere) {
		super();
		this.montantEnchere = montantEnchere;
		this.idArticle = idArticle;
		this.idUtilisateur = idUtilisateur;
		this.dateEnchere = dateEnchere;
	}

	public int getIdEnchere() {
		return idEnchere;
	}

	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public LocalTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [idEnchere=" + idEnchere + ", montantEnchere=" + montantEnchere + ", idArticle=" + idArticle
				+ ", idUtilisateur=" + idUtilisateur + ", dateEnchere=" + dateEnchere + "]";
	}
	
	
}
