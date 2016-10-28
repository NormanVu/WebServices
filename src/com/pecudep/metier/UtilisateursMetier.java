package com.pecudep.metier;

import java.io.InputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.pecudep.beans.Utilisateur;
import com.pecudep.dao.Connexion;

@Path("/utilisateurs")
public class UtilisateursMetier {

	private Utilisateur usr;
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String aide() {
		return "<p>Pecudep Service Web :ajouter-modifier-supprimer des utilisateurs dans cette section</p>";
	}
	public UtilisateursMetier() {
		
	}
	@Path("/ajouter/{id}")
	@POST
	@Consumes("application/xml")
	public String ajouter(InputStream is, @PathParam("id") String id) {
		 usr = lireUser(is);
		String req = "Insert into depense(`id`,`email`,`login`,`nom`,`prenom`,`mdp`)"
				+ " values('"
				+ Integer.parseInt(id)
				+ "','"
				+ usr.getEmail()
				+ "','"
				+ usr.getLogin()
				+ "','"
				+ usr.getNom()
				+ "','"
				+ usr.getPrenom()
				+ "','"
				+usr.getMdp()+"') ";
		Connexion con = new Connexion();

		java.sql.Statement stm = null;
		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stm.executeUpdate(req);
			System.out.println("inserted!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Ajout ok!";

	}
	private Utilisateur lireUser(InputStream is) {

		Utilisateur usr1 = new Utilisateur();
		System.out.println("lecture...");
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();

			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("nom")) {
					usr1.setNom(element.getTextContent());

				}  else if (element.getTagName().equals("prenom")) {
					usr1.setPrenom(element.getTextContent());

				} else if (element.getTagName().equals("email")) {
					usr1.setEmail(element.getTextContent());
				} else if (element.getTagName().equals("login")) {
					usr1.setLogin(element.getTextContent());

				} else if (element.getTagName().equals("mdp")) {
					// new SimpleDateFormat("yyyy-MM-dd");
					// String date = element.getTextContent();
					usr1.setMdp(element.getTextContent());
					// java.sql.Date convertedDate = null;
					/*
					 * try { convertedDate = new java.sql.Date(DOB.parse(
					 * date.replaceAll("\"", "-")).getTime()); } catch
					 * (ParseException ex) { ex.printStackTrace(); }
					 */
					// dep1.setDate(convertedDate);

				}
			}
		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
		System.out.println("fin de lecture...");
		return usr1;
	}
	

}
