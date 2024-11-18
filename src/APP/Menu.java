package APP;
import java.util.Scanner;

public class Menu {
	/*
	 * Afficher le menu de l'application dans la console
	 */
	public static void afficherMenu() 
    {
        String choix ="";

        while (!choix.equals("4")) // Tant que le choix est différent de quitter le programme
        {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n"); // On affiche le menu
            System.out.println("1 - L'équipe \n");
            System.out.println("2 - Les matchs \n");
            System.out.println("3 - Les statistiques \n");
            System.out.println("4 - Quitter l'application \n");

            try (Scanner nombre = new Scanner(System.in)) {
				System.out.println("Saisir votre choix : ");
				choix = nombre.nextLine(); // On récupère le choix de l'utilisateu
				nombre.close();
			}
			if (!choix.equals("4"))
            switch (choix) // On effectue le choix de l'utilisateur
            {
                case "1": // Créer le fichier
                	menuEquipe();
                    break;

                case "2": // Ecrire dans le fichier
                	menuMatchs();
                    break;

                case "3": // Afficher le contenu du fichier
                	menuStats();
                    break;

                default: // Si l'utilisateur entre autre chose que souhaité
                    System.out.println("Vous devez saisir un choix compris entre 1 et 3");
                    break;
            }
			
            
        }
    }
	private static void menuEquipe() 
    {
        String choix ="";

        while (!choix.equals("3")) // Tant que le choix est différent de quitter le programme
        {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n"); // On affiche le menu
            System.out.println("1 - Afficher les joueurs \n");
            System.out.println("2 - Rechercher un joueur dans la base \n");
            System.out.println("3 - Quitter le sous-menu \n");

            try (Scanner nombre = new Scanner(System.in)) {
				System.out.println("Saisir votre choix : ");
				choix = nombre.nextLine(); // On récupère le choix de l'utilisateur
			}
			if (!choix.equals("3"))
            switch (choix) // On effectue le choix de l'utilisateur
            {
                case "1": // Afficher les joueurs
                    JOUEURS.UsineJoueur.AfficherJoueur();
                    break;

                case "2": // Envoie des joueurs dans la base
                    JOUEURS.UsineJoueur.rechercheJoueur();
                    break;

                default: // Si l'utilisateur entre autre chose que souhaité
                    System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                    break;
            }
	        afficherMenu();
        }
    }
	private static void menuMatchs() 
    {
        String choix ="";

        while (!choix.equals("3")) // Tant que le choix est différent de quitter le programme
        {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n"); // On affiche le menu
            System.out.println("1 - Afficher les matchs \n");
            System.out.println("2 - Ajouter les matchs à la base de données \n");
            System.out.println("3 - Quitter le sous-menu \n");


        	try (Scanner nombre = new Scanner(System.in)) {
				System.out.println("Saisir votre choix : ");
				choix = nombre.nextLine(); // On récupère le choix de l'utilisateur
			}
			if (!choix.equals("3"))
            switch (choix) // On effectue le choix de l'utilisateur
            {
                case "1": // Afficher les matchs
                    MATCHS.UsineMatch.AfficherMatch();
                    break;

                case "2": // Permet de rechercher un match dans la base
                    MATCHS.UsineMatch.rechercheMatch();
                    break;

                default: // Si l'utilisateur entre autre chose que souhaité
                    System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                    break;
            }
        }
        afficherMenu();
    }
	private static void menuStats() 
    {
        String choix ="";
        
		
        while (!choix.equals("3")) // Tant que le choix est différent de quitter le programme
        {
            System.out.println("\nMenu de l'application de gestion des Spurs : \n"); // On affiche le menu
            System.out.println("1 - Afficher les stats \n");
            System.out.println("2 - Ajouter les stats à la base de données \n");
            System.out.println("3 - Quitter le sous-menu \n");
            try (Scanner nombre = new Scanner(System.in)) {
				System.out.println("Saisir votre choix : ");
				choix = nombre.nextLine(); // On récupère le choix de l'utilisateur
			}
			if (!choix.equals("3"))
            switch (choix) // On effectue le choix de l'utilisateur
            {
                case "1": // Afficher les stats
                   STATS.UsineStats.AfficherStats();
                    break;

                case "2": // Rechercher des stats dans la base
                    STATS.UsineStats.rechercherStat();
                    break;

                default: // Si l'utilisateur entre autre chose que souhaité
                    System.out.println("Vous devez saisir un choix compris entre 1 et 2");
                    break;
            }
        }
        afficherMenu();
    }
}
