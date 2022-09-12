package bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.cinemas.Film;
import dal.FilmDAO;
@Service
public class FilmBLL {
	@Autowired
	private FilmDAO dao;
	
	public FilmBLL() {
		
	}
	
	public void insert(Film film) throws FilmException {
		verifierValeurs(film);
		dao.save(film);
	}
	
	
	
	public void update(Film film) throws FilmException {
		verifierValeurs(film);
		dao.save(film);
	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	public void deleteFilmBySalle(int idSalle) {
		//A FAIRE
//		String requete = "DELETE from Film p where p.id in (select p.film.id from Seance p join p.salle where p.salle.id ="+idSalle+")";
//		dao.majQuery(requete);
		dao.deleteFilmsBySalleId(idSalle);
	}
	
	private void verifierValeurs(Film film) throws FilmException {
		FilmException exception = new FilmException();
		
		if(film.getNom().isBlank()) {
			exception.ajouterErreur("Vous devez saisir un titre de film");
		}
		if(film.getAffiche().isBlank()) {
			exception.ajouterErreur("Vous devez saisir une affiche");
		}
		if(film.getDescription().isBlank()) {
			exception.ajouterErreur("Vous devez saisir un synopsis");
		}
		if(film.getDuree()<=0) {
			exception.ajouterErreur("Vous devez saisir une durée superieure à 0");
		}
		
		if(exception.getMessages().size()>0) {
			throw exception;
		}
	}

	public void deleteFilmByCinema(Integer idCinema) {
		dao.deletefilmByCinema(idCinema);
		
	}
	
}
