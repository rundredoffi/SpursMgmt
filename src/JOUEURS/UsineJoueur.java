package JOUEURS;

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
        Object[][] data = new Object[liste.size()][5];
        for (int i = 0; i < liste.size(); i++) {
            joueur j = liste.get(i);
            data[i][0] = j.getNom();
            data[i][1] = j.getPrenom();
            data[i][2] = j.getAge();
            data[i][3] = j.getPoid();
            data[i][4] = j.getTaille();
        }
        return data;
    }
}