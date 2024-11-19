package JOUEURS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class joueurCollec {
	private static List<joueur> liste= new ArrayList<joueur>();
	/*
	 * Méthode pour remplir la liste de joueurs
	 */
	public static void remplirJoueurs() {
		// Lecture du fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
		try {
			document = saxBuilder.build("./joueurs.xml");
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
		        joueur nvJoueur = new joueur(nom, prenom, Integer.parseInt(age), Integer.parseInt(poid), Integer.parseInt(taille));
		        liste.add(nvJoueur);
		}
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