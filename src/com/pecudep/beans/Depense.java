package com.pecudep.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "depense")
public class Depense {

	int id;
	int utilisateur;
	String titre;
	int dossier;
	double montant;
	String date;
	public Depense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Depense(int id, int utilisateur, String titre, int dossier,
			double montant, String date) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.titre = titre;
		this.dossier = dossier;
		this.montant = montant;
		this.date = date;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}
	@XmlElement
	public void setTitre(String titre) {
		this.titre = titre;
	}
	@XmlElement
	public void setDossier(int dossier) {
		this.dossier = dossier;
	}
	@XmlElement
	public void setMontant(double montant) {
		this.montant = montant;
	}
	@XmlElement
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public int getUtilisateur() {
		return utilisateur;
	}
	public String getTitre() {
		return titre;
	}
	public int getDossier() {
		return dossier;
	}
	public double getMontant() {
		return montant;
	}
	public String getDate() {
		return date;
	}


	
}
