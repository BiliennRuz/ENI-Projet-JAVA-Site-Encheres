package fr.eni.projetencheres.servlets;

import java.io.IOException;
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
		// 1 - on recupère les repas deouis la couche BLL
		List<Categorie> categories = this.venteManager.getCategorie();
		request.setAttribute("categories", categories);
		
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
		String idCategorie = request.getParameter("categorie");
		System.out.println("DEBUG article : " + article);
		System.out.println("DEBUG idCategorie : " + idCategorie);
		List<ArticleVendu> articles = this.venteManager.SearchArticleVente(article, 1, "Vente en cours");
		System.out.println("DEBUG articles : " + articles);
		request.setAttribute("articles", articles);
		
		// 3 - on délègue la génération de la réponse HTML à la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		
	}

}
