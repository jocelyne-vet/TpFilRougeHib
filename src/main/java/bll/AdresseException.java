package bll;

import java.util.ArrayList;
import java.util.List;

public class AdresseException extends Exception {
	private static final long serialVersionUID = 1L;

	private List<String> messages;

	public AdresseException() {
		messages = new ArrayList<>();
	}

	public void ajouterErreur(String erreur) {
		messages.add(erreur);
	}

	public List<String> getMessages() {
		return messages;
	}
}
