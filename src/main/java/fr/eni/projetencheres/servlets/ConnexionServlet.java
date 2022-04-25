package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurManager utilisateurManager = new UtilisateurManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Affichage de la page connexion
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();

		String login = request.getParameter("login");
		String password = request.getParameter("motDePasse");

		try {
			// On demande au manager de trouver l'utilisateur
			utilisateur = utilisateurManager.trouverUtilisateur(login, password);
			Cookie leCookie = new Cookie("connecte", "oui");
			response.addCookie(leCookie);
			// On récupère la session
			HttpSession session = request.getSession();
			// On y ajoute les attributs
			session.setAttribute("connexion", true);
			session.setAttribute("utilisateurConnecte", utilisateur);
			session.setAttribute("confirmationMessage", "Vous êtes connecté en tant que : " + utilisateur.getPseudo());
			session.setAttribute("succes", "Vous êtes bien connecté !");
			// On redirige sur la page d'accueil
			response.sendRedirect("./");
		} catch (BusinessException | SQLException e) {
			request.setAttribute("erreurMessage", e.getMessage());
			this.doGet(request, response);
		}
	}

}
