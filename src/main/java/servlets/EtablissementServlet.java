package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.CinemaBLL;
import bo.cinemas.Cinema;
import bo.personnes.Gerant;


/**
 * Servlet implementation class EtablissementServlet
 */
@WebServlet("/etablissement")
public class EtablissementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CinemaBLL bll;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new CinemaBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EtablissementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String message = request.getParameter("message");
		Cinema monCinema = null;
		if (session.getAttribute("user") instanceof Gerant) {

			Gerant monGerant = (Gerant) session.getAttribute("user");
			monCinema = bll.selectByIdGerant(monGerant.getId());
		} else {
			String pIdGerant = request.getParameter("idGerant");
			
			if(pIdGerant!=null && !pIdGerant.isBlank()) {
				
				Cookie unCookie = new Cookie("gerant",pIdGerant);
				response.addCookie(unCookie);
				
			}else {
				Cookie[] cookies = request.getCookies();
				for(Cookie monCookie:cookies) {
					if(monCookie.getName().equals("gerant")) {
						pIdGerant = monCookie.getValue();
					}
				}
			}
			
			monCinema = bll.selectByIdGerant(Integer.valueOf(pIdGerant));
		}

		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("cinema", monCinema);
		request.getRequestDispatcher("WEB-INF/etablissement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
