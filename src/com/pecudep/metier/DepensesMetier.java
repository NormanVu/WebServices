package com.pecudep.metier;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.pecudep.beans.Bilan;
import com.pecudep.beans.Depense;
import com.pecudep.dao.Connexion;

@Path("/depenses")
public class DepensesMetier {

	Depense dep = null;
	private Bilan bilan;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String aide() {
		return "<p>Pecudep Service Web :ajouter-modifier-supprimer des depenses</p>";
	}

	@Path("/postDepense/{id}")
	@POST
	@Consumes("application/xml")
	public String ajouter(InputStream is, @PathParam("id") String id) {
		Depense dep = lireDep(is);
		String req = "Insert into depense(`id`,`titre`,`utilisateur`,`montant`,`dossier`,`date`)"
				+ " values('"
				+ id
				+ "','"
				+ dep.getTitre()
				+ "','"
				+ dep.getUtilisateur()
				+ "','"
				+ dep.getMontant()
				+ "','"
				+ dep.getDossier() + "','" + dep.getDate() + "') ";
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

	@GET
	@Path("/{utilisateur}/{dossier}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Depense> Depense(@PathParam("dossier") String dossier,
			@PathParam("utilisateur") String utilisateur) {

		List<Depense> malist = new ArrayList<Depense>();
		String req = "Select * from depense where `utilisateur`='"
				+ utilisateur + "' and `dossier`='" + dossier + "'";
		Connexion con = new Connexion();
		ResultSet rs = null;
		java.sql.Statement stm = null;

		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rs = stm.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				System.out.println("selection de la ligne...");
				int id = rs.getInt("id");

				double montant = rs.getDouble("montant");
				String titre = rs.getString("titre");
				String date = rs.getString("date");
				int utilisateur1 = rs.getInt("utilisateur");
				int dossier1 = rs.getInt("dossier");
				dep = new Depense(id, utilisateur1, titre, dossier1, montant,
						date);
				System.out.println("selection ok!!");
				System.out.println("date : " + dep.getDate() + "montant : "
						+ dep.getMontant() + "titre : " + dep.getTitre());
				malist.add(dep);
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return malist;
	}

	@GET
	@Path("/getDepense/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Depense getDepense(@PathParam("id") String id) {

		System.out.println("initiation de la requete...");
		String req = "Select * from depense where `id`='" + id + "'";
		Connexion con = new Connexion();
		ResultSet rs = null;
		java.sql.Statement stm = null;

		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rs = stm.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				System.out.println("selection de la ligne...");
				int utilisateur = rs.getInt("utilisateur");
				double montant = rs.getDouble("montant");
				String titre = rs.getString("titre");
				int dossier = rs.getInt("dossier");
				String date = rs.getString("date");
				dep = new Depense(Integer.parseInt(id), utilisateur, titre,
						dossier, montant, date);
				System.out.println("selection ok!!");
				System.out.println("date : " + dep.getDate() + "montant : "
						+ dep.getMontant() + "titre : " + dep.getTitre());
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dep;

	}

	@PUT
	@Path("/putDepense/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String putDepense(InputStream is, @PathParam("id") String id) {
		dep = lireDep(is);
		String req = "update depense set `titre`='" + dep.getTitre()
				+ "',`utilisateur`='" + dep.getUtilisateur() + "',"
				+ "`montant`='" + dep.getMontant() + "',`dossier`="
				+ dep.getDossier() + ",`date`='" + dep.getDate() + "'"
				+ " where id=" + id + "";
		Connexion con = new Connexion();

		java.sql.Statement stm = null;
		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stm.executeUpdate(req);
			System.out.println("Modifié!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Modifiaction ok!";
	}
	
	@DELETE
	@Path("/deleteDepense/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String deleteDepense(@PathParam("id") String id) {
		//dep = lireDep(is);
		String req = "delete from depense  where `id`='"+ id +"'";
		Connexion con = new Connexion();

		java.sql.Statement stm = null;
		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stm.executeUpdate(req);
			System.out.println("Supprimé!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Suppression ok!";
	}


	@GET
	@Path("/getBilan/{utilisateur}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Bilan Bilan(@PathParam("utilisateur") String utilisateur) {
		String req = "SELECT sum(montant) as bilan, max(date) as dateActuelle,utilisateur from depense where `utilisateur`='"
				+ utilisateur + "'";
		Connexion con = new Connexion();
		ResultSet rs = null;
		java.sql.Statement stm = null;

		try {
			stm = con.getConnection().createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rs = stm.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				System.out.println("selection de la ligne...");
				int utilisateur1 = rs.getInt("utilisateur");
				String date = rs.getString("dateActuelle");
				bilan = new Bilan(utilisateur1, rs.getDouble("bilan"), date);
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bilan;
	}

	public Depense lireDep(InputStream is) {
		Depense dep1 = new Depense();
		System.out.println("lecture...");
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();

			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("titre")) {
					dep1.setTitre(element.getTextContent());

				} else if (element.getTagName().equals("id")) {
					dep1.setId(Integer.parseInt(element.getTextContent()));

				} else if (element.getTagName().equals("utilisateur")) {
					dep1.setUtilisateur(Integer.parseInt(element
							.getTextContent()));

				} else if (element.getTagName().equals("montant")) {
					dep1.setMontant(Double.parseDouble(element.getTextContent()));
				} else if (element.getTagName().equals("dossier")) {
					dep1.setDossier(Integer.parseInt(element.getTextContent()));

				} else if (element.getTagName().equals("date")) {
					// new SimpleDateFormat("yyyy-MM-dd");
					// String date = element.getTextContent();
					dep1.setDate(element.getTextContent());
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
		return dep1;
	}

}
