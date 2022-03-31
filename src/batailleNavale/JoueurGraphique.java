package batailleNavale;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille {

	// ATTRIBUTS

	private GrilleGraphique grilleTirs;

	// CONSTRUCTEURS

	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
		super(grilleDefense, nom);
		this.grilleTirs = grilleTirs;
	}

	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
		super(grilleDefense);
		this.grilleTirs = grilleTirs;
	}

	// LES METHODES

	public Coordonnee choixAttaque() {
		return this.grilleTirs.getCoordonneeSelectionnee();
	}

	protected void retourDefense(Coordonnee c, int etat) {
		switch (etat) {
		case TOUCHE:
			JOptionPane.showMessageDialog(grilleTirs, "L'enemie a touché un navire après un tir en " + c);
			break;
		case COULE:
			JOptionPane.showMessageDialog(grilleTirs, "L'ennemi a coulé un de vos navires après un tir " + c);
			break;
		case GAMEOVER:
			JOptionPane.showMessageDialog(grilleTirs," GAME OVER ! Toute votre flotte a coulé après l'attaque de l'enemie en " + c);
		}
	}

	protected void retourAttaque(Coordonnee c, int etat) {
		Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
		grilleTirs.colorie(c, couleur);
		switch (etat) {
		case TOUCHE:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
			break;
		case COULE:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
			break;
		case GAMEOVER:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
		}
	}

	public static void main(String[] args) {
		
	// test de defendre et de choisirattaque
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// d'ici

				int[] tabTailles = { 3, 2, 1 };

				FenetreJoueur fen = new FenetreJoueur();
				fen.getGrilleDefense().placementAuto(tabTailles);

				JoueurGraphique cindy = new JoueurGraphique(fen.getGrilleDefense(), fen.getGrilleTirs(), "cindy");
				fen.setVisible(true);

				FenetreJoueur fen2 = new FenetreJoueur();
				fen2.getGrilleDefense().placementAuto(tabTailles);

				JoueurGraphique bob = new JoueurGraphique(fen2.getGrilleDefense(), fen2.getGrilleTirs(), "bob");
				fen2.setVisible(true);

				new Thread(new Runnable() {

					@Override
					public void run() {
						cindy.jouerAvec(bob);

					}

				}).start();

			}

		});

	}

}