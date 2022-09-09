package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.CinemaBLL;
import bll.PersonneBLL;
import bo.cinemas.Cinema;
import bo.personnes.Personne;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/accueil")
public class AccueilServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CinemaBLL bll;
	@Autowired
	private PersonneBLL bllPersonne;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//		bll = new CinemaBLL();
//		bllPersonne = new PersonneBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			Cookie[] mesCookies = request.getCookies();
			if (mesCookies != null) {
				for (Cookie monCookie : mesCookies) {
					if (monCookie.getName().equals("idPersonne")) {
						int id = Integer.valueOf(monCookie.getValue());
						Personne maPersonne = bllPersonne.selectById(id);
						request.getSession().setAttribute("user", maPersonne);
					}
				}
			}
		}

		// mettre les droits
		ServletContext application = this.getServletContext();
		application.setAttribute("roleA", "Administrateur");
		application.setAttribute("roleG", "Gerant");
		application.setAttribute("roleC", "Client");
		
		String message = request.getParameter("message");
		request.setAttribute("message", message);
		request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String critere = request.getParameter("critere");
		List<Cinema> cinemas = bll.selectCinemasCritere(critere);
		request.setAttribute("cinemas", cinemas);
		request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

}
