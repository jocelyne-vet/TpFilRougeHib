package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.FilmBLL;
import bll.SeanceBLL;

/**
 * Servlet implementation class SupprimerFilmServlet
 */
@WebServlet("/supprimerFilm")
public class SupprimerFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FilmBLL bll;
    private SeanceBLL bllSeance;
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	bll = new FilmBLL();
    	bllSeance = new SeanceBLL();
    	
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerFilmServlet() {
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
		String pIdFilm = request.getParameter("idFilm");
		String pIdSalle = request.getParameter("idSalle");
		String pNomFilm = request.getParameter("nomFilm");
		
		bllSeance.deleteSeancesByFilm(Integer.valueOf(pIdFilm));
		bll.delete(Integer.valueOf(pIdFilm));
		
		request.setAttribute("idSalle", pIdSalle);
		String message = "Vous venez de supprimer le film "+ pNomFilm;
		response.sendRedirect("consulterFilmsSalle?idSalle="+pIdSalle+"&message="+message);
	}

}
