package fr.eni.projetencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;

/**
 * Servlet implementation class SupprimerCompteServlet
 */
@WebServlet("/supprimerCompte")
public class SupprimerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UtilisateurManager utilisateurManager = new UtilisateurManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		
		try {
			System.out.println("Servlet - try dopost");
			utilisateurManager.supprimerUtilisateur(pseudo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Je récupère la session
		HttpSession session = request.getSession();
		// J'utilise la fonction invalidate() pour fermer la session
		session.invalidate();
		// Je redirige sur la page d'accueil
		response.sendRedirect("./");
	}

}
