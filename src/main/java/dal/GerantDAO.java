package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.personnes.Gerant;
@Repository
public interface GerantDAO extends JpaRepository<Gerant, Integer>{
	@Query("select g from Gerant g where g.id not in ( select c.gerant.id from Cinema c) or g.id in (select q.id from Gerant q where q.id =?1 )")
	List<Gerant> selectGerants(int idGerant);
	

}
