package fr.erwan.modeles;


/**
 * création/suppression d'un compte admin
 * connexion admin
 */
public class Admin{

	private String pseudo;
	private String pwd;
	private boolean checkPwd = false;
	private boolean checkPseudo = false;
	
	public Admin() {
		super();
	}
	
	
	// getters et setters
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo.toLowerCase();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean isCheckPwd() {
		return this.checkPwd;
	}

	public void setCheckPwd(boolean checkPwd) {
		this.checkPwd = checkPwd;
	}

	public boolean isCheckPseudo() {
		return checkPseudo;
	}

	public void setCheckPseudo(boolean checkPseudo) {
		this.checkPseudo = checkPseudo;
	}
	
	
	// vérifications - checkPseudo et checkPwd sont mis à jour dans la vue selon réultat Dao
	
	public String checkPseudo() {
		String message;
		if (this.checkPseudo == true) {
			message = "Le pseudo est déjà pris !";
		} else {
			message = "Vous êtes enregistré !";
		}
		return message;
	}
	
	
	public String checkPwd() {
		String message;
		if (this.checkPwd == true) {
			message = "Vous êtes bien authentifié(e) !";
		} else {
			message = "Pseudo ou mot de passe invalide ou inexistant";
		}
		return message;
	}
		
}
