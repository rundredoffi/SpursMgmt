package APP;

import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import JOUEURS.*;
import java.awt.BorderLayout;
import java.awt.Font;

import MATCHS.*;
import STATS.*;

public class MenuSwing {	
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu joueursMenu, matchMenu, statsMenu;
    private JMenuItem insertJoueur, consultJoueur, joueurViaXML, insertMatch, consultMatch, matchViaXML, insertStats, consultStats, statsViaXML, quitter;
    private JLabel lblTitreApp;

    public MenuSwing() {
    	initialize();
        
    }
    /**
	 * @wbp.parser.entryPoint
	 */
    private void initialize() {
        try {
            parametres.loadFichierIni("./paramAppli.ini");
            joueurCollec.remplirJoueurs();
            matchCollec.remplirListeMatchs();
            statsCollec.remplirStats();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        frame = new JFrame("Spurs Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

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
        insertStats = new JMenuItem("Insérer une statistique");
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
        joueurViaXML.addActionListener(e -> insertJoueurXML());
        insertMatch.addActionListener(e -> insertMatch());
        consultMatch.addActionListener(e -> consultMatch());
        matchViaXML.addActionListener(e-> insertMatchXML());
        insertStats.addActionListener(e -> insertStats());
        consultStats.addActionListener(e -> consultStats());
    }
/*
 *  Interface d'insertion d'un joueur dans la base
 */
    private void insertJoueur() {
        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField poidField = new JTextField();
        JTextField tailleField = new JTextField();
        JTextField photoPath = new JTextField();

        Object[] message = {
            "Nom:", nomField,
            "Prénom:", prenomField,
            "Âge:", ageField,
            "Poids:", poidField,
            "Taille:", tailleField,
            "Lien vers la photo", photoPath,
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Insérer un joueur", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                int poid = Integer.parseInt(poidField.getText());
                int taille = Integer.parseInt(tailleField.getText());
                String PathPhoto = photoPath.getText();

                if (UsineJoueur.ajouterJoueur(nom, prenom, age, poid, taille, PathPhoto)) {
                    JOptionPane.showMessageDialog(frame, "Joueur ajouté !");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur durant l'ajout :/");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Erreur: Veuillez entrer des valeurs numériques valides pour l'âge, le poids et la taille.");
            }
        }
    }
/*
 * Permet d'afficher la liste des joueurs chargés
 */
    private void consultJoueur() {
        Object[][] data = UsineJoueur.AfficherJoueur();
        String[] columnNames = {"Nom", "Prénom", "Âge", "Poids", "Taille", "Photo"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column){  
                return false;  
            }
        };

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    String nom = (String) table.getValueAt(selectedRow, 0);
                    String prenom = (String) table.getValueAt(selectedRow, 1);
                    int age = (Integer) table.getValueAt(selectedRow, 2);
                    int poid = (Integer) table.getValueAt(selectedRow, 3);
                    int taille = (Integer) table.getValueAt(selectedRow, 4);
                    String photoPath = (String) table.getValueAt(selectedRow, 5);

                    showJoueurDetails(nom, prenom, age, poid, taille, photoPath);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame tableFrame = new JFrame("Liste des joueurs");
        tableFrame.setSize(800, 400);
        tableFrame.add(scrollPane);
        tableFrame.setVisible(true);
    }
/*
 * Permet l'affichage dans une fenêtre de détails des joueurs
 */
    private void showJoueurDetails(String nom, String prenom, int age, int poid, int taille, String photoPath) {
        JFrame detailsFrame = new JFrame("Détails de "+nom+" " + prenom);
        detailsFrame.setSize(500, 400);
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText("Nom: " + nom + "\n" +
                            "Prénom: " + prenom + "\n" +
                            "Âge: " + age + "\n" +
                            "Poids: " + poid + "\n" +
                            "Taille: " + taille);
        
        // Ajouter la photo du joueur
        JLabel photoLabel = new JLabel();
        if (photoPath != null && !photoPath.isEmpty()) {
            File imgFile = new File(photoPath);
            System.out.println("Image path: " + imgFile.getAbsolutePath());
            if (imgFile.exists()) {
                ImageIcon photoIcon = new ImageIcon(imgFile.getAbsolutePath());
                photoLabel.setIcon(photoIcon);
            } else {
                photoLabel.setText("Image file not found");
            }
        } else {
            photoLabel.setText("Pas de photo disponible");
        }

        detailsFrame.setLayout(new BorderLayout());
        detailsFrame.add(detailsArea, BorderLayout.CENTER);
        detailsFrame.add(photoLabel, BorderLayout.NORTH);
        detailsFrame.setVisible(true);
    }
/*
 * Méthode chargement de joueur via un fichier XML.
 */
    private void insertJoueurXML() {
        String cheminFichier = JOptionPane.showInputDialog(frame, "Saisir le chemin vers le fichier XML : ");
        File Fichier = new File(cheminFichier);
        
        if (Fichier.exists()) {
            joueurCollec.chargementViaXML(cheminFichier);
        }else {
            JOptionPane.showMessageDialog(frame, "Erreur: Le fichier est introuvable ou indisponible. Veuillez vérifier le chemin.");
        }
    }
    //@TODO : Insertion d'nu match manuellement
    private void insertMatch() {
        JOptionPane.showMessageDialog(frame, "Match : Ajout de match");
    }
   //@TODO : Insertiond de match via fichier XML
    private void insertMatchXML() {
    	String cheminFichier = JOptionPane.showInputDialog(frame, "Saisir le chemin vers le fichier XML : ");
        File Fichier = new File(cheminFichier);
        
        if (Fichier.exists()) {
            matchCollec.chargementViaXML(cheminFichier);
        }else {
            JOptionPane.showMessageDialog(frame, "Erreur: Le fichier est introuvable ou indisponible. Veuillez vérifier le chemin.");
        }
    }
    //@TODO : Consultation détails d'un match
    private void consultMatch() {
        JOptionPane.showMessageDialog(frame, "Match : Consultation de match");
    }
    //@TODO : Insertion manuelle de stats
    private void insertStats() {
        JOptionPane.showMessageDialog(frame, "Stats : Ajout de stats");
    }
    //@TODO : Consultation de statistiques
    private void consultStats() {
        JOptionPane.showMessageDialog(frame, "Stats : Consultation de stats");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuSwing::new);
    }
}