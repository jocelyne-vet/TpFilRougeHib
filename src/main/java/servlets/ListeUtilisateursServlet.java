package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.PersonneBLL;
import bo.personnes.Personne;

/**
 * Servlet implementation class ListeUtilisateursServlet
 */
@WebServlet("/listeUtilisateurs")
public class ListeUtilisateursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonneBLL bll;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new PersonneBLL();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeUtilisateursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		
		List<Personne> utilisateurs = bll.selectAll();
		
		Collections.sort(utilisateurs, new Comparator<Personne>() {
            @Override
            public int compare(Personne p1, Personne p2) {
                return p1.getNom().toUpperCase().compareTo(p2.getNom().toUpperCase());
            }
        });
		request.setAttribute("utilisateurs", utilisateurs);
		request.setAttribute("message", message);
		request.getRequestDispatcher("WEB-INF/listeUtilisateurs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
