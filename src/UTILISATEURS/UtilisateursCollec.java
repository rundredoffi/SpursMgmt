package UTILISATEURS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP.parametres;

public class UtilisateursCollec {
	public static UTILISATEUR authentification(String identifiant, String password) {
		
	    // Connexion à la base de données
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    // Création utilisateur
	    UTILISATEUR monUtilisateur = null;
	    try {
	        conn = DriverManager.getConnection(parametres.getValeur("bdd", "cheminbd") + "user=" + parametres.getValeur("bdd", "id") + "&password=" + parametres.getValeur("bdd", "psw") + "&serverTimezone=UTC");
	        String sql = "SELECT username, habilitations FROM utilisateurs WHERE username=? AND password=?";
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setString(1, identifiant); // Correspond à la 1ère colonne
	        preparedStatement.setString(2, password); // Correspond à la 2ème colonne
	        ResultSet res = preparedStatement.executeQuery();
	        boolean auth = res.first();
	        if(auth) {
	            String username = res.getString("username");
	            String habilitations = res.getString("habilitations");
	        	monUtilisateur = new UTILISATEUR (username,habilitations);
	        	return monUtilisateur;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException sqlEx) { } // ignore
	            rs = null;
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException sqlEx) { } // ignore
	            stmt = null;
	        }
	    }
        return null;
	}
}
