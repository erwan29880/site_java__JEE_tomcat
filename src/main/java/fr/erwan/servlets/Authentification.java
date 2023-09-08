package fr.erwan.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.erwan.dao.DaoFactory;
import fr.erwan.dao.DaoImpAdmin;
import fr.erwan.modeles.Admin;

@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoImpAdmin adminDao;

	 public Authentification() {
	     super();
    }
	
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.adminDao = daoFactory.getAdminDao();
    }
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/enregistrement.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("supprimer") == null) {
			try {
				
				// enregistrement
				Admin admin = new Admin();
				admin.setPseudo(request.getParameter("pseudo"));
				admin.setPwd(request.getParameter("pwd"));
				
				
				//vérification que le pseudo n'est pas pris
				admin.setCheckPseudo(this.adminDao.checkPseudo(admin));
				String message = admin.checkPseudo();		
				
				
				// si le pseudo n'est pas pris, insertion
				if (admin.isCheckPseudo() == false) {
					try {
						this.adminDao.insert(admin);	
					} catch (Exception e) {
						System.out.println("pb" + e.getMessage());
					}
				}
				request.setAttribute("error", message);	
			} catch (Exception e) {
				request.setAttribute("error", "Erreur lors de l'inscription");
			}	
		} else {
				// suppression
				try {
					Admin admin = new Admin();
					admin.setPseudo(request.getParameter("pseudo"));
					admin.setPwd(request.getParameter("pwd"));
					this.adminDao.delete(admin);
					request.setAttribute("error", "votre compte a bien été supprimé");
				} catch (Exception e) {
					request.setAttribute("error", "Il y a eu un problème, veuillez ré-essayer");
				}
		}
		 
		this.getServletContext().getRequestDispatcher("/WEB-INF/enregistrement.jsp").forward(request, response);
	}

}
