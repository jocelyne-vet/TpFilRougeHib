package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.personnes.Client;
import bo.personnes.Gerant;
import bo.personnes.Personne;
import dal.GerantDAO;
import dal.PersonneDAO;

@Service
public class PersonneBLL {
	@Autowired
	private PersonneDAO dao;
	@Autowired
	private GerantDAO gDao;

	public PersonneBLL() {
		
	}

	public Personne existPersonne(String email, String motDePasse) {
		//return daoG.existPersonne(email, motDePasse);
//		Map<String,Object> critere = new HashMap<>();
//		critere.put("varEmail", email);
//		critere.put("varMotDePasse", motDePasse);
//		return  dao.selectQueryWithParam("existPersonne", critere);
		Personne personne = null;
		List<Personne> personnes = dao.findByEmailAndMotdePasse(email, motDePasse);
		if(personnes!=null) {
			personne = personnes.get(0);
		}
		return personne;
	}

	public Personne selectById(int id) {
		return dao.findById(id).get();
	}
	
	
	public void insertPersonne(Personne personne) {
		dao.save(personne);
	}
	
	public void updatePersonne(Personne personne) {
		dao.save(personne);
	}
	
	public List<Personne> selectAll(){
		return dao.findAll();
	}
	
	public void deletePersonne(int id) {
		dao.deleteById(id);
	}
	//gerant
	public List<Gerant> selectGerants(int id){
//		Map<String, Object> critere = new HashMap<>();
//		critere.put("varGerantId", id);
//		return dao.findWithParam("findGerants", critere);
		//A FAIRE
		return gDao.selectGerants(id);
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
