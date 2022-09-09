package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.util.Reservation;


@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer>{
	List<Reservation> findByClientId(int id);
}
