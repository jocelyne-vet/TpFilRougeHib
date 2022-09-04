package bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.util.Reservation;
import dal.GenericDAO;
import dal.GenericDAOHibernateImpl;

public class ReservationBLL {
	
	private GenericDAO<Reservation> dao;
	
	public ReservationBLL() {
		dao = new GenericDAOHibernateImpl<>(Reservation.class);
	}

	public List<Reservation> findAllReservation(int idClient) {
		Map<String,Object> critere = new HashMap<>();
		critere.put("varClientId", idClient);
		return dao.findWithParam("findAllReservationByClient", critere);
	}
	
	public List<Reservation> findAllClientsInscritsBySeance(int idSeance){
		Map<String, Object> critere = new HashMap<>();
		critere.put("varSeanceId", idSeance);
		return dao.findWithParam("findAllReservationBySeance", critere);
	}
	
	public void update(Reservation res) throws ReservationException {
		verifierValeurs(res);
		dao.update(res);
	}
	
	

	public void insert(Reservation res) throws ReservationException {
		verifierValeurs(res);
		dao.insert(res);
	}
	
	private void verifierValeurs(Reservation res) throws ReservationException {
		ReservationException exception = new ReservationException();
		
		if(res.getNb_places()<=0) {
			exception.ajouterErreur("Vous devez reservez un nombre de places >0");
		}
		
		if(exception.getMessages().size()>0) {
			throw exception; 
		}
		
	}
}
