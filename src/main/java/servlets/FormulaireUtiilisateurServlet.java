package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bll.PersonneBLL;
import bo.personnes.Administrateur;
import bo.personnes.Client;
import bo.personnes.Gerant;
import bo.personnes.Personne;
import bo.util.Adresse;

/**
 * Servlet implementation class FormulaireUtiilisateurServlet
 */
@WebServlet("/formulaireUtilisateur")
public class FormulaireUtiilisateurServlet extends AncetreServlet {
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
	public FormulaireUtiilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pUtilisateurId = request.getParameter("id");
		Personne maPersonne=null;
		if (pUtilisateurId != null && !pUtilisateurId.isEmpty()) {
			 maPersonne = bll.selectById(Integer.valueOf(pUtilisateurId));
		}
		request.setAttribute("utilisateur", maPersonne);
		request.getRequestDispatcher("WEB-INF/jsp/formUtilisateur.jsp").forward(request, response);
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
		
		boolean bCreer=false;

		Personne personne;

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
		if (pId != null && !pId.isBlank()) {
			personne.setId(Integer.valueOf(pId));
		}
		Adresse monAdresse = new Adresse();
		monAdresse.setNumero(Integer.valueOf(pNumeroVoie));
		monAdresse.setTypeRue(pTypeVoie);
		monAdresse.setNomRue(pNomVoie);
		monAdresse.setCpo(pCpo);
		monAdresse.setVille(pVille);
		if (pIdAdresse != null && !pIdAdresse.isBlank()) {
			monAdresse.setId(Integer.valueOf(pIdAdresse));
		}
		personne.setAdresse(monAdresse);

		if (pId.isBlank()) {
			//bllAdresse.insert(monAdresse);
			personne.setAdresse(monAdresse);
			bll.insertPersonne(personne);
			bCreer = true;
		} else {
			//bllAdresse.update(monAdresse);
			personne.setAdresse(monAdresse);
			bll.updatePersonne(personne);
		}
		
		String message = "";
		if(bCreer) {
			message = "Vous venez de créer l'utilisateur "+personne.getNom()+" "+personne.getPrenom();
		}else {
			message = "Vous venez de modifier l'utilisateur "+personne.getNom()+" "+personne.getPrenom();
		}

		response.sendRedirect("listeUtilisateurs?message="+message);
	}

}
