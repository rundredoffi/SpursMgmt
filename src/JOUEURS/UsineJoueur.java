package JOUEURS;

import java.util.List;
import java.util.Scanner;

import MATCHS.match;
import MATCHS.matchCollec;

public class UsineJoueur {
	public static void ajouterMatch() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'age du joueur : ");
			Integer age = Integer.parseInt(s.nextLine()); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le nom : ");
			String nom = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le poid du joueur : ");
			Integer poid = Integer.parseInt(s.nextLine()); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le prenom : ");
			String prenom = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir la taille du joueur : ");
			Integer taille = Integer.parseInt(s.nextLine()); // On récupère le choix de l'utilisateur
			
			joueur LeJoueur = new joueur(nom, prenom,age, poid, taille);
			if(joueurCollec.ajoutJoueur(LeJoueur)) {
				System.out.println("Joueur ajouté !");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rechercheJoueur() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match recherché : ");
			String nomJoueur = s.nextLine(); // On récupère le choix de l'utilisateur
			joueur leJoueur = joueurCollec.searchJoueur(nomJoueur);
			System.out.println(leJoueur.toString());
		}
	}
	
	public static void AfficherJoueur() {
		List<joueur> liste = joueurCollec.recupJoueurs();
		liste.forEach((joueurDeListe)->{
			System.out.println(joueurDeListe.toString());
		});
	}
}