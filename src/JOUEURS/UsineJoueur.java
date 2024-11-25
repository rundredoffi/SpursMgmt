package JOUEURS;

import java.util.ArrayList;
import java.util.List;

public class UsineJoueur {

    public static Boolean ajouterJoueur(String nom, String prenom, int age, int poid, int taille) {
        joueur LeJoueur = new joueur(nom, prenom, age, poid, taille);
        if (joueurCollec.ajoutJoueur(LeJoueur)) {
            System.out.println("Joueur ajouté !");
            return true;
        }
        return false;
    }

    public static String rechercheJoueur(String nomJoueur) {
        joueur leJoueur = joueurCollec.searchJoueur(nomJoueur);
        return leJoueur != null ? leJoueur.toString() : null;
    }

    public static Object[][] AfficherJoueur() {
        List<joueur> liste = joueurCollec.recupJoueurs();
        
        ArrayList<String> MaSuperListe = new ArrayList<String>();
        liste.forEach(joueurDeListe -> {
        MaSuperListe.add(joueurDeListe.getNom());
        MaSuperListe.add(joueurDeListe.getPrenom());
        MaSuperListe.add(joueurDeListe.getAge().toString());
        MaSuperListe.add(joueurDeListe.getPoid().toString());
        MaSuperListe.add(joueurDeListe.getTaille().toString());
        }
        );
        return MaSuperListe;
    }
}