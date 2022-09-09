package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.FilmBLL;
import bll.SalleBLL;
import bll.SeanceBLL;

/**
 * Servlet implementation class SupprimerSalleServlet
 */
@WebServlet("/supprimerSalle")
public class SupprimerSalleServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SalleBLL bll;
	@Autowired
	private SeanceBLL bllSeance;
	@Autowired
	private FilmBLL bllFilm;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//		bll = new SalleBLL();
//		bllSeance = new SeanceBLL();
//		bllFilm = new FilmBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerSalleServlet() {
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
		String pId =  request.getParameter("idSalle");
		String pNumero = request.getParameter("numero");
		
		
		
		bllFilm.deleteFilmBySalle(Integer.valueOf(pId));
		bllSeance.deleteSeancesBySalle(Integer.valueOf(pId));
		bll.deleteById(Integer.valueOf(pId));
		
		String message = "La salle numero "+pNumero+ " a été supprimée";
		
//		request.setAttribute("message", message);
		
		// 5. Redirection de l'utilisateur
		response.sendRedirect("etablissement?message="+message);
		
		
	}

}
