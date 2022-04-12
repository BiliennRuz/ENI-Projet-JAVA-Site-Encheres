package fr.eni.projetencheres.bo;

public class Utilisateur {
	private String pseudo, nom, prenom, email, rue, ville, motDePasse;
	private int noUtilisateur, telephone, codePostal;
	private float credit;
	private boolean administrateur;
	
	public Utilisateur() {
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String rue, String ville, String motDePasse,
			int noUtilisateur, int telephone, int codePostal, float credit, boolean administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.noUtilisateur = noUtilisateur;
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.credit = credit;
		this.administrateur = administrateur;
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

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
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

	@Override
	public String toString() {
		return "User [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", rue=" + rue
				+ ", ville=" + ville + ", motDePasse=" + motDePasse + ", noUtilisateur=" + noUtilisateur
				+ ", telephone=" + telephone + ", codePostal=" + codePostal + ", credit=" + credit + ", administrateur="
				+ administrateur + "]";
	}
}
