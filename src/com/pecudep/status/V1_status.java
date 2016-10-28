package com.pecudep.status;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.mysql.jdbc.Statement;
import com.pecudep.dao.Connexion;

@Path("/v1/status")
public class V1_status {

	private static String api_version="1.0.0";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String titre(){
		return "<p>Pecudep Service Web</p>";
	}
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String version(){
		return "<p>Version :</p> "+api_version;
	}
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String database() throws SQLException{
		Connexion con=new Connexion();
		String s=null;
		String req="select info from information; ";
		java.sql.Statement stm=con.getConnection().createStatement();
		ResultSet rs = stm.executeQuery(req);
		while (rs.next()) {
			
			s=rs.getString("info");
		}
		return "<p>Info :  "+s+"</p>";
				}
	
}
