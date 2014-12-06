package com.occlass.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.occlass.beans.FichierSSTitre;
import com.occlass.dao.DaoFactory;
import com.occlass.dao.FichierSSTitreDAO;
import com.occlass.dao.FichierSSTitreDAOImpl;

public class ModifierSSTitre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoFactory daoFactory;
	private FichierSSTitreDAO fichierDAO;
	
	public void init() {
		daoFactory = DaoFactory.getInstance();
		fichierDAO = new FichierSSTitreDAOImpl(daoFactory);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FichierSSTitre fichierSSTitre = fichierDAO.getFichier(Integer.valueOf(request.getParameter("idFichier")));
		request.setAttribute("fichier", fichierSSTitre);
		this.getServletContext().getRequestDispatcher("/WEB-INF/edition.jsp").forward(request, response);
	}

}
