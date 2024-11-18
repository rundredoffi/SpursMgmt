package APP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class SpursManagement {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpursManagement window = new SpursManagement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpursManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 723, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		springLayout.putConstraint(SpringLayout.NORTH, horizontalGlue, 54, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, horizontalGlue, -79, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, horizontalGlue, -371, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, horizontalGlue, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(horizontalGlue);
		
		JLabel lblTitreApp = new JLabel("Spurs Management");
		springLayout.putConstraint(SpringLayout.NORTH, lblTitreApp, 21, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTitreApp, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTitreApp, -6, SpringLayout.NORTH, horizontalGlue);
		springLayout.putConstraint(SpringLayout.EAST, lblTitreApp, 697, SpringLayout.WEST, frame.getContentPane());
		lblTitreApp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTitreApp.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTitreApp);
		
		JLabel lblTitreJoueurs = new JLabel("Gestion des joueurs");
		lblTitreJoueurs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitreJoueurs.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblTitreJoueurs, 6, SpringLayout.SOUTH, horizontalGlue);
		springLayout.putConstraint(SpringLayout.WEST, lblTitreJoueurs, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTitreJoueurs, 697, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblTitreJoueurs);
		
		JButton btnNewButton = new JButton("Afficher les joueurs");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(horizontalGlue, this,"Clicked", 0, null);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, lblTitreJoueurs);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 288, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
	}
}
