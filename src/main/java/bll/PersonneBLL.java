package bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bo.personnes.Client;
import bo.personnes.Personne;
import dal.GenericDAO;
import dal.GenericDAOHibernateImpl;


public class PersonneBLL {
	
	private GenericDAO<Personne> dao;

	public PersonneBLL() {
		//daoG = new PersonneDAOJdbcImpl();
		dao = new GenericDAOHibernateImpl<>(Personne.class);
	}

	public Personne existPersonne(String email, String motDePasse) {
		//return daoG.existPersonne(email, motDePasse);
		Map<String,Object> critere = new HashMap<>();
		critere.put("varEmail", email);
		critere.put("varMotDePasse", motDePasse);
		return  dao.selectQueryWithParam("existPersonne", critere);
		
	}

	public Personne selectById(int id) {
		return dao.findById(id);
	}
	
	
	public void insertPersonne(Personne personne) {
		dao.insert(personne);
	}
	
	public void updatePersonne(Personne personne) {
		dao.update(personne);
	}
	
	public List<Personne> selectAll(){
		return dao.findAll("findAll");
	}
	
	public void deletePersonne(int id) {
		dao.delete(id);
	}
	//gerant
	public List<Personne> selectGerants(int id){
		Map<String, Object> critere = new HashMap<>();
		critere.put("varGerantId", id);
		return dao.findWithParam("findGerants", critere);		
	}
	//reservations

//	public void majReservation(int idSeance, int idClient, int nbPlaces) {
//
//		if (daoG.existReservationByIdSeanceIdClient(idSeance, idClient)) {
//			daoG.updateReservations(idSeance, idClient, nbPlaces);
//		} else {
//			daoG.insertReservation(idSeance, idClient, nbPlaces);
//		}
//	}
//
//	public Map<Client, Integer> selectClientsInscrits(int idSeance) {
//		return daoG.selectClientsInscrits(idSeance);
//	}
//
	public void selectMesReservations(Client client) {
		// TODO Auto-generated method stub
		// dao.selectMesReservations(client);
	}
	
//	public Map<Seance, Integer> selectHistoriqueReservation(int idClient){
//		return daoG.selectHistoriqueReservation(idClient);
//	}
}
