package fr.erwan.errors;

/**
 * implémentation des erreurs du modèle Utilisateur, récupérées en front
 */
public class UtilisateurError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UtilisateurError(String err) {
		super(err);
	}
}
