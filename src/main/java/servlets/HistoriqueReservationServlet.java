package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bll.ReservationBLL;

import bo.cinemas.Seance;
import bo.personnes.Client;
import bo.util.Reservation;

/**
 * Servlet implementation class HistoriqueReservationServlet
 */
@WebServlet("/historiqueReservation")
public class HistoriqueReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL bll;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		bll = new ReservationBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriqueReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client user = (Client)request.getSession().getAttribute("user"); 
		List<Reservation> resultats = bll.findAllReservation(user.getId());
		
		Map<Seance, Integer> unsortedreservations = new HashMap<>();
	    for (Reservation res : resultats) {
	    	Seance seance = res.getSeance();
	    	int nbPlaces = res.getNb_places();
	    	if(!unsortedreservations.containsKey(seance)) {
	    		unsortedreservations.put(seance, nbPlaces);
	    	}
	    }
//		Map<Seance, Integer> unsortedreservations = bll.selectHistoriqueReservation(user.getId());
//		
		Map<Seance, Integer> reservations = new TreeMap<>(unsortedreservations);
		
		
		request.setAttribute("reservations", reservations);
		request.getRequestDispatcher("WEB-INF/historiqueReservation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
