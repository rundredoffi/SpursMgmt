package STATS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP.parametres;
import JOUEURS.joueur;
import MATCHS.match;

public class statcards {
	private match Match;
	private joueur Joueur;
	private String TDJeu;
	private String Points;
	private Integer Rebonds;
	private Integer Passe_D;
	public statcards(match match, joueur joueur, String tDJeu, String points, Integer rebonds, Integer passe_D) {
		super();
		Match = match;
		Joueur = joueur;
		TDJeu = tDJeu;
		Points = points;
		Rebonds = rebonds;
		Passe_D = passe_D;
	}
	public match getMatch() {
		return Match;
	}
	public void setMatch(match match) {
		Match = match;
	}
	public joueur getJoueur() {
		return Joueur;
	}
	public void setJoueur(joueur joueur) {
		Joueur = joueur;
	}
	public String getTDJeu() {
		return TDJeu;
	}
	public void setTDJeu(String tDJeu) {
		TDJeu = tDJeu;
	}
	public String getPoints() {
		return Points;
	}
	public void setPoints(String points) {
		Points = points;
	}
	public Integer getRebonds() {
		return Rebonds;
	}
	public void setRebonds(Integer rebonds) {
		Rebonds = rebonds;
	}
	public Integer getPasse_D() {
		return Passe_D;
	}
	public void setPasse_D(Integer passe_D) {
		Passe_D = passe_D;
	}
	/*
	 * Méthode utilisé pour afficher l'objet statcards dans la console.
	 */
	@Override
	public String toString() {
		return "Match : " + Match.getAdversaire() + "\n Joueur : " + Joueur.getNom() + "\n Temps de jeu : " + TDJeu + "\n Points : " + Points
				+ "\n Rebonds : " + Rebonds + "\n Passe_D : " + Passe_D+ "\n";
	}
	/*
	 * Méthode void, pour envoyé le match dans la base de données.
	 */
	public void putInBdd() { 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			 conn = DriverManager.getConnection(parametres.getValeur("bdd", "cheminbd") + "user="+parametres.getValeur("bdd", "id")+"&password="+parametres.getValeur("bdd", "psw"));
		        String sqlInsert = "INSERT INTO `statcards` (`Nom`, `MatchID`, `TDJeu`, `Points`, `Rebonds`, `Passe_D`) VALUES (?, ?, ?, ?, ?,?);";
	            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
	            preparedStatement.setString(1, Joueur.getNom()); // Correspond à la 1ère colonne
	            preparedStatement.setInt(2, Match.getMatchID()); // Correspond à la 2ème colonne
	            preparedStatement.setString(3, TDJeu);    // Correspond à la 3ème colonne
	            preparedStatement.setString(4, Points); // Correspond à la 4ème colonne
	            preparedStatement.setInt(5, Rebonds); // Correspond à la 5ème colonne
	            preparedStatement.setInt(6, Passe_D); // Correspond à la 6ème colonne
	            int rowsAffected = preparedStatement.executeUpdate();
		         // Vérification si l'insertion a réussi
	            if (rowsAffected > 0) {
	                System.out.println("La statistique de "+Joueur.getNom()+" a été ajoutée à la base"); // Message de confirmation
	            } else {
	                System.out.println("Aucune statistique n'a été insérée.");
	            }
			} catch (SQLException ex) {
			 // handle any errors
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("SQLState: " + ex.getSQLState());
			 System.out.println("VendorError: " + ex.getErrorCode());
			}finally { // Bloc utilisé pour éviter les erreurs de SQL connector, voir : https://downloads.mysql.com/docs/connector-j-5.1-en.pdf
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
