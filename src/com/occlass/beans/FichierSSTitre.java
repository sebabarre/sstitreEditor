package com.occlass.beans;

import java.util.List;

public class FichierSSTitre {
	
	private int id;
	private String titre;
	private String emplacementOrigine;
	private List<PartSSTitre> detailFichier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getEmplacementOrigine() {
		return emplacementOrigine;
	}
	public void setEmplacementOrigine(String emplacementOrigine) {
		this.emplacementOrigine = emplacementOrigine;
	}
	public List<PartSSTitre> getDetailFichier() {
		return detailFichier;
	}
	public void setDetailFichier(List<PartSSTitre> detailFichier) {
		this.detailFichier = detailFichier;
	}
	
	

}
