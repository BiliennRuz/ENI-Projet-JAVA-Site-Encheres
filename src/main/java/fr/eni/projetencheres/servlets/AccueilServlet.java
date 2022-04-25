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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.projetencheres.bll.VenteManager;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("")
public class AccueilServlet extends HttpServlet {
	
	// initialisation du logger
    Logger logger = LoggerFactory.getLogger(AccueilServlet.class);
    
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
//		logger.debug("verifUtilisateurInscrit : " + verifUtilisateurInscrit);
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
		
		// On force la catégorie recherchée au premier chargement
		String idCategorie = request.getParameter("idcategorie");
		if (idCategorie == null) idCategorie = "0";
		
		// On recupère le filtre achat / vente selectionnée
		String achatVente = request.getParameter("achatvente");
		if (achatVente == null) achatVente = "vente";
		
		// On recupère le type de vente selectionnés
		String venteNonDebutee = request.getParameter("ventenondebutee");
		if (venteNonDebutee == null) venteNonDebutee = "false";
//		logger.debug("venteNonDebutee apres test null " + venteNonDebutee);
		
		String venteEnCours = request.getParameter("venteencours");
		if (venteEnCours == null) venteEnCours = "true"; // on affiche les vente en cours par défaut
//		logger.debug("venteEnCours apres test null : " + venteEnCours);
		
		String venteTerminee = request.getParameter("venteterminee");
		if (venteTerminee == null) venteTerminee = "false";
//		logger.debug("venteTerminee apres test null : " + venteTerminee);
		
		// On recupère le type d'achat selectionnée
		String enchereOuvertes = request.getParameter("enchereouvertes");
		if (enchereOuvertes == null) enchereOuvertes = "false";
//		logger.debug("enchereOuvertes apres test null : " + enchereOuvertes);
		
		String mesEncheres = request.getParameter("mesencheres");
		if (mesEncheres == null) mesEncheres = "false";
//		logger.debug("mesEncheres apres test null : " + mesEncheres);
		
		String mesEncheresRemportees = request.getParameter("mesencheresremportees");
		if (mesEncheresRemportees == null) mesEncheresRemportees = "false";
//		logger.debug("mesEncheresRemportees apres test null : " + mesEncheresRemportees);
		
		// On envoie la liste des encheres en cours 
		List<ArticleVendu> articlesTries = this.venteManager.SearchArticleVente(article, Integer.valueOf(idCategorie), Boolean.valueOf(venteNonDebutee), Boolean.valueOf(venteEnCours), Boolean.valueOf(venteTerminee));
		request.setAttribute("articles", articlesTries);
		
		// mise a jour des checkbox
			if (venteNonDebutee.equals("true")) request.setAttribute("checkventenondebutee", "checked");
			if (venteEnCours.equals("true")) request.setAttribute("checkventeencours", "checked");
			if (venteTerminee.equals("true")) request.setAttribute("checkventeterminee", "checked");

			if (enchereOuvertes.equals("true")) request.setAttribute("checkenchereouvertes", "checked");
			if (mesEncheres.equals("true")) request.setAttribute("checkmesencheres", "checked");
			if (mesEncheresRemportees.equals("true")) request.setAttribute("checkmesencheresremportees", "checked");
		
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

		// On recupère le mot clé recherché
		String article = request.getParameter("article");
		if (article == null) article = "";
		
		// On force la catégorie recherchée au premier chargement
		String idCategorie = request.getParameter("idcategorie");
		if (idCategorie == null) idCategorie = "0";
		
//		// On recupère le filtre achat / vente selectionnée
//		String achatVente = request.getParameter("achatvente");
//		logger.debug("achatvente " + achatVente);
//		if (achatVente == null) achatVente = "vente";

		// On recupère le type de vente selectionnés
		String venteNonDebutee = request.getParameter("ventenondebutee");
//		logger.debug("venteNonDebutee " + venteNonDebutee);
		if (venteNonDebutee == null) venteNonDebutee = "false";
//		logger.debug("post venteNonDebutee apres test null " + venteNonDebutee);
		
		String venteEnCours = request.getParameter("venteencours");
//		logger.debug("venteEnCours : " + venteEnCours);
		if (venteEnCours == null) venteEnCours = "false"; // on affiche les vente en cours par défaut
//		logger.debug("post venteEnCours apres test null : " + venteEnCours);
		
		String venteTerminee = request.getParameter("venteterminee");
//		logger.debug("venteTerminee : " + venteTerminee);
		if (venteTerminee == null) venteTerminee = "false";
//		logger.debug("post venteTerminee apres test null : " + venteTerminee);
		
		// On recupère le type d'achat selectionnée
		String enchereOuvertes = request.getParameter("enchereouvertes");
		if (enchereOuvertes == null) enchereOuvertes = "false";
//		logger.debug("enchereOuvertes " + enchereOuvertes);
		
		String mesEncheres = request.getParameter("mesencheres");
		if (mesEncheres == null) mesEncheres = "false";
//		logger.debug("mesEncheres : " + mesEncheres);
		
		String mesEncheresRemportees = request.getParameter("mesencheresremportees");
		if (mesEncheresRemportees == null) mesEncheresRemportees = "false";
//		logger.debug("mesEncheresRemportees : " + mesEncheresRemportees);
		
		// On envoie la liste des encheres en cours 
		List<ArticleVendu> articlesTries = this.venteManager.SearchArticleVente(article, Integer.valueOf(idCategorie), Boolean.valueOf(venteNonDebutee), Boolean.valueOf(venteEnCours), Boolean.valueOf(venteTerminee));
		request.setAttribute("articles", articlesTries);
				
		// On réactualise le mot clé recherché
		request.setAttribute("article", article);
		
		// on recupère les repas deouis la couche BLL
		List<Categorie> categories = this.venteManager.getCategorie();
		// on balance les categories au JSP
		request.setAttribute("categories", categories);
	
//		// On réactualise la catégorie recherchée
//		request.setAttribute("idcategorie", idCategorie);
		
		// mise a jour des checkbox
		
		if (venteNonDebutee.equals("true")) request.setAttribute("checkventenondebutee", "checked");
		if (venteEnCours.equals("true")) request.setAttribute("checkventeencours", "checked");
		if (venteTerminee.equals("true")) request.setAttribute("checkventeterminee", "checked");
		if (enchereOuvertes.equals("true")) request.setAttribute("checkenchereouvertes", "checked");
		if (mesEncheres.equals("true")) request.setAttribute("checkmesencheres", "checked");
		if (mesEncheresRemportees.equals("true")) request.setAttribute("checkmesencheresremportees", "checked");
		
		
		// je redirige sur le formulaire
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
//		this.doGet(request, response);
		
	}

}
