package fr.erwan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.erwan.errors.AdminError;
import fr.erwan.modeles.Admin;

/**
 * classe du vérification pseudo/mot de passe enregistré en bdd
 */
public class DaoImpAdmin implements DaoImp<Admin>{

	private DaoFactory daoFactory;
	
	public DaoImpAdmin(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	public boolean checkpwd(Admin admin){	
		
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;
        boolean check = false;
        
        // sql, mise à jour évenutelle de check en cas de résultat
        try {
            connexion = this.daoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT * FROM admin where pseudo=? and pwd=SHA1(?);");
            statement.setString(1, admin.getPseudo());
            statement.setString(2, admin.getPwd());
            resultat = statement.executeQuery();           
        	
            if (resultat.next() == true) {
                check = true;
        	}
            
        } catch (SQLException e) {
        	System.out.println("checkPwd error" + e.getMessage());        } 
        finally {
        	// fermeture de la connextion bdd
        	try {
				statement.close();
	        	connexion.close();
			} catch (SQLException e) {
				System.out.println("checkPwd close error" + e.getMessage());
			}
        }
        return check;
	}
	
	
	/**
	 * méthode à implémenter cause interface
	 * ne pas utiliser
	 */
	@Override
	public List<Admin> select(){		
		List<Admin> admins = new ArrayList<Admin>();
		Admin ad = new Admin();
		ad.setPseudo("vide");
		ad.setPwd("rien");
		admins.add(ad);
		return admins;
	}
	
	
	public boolean checkPseudo(Admin admin) {
	
		Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;
        boolean check = false;
        
        //sql -> mise à jour éventuelle de check
        try {
            connexion = this.daoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT pseudo FROM admin where pseudo=?;");
            statement.setString(1, admin.getPseudo());
            resultat = statement.executeQuery();           
        	
            // vérification si il y a un résultat
            if (resultat.next() == true) {
                check = true;
        	}
        } catch (SQLException e) {
        	System.out.println("checkpseudo error" + e.getMessage());        } 
        finally {
        	try {
				statement.close();
	        	connexion.close();
			} catch (SQLException e) {
				System.out.println("checkPseudo close " + e.getMessage());
			}
        }
        return check;
	}
	
	
	
	@Override
	public void insert(Admin admin) {
		
		Connection connexion = null;
        PreparedStatement statement = null;
        
        //sql 
        try {
            connexion = this.daoFactory.getConnection();
            statement = connexion.prepareStatement("INSERT into admin(pseudo, pwd) VALUES(?, SHA1(?) );");
            statement.setString(1, admin.getPseudo());
            statement.setString(2,  admin.getPwd());
            statement.executeUpdate();
            System.out.println("inseret");
        } catch (SQLException e) {
            System.out.println("insert error" + e.getMessage());
        } 
        finally {
        	try {
				statement.close();
	        	connexion.close();
			} catch (SQLException e) {
				System.out.println("close error insert" + e.getMessage());;
			}
        }
	}
	
	
	@Override
	public void update(Admin admin) {
	}
	
	
	@Override
	public void delete(Admin admin) {
		
		Connection connexion = null;
        PreparedStatement statement = null;
        
        //sql 
        try {
            connexion = this.daoFactory.getConnection();
            statement = connexion.prepareStatement("DELETE from admin where pseudo=? AND pwd=SHA1(?);");
            statement.setString(1, admin.getPseudo());
            statement.setString(2, admin.getPwd());
            statement.executeUpdate();          
        } catch (SQLException e) {
        	System.out.println("delete error" + e.getMessage());
        } 
        finally {
        	try {
				statement.close();
	        	connexion.close();
			} catch (SQLException e) {
				System.out.println("delete close" + e.getMessage());
			}
        }
	}
}
