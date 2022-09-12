package dal;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.cinemas.Seance;
@Repository
public interface SeanceDAO extends JpaRepository<Seance, Integer>{
	@Query("Delete from Seance p where p.film.id = ?1")
	@Modifying
	@Transactional
	void deleteByFilmId(int id);
	@Query("Delete from Seance p where p.salle.id =?1")
	@Modifying
	@Transactional
	void deleteBySalleId(int id);
	List<Seance> findBySalleIdAndFilmId(int salleId,int filmId);
	List<Seance> findByFilmId(int id);
//	@Query("DELETE from Seance s join s.salle sa where sa.cinema.id=?1")
//	@Modifying
//	@Transactional
//	void deleteSeanceByCinema(int idCinema);
}
