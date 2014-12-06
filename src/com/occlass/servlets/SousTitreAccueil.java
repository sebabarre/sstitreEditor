package com.occlass.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.occlass.beans.FichierSSTitre;
import com.occlass.dao.DaoFactory;
import com.occlass.dao.FichierSSTitreDAO;
import com.occlass.model.SSTitreInOut;

/**
 * Servlet implementation class SousTitreAccueil
 */
@WebServlet("/SousTitreAccueil")
public class SousTitreAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoFactory daoFactory;
	private FichierSSTitreDAO fichierSSTitreDAO;
	private SSTitreInOut model;
	
	public void init() {
		daoFactory = DaoFactory.getInstance();
		fichierSSTitreDAO = daoFactory.getFichierSSTitreDAO();
		model = new SSTitreInOut();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listFichier", fichierSSTitreDAO.getAllFichiers());
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part fichierPart = request.getPart("fichierSSTitre");
		FichierSSTitre srt = model.traiterSrtIn(fichierPart);
		fichierSSTitreDAO.enregistrerFichier(srt);
		request.setAttribute("listFichier", fichierSSTitreDAO.getAllFichiers());
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
