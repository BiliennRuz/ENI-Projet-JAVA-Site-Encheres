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
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/nouvelleVente")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	VenteManager venteManager = new VenteManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'utilisateur
		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateurConnecte"));
		
		// on récupère les catégories
		// on recupère les repas deouis la couche BLL
		List<Categorie> categories = this.venteManager.getCategorie();
		// on balance les categories au JSP
		request.setAttribute("categories", categories);
		
		// On affiche la page nouvelle vente
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String categorie = request.getParameter("categories");
		Categorie categorieEnCours = venteManager.getCategorieByName(categorie);
		
		ArticleVendu nouvelArticle = new ArticleVendu();
		nouvelArticle.setCategorieArticle(null);
		
	}

}
