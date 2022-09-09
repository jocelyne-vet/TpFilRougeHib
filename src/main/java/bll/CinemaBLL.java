package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.cinemas.Cinema;
import dal.CinemaDAO;

@Service
public class CinemaBLL {
	@Autowired
	private CinemaDAO dao; 

	
	public CinemaBLL() {
		
	}
	
	public Cinema selectByIdCritere(int id, String critere){
//		Map<String, Object> mapCritere = new HashMap<>();
//		mapCritere.put("varNom", "%"+critere+"%");
//		mapCritere.put("varId", id);
//		Cinema cinema = dao.selectQueryWithParam("findFilmsByCinemaByCritere", mapCritere);
//		return cinema;
		
		System.out.println("*************************************");
		critere = "%"+critere.toUpperCase()+"%";
		Cinema cinema =  dao.findFilmsByCinemaByCritere(critere, id);
		
//		if(cinemas!=null) {
//			cinema = cinemas.get(0);
//		}
		return cinema;
	}
	
	public List<Cinema> selectCinemasCritere(String critere){
			
//		Map<String, Object> mapCritere = new HashMap<>();
//		mapCritere.put("varNom", "%"+critere+"%");
//		mapCritere.put("varVille","%"+ critere+"%");
//		List<Cinema> cinemas = dao.findWithParam("findCinemasByCritere", mapCritere);
//		
//		return cinemas;
		return dao.findByNomContainingIgnoreCaseOrAdresseVilleContainingIgnoreCase(critere, critere);
	}
	
	public Cinema selectById(int id){
		return dao.findById(id).get();
		
	}
	
	public Cinema selectByIdGerant(int idGerant){
//		Map<String, Object> mapCritere = new HashMap<>();
//
//		mapCritere.put("varId",idGerant);
//		Cinema cinema = dao.selectQueryWithParam("findFilmsByCinemaByIdGerant", mapCritere);
//		
//		return cinema;
		Cinema cinema = null;
		List<Cinema> cinemas =  dao.findByGerantId(idGerant);
		if(cinemas!=null) {
			cinema = cinemas.get(0);
		}
		return cinema;
		
	}
	
	public List<Cinema> selectAll( ){
		return dao.findAll();
	}
	
	public void insert(Cinema cinema) throws CinemaException {
		verifierValeurs(cinema);
		dao.save(cinema);
	}
	
	
	
	public void update(Cinema cinema) throws CinemaException {
		verifierValeurs(cinema);
		dao.save(cinema);
	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	public void delete(Cinema cinema) {
		dao.delete(cinema);
	}
	
	public Cinema selectCinemaById(int id) {
		return dao.findById(id).get();
	}
	
	private void verifierValeurs(Cinema cinema) throws CinemaException {
		CinemaException exception = new CinemaException();
		if (cinema.getNom().isBlank()) {
			exception.ajouterErreur("Veuillez saisir un nom de cinema");
		}
		if (cinema.getAffiche().isBlank()) {
			exception.ajouterErreur("Veuillez saisir l'url d'une affiche");
		}
		if (cinema.getGerant().getId()<=0) {
			exception.ajouterErreur("Veuillez selectionner un gérant");
		}
		
		if (exception.getMessages().size() > 0) {
			throw exception;
		}
		
	}
}
