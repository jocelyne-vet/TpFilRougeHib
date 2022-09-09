package dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.cinemas.Salle;
@Repository
public interface SalleDAO extends JpaRepository<Salle, Integer>{
	@Query("select sa from Cinema c Join c.salles sa where c.id =?1 and numero =?2")
	Salle existNumero(int idCinema,int numero);
}
