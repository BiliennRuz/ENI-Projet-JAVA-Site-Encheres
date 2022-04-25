package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.projetencheres.bo.Utilisateur;

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
		
		request.getAttribute("succes");
		
		// On affiche la page nouvelle vente
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UTILISATEUR
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
		
		// PRIX
		int prixInitial = Integer.parseInt(request.getParameter("prix"));
		
		// CATEGORIES
		String categorie = request.getParameter("categories");
		Categorie categorieEnCours = venteManager.getCategorieByName(categorie);
		
		// DATES
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate dateDebut = LocalDate.parse(request.getParameter("debut"), formatter);
		LocalDate dateFin = LocalDate.parse(request.getParameter("fin"), formatter);
		
		// (prix_initial, prix_vente, no_utilisateur, no_categorie, nom_article, description, date_debut_encheres, date_fin_encheres)
		ArticleVendu nouvelArticle = new ArticleVendu();
		nouvelArticle.setPrixInitial(prixInitial);
		nouvelArticle.setIdUtilisateur(utilisateur.getIdUtilisateur());
		nouvelArticle.setIdCategorie(categorieEnCours.getIdCategorie());
		nouvelArticle.setNomArticle(request.getParameter("article"));
		nouvelArticle.setDescription(request.getParameter("description"));
		nouvelArticle.setDateDebutEncheres(dateDebut);
		nouvelArticle.setDateFinEncheres(dateFin);
		
		venteManager.ajouterArticle(nouvelArticle);
		
		request.setAttribute("succes", "Votre article a bien été ajouté !");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
		
	}

}
