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
		
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("tel"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setMotDePasse(request.getParameter("motDePasse"));
		
		try {
			// On vérifie d'abord si les mots de passe correspondent
			if (utilisateurManager.verifierMotsDePasse(request.getParameter("motDePasse"), request.getParameter("motDePasseConfirm"))) {
				
				try {
					// Ensuite on ajoute l'utilisateur
					utilisateurManager.ajouterUtilisateur(utilisateur);
					session.setAttribute("utilisateurConnecte", utilisateur);
					session.setAttribute("succes", "Vous êtes bien enregistré !");
					session.setAttribute("utilisateur", utilisateur);
					session.setAttribute("confirmationMessage", "Vous êtes connecté en tant que : " + utilisateur.getPseudo());
					response.sendRedirect("./");
				} catch (BusinessException e) {
					// On récupère l'exception lancée par la fonction ajouterUtilisateur de utilisateurManager
					session.setAttribute("messageErreur", e.getMessage());
					session.setAttribute("utilisateur", utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
					rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				session.setAttribute("messageErreur", "les mots de passe ne correspondent pas");
			}
		} catch (BusinessException e) {
			// On récupère l'exception lancée par la fonction verifierMotsDepasse de utilisateurManager
			session.setAttribute("messageErreur", e.getMessage());
			session.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
