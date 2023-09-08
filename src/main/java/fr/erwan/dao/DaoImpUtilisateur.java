package fr.erwan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.erwan.errors.UtilisateurError;
import fr.erwan.modeles.Utilisateur;

/**
 * implémentation de l'interface DaoImp pour la table users - modèle Utilisateur
 */
public class DaoImpUtilisateur implements DaoImp<Utilisateur>{

	private DaoFactory daoFactory;
	
	public DaoImpUtilisateur(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Utilisateur> select(){		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT nom, prenom FROM users;");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");

                Utilisateur utilisateur = new Utilisateur();
                try {
					utilisateur.setNom(nom);
				} catch (UtilisateurError e) {
					e.printStackTrace();
				}
                try {
					utilisateur.setPrenom(prenom);
				} catch (UtilisateurError e) {
					e.printStackTrace();
				}

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
        	try {
				statement.close();
	        	connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return utilisateurs;
		
		
	}
	
	@Override
	public void insert(Utilisateur utilisateur) {
		Connection connexion = null;
        PreparedStatement pstmt = null;
		try {
			connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement("insert into users(nom, prenom) values(?, ?);");	        
			pstmt.setString(1, utilisateur.getNom());
			pstmt.setString(2, utilisateur.getPrenom());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("fonction insertion : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				connexion.close();
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public void update(Utilisateur utilisateur) {
	}
	
	@Override
	public void delete(Utilisateur utilisateur) {
		Connection connexion = null;
        PreparedStatement pstmt = null;
        
		try {
			connexion = daoFactory.getConnection();
            pstmt = connexion.prepareStatement("delete from users where nom=? and prenom=?;");	
			pstmt.setString(1, utilisateur.getNom());
			pstmt.setString(2, utilisateur.getPrenom());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("fonction insertion : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				connexion.close();
			} catch (SQLException e) {}
		}
	}
}
