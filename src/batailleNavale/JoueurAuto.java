package batailleNavale;

import java.util.Scanner;

public class JoueurAuto extends JoueurAvecGrille {

/*------------------- CONSTRUCTEURS -----------------------------------------------------------------------------------------------------------------*/

	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
	}

	public JoueurAuto(GrilleNavale g) {
		super(g);

	}

/*------------------- LES METHODES -----------------------------------------------------------------------------------------------------------------*/

	
	
/* retourAttaque :
	 Definition ***:Cette méthode est invoquée sur le joueur attaquant à la fin
	 d’un tour de jeu. c est la coordonnée à laquelle le tir a eu lieu et etat le
	 résultat de l'attaque. etat ne peut être que TOUCHE, COULE, A_L_EAU, ou
	 GAMEOVER 
 */
	
	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println("\nretourAttaque :");
		if (etat == TOUCHE)
			System.out.println(this.getNom() +" >>>>> a touché la partie " + "\""+c + "\" " + "d'un des navires enemies");
		else if (etat == COULE)
			System.out.println(this.getNom() +" >>>>> a coulé un navire enemie apres l'attaque en " + "\""+ c + "\" ");
			//System.out.println("un bateau en moins");
		else if (etat == A_L_EAU)
			System.out.println(this.getNom() +" >>>>> a effectuer un tir dans l'eau localisé en " + "\""+ c + "\" ");
			//System.out.println("presque");
		else
			System.out.println(this.getNom() +" >>>>> BRAVO ! Vous etes trés fort, Toute la flotte enemies a coulé apres votre attaque en " + "\"" + c + "\" ");
	}

/*
	 retourDefense :
	 Definition ***:Cette méthode est invoquée sur le joueur defenseur à la fin
	 d’un tour de jeu. c est la coordonnée à laquelle le tir a eu lieu et etat le
	 résultat de l'attaque. etat ne peut être que TOUCHE, COULE, A_L_EAU, ou
	 GAMEOVER 
 */	
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println("\nretourDefense :");
		if (etat == TOUCHE)
			System.out.println(this.getNom()+" >>>>> la partie d'un de vos navires est touché en " + "\"" + c + "\" ");
			//System.out.println("Un de vos bateau a été touché");
		else if (etat == COULE)
			System.out.println(this.getNom()+" >>>>> vous avez un navire en moins apres l'attaque enemie en " + "\"" + c + "\" ");
			//System.out.println("vous avez un bateau en moins haha");
		else if (etat == A_L_EAU)
			System.out.println(this.getNom()+" >>>>> l'ennemie vous a raté par un tir dans l'eau localisé en " + "\"" + c + "\" ");
			//System.out.println("rien ne s'est passé");
		else
			System.out.println(this.getNom()+" >>>>> GAME OVER ! Toute votre flotte a coulé apres l'attaque enemie en "+ "\""+c + "\" ");
			//System.out.println("dommage vous avez perdu");
	}

	public Coordonnee choixAttaque() {
		/*
		 * Commentaire *** : verifier les coordonnées rentrer par l'utilisateur.
		 * Definition ***: Cette méthode est invoquée sur le joueur attaquant au début
		 * d’un tour de jeu. Elle retourne la coordonnée cible du tir effectué par
		 * l’attaquant
		 */

		//Scanner sc = new Scanner(System.in);
		Coordonnee c;
		while (true) {
			/*System.out.print(this.getNom() + " attaque en : ");
			String s = sc.next();*/
			//(MIN + (MAX-MIN)*MATH.RANDOM + 1)  ---> RENVOIE UNE VALEUR ENTRE [MIN, MAX]
			
			int ligne =(int)(this.getTailleGrille()*Math.random());
			int colonne =(int)(this.getTailleGrille()*Math.random());
			
			try {
				c = new Coordonnee(ligne, colonne);
			} catch (IllegalArgumentException e) {
				System.out.println("Attaque hors champ de bataille, merci de localiser la cible entre 1 et " + this.getTailleGrille());
				continue;
			}
				if((c.getColonne() > this.getTailleGrille()) || (c.getLigne() > this.getTailleGrille())) {
					System.out.println("Attaque hors champ de bataille, [1, "+ this.getTailleGrille()+"]*[1, "+ this.getTailleGrille()+"]");
					continue;
					}
			
			break;
		}
		//sc.close();
		return c;
	}

	/*------------------- LA PRINCIPALE ----------------------------------------------------------------------------------------------------*/

	public static void main(String[] args) {
		JoueurTexte j1 = new JoueurTexte(new GrilleNavale(10,5),"Ousse");
		JoueurAuto j0 = new JoueurAuto(new GrilleNavale(10,5),"IA");
		j1.jouerAvec(j0);

	}

}
