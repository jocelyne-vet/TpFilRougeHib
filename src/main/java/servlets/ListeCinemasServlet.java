package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.CinemaBLL;
import bo.cinemas.Cinema;

/**
 * Servlet implementation class ListeCinemasServlet
 */
@WebServlet("/listeCinemas")
public class ListeCinemasServlet extends AncetreServlet {
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
    public ListeCinemasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		List<Cinema> cinemas = bll.selectAll();
		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("cinemas", cinemas);
		request.getRequestDispatcher("WEB-INF/jsp/listeCinemas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
