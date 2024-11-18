package STATS;

import java.util.List;
import java.util.Scanner;
import MATCHS.*;
import JOUEURS.*;


public class UsineStats {
	
	public static void AfficherStats() {
		List<statcards> liste = statsCollec.RecupStats();
		liste.forEach((statsDeListe)->{
			System.out.println(statsDeListe.toString());
		});
	}
	public static void rechercherStat() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match recherché : ");
			String matchId = s.nextLine(); // On récupère le choix de l'utilisateur
			match leMatch = matchCollec.searchMatch(matchId);
			System.out.println("Saisir le nom du joueur recherché : ");
			String nomJoueur = s.nextLine(); // On récupère le choix de l'utilisateur
			joueur LeJoueur = joueurCollec.searchJoueur(nomJoueur);
			statcards LaStat = statsCollec.searchStats(leMatch, LeJoueur);
			System.out.println(LaStat.toString());
		}
	}
	public static void supprStat() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match recherché : ");
			String matchId = s.nextLine(); // On récupère le choix de l'utilisateur
			match leMatch = matchCollec.searchMatch(matchId);
			System.out.println("Saisir le nom du joueur recherché : ");
			String nomJoueur = s.nextLine(); // On récupère le choix de l'utilisateur
			joueur LeJoueur = joueurCollec.searchJoueur(nomJoueur);
			if(statsCollec.supprStat(leMatch, LeJoueur)){
				System.out.println("La statistique de "+LeJoueur.getNom()+" contre "+leMatch.getAdversaire()+" a bien été supprimé");
			}
		}
	}
}
