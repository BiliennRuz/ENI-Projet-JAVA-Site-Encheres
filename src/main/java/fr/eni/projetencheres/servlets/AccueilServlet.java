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
		System.out.println("DEBUG categories : " + categories);
//		// on ajoute une categorie "toutes"
//		Categorie categorieToutes = new Categorie("Toutes");
//		categorieToutes.setIdCategorie(0);
//		categories.add(categorieToutes);
		// on balance les categories au JSP
		request.setAttribute("categories", categories);
		
		// On envoi la liste des enchere en cour 
		List<ArticleVendu> articles = this.venteManager.SearchArticleVente("", 0, "Vente en cours");
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

		String article = request.getParameter("article");
		String idCategorie = request.getParameter("idcategorie");
		System.out.println("DEBUG article : " + article);
		System.out.println("DEBUG idCategorie : " + idCategorie);
		List<ArticleVendu> articles = this.venteManager.SearchArticleVente(article, Integer.valueOf(idCategorie), "Vente en cours");
		request.setAttribute("articles", articles);
		
		// 3 - on délègue la génération de la réponse HTML à la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		
	}

}
