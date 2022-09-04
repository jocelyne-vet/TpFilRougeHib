package bll;

import java.util.HashMap;
import java.util.Map;

import bo.cinemas.Salle;
import dal.GenericDAO;
import dal.GenericDAOHibernateImpl;


public class SalleBLL {
	
	private GenericDAO<Salle> dao;
	
	public SalleBLL() {
		
		dao = new GenericDAOHibernateImpl<>(Salle.class);
	}
	
	public void insert(Salle salle) throws SalleException {
		verifierValeurs(salle);
		dao.insert(salle);
	}
	
	public void update(Salle salle) throws SalleException {
		verifierValeurs(salle);
		dao.update(salle);
	}
	
	

	public void deleteById(int id) {
		dao.delete(id);
	}
	
	public Salle selectById(int id) {
		return dao.findById(id);
	}
	
	public void existNumeroSalle(int numero, int idCinema) throws SalleException {
		SalleException exception = new SalleException();
		Map<String, Object> critere = new HashMap<>();
		critere.put("varId", idCinema);
		critere.put("varNumero",numero);
		Salle s =  dao.selectQueryWithParam("existNumero", critere);
		boolean bExist=false;
		if(s!=null) {
			bExist=true;
		}
		if(bExist) {
			exception.ajouterErreur("Vous devez saisir un autre numéro de salle");
			
		}
		if(exception.getMessages().size()>0) {
			throw exception;
		}
	}
	
//	public Salle selectSalleSeanceFilmById(int id) {
//		return daoG.selectSalleSeanceFilmById(id);
//	}
	
	private void verifierValeurs(Salle salle) throws SalleException {
		SalleException exception = new SalleException();
		
		if(salle.getNombreDePlaces()<=50) {
			exception.ajouterErreur("Vous devez saisir un nombres de places superieur à 50");
		}
		if(salle.getNumero()<=0) {
			exception.ajouterErreur("vous devez saisir un numéro de salle");
		}
		
		if(exception.getMessages().size()>0) {
			throw exception;
		}
	}

	public void delete(Salle maSalle) {
		dao.delete(maSalle);
		
	}
}
