package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManager utilisateurManager = new UtilisateurManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Affichage de la page d'accueil
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		Utilisateur utilisateurEnCours = new Utilisateur();
		
		utilisateurEnCours.setPseudo(request.getParameter("pseudo"));
		utilisateurEnCours.setNom(request.getParameter("nom"));
		utilisateurEnCours.setPrenom(request.getParameter("prenom"));
		utilisateurEnCours.setEmail(request.getParameter("email"));
		utilisateurEnCours.setTelephone(request.getParameter("tel"));
		utilisateurEnCours.setRue(request.getParameter("rue"));
		utilisateurEnCours.setCodePostal(request.getParameter("codePostal"));
		utilisateurEnCours.setVille(request.getParameter("ville"));
		utilisateurEnCours.setMotDePasse(request.getParameter("motDePasse"));
		
				
		try {
			// On vérifie les mots de passe
			utilisateurManager.verifierMotsDePasse(request.getParameter("motDePasse"), request.getParameter("motDePasseConfirm"));
			// On ajoute l'utilisateur à la base de données
			utilisateurManager.ajouterUtilisateur(utilisateurEnCours);
			// On connecte l'utilisateur
			Utilisateur utilisateur = utilisateurManager.trouverUtilisateur(utilisateurEnCours.getPseudo(), utilisateurEnCours.getMotDePasse());
			
			// on retourne à la page d'accueil en gardant le message
			request.setAttribute("succes", "Vous êtes bien enregistré !");
			
		} catch (BusinessException e) {
			// On récupère l'exception lancée par la fonction ajouterUtilisateur de utilisateurManager
			session.setAttribute("messageErreur", e.getMessage());
			session.setAttribute("utilisateur", utilisateurEnCours);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

}
