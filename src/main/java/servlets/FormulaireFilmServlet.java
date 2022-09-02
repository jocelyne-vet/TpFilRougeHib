package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.FilmBLL;
import bll.FilmException;
import bll.SeanceBLL;
import bll.SeanceException;
import bo.cinemas.Film;
import bo.cinemas.Salle;
import bo.cinemas.Seance;
import bo.util.Outils;

/**
 * Servlet implementation class FormulaireFilmServlet
 */
@WebServlet("/formulaireFilm")
public class FormulaireFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeanceBLL bll;
	private FilmBLL bllFilm;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		bll = new SeanceBLL();
		bllFilm = new FilmBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormulaireFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pIdSalle = request.getParameter("idSalle");
		String pIdFilm = request.getParameter("idFilm");
		String pMessage = request.getParameter("messageErreur");

		if (pIdFilm != null && !pIdFilm.isBlank()) {

			List<Seance> seances = bll.selectAllSeanceFilmBySalleId(Integer.valueOf(pIdFilm),
					Integer.valueOf(pIdSalle));

			Film monFilm = null;
			if (!seances.isEmpty()) {
				monFilm = seances.get(0).getFilm();
			}

			request.setAttribute("film", monFilm);

			Outils outils = new Outils();
			outils.setSeances(seances);
			seances = outils.toutesLesSeancesValides(LocalDate.now(), LocalTime.now());
			// tri des seance selon heuredebut
			Collections.sort(seances);
			request.setAttribute("seances", seances);
//			request.setAttribute("idSalle", pIdSalle);
			request.setAttribute("messageErreur", pMessage);
		}
		request.setAttribute("idSalle", pIdSalle);
		request.getRequestDispatcher("WEB-INF/formFilm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// film
		String pTitre = request.getParameter("titre");
		String pDuree = request.getParameter("duree");
		String pAffiche = request.getParameter("affiche");
		String pDescription = request.getParameter("synopsis");
		String pIdFilm = request.getParameter("idFilm");
		String pIdSalle = request.getParameter("idSalle");
		String pAgeMinimum = request.getParameter("ageMinimum");
		boolean bCreer = false;

		Film monFilm = new Film();
		if (pIdFilm != null && !pIdFilm.isBlank()) {
			monFilm.setId(Integer.valueOf(pIdFilm));
		}
		monFilm.setDuree(Integer.valueOf(pDuree));
		monFilm.setDescription(pDescription);
		monFilm.setNom(pTitre);
		if (pAffiche != null && !pAffiche.isBlank()) {
			monFilm.setAffiche(pAffiche);
		}
		if (!pAgeMinimum.isBlank() && !pAgeMinimum.equals("0")) {
			monFilm.setAgeMinimum(Integer.valueOf(pAgeMinimum));
		}

		String[] tDateSeance = request.getParameterValues("dateSeance");
		String[] tHeureSeance = request.getParameterValues("heureSeance");
		String[] tIdSeance = request.getParameterValues("idSeance");

		List<Seance> seances = new ArrayList<>();

		boolean bOk = true;

		String messageErreur = "";

		if (tDateSeance.length == 0) {
			bOk = false;
			messageErreur = "Vous devez créer des séances";
		}

		// if (!bMessageErreur) {

		for (int i = 0; i < tDateSeance.length; i++) {
			Seance maSeance = new Seance();
			String idSeance = tIdSeance[i];
			if (idSeance != null && !idSeance.isBlank()) {
				maSeance.setId(Integer.valueOf(idSeance));
			}

			String date = tDateSeance[i];
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateDebut = LocalDate.parse(date, dtf);

			String heure = tHeureSeance[i];
			DateTimeFormatter dtft = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime heureDebut = LocalTime.from(dtft.parse(heure));

			maSeance.setHeureDebut(LocalDateTime.of(dateDebut, heureDebut));
//				if (maSeance.getHeureDebut().compareTo(LocalDateTime.now()) < 0) {
//					bMessageErreur = true;
//					messageErreur = "Une séance à une date inférieure à la date du jour et l'heure a été saisie";
//				}

			maSeance.setFilm(monFilm);

			Salle maSalle = new Salle();
			maSalle.setId(Integer.valueOf(pIdSalle));
			maSeance.setSalle(maSalle);
			seances.add(maSeance);
		}
		// }

		if (bOk) {
			if (monFilm.getId() > 0) {
				// update film
				try {
					bllFilm.update(monFilm);

					// m.a.j seances
					List<Seance> seancesAvantUpdate = bll.selectAllSeanceFilmBySalleId(monFilm.getId(),
							Integer.valueOf(pIdSalle));
					Outils outils = new Outils();
					outils.setSeances(seancesAvantUpdate);
					List<Seance> seancesAvantUpdateValide = outils.toutesLesSeancesValides(LocalDate.now(),
							LocalTime.now());

					List<Seance> seancesUpdate = Outils.intersection(seances, seancesAvantUpdateValide);
					List<Seance> seancesInsert = Outils.findDifference(seances, seancesAvantUpdateValide);
					List<Seance> seancesSupprime = Outils.findDifference(seancesAvantUpdateValide, seances);

					for (Seance seance : seancesUpdate) {
						bll.update(seance);
					}

					for (Seance seance : seancesInsert) {
						bll.insert(seance);
					}

					for (Seance seance : seancesSupprime) {
						bll.delete(seance.getId());
					}

				} catch (FilmException e) {
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);

				} catch (SeanceException e) {
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);

				} catch (Exception e) {
					List<String> erreurs = new ArrayList<>();
					erreurs.add("Une erreur est intervenue lors de la mise à jour.");
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);
				}
			} else {
				try {
					bllFilm.insert(monFilm);
					for (Seance seance : seances) {
						seance.setFilm(monFilm);
						bll.insert(seance);
					}
					bCreer = true;
				} catch (FilmException e) {
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);

				} catch (SeanceException e) {
					request.setAttribute("erreurs", e.getMessages());
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);

				} catch (Exception e) {
					List<String> erreurs = new ArrayList<>();
					erreurs.add("Une erreur est intervenue lors de la mise à jour.");
					request.setAttribute("idSalle", pIdSalle);
					request.setAttribute("idFilm", pIdFilm);
					bOk = false;
					doGet(request, response);
				}
			}

		}

		if (bOk) {
			String message = "";
			if (bCreer) {
				message = "Vous venez de créer le film " + monFilm.getNom();
			} else {
				message = "Vous venez de modifier le film " + monFilm.getNom();
			}

			response.sendRedirect(
					"consultFilmSalle?idSalle=" + pIdSalle + "&idFilm=" + monFilm.getId() + "&message=" + message);
		} 
		else {
			List<String> erreurs = new ArrayList<>();
			erreurs.add(messageErreur);
			request.setAttribute("idSalle", pIdSalle);
			request.setAttribute("idFilm", pIdFilm);
			doGet(request, response);

		}

	}

}
