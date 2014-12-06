package com.occlass.dao;

import java.util.List;

import com.occlass.beans.FichierSSTitre;

public interface FichierSSTitreDAO {
	
	void enregistrerFichier(FichierSSTitre fichier);
	FichierSSTitre getFichier(int id);
	List<FichierSSTitre> getAllFichiers();

}
