package MATCHS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP.parametres;

public class match {
	private Integer MatchID;
	private String Adversaire;
	private String Date;
	private String Lieu;
	private String Resultat;
	private String DifScore;
	public match(Integer matchID, String adversaire, String date, String lieu, String resultat, String difScore) {
		super();
		MatchID = matchID;
		Adversaire = adversaire;
		Date = date;
		Lieu = lieu;
		Resultat = resultat;
		DifScore = difScore;
	}

	public Integer getMatchID() {
		return MatchID;
	}
	public void setMatchID(Integer matchID) {
		MatchID = matchID;
	}
	public String getAdversaire() {
		return Adversaire;
	}
	public void setAdversaire(String adversaire) {
		Adversaire = adversaire;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getLieu() {
		return Lieu;
	}
	public void setLieu(String lieu) {
		Lieu = lieu;
	}
	public String getResultat() {
		return Resultat;
	}
	public void setResultat(String resultat) {
		Resultat = resultat;
	}
	public String getDifScore() {
		return DifScore;
	}
	public void setDifScore(String difScore) {
		DifScore = difScore;
	}
	@Override
	public String toString() {
		return "MatchID : " + MatchID + "\n Adversaire : " + Adversaire + "\n Date : " + Date + "\n Lieu : " + Lieu
				+ "\n Resultat : " + Resultat + "\n Différence : " + DifScore + "\n";
	}
	public void putInBdd() { // Envoie du match en base de données
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 conn = DriverManager.getConnection(parametres.getValeur("bdd", "cheminbd") + "user="+parametres.getValeur("bdd", "id")+"&password="+parametres.getValeur("bdd", "psw"));
		        String sqlInsert = "INSERT INTO `matchs` (`MatchID`, `Adversaire`, `Lieu`, `Resultat`, `DifScore`, `Date`) VALUES (?, ?, ?, ?, ?,?);";
	            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
	            preparedStatement.setInt(1, MatchID); // Correspond à la 1ère colonne
	            preparedStatement.setString(2, Adversaire); // Correspond à la 2ème colonne
	            preparedStatement.setString(3, Lieu);    // Correspond à la 3ème colonne
	            preparedStatement.setString(4, Resultat); // Correspond à la 4ème colonne
	            preparedStatement.setString(5, DifScore); // Correspond à la 5ème colonne
	            preparedStatement.setString(6, Date); // Correspond à la 6ème colonne
	            int rowsAffected = preparedStatement.executeUpdate();
		         // Vérification si l'insertion a réussi
	            if (rowsAffected > 0) {
	                System.out.println("Le match "+MatchID+" a été ajoutée à la base");
	            } else {
	                System.out.println("Aucune ligne n'a été insérée.");
	            }
			} catch (SQLException ex) {
			 // handle any errors
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("SQLState: " + ex.getSQLState());
			 System.out.println("VendorError: " + ex.getErrorCode());
			}finally {
				 // it is a good idea to release
				 // resources in a finally{} block
				 // in reverse-order of their creation
				 // if they are no-longer needed
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
	}
	
}
