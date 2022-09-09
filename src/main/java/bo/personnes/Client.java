package bo.personnes;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import bo.cinemas.Seance;
import bo.util.Reservation;

@Entity
@DiscriminatorValue(value="Client")
public class Client extends Personne {
	@OneToMany(targetEntity = Reservation.class,mappedBy = "client")
	@MapKey
	private Map<Seance, Reservation> reservations;	
	
	public Client() {
		super();
		roleLibelle="Client";
		this.reservations = new HashMap<>();

	}

	public Map<Seance, Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Map<Seance, Reservation> reservations) {
		this.reservations = reservations;
	}

//	public List<Reservation> getReservationsC() {
//		return reservationsC;
//	}
//
//	public void setReservationsC(List<Reservation> reservationsC) {
//		this.reservationsC = reservationsC;
//	}
//	
	
}
