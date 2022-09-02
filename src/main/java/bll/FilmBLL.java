package bll;

import bo.cinemas.Film;

import dal.GenericDAO;
import dal.GenericDAOHibernateImpl;

public class FilmBLL {
	
	private GenericDAO<Film> dao;
	
	public FilmBLL() {
		dao = new GenericDAOHibernateImpl<>(Film.class);
	}

	public void insert(Film film) throws FilmException {
		verifierValeurs(film);
		dao.insert(film);
	}
	
	

	public void update(Film film) throws FilmException {
		verifierValeurs(film);
		dao.update(film);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}
	
	public void deleteFilmBySalle(int idSalle) {
		
		String requete = "DELETE from Film p where p.id in (select p.film.id from Seance p join p.salle where p.salle.id ="+idSalle+")";
		dao.majQuery(requete);
		//dao.deleteFilmBySalle(idSalle);
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
	
}
