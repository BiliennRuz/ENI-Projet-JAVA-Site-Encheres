package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {

	private int idArticle , prixInitial, prixVente, idUtilisateur, idCategorie;
	private String nomArticle, description;
	private LocalDate dateDebutEncheres, dateFinEncheres;
	private Enchere lastEnchere;
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;
	
	public ArticleVendu() {
	}
	public ArticleVendu(int idArticle, int prixInitial, int prixVente, int idUtilisateur, int idCategorie, String nomArticle,
			String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres) {
		super();
		this.idArticle = idArticle;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.idUtilisateur = idUtilisateur;
		this.idCategorie = idCategorie;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
	}
	public ArticleVendu(int prixInitial, int prixVente, int idUtilisateur, int idCategorie, String nomArticle,
			String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres) {
		super();
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.idUtilisateur = idUtilisateur;
		this.idCategorie = idCategorie;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public Enchere getLastEnchere() {
		return lastEnchere;
	}
	public void setLastEnchere(Enchere lastEnchere) {
		this.lastEnchere = lastEnchere;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}
	public Categorie getCategorieArticle() {
		return categorieArticle;
	}
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	
	@Override
	public String toString() {
		return "ArticleVendu [idArticle=" + idArticle + ", prixInitial=" + prixInitial + ", prixVente=" + prixVente
				+ ", idUtilisateur=" + idUtilisateur + ", idCategorie=" + idCategorie + ", nomArticle=" + nomArticle
				+ ", description=" + description + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres="
				+ dateFinEncheres + ", lastEnchere=" + lastEnchere + ", vendeur=" + vendeur + ", lieuRetrait="
				+ lieuRetrait + ", categorieArticle=" + categorieArticle + "]";
	}	
}
