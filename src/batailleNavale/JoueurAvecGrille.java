package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
	/*------------------- ATTRIBUTS --------------------------------------------------------------------------------------------------------*/

	private GrilleNavale grille;

	/*
	 * public JoueurAvecGrille(int tailleGrille) { super(tailleGrille);
	 */
	/*------------------- CONSTRUCTEURS ----------------------------------------------------------------------------------------------------*/

	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(), nom);
		this.grille = g;
	}

	/*
	 * public JoueurAvecGrille(GrilleNavale g) { super(g.getTaille()); }
	 */

	public JoueurAvecGrille(GrilleNavale g) {
		super(g.getTaille());
		this.grille = g;
	}

	// public JoueurAvecGrille() {}

	/*--------------------------- LES METHODES -----------------------------------------------------------------------------------------------*/

	public int defendre(Coordonnee c) {
		grille.recoitTir(c);
	//	System.out.println(grille); LA 
		if (grille.perdu()) {
			return Joueur.GAMEOVER;
			// return 4
		}
		if (grille.estCoule(c)) {
			return Joueur.COULE;
			// return 2
		}
		if (grille.estTouche(c)) {
			return Joueur.TOUCHE;
			// return 1
		}
		return Joueur.A_L_EAU;
		// return 3
}
	
	
/*--------------------------- LE PRINCIPALE --------------------------------------------------------------------------------------------------------------------------------*/

	public GrilleNavale getGrille() {
		return grille;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
