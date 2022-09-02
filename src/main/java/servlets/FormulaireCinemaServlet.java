package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.CinemaBLL;
import bll.CinemaException;
import bll.PersonneBLL;
import bo.cinemas.Cinema;
import bo.personnes.Gerant;
import bo.personnes.Personne;
import bo.util.Adresse;

/**
 * Servlet implementation class FormulaireCinemaServlet
 */
@WebServlet("/formulaireCinema")
public class FormulaireCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonneBLL bll;
	private CinemaBLL bllCinema;
//	private AdresseBLL bllAdresse;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new PersonneBLL();
		bllCinema = new CinemaBLL();
//		bllAdresse = new AdresseBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormulaireCinemaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCinema = request.getParameter("idCinema");
		Cinema monCinema = null;
		List<Personne> gerants = null;
		if (pIdCinema != null && !pIdCinema.isBlank()) {
			monCinema = bllCinema.selectCinemaById(Integer.valueOf(pIdCinema));
			gerants = bll.selectGerants(monCinema.getGerant().getId());
		} else {
			gerants = bll.selectGerants(0);
		}
		if (!gerants.isEmpty()) {
			request.setAttribute("cinema", monCinema);
			request.setAttribute("gerants", gerants);
			request.getRequestDispatcher("WEB-INF/formCinema.jsp").forward(request, response);
		} else {
			String message = "Tous les gérants ont déjà un cinéma ; vous devez créer de nouveaux gérants ";
			List<Cinema> cinemas = bllCinema.selectAll();
			request.setAttribute("message", message);
			request.setAttribute("cinemas", cinemas);
			request.getRequestDispatcher("WEB-INF/listeCinemas.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCinema = request.getParameter("idCinema");
		String pNom = request.getParameter("nom");
		String pAffiche = request.getParameter("affiche");
		String pIdGerant = request.getParameter("gerant");
		String pIdAdresse = request.getParameter("idAdresse");
		String pNumero = request.getParameter("numero_voie");
		String pTypeVoie = request.getParameter("type_voie");
		String pNomVoie = request.getParameter("nom_voie");
		String pCodePostal = request.getParameter("code_postal");
		String pVille = request.getParameter("ville");

		boolean bCreer = false;

		Cinema monCinema = new Cinema();
		monCinema.setNom(pNom);
		monCinema.setAffiche(pAffiche);
		Gerant monGerant = new Gerant();
		monGerant.setId(Integer.valueOf(pIdGerant));
		monCinema.setGerant(monGerant);
		Adresse monAdresse = new Adresse();
		if (pIdAdresse != null && !pIdAdresse.isBlank()) {
			monAdresse.setId(Integer.valueOf(pIdAdresse));
		}
		monAdresse.setNumero(Integer.valueOf(pNumero));
		monAdresse.setTypeRue(pTypeVoie);
		monAdresse.setNomRue(pNomVoie);
		monAdresse.setCpo(pCodePostal);
		monAdresse.setVille(pVille);

		boolean bMaj = true;

		if (pIdCinema != null && !pIdCinema.isBlank()) {
			monCinema.setAdresse(monAdresse);
			monCinema.setId(Integer.valueOf(pIdCinema));
			// bllAdresse.update(monAdresse);
			try {
				bllCinema.update(monCinema);
			} catch (CinemaException e) { // L'exception renvoyee par le BLL
				bMaj = false;
				request.setAttribute("erreurs", e.getMessages());
				doGet(request, response);
			} catch (Exception e) { // Les exceptions issues de mauvais Integer.parseInt...
				bMaj = false;
				List<String> erreurs = new ArrayList<>();
				erreurs.add("Une erreur est intervenue lors de l'insertion.");
				request.setAttribute("erreurs", erreurs);
				doGet(request, response);
			}

		} else {
			// bllAdresse.insert(monAdresse);
			monCinema.setAdresse(monAdresse);
			try {
				bllCinema.insert(monCinema);
				bCreer = true;
			} catch (CinemaException e) {
				bMaj = false;
				request.setAttribute("erreurs", e.getMessages());
				request.setAttribute("idCinema", pIdAdresse);
				doGet(request, response);
			} catch (Exception e) { // Les exceptions issues de mauvais Integer.parseInt...
				bMaj = false;
				List<String> erreurs = new ArrayList<>();
				erreurs.add("Une erreur est intervenue lors de l'insertion.");
				request.setAttribute("erreurs", erreurs);
				request.setAttribute("idCinema", pIdAdresse);
				doGet(request, response);
			}

		}

		if (bMaj) {

			String message = "";
			if (bCreer) {
				message = "Vous venez de créer le cinéma " + monCinema.getNom();
			} else {
				message = "Vous venez de modifier le cinéma " + monCinema.getNom();
			}
			response.sendRedirect("listeCinemas?message=" + message);
		}

	}

}
