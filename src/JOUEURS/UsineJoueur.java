package JOUEURS;

import java.util.List;

public class UsineJoueur {
	/**
	 * Méthode pour ajouter un joueur à une collection
	 * @param nom
	 * @param prenom
	 * @param age
	 * @param poid
	 * @param taille
	 * @param photo
	 * @return
	 */
    public static Boolean ajouterJoueur(String nom, String prenom, int age, int poid, int taille, String photo) {
        joueur LeJoueur = new joueur(nom, prenom, age, poid, taille, photo);
        if (joueurCollec.ajoutJoueur(LeJoueur)) {
            System.out.println("Joueur ajouté !");
            return true;
        }
        return false;
    }
    /**
     * Méthode pour rechercher un joueur au sein d'une collection
     * @param nomJoueur
     * @return
     */
    public static String rechercheJoueur(String nomJoueur) {
        joueur leJoueur = joueurCollec.searchJoueur(nomJoueur);
        return leJoueur != null ? leJoueur.toString() : null;
    }
    /**
     * Méthode pour afficher un joueur
     * @return
     */
    public static Object[][] AfficherJoueur() {
        List<joueur> liste = joueurCollec.recupJoueurs();
        Object[][] data = new Object[liste.size()][6];
        for (int i = 0; i < liste.size(); i++) {
            joueur j = liste.get(i);
            data[i][0] = j.getNom();
            data[i][1] = j.getPrenom();
            data[i][2] = j.getAge();
            data[i][3] = j.getPoid();
            data[i][4] = j.getTaille();
            data[i][5] = j.getPhotoPath();
        }
        return data;
    }
}