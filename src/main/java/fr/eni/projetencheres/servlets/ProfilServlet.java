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
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/profil")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateurConnecte"));
		System.out.println(session.getAttribute("utilisateurConnecte"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurEnCours = (Utilisateur) session.getAttribute("utilisateurConnecte");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		
		utilisateurEnCours.setPseudo(request.getParameter("pseudo"));
		utilisateurEnCours.setNom(request.getParameter("nom"));
		utilisateurEnCours.setPrenom(request.getParameter("prenom"));
		utilisateurEnCours.setEmail(request.getParameter("email"));
		utilisateurEnCours.setTelephone(request.getParameter("tel"));
		utilisateurEnCours.setRue(request.getParameter("rue"));
		utilisateurEnCours.setCodePostal(request.getParameter("codePostal"));
		utilisateurEnCours.setVille(request.getParameter("ville"));
		utilisateurEnCours.setMotDePasse(request.getParameter("motDePasse"));
		
		String motDePasseConfirm = request.getParameter("motDePasseConfirm");
		
		try {
			utilisateurManager.verifierMotsDePasse(utilisateurEnCours.getMotDePasse(), motDePasseConfirm);
			utilisateurManager.modifierUtilisateur(utilisateurEnCours);
			session.setAttribute("utilisateurConnecte", utilisateurEnCours);
			request.setAttribute("messageConfirmation", "Votre compte a bien été modifié !");
			request.setAttribute("utilisateur", utilisateurEnCours);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("messageErreur", e.getMessage());
			request.setAttribute("utilisateur", utilisateurEnCours);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
