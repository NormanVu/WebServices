package com.pecudep.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bilan {

	int utilisateur;
	double montant;
	String date;

	public Bilan() {

	}

	public Bilan(int utilisateur, double montant, String date) {
		super();
		this.utilisateur = utilisateur;
		this.montant = montant;
		this.date = date;
	}

	@XmlElement
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}

	@XmlElement
	public void setMontant(double montant) {
		this.montant = montant;
	}

	@XmlElement
	public void setDate(String date) {
		this.date = date;
	}

	public int getUtilisateur() {
		return utilisateur;
	}

	public double getMontant() {
		return montant;
	}

	public String getDate() {
		return date;
	}

}
