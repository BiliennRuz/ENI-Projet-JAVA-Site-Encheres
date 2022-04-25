package fr.eni.projetencheres.bo;

public class Retrait {

	private int idArticle;
	private String rue, codePostal, ville;
	
	public Retrait() {
	}
	
	public Retrait(int idArticle, String rue, String codePostal, String ville) {
		super();
		this.idArticle = idArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [idArticle=" + idArticle + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
