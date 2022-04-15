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
				
		// on recupère les repas deouis la couche BLL
		List<Categorie> categories = this.venteManager.getCategorie();
		// on balance les categories au JSP
		request.setAttribute("categories", categories);
		
		// On envoi la liste des enchere en cour 
		String article = request.getParameter("article");
		if (article == null) article = "";
		
		String idCategorie = request.getParameter("idcategorie");
		if (idCategorie == null) idCategorie = "0";
		List<ArticleVendu> articles = this.venteManager.SearchArticleVente(article, Integer.valueOf(idCategorie), "Vente en cours");
		request.setAttribute("articles", articles);
		
		// Affichage de la page d'accueil
		HttpSession session = request.getSession();
		session.getAttribute("utilisateurConnecte");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// je redirige sur le formulaire
		this.doGet(request, response);
		
	}

}
