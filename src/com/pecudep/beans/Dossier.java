package com.pecudep.beans;

import java.util.Date;

public class Dossier {
	
	long id;
	String nom;
	Utilisateur utilisateur;
	Date date;
	public Dossier() {
		super();
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Utilisateur getBean_Utilisateur() {
		return utilisateur;
	}
	public void setBean_Utilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
