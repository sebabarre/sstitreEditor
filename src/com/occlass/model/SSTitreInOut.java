package com.occlass.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.occlass.beans.FichierSSTitre;
import com.occlass.beans.PartSSTitre;

public class SSTitreInOut {
	
	public FichierSSTitre traiterSrtIn(Part fichier) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		String line = null;
		List<PartSSTitre> listSSTitre;
		FichierSSTitre fichierSSTitre = null;
		try {
			inputStream = fichier.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			listSSTitre = new ArrayList<PartSSTitre>();
			fichierSSTitre = new FichierSSTitre();
			fichierSSTitre.setTitre(fichier.getSubmittedFileName());
			PartSSTitre ligne = new PartSSTitre();
			while ((line = reader.readLine()) != null) {
				ligne = traiterLigne(ligne,line, listSSTitre);
			}
			fichierSSTitre.setDetailFichier(listSSTitre);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fichierSSTitre;
	}

	private PartSSTitre traiterLigne(PartSSTitre ligne, String line, List<PartSSTitre> listSSTitre) {
		//Si chiffre
		if (isEntier(line)) {
			ligne.setNumLigne(Integer.valueOf(line));
		} else if(line.contains("-->")) { 
			int split = line.indexOf("-->");
			ligne.setDateDebut(line.substring(0, split).trim());
			ligne.setDateFin(line.substring(split+3).trim());
		} else if (line.isEmpty()) {//Si String vide, ajouter à la liste et renvoyer un nouveau PartSSTitre
			listSSTitre.add(ligne);
			ligne = new PartSSTitre();
		} else {//Sinon, c'est que c'est String
			ligne.setSsTitre((ligne.getSsTitre() == null ? "" : ligne.getSsTitre())+ " "+line);
		}
		return ligne;
	}

	private boolean isEntier(String line) {
		try {
			Integer.valueOf(line);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
