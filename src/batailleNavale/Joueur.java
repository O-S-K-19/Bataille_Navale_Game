package batailleNavale;

public abstract class Joueur {

/*------------------- ATTRIBUTS ------------------------------------------------------------------------------------------------------------------------*/

	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;
	
/*------------------- CONSTRUCTEURS ---------------------------------------------------------------------------------------------------------------------*/
	
// CONSTRUCTEUR QUI PREND TAILLE DE LA GRILLE ET LE NOM DU JOUEUR
// permet d'obtenir un joueur de nom "nom" et jouant sur une grille de taille taillegrille.

	public Joueur(int tailleGrille, String nom) {
		this.nom = nom;
		this.tailleGrille = tailleGrille; 
	}
	
// CONSTRUCTEUR QUI PREND QUE TAILLE DE LA GRILLE
// permet d'obtenir un joueur jouant sur une grille de taille taillegrille.
	
	public Joueur(int tailleGrille) {
		this.tailleGrille = tailleGrille;
		this.nom = "JoueurParDefaut";
	}
	
/*--------------------------- LES METHODES ------------------------------------------------------------------------------------------------------------*/
	
//Accesseur en lecture pour tailleGrille. 
	
	public int getTailleGrille() {
		return this.tailleGrille;
	}

// Accesseur en lecture pour nom. 
	
	public String getNom() {
		return this.nom;
	}

/*Démarre une partie contre j. Avant de lancer le déroulement du jeu, il faut veiller à établir 
	le lien entre les 2 joueurs et bien entendu vérifier qu’il puisse être établi.*/
	
	public void jouerAvec(Joueur j) {
		
	// try ........... catch pour voir si le lien a bien ete eteblie ou pas
		this.adversaire = j;
		j.adversaire = this;
		System.out.println(" connection succes !!!");
		
		deroulementJeu(this, j);
	}

	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
		System.out.println("##########################################################################################################################################################");
		System.out.println("                    " + attaquant.getNom() + " VS " + defenseur.getNom()); 
		System.out.println("##########################################################################################################################################################");
		int resAtt = 0;
		int resDef = 0;
		while (resAtt != GAMEOVER || resDef != GAMEOVER) {
			
			// Attaquant joue en premier
			Coordonnee cAtt = attaquant.choixAttaque();
			resAtt = defenseur.defendre(cAtt);
			attaquant.retourAttaque(cAtt, resAtt);
			defenseur.retourDefense(cAtt, resAtt);
			if(resAtt == GAMEOVER)
				break;
			
			Joueur x = attaquant;
			attaquant = defenseur;
			defenseur = x;
			
		}
	}

	protected abstract void retourAttaque(Coordonnee c, int etat);

	protected abstract void retourDefense(Coordonnee c, int etat);

	public abstract Coordonnee choixAttaque();

	public abstract int defendre(Coordonnee c);
	
	
	/*--------------------------- LE PRINCIPALE ---------------------------------------------------------------------------------------------------------*/


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// tester tester tester tester 
	}

}
