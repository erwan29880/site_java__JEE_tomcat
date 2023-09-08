package fr.erwan.modeles;

import fr.erwan.errors.UtilisateurError;

/**
 * Modèle utilisateur pour la page accueil accessible par connexion préalable
 */
public class Utilisateur {
	private String nom;
	private String prenom;
		
	// getters setters
	
	public String getPrenom() {	
		return this.firstLetterToUppercase(this.prenom);
	}
	
	public String getNom() {
		return nom.toUpperCase();
	}
	
	public void setPrenom(String prenom) throws UtilisateurError {
		if(prenom.length() >= 49) {
			throw new UtilisateurError("Le prénom doit avoir moins de 50 caractères");
		} else if (!prenom.matches("[a-zA-Z]+")) {
			throw new UtilisateurError("Le prénom ne peut pas contenir de caractères spéciaux");
		}
		else {
			this.prenom = prenom.toLowerCase();	
		}	
	}
	
	public void setNom(String nom) throws UtilisateurError {
		if(nom.length() >= 49) {
			throw new UtilisateurError("Le nom doit avoir moins de 50 caractères");
		} else if (!nom.matches("[a-zA-Z]+")) {
			throw new UtilisateurError("Le nom ne peut pas contenir de caractères spéciaux");
		}
		else {
			this.nom = nom.toLowerCase();	
		}
	}
	
	
	// fonction utilitaire
	private String firstLetterToUppercase(String st){
		return st.substring(0, 1).toUpperCase() + st.substring(1);
	}
}
