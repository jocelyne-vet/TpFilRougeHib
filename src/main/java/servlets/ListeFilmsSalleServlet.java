package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.SalleBLL;
import bo.cinemas.Film;
import bo.cinemas.Salle;

/**
 * Servlet implementation class ListeFilmsSalleServlet
 */
@WebServlet("/consulterFilmsSalle")
public class ListeFilmsSalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalleBLL bll;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new SalleBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeFilmsSalleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pIdSalle = request.getParameter("idSalle");
		String message = request.getParameter("message");
//		Salle maSalle = bll.selectSalleSeanceFilmById(Integer.valueOf(pIdSalle));
		Salle maSalle = bll.selectById(Integer.valueOf(pIdSalle));
		List<Film> mesFilms = maSalle.tousLesFilmsValides(LocalDate.now(),LocalTime.now());
		
		
		request.setAttribute("salle", maSalle);
		request.setAttribute("films", mesFilms);
		request.setAttribute("message", message);
		request.getRequestDispatcher("WEB-INF/listeFilmsSalle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
