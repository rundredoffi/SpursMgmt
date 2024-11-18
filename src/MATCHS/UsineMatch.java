package MATCHS;
import java.util.List;
import java.util.Scanner;

public class UsineMatch {
	public static void AfficherMatch() {
		List<match> liste = matchCollec.recupMatchs();
		liste.forEach((matchDeListe)->{
			System.out.println(matchDeListe.toString());
		});
	}
	public static void rechercheMatch() {
		matchCollec.remplirListeMatchs();
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match recherché : ");
			String matchId = s.nextLine(); // On récupère le choix de l'utilisateur
			match leMatch = matchCollec.searchMatch(matchId);
			
			System.out.println(leMatch.toString());
		}
	}
	public static void ajouterMatch() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match recherché : ");
			Integer matchId = Integer.parseInt(s.nextLine()); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le nom de l'adversaire : ");
			String adversaire = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir la date du match : ");
			String dateMatch = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le lieu du match : ");
			String lieu = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir le résultat du match : ");
			String resultat = s.nextLine(); // On récupère le choix de l'utilisateur
			
			System.out.println("Saisir l'identifiant du match recherché : ");
			String difScore = s.nextLine(); // On récupère le choix de l'utilisateur
			
			match LeMatch = new match(matchId, adversaire, dateMatch, lieu, resultat, difScore);
			if(matchCollec.ajoutMatch(LeMatch)) {
				System.out.println("Match ajouté !");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void supprMatch() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Saisir l'identifiant du match a supprimer : ");
			String matchID = s.nextLine(); // On récupère l'identifiant du match
			if(MATCHS.matchCollec.supprMatch(matchID)) {
				System.out.println("Le match a été supprimé !");
			}else {
				System.out.println("Erreur lors de la suppression du match");
			}
		}
	}
}
