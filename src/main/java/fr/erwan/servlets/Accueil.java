package fr.erwan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.erwan.dao.DaoFactory;
import fr.erwan.dao.DaoImpAdmin;
import fr.erwan.dao.DaoImpUtilisateur;
import fr.erwan.modeles.Utilisateur;

/**
 * Servlet implementation class Accueil page index
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoImpUtilisateur utilisateurDao;   
	
    public Accueil() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", utilisateurDao.select());
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur nouvelUtilisateur = new Utilisateur();
		
		try {
			// ajouter un nouvel utilisateur 
			nouvelUtilisateur.setNom(request.getParameter("nom"));
			nouvelUtilisateur.setPrenom(request.getParameter("prenom"));
			utilisateurDao.insert(nouvelUtilisateur);
		} catch (Exception e) {
			// exceptions UtilisateurError affichée en front - modifier dans modèle Utilisateur
			request.setAttribute("error", e.getMessage());
		}
		
		// envoi des résultats bdd mis à jour
		request.setAttribute("users", utilisateurDao.select());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
