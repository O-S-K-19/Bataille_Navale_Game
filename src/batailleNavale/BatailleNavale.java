package batailleNavale;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BatailleNavale {
	private Joueur joueur1, joueur2;
	private int tailleGrille;
	private JFrame frmBatailleNavale;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup_Joueur1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_Joueur2 = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatailleNavale window = new BatailleNavale();
					window.frmBatailleNavale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void demarrerPartie() {
		new Thread() {
			public void run() {
				if((joueur1 == null) || (joueur2 == null)) {
					JOptionPane.showMessageDialog(frmBatailleNavale, " Veuillez donner un nom de joueur \n et choisir un type ");	
					return;
				}
				joueur1.jouerAvec(joueur2);
			}
		}.start();
	}

	public BatailleNavale() {
		initialize();
	}

	private void initialize() {
		frmBatailleNavale = new JFrame();
		frmBatailleNavale.setResizable(true);
		frmBatailleNavale.setTitle("Bataille Navale");
		frmBatailleNavale.setBounds(500, 150, 640, 480);
		frmBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBatailleNavale.getContentPane().setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 1, 250, 33);
		panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmBatailleNavale.getContentPane().add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		JLabel lblTailleDeGrille = new JLabel("Taille de grille : ");
		lblTailleDeGrille.setFont(new Font("Dialog", Font.BOLD, 13));
		panel1.add(lblTailleDeGrille);
		textField = new JTextField();
		panel1.add(textField);
		textField.setColumns(10);

		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 41, 250, 320);
		frmBatailleNavale.getContentPane().add(panel2);
		panel2.setLayout(new GridLayout(2,0,0,0));
		JPanel panel_Joueur1 = new JPanel();
		
		
		panel_Joueur1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Joueur 1",
		TitledBorder.LEADING, TitledBorder.TOP, null));
		panel2.add(panel_Joueur1);
		panel_Joueur1.setLayout(new GridLayout(5, 0, 0, 0));
		JPanel panel = new JPanel();
		panel_Joueur1.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 13));
		panel.add(lblNom);
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		JRadioButton rdbtJ1Graphique = new JRadioButton("Joueur graphique");
		buttonGroup_Joueur1.add(rdbtJ1Graphique);
		rdbtJ1Graphique.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(rdbtJ1Graphique);
		JRadioButton rdbtJ1Texte = new JRadioButton("Joueur Texte");
		buttonGroup_Joueur1.add(rdbtJ1Texte);
		rdbtJ1Texte.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(rdbtJ1Texte);
		JRadioButton rdbtJ1Auto = new JRadioButton("Joueur Auto");
		buttonGroup_Joueur1.add(rdbtJ1Auto);
		rdbtJ1Auto.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(rdbtJ1Auto);
		JPanel panel_Joueur2 = new JPanel();
		
		
		
		panel_Joueur2.setBorder(BorderFactory.createTitledBorder("Joueur 2"));
		panel2.add(panel_Joueur2);
		panel_Joueur2.setLayout(new GridLayout(5, 0, 0, 0));
		JPanel panel_1 = new JPanel();
		panel_Joueur2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		JLabel lblNewLabel = new JLabel("Nom : ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_1.add(lblNewLabel);
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JRadioButton rdbtJ2Graphique = new JRadioButton("Joueur graphique");
		rdbtJ2Graphique.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(rdbtJ2Graphique);
		panel_Joueur2.add(rdbtJ2Graphique);

		JRadioButton rdbtJ2Texte = new JRadioButton("Joueur Texte");
		rdbtJ2Texte.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(rdbtJ2Texte);
		panel_Joueur2.add(rdbtJ2Texte);

		JRadioButton rdbtJ2Auto = new JRadioButton("Joueur Auto");
		rdbtJ2Auto.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(rdbtJ2Auto);
		panel_Joueur2.add(rdbtJ2Auto);

		JPanel panel3 = new JPanel();
		panel3.setBounds(20, 360, 280, 53);
		frmBatailleNavale.getContentPane().add(panel3);
		panel3.setLayout(null);
		JButton btnLancerLaPartie = new JButton("Lancer la partie");
		btnLancerLaPartie.addActionListener(new ActionListener() {

//////////  Debut Click Bouton 

			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {

						try {
							tailleGrille = Integer.parseInt(textField.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(frmBatailleNavale,
									"Il faut entrer une taille entre  5 et 26");
							return;
						}
						if ((tailleGrille < 5) || (tailleGrille > 26))
						{
							JOptionPane.showMessageDialog(frmBatailleNavale, "Il faut que ca soit entre 5 et 26 ");
							return;
						}
						int tabNav[] = { 2, 3, 4 };
						if (tailleGrille < 6) {
							 tabNav = new int[] { 2, 3};
						}
						

						if (rdbtJ1Graphique.isSelected()) {
							
							FenetreJoueur fen = new FenetreJoueur(textField_1.getText(), tailleGrille);
							fen.getGrilleDefense().placementAuto(tabNav);
							fen.pack();
							if(((textField_1.getText().compareTo("")==0)))
								fen.setTitle("Jeu Bataille Navale : Joueur 1");
							else
								fen.setTitle("Jeu Bataille Navale : " + textField_1.getText());
							fen.setVisible(true);
							joueur1 = new JoueurGraphique(fen.getGrilleDefense(), fen.getGrilleTirs(),
									textField_1.getText());
						} else if (rdbtJ1Texte.isSelected()) {
// System.out.println("bouton Joueur text");
							GrilleNavale grilleJ1 = new GrilleNavale(tailleGrille, tabNav);
							grilleJ1.placementAuto(tabNav);
							if(((textField_1.getText().compareTo("") == 0)))
								joueur1 = new JoueurTexte(grilleJ1, "Joueur 1");
							else
								joueur1 = new JoueurTexte(grilleJ1, textField_1.getText());
						} else if (rdbtJ1Auto.isSelected()) {
							System.out.println("bouton Joueur auto");
							/*
							 * FenetreJoueur fen = new FenetreJoueur(textField_1.getText(), tailleGrille);
							 * fen.getGrilleDefense().placementAuto(tabNav); fen.pack();
							 * fen.setTitle("Jeu Bataille Navale: " + textField_1.getText());
							 * fen.setVisible(false);
							 */
							GrilleNavale g1 = new GrilleNavale(tailleGrille, tabNav);
							g1.placementAuto(tabNav);
							if(((textField_1.getText().compareTo("")==0)))
								joueur1 = new JoueurAuto(g1, "JoueurAuto1");
							else
								joueur1 = new JoueurAuto(g1, textField_1.getText());
							
						}

						if (rdbtJ2Graphique.isSelected()) {
// System.out.println("bouton Joueur graphique1");
							FenetreJoueur fen2 = new FenetreJoueur(textField_2.getText(), tailleGrille);
							fen2.getGrilleTirs().setClicActive(false);
							fen2.getGrilleDefense().placementAuto(tabNav);
							fen2.pack();
							if(((textField_2.getText().compareTo("")==0)))
								fen2.setTitle("Jeu Bataille Navale : Joueur 2");
							else
								fen2.setTitle("Jeu Bataille Navale : " + textField_2.getText());
							fen2.setVisible(true);
							joueur2 = new JoueurGraphique(fen2.getGrilleDefense(), fen2.getGrilleTirs(),
									textField_2.getText());
						} else if (rdbtJ2Texte.isSelected()) {
// System.out.println("bouton Joueur text1");
							GrilleNavale grilleJ2 = new GrilleNavale(tailleGrille, tabNav);
							grilleJ2.placementAuto(tabNav);
							if(((textField_1.getText().compareTo("")==0)))
								joueur2 = new JoueurTexte(grilleJ2, "Joueur 2");
							else
								joueur2 = new JoueurTexte(grilleJ2, textField_2.getText());
							
						}
// ################# JoueurAuto2 ####################################
						else if (rdbtJ2Auto.isSelected()) {
// System.out.println("bouton Joueur auto1");
							GrilleNavale g2 = new GrilleNavale(tailleGrille, tabNav);
							g2.placementAuto(tabNav);
							if(((textField_1.getText().compareTo("")==0)))
								joueur2 = new JoueurAuto(g2, "JoueurAuto2");
							else
								joueur2 = new JoueurAuto(g2, textField_2.getText());
							
						}

// ################################ DEMARRER PARTIE ############################
						demarrerPartie();
					}
				});
			}
		});
		btnLancerLaPartie.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLancerLaPartie.setBounds(27, 12, 135, 27);
		panel3.add(btnLancerLaPartie);
	}
}