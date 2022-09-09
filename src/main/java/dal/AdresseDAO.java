package dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.util.Adresse;
@Repository
public interface AdresseDAO extends JpaRepository<Adresse, Integer>{

}
