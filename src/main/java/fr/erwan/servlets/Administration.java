package fr.erwan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.erwan.dao.DaoFactory;
import fr.erwan.dao.DaoImpAdmin;
import fr.erwan.modeles.Admin;

/**
 * page connection
 */
@WebServlet("/Administration")
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoImpAdmin adminDao;

    public Administration() {
        super();
    }

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.adminDao = daoFactory.getAdminDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			// récupération données formulaire
			Admin admin = new Admin();
			admin.setPseudo(request.getParameter("pseudo"));
			admin.setPwd(request.getParameter("pwd"));
			
			// vérification user/pwd par dao
			admin.setCheckPwd(this.adminDao.checkpwd(admin));
			
			// gestion session et vue via checkPwd du controlleur
			if(admin.isCheckPwd() == true) {
				session.setAttribute("pseudo", admin.getPseudo());	
			} else {
				request.setAttribute("error", "pseudo/mot de passe erronés");
			}
		} catch (Exception e) {
			request.setAttribute("error", "veuillez vous connecter !");
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);

	}

}
