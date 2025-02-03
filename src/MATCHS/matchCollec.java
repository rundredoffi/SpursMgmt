package MATCHS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class matchCollec {
    private static List<match> liste = new ArrayList<match>();
 
	/*
	 * Méthode pour remplir la liste de Match
	 */
    public static void remplirListeMatchs() {
        // Lecture du fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
		try {
			document = saxBuilder.build("./Matchs.xml");
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // Récupération de la racine
		Element racine = document.getRootElement();
		List<Element> matchElements = racine.getChildren("match");
		    for (Element match : matchElements) {
		        String MatchID = match.getChildText("MatchID");
		        String Adversaire = match.getChildText("Adversaire");
		        String Date = match.getChildText("Date");
		        String Lieu = match.getChildText("Lieu");
		        String Resultat = match.getChildText("Resultat");
		        String DifScore = match.getChildText("DifScore");
		        match nvMatch = new match(Integer.parseInt(MatchID), Adversaire, Date, Lieu, Resultat, DifScore);
		        liste.add(nvMatch);
		}
    }
    /*
     * Méthode qui renvoie les matchs présent dans la liste
     * @return List<match>
     */
    public static List<match> recupMatchs(){
    	return liste;
    }
    /*
     * Méthode qui permet d'ajouter un match à la liste
     * @params match LeMatch
     * @return Bool
     */
    public static Boolean ajoutMatch(match leMatch) {
    	liste.add(leMatch);
    	if(liste.contains(leMatch)) {
    		return true;
    	}
    	return false;
    }
    /*
     * Méthode pour supprimer un match de la liste
     * @params String matchID
     * @return Bool
     */
	public static Boolean supprMatch(String matchID) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	while(!MaVar) {
    		match leMatch = liste.get(i);
    		if(leMatch.getMatchID().equals(Integer.parseInt(matchID))) {
    			liste.remove(leMatch);
    			MaVar = true;
    		}
    		i++;
    	}
    	return MaVar;
    }
    /*
     * Méthode pour cherche un match dans la liste de match
     * @params String MatchID
     * @return match
     */
    public static match searchMatch(String matchID) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	match leMatch = null;
    	while(!MaVar) {
    		leMatch = liste.get(i);
    		if(leMatch.getMatchID().equals(Integer.parseInt(matchID))) {
    			MaVar = true;
    		}
    		i++;
    	}
    	return leMatch;
    }
    
    public static void chargementViaXML(String Chemin) {
        // Lecture du fichier XML
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
		try {
			document = saxBuilder.build(Chemin);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // Récupération de la racine
		Element racine = document.getRootElement();
		List<Element> matchElements = racine.getChildren("match");
		    for (Element match : matchElements) {
		        String MatchID = match.getChildText("MatchID");
		        String Adversaire = match.getChildText("Adversaire");
		        String Date = match.getChildText("Date");
		        String Lieu = match.getChildText("Lieu");
		        String Resultat = match.getChildText("Resultat");
		        String DifScore = match.getChildText("DifScore");
		        match nvMatch = new match(Integer.parseInt(MatchID), Adversaire, Date, Lieu, Resultat, DifScore);
		        liste.add(nvMatch);
		        nvMatch.putInBdd();
		}
    }
}