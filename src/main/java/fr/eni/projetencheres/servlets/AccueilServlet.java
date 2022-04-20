package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.VenteManager;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenteManager venteManager = new VenteManager();

    /**
     * Default constructor. 
     */
    public AccueilServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
//		String verifUtilisateurInscrit = (String) session.getAttribute("utilisateurInscrit");
//		System.out.println(verifUtilisateurInscrit);
//		
//		if (verifUtilisateurInscrit.equals("true")) {
//			request.setAttribute("succes", "Vous êtes bien enregistré !");
//			session.removeAttribute("utilisateurinscrit");
//		}
		
		// on recupère les repas deouis la couche BLL
		List<Categorie> categories = this.venteManager.getCategorie();
		// on balance les categories au JSP
		request.setAttribute("categories", categories);
		
		// On recupère le mot clé recherché
		String article = request.getParameter("article");
		if (article == null) article = "";
		// On recupère la catégorie recherchée
		String idCategorie = request.getParameter("idcategorie");
		if (idCategorie == null) idCategorie = "0";
		// On recupère le type de vente selectionnée
		String achatVente = request.getParameter("achatvente");
		System.out.println("DEBUG : achatvente " + achatVente);
		if (achatVente == null) achatVente = "vente";
		
		String venteNonDebutee = request.getParameter("ventenondebutee");
		System.out.println("DEBUG : venteNonDebutee " + venteNonDebutee);
		if (venteNonDebutee == null) venteNonDebutee = "false";
		System.out.println("DEBUG : venteNonDebutee " + venteNonDebutee);
		
		String venteEnCours = request.getParameter("venteencours");
		System.out.println("DEBUG : venteEnCours : " + venteEnCours);
		if (venteEnCours == null) venteEnCours = "false";
		System.out.println("DEBUG : venteEnCours : " + venteEnCours);
		
		String venteTerminee = request.getParameter("venteterminee");
		System.out.println("DEBUG : venteTerminee : " + venteTerminee);
		if (venteTerminee == null) venteTerminee = "false";
		System.out.println("DEBUG : venteTerminee : " + venteTerminee);
		
		// On recupère le type d'achat selectionnée
		String enchereOuvertes = request.getParameter("enchereouvertes");
		if (enchereOuvertes == null) enchereOuvertes = "false";
		System.out.println("DEBUG : enchereOuvertes " + enchereOuvertes);
		
		String mesEncheres = request.getParameter("mesencheres");
		if (mesEncheres == null) mesEncheres = "false";
		System.out.println("DEBUG : mesEncheres : " + mesEncheres);
		
		String mesEncheresRemportees = request.getParameter("mesencheresremportees");
		if (mesEncheresRemportees == null) mesEncheresRemportees = "false";
		System.out.println("DEBUG : mesEncheresRemportees : " + mesEncheresRemportees);
		
		// On envoie la liste des encheres en cours 
		List<ArticleVendu> articlesTries = this.venteManager.SearchArticleVente(article, Integer.valueOf(idCategorie), Boolean.valueOf(venteNonDebutee), Boolean.valueOf(venteEnCours), Boolean.valueOf(venteTerminee));
		request.setAttribute("articles", articlesTries);
		
		List<ArticleVendu> articles = this.venteManager.getArticle();
		request.setAttribute("articles", articles);
		
		// Affichage de la page d'accueil
		request.setAttribute("utilisateurConnecte", session.getAttribute("utilisateurConnecte"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		session.removeAttribute("succes");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// je redirige sur le formulaire
		this.doGet(request, response);
		
	}

}
