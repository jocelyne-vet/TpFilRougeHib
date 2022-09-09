package servlets;

import java.io.IOException;
import java.time.LocalDate;

import java.time.LocalTime;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.SeanceBLL;
import bo.cinemas.Film;
import bo.cinemas.Seance;
import bo.util.Outils;

/**
 * Servlet implementation class FilmServlet
 */
@WebServlet("/film")
public class FilmServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SeanceBLL bll;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//	    bll = new SeanceBLL();	
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pIdFilm = request.getParameter("idfilm");
		String pIdCinema = request.getParameter("idcinema");
		List<Seance> seances = bll.selectAllSeancesByFilm(Integer.valueOf(pIdFilm),Integer.valueOf(pIdCinema));
		Film monFilm = null;
		if(!seances.isEmpty()) {
			monFilm = seances.get(0).getFilm();
		}
		
		request.setAttribute("film", monFilm);
		
		Outils outils = new Outils();
		outils.setSeances(seances);
		seances = outils.toutesLesSeancesValides(LocalDate.now(), LocalTime.now());
		//tri des seance selon heuredebut
		Collections.sort(seances);
		request.setAttribute("seances", seances);
		
		
		request.getRequestDispatcher("WEB-INF/jsp/film.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
}
