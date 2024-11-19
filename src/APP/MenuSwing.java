package APP;

import javax.swing.*;
import JOUEURS.UsineJoueur;

public class MenuSwing {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu joueursMenu, matchMenu, statsMenu;
    private JMenuItem insertJoueur, consultJoueur, insertMatch, consultMatch, insertStats, consultStats, quitter;

    public MenuSwing() {
        initialize();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() {
        frame = new JFrame("Spurs Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        menuBar = new JMenuBar();

        // Joueurs Menu
        joueursMenu = new JMenu("Joueurs");
        insertJoueur = new JMenuItem("Insérer");
        consultJoueur = new JMenuItem("Consulter");
        joueursMenu.add(insertJoueur);
        joueursMenu.add(consultJoueur);

        // Match Menu
        matchMenu = new JMenu("Match");
        insertMatch = new JMenuItem("Insérer");
        consultMatch = new JMenuItem("Consulter");
        matchMenu.add(insertMatch);
        matchMenu.add(consultMatch);

        // Stats Menu
        statsMenu = new JMenu("Stats");
        insertStats = new JMenuItem("Insérer");
        consultStats = new JMenuItem("Consulter");
        statsMenu.add(insertStats);
        statsMenu.add(consultStats);

        // Quit Menu
        quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> System.exit(0));

        menuBar.add(joueursMenu);
        menuBar.add(matchMenu);
        menuBar.add(statsMenu);
        menuBar.add(quitter);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        // Add action listeners
        insertJoueur.addActionListener(e -> insertJoueur());
        consultJoueur.addActionListener(e -> consultJoueur());
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
            UsineJoueur.ajouterJoueur(nom, prenom, age, poid, taille);
            JOptionPane.showMessageDialog(frame, "Joueur ajouté !");
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

    private void insertMatch() {
        JOptionPane.showMessageDialog(frame, "Insert Match functionality");
    }

    private void consultMatch() {
        JOptionPane.showMessageDialog(frame, "Consult Match functionality");
    }

    private void insertStats() {
        JOptionPane.showMessageDialog(frame, "Insert Stats functionality");
    }

    private void consultStats() {
        JOptionPane.showMessageDialog(frame, "Consult Stats functionality");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuSwing::new);
    }
}