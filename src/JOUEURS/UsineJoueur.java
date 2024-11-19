package JOUEURS;

import java.util.List;

public class UsineJoueur {

    public static void ajouterJoueur(String nom, String prenom, int age, int poid, int taille) {
        joueur LeJoueur = new joueur(nom, prenom, age, poid, taille);
        if (joueurCollec.ajoutJoueur(LeJoueur)) {
            System.out.println("Joueur ajouté !");
        }
    }

    public static String rechercheJoueur(String nomJoueur) {
        joueur leJoueur = joueurCollec.searchJoueur(nomJoueur);
        return leJoueur != null ? leJoueur.toString() : null;
    }

    public static String AfficherJoueur() {
        StringBuilder sb = new StringBuilder();
        List<joueur> liste = joueurCollec.recupJoueurs();
        liste.forEach(joueurDeListe -> sb.append(joueurDeListe.toString()).append("\n"));
        return sb.toString();
    }
}