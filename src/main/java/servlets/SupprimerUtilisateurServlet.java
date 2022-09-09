package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.PersonneBLL;
import bo.personnes.Personne;

/**
 * Servlet implementation class SupprimerUtilisateurServlet
 */
@WebServlet("/supprimerUtilisateur")
public class SupprimerUtilisateurServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PersonneBLL bll;
//	@Autowired
//	private AdresseBLL bllAdresse;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//		bll = new PersonneBLL();
//		bllAdresse = new AdresseBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerUtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String pPage = request.getParameter("page");
		//
		String pNom, pPrenom, message, pId;
		if (pPage!=null && pPage.equals("compte")) {
			Personne user = (Personne) request.getSession().getAttribute("user");
			pNom = user.getNom();
			pPrenom = user.getPrenom();
			bll.deletePersonne(user.getId());
			request.getSession().invalidate();
		} else {
			pNom = request.getParameter("nomUtilisateur");
			pPrenom = request.getParameter("prenomUtilisateur");
			message = "";
			pId = request.getParameter("id");
			bll.deletePersonne(Integer.valueOf(pId));
		}
		// bllAdresse.deleteByIdPersonne(Integer.valueOf(pId));

		//

		//
		message = "Vous venez de supprimer l'utilisateur " + pNom + " " + pPrenom;

		if (pPage!=null && pPage.equals("compte")) {
			response.sendRedirect("accueil?message=" + message);
		} else {
			response.sendRedirect("listeUtilisateurs?message=" + message);
		}
	}

}
