package STATS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import manip;
import JOUEURS.joueur;
import MATCHS.match;

public class statsCollec {
	private static joueur LeJoueur = null; // Variable utilisé dans la méthode "listeStats", pour récupérer le joueur concerné
	private static match LeMatch = null; // Variable utilisé dans la méthode "listeStats", pour récupérer le match concerné
	private static List<statcards> liste = new ArrayList<statcards>();
	/*
	 * Méthode pour récupérer toutes les stats
	 * @return List<statcards> Liste de statisque
	 */
	public static void remplirStats() {
	    List<joueur> listeJoueurs = JOUEURS.joueurCollec.recupJoueurs();
	    List<match> ListeMatchs = MATCHS.matchCollec.recupMatchs();
	    String[] ListeFichier = {"./Heat.xml","./Rockets.xml","./Thunder.xml"}; // Liste des fichier de stats
	    
	    for(int i = 0; i < ListeFichier.length;i++ ){
	    	// Lecture du fichier XML
		    SAXBuilder saxBuilder = new SAXBuilder();
		    Document document = null;
			try {
				document = saxBuilder.build(ListeFichier[i]);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Récupération de la racine
			Element racine = document.getRootElement();
			List<Element> statElements = racine.getChildren("Stats");
			
			for (Element statcards : statElements) {
			        String Nom = statcards.getChildText("Nom");
			        String MatchID = statcards.getChildText("MatchID");
			        String TDJeu = statcards.getChildText("TDJeu");
			        String Points = statcards.getChildText("Points");
			        String Rebonds = statcards.getChildText("Rebonds");
			        String Passe_D = statcards.getChildText("Passe_D");
			        listeJoueurs.forEach((joueurdeListe) -> {
						if(joueurdeListe.getNom().equals(Nom)) {
							LeJoueur = joueurdeListe;
						};
						});	
					ListeMatchs.forEach((matchdeListe) -> {
						if(matchdeListe.getMatchID().equals(Integer.parseInt(MatchID))) {
							LeMatch = matchdeListe;
						};
						});
					statcards nvStat = new statcards(LeMatch, LeJoueur, TDJeu, Points, Integer.parseInt(Rebonds), Integer.parseInt(Passe_D));
			        
					liste.add(nvStat);
				}
	    }
    }
	public static List<statcards> RecupStats(){
		return liste;
	}
	
	/*
     * Méthode pour cherche une statisique dans la liste de stats
     * @params match
     * @params joueur
     * @return statcards
     */
    public static statcards searchStats(match LeMatch,joueur LeJoueur) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	statcards laStat = null;
    	while(!MaVar) {
    		laStat = liste.get(i);
    		if(laStat.getJoueur().equals(LeJoueur) && laStat.getMatch().equals(LeMatch)) {
    			MaVar = true;
    		}
    		i++;
    	}
    	return laStat;
    }
    /*
     * Méthode pour supprimer les statistiques d'un match de la liste
     * @params match
     * @params joueur
     * @return Boolean
     */
    public static Boolean supprStat(match LeMatch,joueur LeJoueur) {
    	Boolean MaVar = false;
    	Integer i = 0;
    	while(!MaVar) {
    		statcards laStat = liste.get(i);
    		if(laStat.getJoueur().equals(LeJoueur) && laStat.getMatch().equals(LeMatch)) {
    			liste.remove(laStat);
    			MaVar = true;
    		}
    		i++;
    	}
    	return MaVar;
    }
}
