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

import bll.SeanceBLL;
import bo.cinemas.Film;
import bo.cinemas.Seance;
import bo.util.Outils;

/**
 * Servlet implementation class ConsultFilmSalleServlet
 */
@WebServlet("/consultFilmSalle")
public class ConsultFilmSalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeanceBLL bll; 
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new SeanceBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultFilmSalleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pIdSalle = request.getParameter("idSalle");
		String pIdFilm  = request.getParameter("idFilm");
		String message = request.getParameter("message");
		
		
		List<Seance> seances = bll.selectAllSeanceFilmBySalleId(Integer.valueOf(pIdFilm), Integer.valueOf(pIdSalle));
		
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
		request.setAttribute("idSalle", pIdSalle);
		if(message!=null) {
			request.setAttribute("message", message);
		}
		
		request.getRequestDispatcher("WEB-INF/filmSalle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
