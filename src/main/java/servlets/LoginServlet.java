package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.PersonneBLL;
import bo.personnes.Client;
import bo.personnes.Gerant;
import bo.personnes.Personne;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonneBLL bll;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		bll = new PersonneBLL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pEmail = request.getParameter("email");
		String pMdp = request.getParameter("mdp");
		String pSeSouvenir = request.getParameter("seSouvenir");

		boolean accesOK = false;

		if (pEmail != null && !pEmail.isBlank() && pMdp != null && !pMdp.isBlank()) {
			Personne maPersonne = bll.existPersonne(pEmail, pMdp);
			if (maPersonne != null) {
				//
				accesOK = true;
				request.getSession().setAttribute("user", maPersonne);
				if (pSeSouvenir != null && pSeSouvenir.equals("on")) {
					Cookie monCookie = new Cookie("idPersonne", String.valueOf(maPersonne.getId()));
					response.addCookie(monCookie);
				}
			}
		}

		if (!accesOK) {
			String messageErreur = "L'email " + pEmail + " ou le mot de passe est incorrecte.";
			request.setAttribute("messageErreur", messageErreur);
			doGet(request, response);
		} else {

			String pPath = (String) request.getSession().getAttribute("path");
			if (pPath != null) {
				Personne maPersonne = (Personne) request.getSession().getAttribute("user");
				if (maPersonne instanceof Client) {
					response.sendRedirect(pPath.substring(1));
				}else {
					String message = "Seuls les clients ont le droit de réserver des places";
					response.sendRedirect("accueil?message="+message);
				}
			} else {
				response.sendRedirect("accueil");
			}
		}

	}

}
