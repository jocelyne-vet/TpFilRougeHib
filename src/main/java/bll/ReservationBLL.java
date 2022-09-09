package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.util.Reservation;
import dal.ReservationDAO;
@Service
public class ReservationBLL {
	@Autowired
	private ReservationDAO dao;
	
	public ReservationBLL() {
		
	}

	public List<Reservation> findAllReservation(int idClient) {
//		Map<String,Object> critere = new HashMap<>();
//		critere.put("varClientId", idClient);
//		return dao.findWithParam("findAllReservationByClient", critere);
		return dao.findByClientId(idClient);
	}
	
	public void update(Reservation res) {
		dao.save(res);
	}
	
	public void insert(Reservation res) {
		dao.save(res);
	}
}
