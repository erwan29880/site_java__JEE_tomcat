package fr.erwan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * connexion bdd
 * récupération données
 */
public class DaoFactory {
    private String url;
    private String user;
    private String passwd;

    DaoFactory(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }
    
    
    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	System.out.println("probleme de classe  " + e.getMessage());
        }
    	DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/jee", "root", "pa");
        return instance;
    }

    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.passwd );
    }

    
    // Récupération du Dao Utilisateur
    public DaoImpUtilisateur getUtilisateurDao() {
        return new DaoImpUtilisateur(this);
    }
    
 // Récupération du Dao Admin
    public DaoImpAdmin getAdminDao() {
        return new DaoImpAdmin(this);
    }
    
    
}