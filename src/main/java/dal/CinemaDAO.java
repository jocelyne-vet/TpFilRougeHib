package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.cinemas.Cinema;
@Repository
public interface CinemaDAO extends JpaRepository<Cinema, Integer>{
	List<Cinema> findByGerantId(int idGerant);
	@Query("select p from Cinema p  Join fetch  p.salles sa  join fetch  sa.seances se where UPPER(se.film.nom) like ?1  and p.id =?2")
	Cinema findFilmsByCinemaByCritere(String nom, Integer id);
	List<Cinema> findByNomContainingIgnoreCaseOrAdresseVilleContainingIgnoreCase(String nom, String ville);

}
