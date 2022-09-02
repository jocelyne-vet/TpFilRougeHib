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



import bll.CinemaBLL;

import bo.cinemas.Cinema;
import bo.cinemas.Film;

/**
 * Servlet implementation class CinemaServlet
 */
@WebServlet("/cinema")
public class CinemaServlet extends HttpServlet {
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
    public CinemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pIdCinema = request.getParameter("idcinema");
		
		Cinema monCinema = bll.selectById(Integer.valueOf(pIdCinema));
		List<Film> mesFilms = monCinema.tousLesFilmsValides(LocalDate.now(),LocalTime.now());
		
		request.setAttribute("cinema", monCinema);
		request.setAttribute("films", mesFilms);
		request.getRequestDispatcher("WEB-INF/cinema.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pCritere = request.getParameter("critere");
		
		String pId = request.getParameter("idCinema");
		int id = Integer.valueOf(pId);
		Cinema monCinema = bll.selectByIdCritere(id, pCritere);
		List<Film> mesFilms = monCinema.tousLesFilmsValides(LocalDate.now(),LocalTime.now());
		
		request.setAttribute("cinema", monCinema);
		request.setAttribute("films", mesFilms);
		
		request.getRequestDispatcher("WEB-INF/cinema.jsp").forward(request, response);
		
	}

}
