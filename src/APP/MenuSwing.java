package APP;

import java.io.IOException;

import javax.swing.*;
import JOUEURS.*;
import MATCHS.*;
import STATS.*;
import java.awt.BorderLayout;
import java.awt.Font;


public class MenuSwing {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu joueursMenu, matchMenu, statsMenu;
    private JMenuItem insertJoueur, consultJoueur,joueurViaXML, insertMatch, consultMatch,matchViaXML, insertStats, consultStats, statsViaXML,quitter;
    private JLabel lblTitreApp;

    public MenuSwing() {
        initialize();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() {
    	try {
			APP.parametres.loadFichierIni("./paramAppli.ini");
			joueurCollec.remplirJoueurs();
			matchCollec.remplirListeMatchs();
			statsCollec.remplirStats();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        frame = new JFrame("Spurs Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        menuBar = new JMenuBar();

        // Joueurs Menu
        joueursMenu = new JMenu("Joueurs");
        insertJoueur = new JMenuItem("Insérer un joueur");
        consultJoueur = new JMenuItem("Consulter les joueurs");
        joueurViaXML = new JMenuItem("Ajouter des joueurs via XML");
        joueursMenu.add(insertJoueur);
        joueursMenu.add(consultJoueur);
        joueursMenu.add(joueurViaXML);

        // Match Menu
        matchMenu = new JMenu("Match");
        insertMatch = new JMenuItem("Insérer un match");
        consultMatch = new JMenuItem("Consulter les matchs");
        matchViaXML = new JMenuItem("Ajouter un match via XML");
        matchMenu.add(insertMatch);
        matchMenu.add(consultMatch);
        matchMenu.add(matchViaXML);

        // Stats Menu
        statsMenu = new JMenu("Stats");
        insertStats = new JMenuItem("Insérer une statisitique");
        consultStats = new JMenuItem("Consulter les statistiques");
        statsViaXML = new JMenuItem("Ajouter des stats via XML");
        statsMenu.add(insertStats);
        statsMenu.add(consultStats);
        statsMenu.add(statsViaXML);
        // Quit Menu
        quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> System.exit(0));

        menuBar.add(joueursMenu);
        menuBar.add(matchMenu);
        menuBar.add(statsMenu);
        menuBar.add(quitter);

        frame.setJMenuBar(menuBar);
        
        lblTitreApp = new JLabel("Spurs Management");
        lblTitreApp.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitreApp.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        frame.getContentPane().add(lblTitreApp, BorderLayout.NORTH);
        frame.setVisible(true);

        // Add action listeners
        insertJoueur.addActionListener(e -> insertJoueur());
        consultJoueur.addActionListener(e -> consultJoueur());
        joueurViaXML.addActionListener(e->insertJoueurXML());
        insertMatch.addActionListener(e -> insertMatch());
        consultMatch.addActionListener(e -> consultMatch());
        insertStats.addActionListener(e -> insertStats());
        consultStats.addActionListener(e -> consultStats());
    }

    private void insertJoueur() {
        String nom = JOptionPane.showInputDialog(frame, "Saisir le nom du joueur :");
        String prenom = JOptionPane.showInputDialog(frame, "Saisir le prénom du joueur :");
        String ageStr = JOptionPane.showInputDialog(frame, "Saisir l'âge du joueur :");
        String poidStr = JOptionPane.showInputDialog(frame, "Saisir le poids du joueur :");
        String tailleStr = JOptionPane.showInputDialog(frame, "Saisir la taille du joueur :");

        try {
            int age = Integer.parseInt(ageStr);
            int poid = Integer.parseInt(poidStr);
            int taille = Integer.parseInt(tailleStr);
            if(UsineJoueur.ajouterJoueur(nom, prenom, age, poid, taille)) {
                JOptionPane.showMessageDialog(frame, "Joueur ajouté !");
            }else {
            	JOptionPane.showMessageDialog(frame, "Erreur durant l'ajout :/");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Erreur: Veuillez entrer des valeurs numériques valides pour l'âge, le poids et la taille.");
        }
    }

    private void consultJoueur() {
        JTextArea textArea = new JTextArea(20, 30);
        textArea.setText(UsineJoueur.AfficherJoueur());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Liste des joueurs", JOptionPane.INFORMATION_MESSAGE);
    }

    private void insertJoueurXML() {
    	String cheminFichier = JOptionPane.showInputDialog(frame, "Saisir le chemin vers le fichier XML : ");
    	joueurCollec.chargementViaXML(cheminFichier);
    }
    
    private void insertMatch() {
        JOptionPane.showMessageDialog(frame, "Match : Ajout de match");
    }

    private void consultMatch() {
        JOptionPane.showMessageDialog(frame, "Match : Consultation de match");
    }

    private void insertStats() {
        JOptionPane.showMessageDialog(frame, "Stats : Ajout de stats");
    }

    private void consultStats() {
        JOptionPane.showMessageDialog(frame, "Stats : consultation de stats");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuSwing::new);
    }
}