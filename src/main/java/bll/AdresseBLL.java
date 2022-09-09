package bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.util.Adresse;
import dal.AdresseDAO;

@Service
public class AdresseBLL {
	@Autowired
    private AdresseDAO dao;
    
	public AdresseBLL() {
	}
	
	public void insert(Adresse adresse) throws AdresseException {
		verifierValeurs(adresse);
		 dao.save(adresse);
	}
	
	
	
	public void update(Adresse adresse) throws AdresseException {
		verifierValeurs(adresse);
		dao.save(adresse);
	}
	
//	public void deleteByIdPersonne(int idPersonne) {
//		dao.delete(idPersonne);
//	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	private void verifierValeurs(Adresse adresse) throws AdresseException {
		AdresseException exception = new AdresseException();
		if (adresse.getCpo().isBlank()) {
			exception.ajouterErreur("Veuillez saisir un code postal");
		}
		if (adresse.getNomRue().isBlank()) {
			exception.ajouterErreur("Veuillez saisir un nom de rue");
		}
		if (adresse.getNumero()<=0) {
			exception.ajouterErreur("Veuillez saisir un numéro de voie");
		}
		if (adresse.getTypeRue().isBlank()) {
			exception.ajouterErreur("Veuillez saisir un type de voie");
		}
		if (adresse.getVille().isBlank()) {
			exception.ajouterErreur("Veuillez saisir une ville");
		}
		
		
		if (exception.getMessages().size() > 0) {
			throw exception;
		}
		
	}
}
