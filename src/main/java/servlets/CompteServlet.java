 package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import bll.PersonneBLL;
import bo.personnes.Administrateur;
import bo.personnes.Client;
import bo.personnes.Gerant;
import bo.personnes.Personne;
import bo.util.Adresse;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/compte")
public class CompteServlet extends AncetreServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PersonneBLL bll;
//	private AdresseBLL bllAdresse;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
//		bll = new PersonneBLL();
//		bllAdresse = new AdresseBLL();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
		

		request.getRequestDispatcher("WEB-INF/jsp/compte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNom = request.getParameter("nom");
		String pPrenom = request.getParameter("prenom");
		String pDateNaissance = request.getParameter("dateNaissance");
		String pEmail = request.getParameter("email");
		String pPwd = request.getParameter("pwd");
		String pNumeroVoie = request.getParameter("numero_voie");
		String pTypeVoie = request.getParameter("type_voie");
		String pNomVoie = request.getParameter("nom_voie");
		String pCpo = request.getParameter("code_postal");
		String pVille = request.getParameter("ville");
		String pRole = request.getParameter("role");
		String pId = request.getParameter("idPersonne");
		String pIdAdresse = request.getParameter("idAdresse");

		Personne personne;
		
		if(pRole==null || pRole.isBlank()) {
			pRole="Client";
		}

		switch (pRole) {
		case "Client": {

			personne = new Client();
			
			break;
		}
		case "Gerant": {

			personne = new Gerant();
			
			break;
		}
		case "Administrateur": {

			personne = new Administrateur();
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + pRole);
		}
		
		personne.setNom(pNom);
		personne.setPrenom(pPrenom);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ddn = LocalDate.parse(pDateNaissance, dtf);
		
		personne.setDateNaissance(ddn);
		
		personne.setEmail(pEmail);
		personne.setMotdePasse(pPwd);
		if(pId!=null && !pId.isBlank()) {
			personne.setId(Integer.valueOf(pId));
		}
		Adresse monAdresse = new Adresse();
		monAdresse.setNumero(Integer.valueOf(pNumeroVoie));
		monAdresse.setTypeRue(pTypeVoie);
		monAdresse.setNomRue(pNomVoie);
		monAdresse.setCpo(pCpo);
		monAdresse.setVille(pVille);
		if(pIdAdresse!=null && !pIdAdresse.isBlank()) {
			monAdresse.setId(Integer.valueOf(pIdAdresse));
		}
		personne.setAdresse(monAdresse);
		
		if( pId.isBlank()) {
//			bllAdresse.insert(monAdresse);
			personne.setAdresse(monAdresse);
			bll.insertPersonne(personne);
		}else {
//			bllAdresse.update(monAdresse);
			personne.setAdresse(monAdresse);
			bll.updatePersonne(personne);
		}
		
		HttpSession session= request.getSession();
		session.setAttribute("user", personne);
		request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

}
