package bll;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.cinemas.Seance;
import dal.SeanceDAO;

@Service
public class SeanceBLL {
	@Autowired
	private SeanceDAO dao;
	
//	private GenericDAO<Seance> dao;
	
	public SeanceBLL() {
		
	}
	
	public List<Seance> selectAllSeancesByFilm(int idFilm, int idCinema){
		
//		Map<String,Object> critere = new HashMap<>();
//		critere.put("varId", idFilm);
//		return dao.findWithParam("findSeancesByFilm", critere);
		return dao.findByFilmId(idFilm);
	}
	
	public Seance selectById(int idseance) {
		return dao.findById(idseance).get();
	}

//	public void updateSeance(int idSeance, int nbPlaces) {
//		//dao.updateSeance(idSeance,nbPlaces);
//		
//	}
	
	public List<Seance> selectAllSeanceFilmBySalleId(int idFilm, int idSalle){
		
//		Map<String,Object> critere = new HashMap<>();
//		critere.put("varIdSalle", idSalle);
//		critere.put("varIdFilm", idFilm);
//		return dao.findWithParam("findAllSeancesBySalleByFilm", critere);
		return dao.findBySalleIdAndFilmId(idSalle, idFilm);
		
	}
	
	public void update(Seance seance) throws Exception {
		verifierValeurs(seance);
		dao.save(seance);
		
	}
	
	
	
	public void insert(Seance seance) throws Exception {
		verifierValeurs(seance);
		dao.save(seance);
		
	}
	
	public void delete(int id) {
		
		dao.deleteById(id);
		
	}

	public void deleteSeancesByFilm(int idFilm) {
//		String requete = "Delete from Seance p where p.film.id ="+idFilm;
//		dao.majQuery(requete);
		dao.deleteByFilmId(idFilm);
	}
	
	public void deleteSeancesBySalle(int idSalle) {
//		String requete = "Delete from Seance p where p.salle.id ="+idSalle;
//		dao.majQuery(requete);
		dao.deleteBySalleId(idSalle);
	}
	
	private void verifierValeurs(Seance seance) throws Exception {
		SeanceException exception = new SeanceException();
		
		if(seance.getHeureDebut().isBefore(LocalDateTime.now())) {
			exception.ajouterErreur("Vous devez entrer une date supérieur à la date du jour");
		}
		
		if(exception.getMessages().size()>0) {
			throw new Exception();
		}
		
	}

//	public void deleteSeanceByCinema(int idCinema) {
//		dao.deleteSeanceByCinema(idCinema);
//		
//	}
}
