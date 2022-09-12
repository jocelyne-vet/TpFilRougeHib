package dal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.cinemas.Film;
@Repository
public interface FilmDAO extends JpaRepository<Film, Integer>{
	@Query("DELETE from Film f where f.id in (select p.film.id from Seance p join p.salle where p.salle.id =?1)")
	@Modifying
	@Transactional
	void deleteFilmsBySalleId(int salleId);
	@Query("DELETE from Film f where f.id in (select p.film.id from Seance p join p.salle se where se.cinema.id =?1)")
	@Modifying
	@Transactional
	void deletefilmByCinema(Integer idCinema);
	

}
