package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.CinemaBLL;
import bo.cinemas.Cinema;

/**
 * Servlet implementation class SupprimerCinemaServlet
 */
@WebServlet("/supprimerCinema")
public class SupprimerCinemaServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CinemaBLL bll;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//		bll = new CinemaBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCinemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pIdCinema = request.getParameter("idCinema");
		String pNomCinema = request.getParameter("nomCinema");
		
		Cinema cinema = bll.selectById(Integer.valueOf(pIdCinema));
		bll.delete(cinema);
		String message = "Vous venez de supprimer le cinéma"+pNomCinema;
		response.sendRedirect("listeCinemas?message="+message);
	}

}
