package fr.eni.projetencheres.bo;

import java.time.LocalDateTime;


public class Enchere {

	private int idEnchere, montantEnchere, idArticle, idUtilisateur;
	private LocalDateTime dateEnchere;
	private ArticleVendu articleConcerne;
	private Utilisateur enrichisseur;
	
	public Enchere() {
	}
			
	public Enchere(int montantEnchere, int idArticle, int idUtilisateur, LocalDateTime dateEnchere) {
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

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public ArticleVendu getArticleConcerne() {
		return articleConcerne;
	}

	public void setArticleConcerne(ArticleVendu articleConcerne) {
		this.articleConcerne = articleConcerne;
	}

	public Utilisateur getEnrichisseur() {
		return enrichisseur;
	}

	public void setEnrichisseur(Utilisateur enrichisseur) {
		this.enrichisseur = enrichisseur;
	}

	@Override
	public String toString() {
		return "Enchere [idEnchere=" + idEnchere + ", montantEnchere=" + montantEnchere + ", idArticle=" + idArticle
				+ ", idUtilisateur=" + idUtilisateur + ", dateEnchere=" + dateEnchere + ", articleConcerne="
				+ articleConcerne + ", enrichisseur=" + enrichisseur + "]";
	}
	
}
