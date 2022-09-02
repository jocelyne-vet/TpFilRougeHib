package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.SalleBLL;
import bll.SalleException;
import bo.cinemas.Cinema;
import bo.cinemas.Salle;

/**
 * Servlet implementation class FormulaireSalleServlet
 */
@WebServlet("/formulaireSalle")
public class FormulaireSalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SalleBLL bll;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new SalleBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormulaireSalleServlet() {
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
		String pIdSalle = request.getParameter("idSalle");
		String pAfficheCinema = request.getParameter("afficheCinema");
		Salle maSalle = new Salle();
		if (pIdSalle != null && !pIdSalle.isBlank()) {
			maSalle = bll.selectById(Integer.valueOf(pIdSalle));
		}
		request.setAttribute("idCinema", pIdCinema);
		request.setAttribute("salle", maSalle);
		request.setAttribute("afficheCinema", pAfficheCinema);

		request.getRequestDispatcher("WEB-INF/formSalle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdCinema = request.getParameter("idCinema");
		String pAfficheCinema = request.getParameter("afficheCinema");
		String pIdSalle = request.getParameter("idSalle");
		String pNumero = request.getParameter("numero");
		String pNbPlaces = request.getParameter("nbplaces");
		
		boolean bOk = true;
		boolean bInsert = false;

		// numero et nbplaces
		int numero = Integer.valueOf(pNumero);
		int nbPlaces = Integer.valueOf(pNbPlaces);

//		if (numero <= 0) {
//			messageErreur = "Vous devez rentrer un numéro > 0 ";
//			bOk = false;
//		}

//		if (nbPlaces <= 0) {
//			bOk = false;
//			if (messageErreur == null) {
//				messageErreur = " Vous devez rentrer un nombre de places > 0";
//			} else {
//				messageErreur += " Vous devez rentrer un nombre de places > 0";
//			}
//		}


		
		Salle maSalle = new Salle();

		if (bOk) {
			
			maSalle.setNumero(numero);
			maSalle.setNombreDePlaces(nbPlaces);
			Cinema c = new Cinema();
			c.setId(Integer.valueOf(pIdCinema));
			maSalle.setCinema(c);

			if (pIdSalle != null && !pIdSalle.isBlank() && !pIdSalle.equals("0")) {

				maSalle.setId(Integer.valueOf(pIdSalle));
				try {
					bll.existNumeroSalle(numero, Integer.valueOf(pIdCinema));
					bll.update(maSalle);
				} catch (SalleException e) {
					bOk =false;
					
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idCinema", pIdCinema);
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("afficheCinema", pAfficheCinema);
					doGet(request, response);
				}catch (Exception e) { // Les exceptions issues de mauvais Integer.parseInt...
					bOk = false;
					List<String> erreurs = new ArrayList<>();
					erreurs.add("Une erreur est intervenue lors de la mise à jour.");
					request.setAttribute("erreurs", erreurs);
					request.setAttribute("idCinema", pIdCinema);
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("afficheCinema", pAfficheCinema);
					doGet(request, response);
				}
			} else {
				try {
					bll.existNumeroSalle(numero, Integer.valueOf(pIdCinema));
					bll.insert(maSalle);
					bInsert = true;
				} catch (SalleException e) {
					bOk =false;
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idCinema", pIdCinema);
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("afficheCinema", pAfficheCinema);
					doGet(request, response);
				}catch (Exception e) { // Les exceptions issues de mauvais Integer.parseInt...
					bOk = false;
					List<String> erreurs = new ArrayList<>();
					erreurs.add("Une erreur est intervenue lors de la mise à jour.");
					request.setAttribute("erreurs", erreurs);
					request.setAttribute("idCinema", pIdCinema);
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("afficheCinema", pAfficheCinema);
					doGet(request, response);
				}
				
			}
		}

//		HttpSession session = request.getSession();
//		session.setAttribute("user", session.getAttribute("user"));
//		if (!bOk) {

//			request.setAttribute("idCinema", pIdCinema);
//			request.setAttribute("idSalle", pIdSalle);
//			request.setAttribute("afficheCinema", pAfficheCinema);
//
//			request.setAttribute("messageErreur", messageErreur);
//			request.getRequestDispatcher("WEB-INF/formSalle.jsp").forward(request, response);
			
		if(bOk) {
			String message = "";
			if(bInsert) {
				message = "Vous venez de créer une nouvelle salle, la salle numéro "+maSalle.getNumero()+ " de "+maSalle.getNombreDePlaces() + " places.";
			}else {
				message = "Vous venez de modifier la salle "+ maSalle.getNumero();
			}
			
//			request.setAttribute("message", message);
			response.sendRedirect("etablissement?message="+message);
		}
	}

}
