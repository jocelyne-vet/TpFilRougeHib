package bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.cinemas.Salle;
import dal.SalleDAO;

@Service
public class SalleBLL {
	@Autowired
	private SalleDAO dao;
	
	public SalleBLL() {
	}
	
	
	public void insert(Salle salle) throws SalleException {
		verifierValeurs(salle);
		dao.save(salle);
	}
	
	public void update(Salle salle) throws SalleException {
		
		dao.save(salle);
	}
	
	
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	public Salle selectById(int id) {
		return dao.findById(id).get();
	}
	
	public void existNumeroSalle(Salle maSalle, int numero, int idCinema) throws SalleException {
		SalleException exception = new SalleException();
//		Map<String, Object> critere = new HashMap<>();
//		critere.put("varId", idCinema);
//		critere.put("varNumero",numero);
//		Salle s =  dao.selectQueryWithParam("existNumero", critere);
		Salle s = dao.existNumero(idCinema, numero);
		boolean bExist=false;
		if(s!=null && (s.getId()!= maSalle.getId())) {
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
}
