package batailleNavale;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Dimension;



public class FenetreJoueur extends JFrame {

	

	private JPanel contentPane;
	private GrilleGraphique grilleTirs;
	private GrilleNavaleGraphique grilleDefense;



// CONSTRUCTEURS DE FENETRE JOUEUR PAR DEFAUT PREND EN COMPTE LE NOM ET LA TAILLE 10 POUR SA GRILLE NAVALE
	
	public FenetreJoueur() {
		this("Nom du joueur", 10);	
	}

// CONSTRUCTEURS DE FENETRE JOUEUR QUI PREND EN COMPTE LE NOM ET LE TAILLE DE SA GRILLE
	
	public FenetreJoueur(String nom, int taille) {
		
		this.grilleTirs = new GrilleGraphique(taille);
		this.grilleDefense = new GrilleNavaleGraphique(taille);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// CARATERISTIQUES FENETRES INTERIEURS
		
		setBounds(100, 100, 800, 600); // les limites : coordonnées(x,y) à partir du coinHautGauche(par défaut = 0,0), largeur, hauteur
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // BORDURE DE LA FENETRE (TOP, LEFT, RIGHT, BOTTOM) 
		contentPane.setLayout(new GridLayout(1, 2)); // DIVISE LA FENETRE EN UNE LIGNE QUI CONTIENT DEUX COLONES
		setContentPane(contentPane);
		
		// DONNER DES NOMS AUX COLONNES
		
		// 1 ER
		JPanel panel1_grilleDeTir = getGrilleTirs();
		panel1_grilleDeTir.setBorder(BorderFactory.createTitledBorder(null, "CHAMP DE BATAILLE", TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION));
		contentPane.add(panel1_grilleDeTir);
		
		//2 EME
		JPanel panel2_grilleDeTir = grilleDefense.getGrilleGraphique();
		panel2_grilleDeTir.setBorder(BorderFactory.createTitledBorder(null,"POSITIONS DEFENDUES", TitledBorder.CENTER,TitledBorder.DEFAULT_JUSTIFICATION));
		contentPane.add(panel2_grilleDeTir);
	


	}

	public GrilleGraphique getGrilleTirs() {
		return grilleTirs;
	}

	public GrilleNavaleGraphique getGrilleDefense() {
		return grilleDefense;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

					FenetreJoueur frame = new FenetreJoueur("Ousseini", 10);
					frame.pack();
					//frame.setSize(600,600);
					frame.setVisible(true);
			}
		});
	}

} 