package JOUEURS;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import APP.parametres;

public class joueurCollec {
	private static List<joueur> liste= new ArrayList<joueur>();
	/**
	 * Méthode pour remplir la liste de joueurs
	 */
	public static void remplirJoueurs() {
	    // Connexion à la base de données
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection(parametres.getValeur("bdd", "cheminbd") + "user=" + parametres.getValeur("bdd", "id") + "&password=" + parametres.getValeur("bdd", "psw") + "&serverTimezone=UTC");
	        String sql = "SELECT nom, prénom, age, poid, taille, photo FROM joueurs";
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prénom");
	            int age = rs.getInt("age");
	            int poid = rs.getInt("poid");
	            int taille = rs.getInt("taille");
	            String photo = rs.getString("photo");
	            joueur nvJoueur = new joueur(nom, prenom, age, poid, taille,photo);
	            liste.add(nvJoueur);
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
	}
	/**
	 * Méthode pour chargé un fichier XML avec les joueurs
	 * @param chemin
	 * @return
	 */
	public static Boolean chargementViaXML(String chemin) {
		// Lecture du fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
		try {
			document = saxBuilder.build("./"+chemin);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Récupération de la racine
		Element racine = document.getRootElement();
		List<Element> joueurElements = racine.getChildren("joueur");
		    for (Element joueur : joueurElements) {
		        String nom = joueur.getChildText("Nom");
		        String prenom = joueur.getChildText("Prénom");
		        String age = joueur.getChildText("Age");
		        String poid = joueur.getChildText("Poid");
		        String taille = joueur.getChildText("Taille");
		        String photo = joueur.getChildText("PhotoPath");
		        joueur nvJoueur = new joueur(nom, prenom, Integer.parseInt(age), Integer.parseInt(poid), Integer.parseInt(taille), photo);
		        liste.add(nvJoueur);
		        nvJoueur.putInBdd();
		};
		return true;
	}
	/*
	 * Méthode pour récupérer la liste des joueurs
	 * @return List<joueur>
	 */
	public static List<joueur> recupJoueurs() {
        return liste;
    }
	/*
     * Méthode pour cherche un match dans la liste de match
     * @params String MatchID
     * @return match
     */
    public static joueur searchJoueur(String nomJoueur) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	joueur leJoueur = null;
    	List<joueur> liste = recupJoueurs();
    	while(!MaVar) {
    		leJoueur = liste.get(i);
    		if(leJoueur.getNom().equals(nomJoueur)) {
    			MaVar = true;
    		}
    		i++;
    	}
    	return leJoueur;
    }
    /*
     * Méthode pour supprimer un joueur de la liste
     * @params String NomJoueur
     * @return Boolean
     */
    public static Boolean supprJoueur(String nomJoueur) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	List<joueur> liste = recupJoueurs();
    	while(!MaVar) {
    		joueur leJoueur = liste.get(i);
    		if(leJoueur.getNom().equals(nomJoueur)) {
    			liste.remove(leJoueur);
    			MaVar = true;
    		}
    		i++;
    	}
    	return MaVar;
    }
    /*
     * Méthode pour ajouter un joueur à la liste
     * @params joueur LeJoueur
     * @return Boolean
     */
    public static Boolean ajoutJoueur(joueur LeJoueur) {
    	List<joueur> liste = recupJoueurs();
    	liste.add(LeJoueur);
    	LeJoueur.putInBdd();
    	if(liste.contains(LeJoueur)) {
    		return true;
    	}
    	return false;
    }
}