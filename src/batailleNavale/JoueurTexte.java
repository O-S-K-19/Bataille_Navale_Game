package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {

/*------------------- ATTRIBUTS --------------------------------------------------------------------------------------------------------*/
	
	/* herite des attributs de JoueurAvecGrille :
	 * 
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;
	private GrilleNavale grille;
	 * 
	 * */
	
	private Scanner sc;

/*------------------- CONSTRUCTEURS ----------------------------------------------------------------------------------------------------*/
	
	/*
	 * 	herite des constructeurs de JoueurAvecGrille par le mot cl� super :
	

	super(GrilleNavale g, String nom) ---> (public JoueurAvecGrille(GrilleNavale g, String nom)
	
	super(GrilleNavale g) ---> public JoueurAvecGrille (GrilleNavale g)
		
	
	 * 
	 * 
	 * */

// CONSTRUCTUEUR AVEC GRILLE ET NOM
	
	public JoueurTexte(GrilleNavale g, String nom) {

		super(g, nom);
	}

// CONSTRUCTUEUR AVEC GRILLE UNIQUEMENT 
	
	public JoueurTexte(GrilleNavale g) {
		super(g);
	}

/*------------------- LES METHODES ----------------------------------------------------------------------------------------------------*/
	
	/*
	Herite des m�thodes de JoueurAvecGrille :
	
	*
	*
	*
	*/
	
	

/* retourAttaque :
	 Definition ***:Cette m�thode est invoqu�e sur le joueur attaquant � la fin
	 d�un tour de jeu. c est la coordonn�e � laquelle le tir a eu lieu et etat le
	 r�sultat de l'attaque. etat ne peut �tre que TOUCHE, COULE, A_L_EAU, ou
	 GAMEOVER 
 */
	
	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println("\nretourAttaque :");
		if (etat == TOUCHE)
			System.out.println(this.getNom() +" >>>>> a touch� la partie " + "\""+c + "\" " + "d'un des navires enemies");
		else if (etat == COULE)
			System.out.println(this.getNom() +" >>>>> a coul� un navire enemie apres l'attaque en " + "\""+ c + "\" ");
			//System.out.println("un bateau en moins");
		else if (etat == A_L_EAU)
			System.out.println(this.getNom() +" >>>>> a effectuer un tir dans l'eau localis� en " + "\""+ c + "\" ");
			//System.out.println("presque");
		else
			System.out.println(this.getNom() +" >>>>> BRAVO ! Vous etes tr�s fort, Toute la flotte enemies a coul� apres votre attaque en " + "\"" + c + "\" ");
	}

/*
	 retourDefense :
	 Definition ***:Cette m�thode est invoqu�e sur le joueur defenseur � la fin
	 d�un tour de jeu. c est la coordonn�e � laquelle le tir a eu lieu et etat le
	 r�sultat de l'attaque. etat ne peut �tre que TOUCHE, COULE, A_L_EAU, ou
	 GAMEOVER 
 */	
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println(this.getGrille());
		System.out.println("\nretourDefense :");
		if (etat == TOUCHE)
			System.out.println(this.getNom()+" >>>>> la partie d'un de vos navires est touch� en " + "\"" + c + "\" ");
			//System.out.println("Un de vos bateau a �t� touch�");
		else if (etat == COULE)
			System.out.println(this.getNom()+" >>>>> vous avez un navire en moins apres l'attaque enemie en " + "\"" + c + "\" ");
			//System.out.println("vous avez un bateau en moins haha");
		else if (etat == A_L_EAU)
			System.out.println(this.getNom()+" >>>>> l'ennemie vous a rat� par un tir dans l'eau localis� en " + "\"" + c + "\" ");
			//System.out.println("rien ne s'est pass�");
		else
			System.out.println(this.getNom()+" >>>>> GAME OVER ! Toute votre flotte a coul� apres l'attaque enemie en "+ "\""+c + "\" ");
			//System.out.println("dommage vous avez perdu");
	}
	
	
	
	public Coordonnee choixAttaque() {
		/*
		 * Commentaire * : verifier les coordonn�es rentrer par l'utilisateur.
		 * Definition *: Cette m�thode est invoqu�e sur le joueur attaquant au d�but
		 * d�un tour de jeu. Elle retourne la coordonn�e cible du tir effectu� par
		 * l�attaquant
		 */
		boolean incorrect = true;
		Coordonnee coordonneeAttaque=null;
		while (incorrect) {
			System.out.print("Attaque en ? : ");
			Scanner sc = new Scanner(System.in);
			String s = sc.next();
			try {
				coordonneeAttaque = new Coordonnee(s);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Coordonn�e incorrecte : merci de respecter le format \"charInt\".");
				continue;
			}
			if((coordonneeAttaque.getColonne() > this.getTailleGrille()) || (coordonneeAttaque.getLigne() > this.getTailleGrille())) {
				System.out.println("Attaque hors champ de bataille, [1, "+ this.getTailleGrille()+"]*[1, "+ this.getTailleGrille()+"]");
				continue;
				}
			incorrect = false;
			
		}
		return coordonneeAttaque;
	}

/*
	public Coordonnee choixAttaque() {
		/*
		 * Commentaire *** : verifier les coordonn�es rentrer par l'utilisateur.
		 * Definition ***: Cette m�thode est invoqu�e sur le joueur attaquant au d�but
		 * d�un tour de jeu. Elle retourne la coordonn�e cible du tir effectu� par
		 * l�attaquant
		 

		Scanner sc = new Scanner(System.in);
		
		Coordonnee c;
		while (true) {
			System.out.print(this.getNom() + " attaque en : ");
			String s = sc.next();
			sc.close();
			c = new Coordonnee(s);
			if((c.getColonne() > this.getTailleGrille()) || (c.getLigne() > this.getTailleGrille())) {
				System.out.println("Attaque hors champ de bataille, [1, "+ this.getTailleGrille()+"]*[1, "+ this.getTailleGrille()+"]");
				continue;
		}
			break;
		}
	
		//sc.close();
		return c;
	}

	*/	
	

	/*------------------- LA PRINCIPALE ----------------------------------------------------------------------------------------------------*/

	public static void main(String[] args) {
	
	}

}
