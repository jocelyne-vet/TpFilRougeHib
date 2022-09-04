package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.PersonneBLL;
import bll.ReservationBLL;
import bll.ReservationException;
import bll.SeanceBLL;
import bll.SeanceException;
import bo.cinemas.Seance;
import bo.personnes.Client;
import bo.util.Reservation;

/**
 * Servlet implementation class ReserverServlet
 */
@WebServlet("/reserver")
public class ReserverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeanceBLL bll;
	private ReservationBLL bllReservation;
	private PersonneBLL bllPersonne;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new SeanceBLL();
		bllReservation = new ReservationBLL();
		bllPersonne = new PersonneBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pMessageErreur = request.getParameter("messageErreur");
		String pIdSeance = request.getParameter("idseance");
		if (pIdSeance == null) {
			pIdSeance = (String) request.getSession().getAttribute("pathParam");
		}
		Seance maSeance = bll.selectById(Integer.valueOf(pIdSeance));

		Client user = (Client) request.getSession().getAttribute("user");
//		bllPersonne.selectMesReservations(user);

		List<Reservation> resultats = bllReservation.findAllReservation(user.getId());

		Map<Seance, Reservation> reservClient = new HashMap<>();
		for (Reservation res : resultats) {
			reservClient.put(res.getSeance(), res);
		}

		user.setReservations(reservClient);

		if (pMessageErreur != null) {
			request.setAttribute("messageErreur", pMessageErreur);
		}

		Integer nb_places = 0;

		Map<Seance, Reservation> mesReservations = user.getReservations();
		for (Seance seance : mesReservations.keySet()) {
			if (seance.equals(maSeance)) {
				nb_places = mesReservations.get(seance).getNb_places();
			}
		}

//		HttpSession session = request.getSession();
		request.getSession().setAttribute("user", user);
		request.setAttribute("nbdeplacesres", nb_places);
		request.setAttribute("seance", maSeance);
		System.out.println(user.getDateNaissance());
		request.getRequestDispatcher("WEB-INF/reservation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNbPlacesRes = request.getParameter("nbdeplacesres");
		String pIdSeance = request.getParameter("idseance");
		Client user = (Client) request.getSession().getAttribute("user");
		String messageError = null;
		Seance maSeance = null;
		int nbPlaces = Integer.valueOf(pNbPlacesRes);
		boolean bOk = true;
		if (pNbPlacesRes != null) {

			maSeance = bll.selectById(Integer.valueOf(pIdSeance));
			
			List<Reservation> reservClientsInscrits= bllReservation.findAllClientsInscritsBySeance(Integer.valueOf(pIdSeance));
			
			
			Map<Client, Integer> clientsInscrits = new HashMap<>();
			
			for(Reservation res: reservClientsInscrits) {
				clientsInscrits.put(res.getClient(), res.getNb_places());
			}
			
			
			//clientInstcrits.put(user, maSeance.getNbInscrits());
			maSeance.setClientsInscrits(clientsInscrits);
			try {
				maSeance.reserver(user, nbPlaces);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				messageError = e.getMessage();
				bOk = false;
			}
		}

		if (!bOk) {
//			HttpSession session = request.getSession();
//			request.getSession().setAttribute("user", session.getAttribute("user"));
//			response.sendRedirect("reserver?idseance=" + pIdSeance + "&messageErreur=" + messageError);
			request.setAttribute("messageErreur", messageError);
			request.setAttribute("idSeance", pIdSeance);
			bOk = false;
			doGet(request, response);
		} else {
			Map<Seance, Reservation> reserv = user.getReservations();

			// enregistremnet du nb d'inscrits dans la séance
			try {
				bll.update(maSeance);

				// enregistrement de la réservation dans la table reservation

				for (Seance seance : reserv.keySet()) {
					if (seance.equals(maSeance)) {
						// bllPersonne.majReservation(seance.getId(), user.getId(), reserv.get(seance));
						Reservation res = reserv.get(maSeance);
						// il manque id reservation
						if (res.getId() > 0) {
							bllReservation.update(res);
						} else {
							bllReservation.insert(res);
						}
					}
				}
			} catch (SeanceException e) {
				request.setAttribute("erreurs", e.getMessages());
				request.setAttribute("idSeance", pIdSeance);
				bOk = false;
				doGet(request, response);

			} catch (ReservationException e) {
				request.setAttribute("erreurs", e.getMessages());
				request.setAttribute("idSeance", pIdSeance);
				bOk = false;
				doGet(request, response);

			}  catch (Exception e) {
				List<String> erreurs = new ArrayList<>();
				erreurs.add("Une erreur est intervenue lors de la mise à jour.");
				request.setAttribute("idSeance", pIdSeance);
				bOk = false;
				doGet(request, response);
			}
			if (bOk) {
				String message = "Vous venez de réserver " + nbPlaces + " pour le film " + maSeance.getFilm().getNom()
						+ " à la séance de " + maSeance.getHeureDebut().toLocalDate() + " à "
						+ maSeance.formatHeureMinute();
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/accueil.jsp").forward(request, response);
			}
		}
	}

}
