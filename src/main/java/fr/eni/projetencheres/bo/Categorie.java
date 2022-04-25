package fr.eni.projetencheres.bo;

import java.util.ArrayList;

public class Categorie {

	private int idCategorie;
	private String libelle;
	private ArrayList<ArticleVendu> listeArticles  = new ArrayList<ArticleVendu>();
	
	public Categorie() {
		super();
	}
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	public Categorie(int idCategorie, String libelle) {
		super();
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}
	
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public ArrayList<ArticleVendu> getListeArticles() {
		return listeArticles;
	}
	public void setListeArticles(ArrayList<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", libelle=" + libelle + ", listeArticles=" + listeArticles
				+ "]";
	}
	
}
