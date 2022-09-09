package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.personnes.Personne;
@Repository
public interface PersonneDAO extends JpaRepository<Personne, Integer>{
	List<Personne> findByEmailAndMotdePasse(String email, String motdepasse);
	
}
