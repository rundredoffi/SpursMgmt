package APP;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in); // Declare the Scanner here

    public static void afficherMenu() {
        String choix = "";
        JOUEURS.joueurCollec.remplirJoueurs();
        MATCHS.matchCollec.remplirListeMatchs();
        STATS.statsCollec.remplirStats();

        while (!choix.equals("4")) {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n"); // On affiche le menu
            System.out.println("1 - L'équipe \n");
            System.out.println("2 - Les matchs \n");
            System.out.println("3 - Les statistiques \n");
            System.out.println("4 - Quitter l'application \n");

            System.out.println("Saisir votre choix : ");
            choix = scanner.nextLine(); // On récupère le choix de l'utilisateur

            if (!choix.equals("4"))
                switch (choix) {
                    case "1":
                        menuEquipe();
                        break;
                    case "2":
                        menuMatchs();
                        break;
                    case "3":
                        menuStats();
                        break;
                    default:
                        System.out.println("Vous devez saisir un choix compris entre 1 et 3");
                        break;
                }
        }
    }

    private static void menuEquipe() {
        String choix = "";

        while (!choix.equals("3")) {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n");
            System.out.println("1 - Afficher les joueurs \n");
            System.out.println("2 - Rechercher un joueur dans la base \n");
            System.out.println("3 - Quitter le sous-menu \n");

            System.out.println("Saisir votre choix : ");
            choix = scanner.nextLine();

            if (!choix.equals("3"))
                switch (choix) {
                    case "1":
                        JOUEURS.UsineJoueur.AfficherJoueur();
                        break;
                    case "2":
                        JOUEURS.UsineJoueur.rechercheJoueur(choix);
                        break;
                    default:
                        System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                        break;
                }
        }
    }

    private static void menuMatchs() {
        String choix = "";

        while (!choix.equals("3")) {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n");
            System.out.println("1 - Afficher les matchs \n");
            System.out.println("2 - Ajouter les matchs à la base de données \n");
            System.out.println("3 - Quitter le sous-menu \n");

            System.out.println("Saisir votre choix : ");
            choix = scanner.nextLine();

            if (!choix.equals("3"))
                switch (choix) {
                    case "1":
                        MATCHS.UsineMatch.AfficherMatch();
                        break;
                    case "2":
                        MATCHS.UsineMatch.rechercheMatch();
                        break;
                    default:
                        System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                        break;
                }
        }
    }

    private static void menuStats() {
        String choix = "";

        while (!choix.equals("3")) {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n");
            System.out.println("1 - Afficher les stats \n");
            System.out.println("2 - Ajouter les stats à la base de données \n");
            System.out.println("3 - Quitter le sous-menu \n");

            System.out.println("Saisir votre choix : ");
            choix = scanner.nextLine();

            if (!choix.equals("3"))
                switch (choix) {
                    case "1":
                        STATS.UsineStats.AfficherStats();
                        break;
                    case "2":
                        STATS.UsineStats.rechercherStat();
                        break;
                    default:
                        System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                        break;
                }
        }
    }
}