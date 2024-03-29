package fr.eni.projetencheres.bo;

import java.util.ArrayList;

public class Utilisateur {

	private String pseudo, nom, prenom, email, rue, ville, motDePasse, telephone, codePostal;
	private int idUtilisateur;
	private float credit;
	private boolean administrateur;
	private ArrayList<ArticleVendu> listeArticles  = new ArrayList<ArticleVendu>();
	private ArrayList<Enchere> listeEncheres  = new ArrayList<Enchere>();
	
	public Utilisateur() {
	}
	// Constructeur "full" utilisé pour la gestion utilisateurs
	public Utilisateur(String pseudo, String nom, String prenom, String email, String rue,
			String ville, String motDePasse, int noUtilisateur, String telephone, String codePostal,
			float credit, boolean administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.idUtilisateur = noUtilisateur;
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	// Constructeur "light" utilisé pour les encheres
	public Utilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String rue,
			String ville, int noUtilisateur, String telephone, String codePostal,
			float credit) {
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.idUtilisateur = noUtilisateur;
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	public ArrayList<ArticleVendu> getListeArticles() {
		return listeArticles;
	}
	public void setListeArticles(ArrayList<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}
	public ArrayList<Enchere> getListeEncheres() {
		return listeEncheres;
	}
	public void setListeEncheres(ArrayList<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + "pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", rue="
				+ rue + ", ville=" + ville + ", motDePasse=" + motDePasse + ", idUtilisateur=" + idUtilisateur
				+ ", telephone=" + telephone + ", codePostal=" + codePostal + ", credit=" + credit + ", administrateur="
				+ administrateur + ", listeArticles=" + listeArticles + ", listeEncheres=" + listeEncheres + "]";
	}
}
