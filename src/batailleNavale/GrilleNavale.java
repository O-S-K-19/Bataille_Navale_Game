package batailleNavale;

public class GrilleNavale {

// #########################################################################################################################################################################
// 														LES ATTRIBUTS
// #########################################################################################################################################################################

	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

// ##################################################################################################################################################################
// 														LES CONSTRUCTEURS
// ###################################################################################################################################################################

// CONSTRUCTEURS TAILLE ET TABLEAU_TAILLE_NAVIRES

	public GrilleNavale(int taille, int[] taillesNavires) {

		if (taille < 0 || taille > 26)
			throw new IllegalArgumentException("taille de la grille incorrecte");

		this.taille = taille;
		this.tirsRecus = new Coordonnee[taille * taille];
		this.navires = new Navire[taillesNavires.length];
		this.nbTirsRecus = 0;
		this.nbNavires = 0;
	}

// CONSTRUCTEURS TAILLE ET NOMBRE_NAVIRES

	public GrilleNavale(int taille, int nbNavires) {

		if (taille < 0 || taille > 26)
			throw new IllegalArgumentException("taille de la grille incorrecte");

		this.tirsRecus = new Coordonnee[taille * taille];
		this.navires = new Navire[nbNavires];
		this.nbTirsRecus = 0;
		this.taille = taille;
		this.nbNavires = 0;
	}

// ############################################################################################################################################################################
// 															LES METHODES :
// #############################################################################################################################################################################

//----------------------------------------------- TOSTRING ------------------------------------------------------------------------------------------------------------------------------------

// Retourne une String représentant this. On souhaite obtenir une représentation s'affichant sur la console de la façon suivante :

	public String toString() {

		StringBuffer str = new StringBuffer("   ");
		for (int i = 0; i < this.taille; i++) {
			str.append((char) (65 + i)).append(" ");
		}
		str.append("\n");
		for (int i = 0; i < this.taille; i++) {
			if (i < 9) {
				str.append(" ");
			}
			str.append(i + 1);
			for (int j = 0; j < this.taille; j++) {
				str.append(" ").append(".");
			}
			str.append("\n");
		}
		// NAVIRES
		int indDebut, ind, indFin, indSuivant;
		for (int i = 0; i < navires.length; i++) {
			try {
				// NAVIRE Horizontal
				if ((navires[i].getDebut().getLigne() == navires[i].getFin().getLigne())) {
					ind = i;
					indDebut = (navires[ind].getDebut().getLigne() + 1) * (this.taille * 2 + 3) + 4
							+ navires[ind].getDebut().getColonne() * 2;
					indFin = (navires[ind].getFin().getLigne() + 1) * (this.taille * 2 + 3) + 4
							+ navires[ind].getFin().getColonne() * 2;
					for (int j = indDebut; j <= indFin; j += 2) {
						str.setCharAt(j, '#');
					}
				}
				// VERTICAL
				else {
					ind = i;
					indSuivant = ((navires[ind].getDebut().getLigne() + 1) + 1) * (this.taille * 2 + 3) + 4
							+ (navires[ind].getDebut().getColonne()) * 2;
					indDebut = (navires[ind].getDebut().getLigne() + 1) * (this.taille * 2 + 3) + 4
							+ navires[ind].getDebut().getColonne() * 2;
					indFin = (navires[ind].getFin().getLigne() + 1) * (this.taille * 2 + 3) + 4
							+ navires[ind].getFin().getColonne() * 2;
					for (int j = indDebut; j <= indFin; j += (indSuivant - indDebut)) {
						str.setCharAt(j, '#');
					}
				}
			} catch (NullPointerException e) {
				break;

			}

		}
		// TOUCHER OU A L'EAU
		for (int i = 0; i < nbTirsRecus; i++) {
			try {
				int j = (tirsRecus[i].getLigne() + 1) * (this.taille * 2 + 3) + 4 + tirsRecus[i].getColonne() * 2;
				if (str.charAt(j) == '#')
					str.setCharAt(j, 'X');
				else
					str.setCharAt(j, 'O');

			} catch (StringIndexOutOfBoundsException e) {
				continue;
			}
		}
		return str.toString();

	}
//--------------------------------------------------- getTaille --------------------------------------------------------------------------------------------------------------------------------
// Accesseur en lecture.

	public int getTaille() {
		return this.taille;

	}
//---------------------------------------------------- ajouterNavire -------------------------------------------------------------------------------------------------------------------------------

// Retourne true après avoir ajouté n à this si cet ajout est possible. L'ajout est impossible si n touche ou chevauche un navire déjà présent dans this, ou encore si n dépasse les limites de this.

	public boolean ajouteNavire(Navire n) {

		if (this.estDansGrille(n.getFin()) && this.estDansGrille(n.getDebut())) {
			for (int i = 0; i < this.nbNavires; i++) {
				if (this.navires[i].touche(n) || this.navires[i].chevauche(n)) {
					System.out.println("\n" + n + " chevauche ou touche " + this.navires[i] + "fail");
					return false;

				}
				;
			}

			this.navires[nbNavires] = n;
			nbNavires++;
			System.out.println("\n" + n + " succes");
			return true;

		}
		System.out.println("\n" + n + " en dehors de la grille");
		return false;

	}

	public int getNavire() {
		return navires.length;
	}

//----------------------------------------------- placementAuto ------------------------------------------------------------------------------------------------------------------------------------
// Place automatiquement et aléatoirement taillesNavires.length navires dont les tailles sont données dans taillesNavire.

	public void placementAuto(int[] taillesNavires) {

		int i = 0;
		if (taillesNavires.length > this.navires.length) {
			System.out.println("La grille doit contenir au max " + this.navires.length + " navires");
		} else
			while (i < taillesNavires.length) {
				// (MIN + (MAX-MIN)*MATH.RANDOM + 1)
				boolean estVertical = Math.random() < 0.5;
				int lignedebut = 0;// probabilité de tirage vert/hor 50%
				int coldebut = 0;
				if (estVertical) {
					lignedebut = (int) ((this.taille - taillesNavires[i]+1) * Math.random());
					coldebut = (int) ((this.taille) * Math.random());
				} else {
					lignedebut = (int) ((this.taille) * Math.random());
					coldebut = (int) ((this.taille - taillesNavires[i]+1) * Math.random());
				}

				// if (lignedebut<0 || lignedebut >25 || coldebut < 0 || coldebut> 25)
				// continue;

				// int lignedebut = ((int) ( (this.taille+1)*Math.random()));
				// int coldebut = ((int) ( (this.taille+1)*Math.random()));
				Coordonnee coordonneedebut = new Coordonnee(lignedebut, coldebut);
				Navire n = new Navire(coordonneedebut, taillesNavires[i], estVertical);
				if (ajouteNavire(n)) {
					i++;
				}
			}
	}

//------------------------------------------ estDansGrille -----------------------------------------------------------------------------------------------------------------------------------------
// Retourne true si et seulement si c est dans this.

	private boolean estDansGrille(Coordonnee c) {
		return (c.getColonne() < this.taille && c.getLigne() < this.taille);
	}

//----------------------------------------------- estDansTirsRecus ------------------------------------------------------------------------------------------------------------------------------------
// Retourne true si et seulement si c correspond à un tir reçu par this.

	private boolean estDansTirsRecus(Coordonnee c) {
 if(!estDansGrille(c)) {
	 return false;
 }else 
		if (nbTirsRecus != 0) {
			for (int i = 0; i < nbTirsRecus; i++) {// tirs reçus sont stocké dans un tableau voir attributs
				if (this.tirsRecus[i].equals(c)) {// appel a la methode equals de la classe coordonee
					return true;
				}
			}
		}
		return false;

	}

//------------------------------------------------ ajouteDansTirsRecus -----------------------------------------------------------------------------------------------------------------------------------
// Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si this est modifié.	

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if (this.estDansTirsRecus(c) == true)
			return false;
		tirsRecus[nbTirsRecus] = c;
		nbTirsRecus++;
		return true;
	}

//---------------------------------------------- recoitTir -------------------------------------------------------------------------------------------------------------------------------------
// Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si c ne correspondait pas déjà à un tir reçu et a permis de toucher un navire de this.

	public boolean recoitTir(Coordonnee c) {
		boolean tir = ajouteDansTirsRecus(c);
		boolean touche = false;
		if (tir) {
			int i = 0;
			while (i < nbNavires && !touche) {
				if (navires[i].recoitTir(c))
					touche = true;
				i++;
			}
		}

		return touche;
	}

//-------------------------------------------------------- estTouche  ---------------------------------------------------------------------------------------------------------------------------	
// Retourne true si et seulement si un des navires présents dans this a été touché en c.

	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < this.nbNavires; i++) {
			if (this.navires[i].estTouche(c))
				// System.out.println(c + " a touche ");
				return true;
		}
		// System.out.println(c + " n'a pas touche ");
		return false;
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
// Retourne true si et seulement si c correspond à un tir reçu dans l'eau par this.	

	public boolean estALEau(Coordonnee c) {
		return !(this.estTouche(c));
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Retourne true si et seulement si un des navires présents dans this a été touché en c et est coulé.		

	public boolean estCoule(Coordonnee c) {
		for (int i = 0; i < this.nbNavires; i++) {
			if (this.navires[i].estTouche(c) && this.navires[i].estCoule()) {
				System.out.println(this.navires[i] + " a été coulé suite au tir en " + c);
				return true;
			}
		}
		return false;
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Retourne true si et seulement si tous les navires de this ont été coulés.

	public boolean perdu() {
		for (int i = 0; i < this.nbNavires; i++)
			if (!(this.navires[i].estCoule()))
				return false;
		System.out.println("GAME OVER !!! ");
		return true;
	}

	public static void main(String args[]) {
//####################################################################################################################################################################
// 														CONFIG TEST CLASS : "GrilleNavale"
//####################################################################################################################################################################

		// SCHEMA DESCRIPTIF
		// COORDONNES CREER = 7 DONT 5 LOCALISER DANS LA GRILLE ET 2 HORS DE LA GRILLE
		// NAVIRES CREER = 9 DONT 7 DANS LA GRILLE ET 2 HORS DE LA GRILLE
		// TAILLE DE LA GRILLE = 10

//##################################################################################################################################################################
//														CREATIONS COMME INDIQUER SUR LE SCHEMA
//##################################################################################################################################################################

		// JE CREE LES COORDONNEES

		// LOCALISER EN DEHORS DE LA GRILLE
		Coordonnee c01 = new Coordonnee(5, 11);// L6
		Coordonnee c02 = new Coordonnee("D11");

		// LOCALISER DANS LA GRILLE
		Coordonnee c1 = new Coordonnee("A3");
		Coordonnee c2 = new Coordonnee("E9");
		Coordonnee c3 = new Coordonnee(5, 5);// F6
		Coordonnee c4 = new Coordonnee(7, 2);// C8
		Coordonnee c5 = new Coordonnee("F9");

// JE CREE LES NAVIRES

		// SONT EN DEHORS DE LA GRILLE
		Navire n01 = new Navire(new Coordonnee("K1"), 4, true); // n01("K1",4,V)
		Navire n02 = new Navire(new Coordonnee("C12"), 9, false);// n02("C12",9,H)

		// SONT DANS LA GRILLE
		Navire n1 = new Navire(new Coordonnee("A2"), 3, true); // n1("A2",3,V)
		Navire n2 = new Navire(new Coordonnee("A3"), 7, false); // n2("A3",7,H)
		Navire n3 = new Navire(new Coordonnee("J5"), 3, true); // n3("J5",8,V)
		Navire n4 = new Navire(new Coordonnee("I4"), 4, true); // n4("I4",4,V)
		Navire n5 = new Navire(new Coordonnee("F2"), 2, false); // n5("F2",6,H)
		Navire n6 = new Navire(new Coordonnee("F9"), 2, true); // n6("F9",2,V)
		Navire n7 = new Navire(new Coordonnee("B6"), 5, false); // n7("B6",5,H)

// JE CREE LA GRILLE NAVALE 

		// TABLEAU QUI CONTIENT LA TAILLE DE CHACUN DES NAVIRES QUI SERONT DANS LA
		// GRILLE
		int[] tabLenNavire = { 3, 8, 3, 4, 2, 2, 5 }; // length = 7

		// GRILLE DE PARAMETRE : TAILLE 10*10 && NOMBRE_NAVIRES = 7
		//GrilleNavale g = new GrilleNavale(25, tabLenNavire);
		//g.placementAuto(tabLenNavire);
		// GRILLE DE PARAMETRE : TAILLE 10*10 && tabLenNavire
		// GrilleNavale maGrilleNavale = new GrilleNavale(10,tabLenNavire);
		for (int i = 0; i < 1000; ) {
			GrilleNavale g = new GrilleNavale(25, tabLenNavire);
			g.placementAuto(tabLenNavire);
			System.out.println(g.toString());

		}
//############################################################################################################################################################################
//										TEST !!!!!
//#############################################################################################################################################################################		

//AJOUTE MOI UN NAVIRE SI POSSIBLE DANS MA GRILLE

		/*
		 * g.ajouteNavire(n1); g.ajouteNavire(n2); g.ajouteNavire(n3);
		 * g.ajouteNavire(n4); g.ajouteNavire(n5); g.ajouteNavire(n6);
		 * g.ajouteNavire(n7); g.ajouteNavire(n01); g.ajouteNavire(n02);
		 * 
		 * System.out.println(g.toString());
		 */

// ENVOIE DES TIRS 	
/*
		g.recoitTir(c1);
		g.recoitTir(c3);
		g.recoitTir(c4);
		g.recoitTir(c5);
		g.recoitTir(c2);

		System.out.println(g.toString());

		g.recoitTir(c3);
		g.recoitTir(c1);
		g.recoitTir(c5);
		g.recoitTir(c02);
		g.recoitTir(c4);
		g.recoitTir(c1);
		g.recoitTir(c2);

		g.recoitTir(c1);
		g.recoitTir(c3);
		g.recoitTir(c4);
		g.recoitTir(c5);
		g.recoitTir(c2);
		g.recoitTir(new Coordonnee("F10"));

		System.out.println(g.toString());
	*/

		// System.out.println(g.estCoule(new Coordonnee("F10")));

		// COULER TOUS LES NAVIRES
		/*
		 * g.recoitTir(new Coordonnee("A2")); g.recoitTir(new Coordonnee("F2"));
		 * g.recoitTir(new Coordonnee("J5")); g.recoitTir(new Coordonnee("A4"));
		 * g.recoitTir(new Coordonnee("B6")); g.recoitTir(new Coordonnee("C6"));
		 * g.recoitTir(new Coordonnee("D6")); g.recoitTir(new Coordonnee("E6"));
		 * g.recoitTir(new Coordonnee("F6")); g.recoitTir(new Coordonnee("A3"));
		 * g.recoitTir(new Coordonnee("G2")); g.recoitTir(new Coordonnee("J6"));
		 * g.recoitTir(new Coordonnee("J7")); g.recoitTir(new Coordonnee("H7"));
		 * g.recoitTir(new Coordonnee("D4"));
		 * 
		 * System.out.println(g.toString());
		 * 
		 * System.out.println(g.perdu());
		 */

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		/*
		 * // PLACE ALEATOIREMENT LES NAVIRES DONT LES TAILLES SONT STOCKES DANS LE
		 * TABLEAU TAILLE_NAVIRES DANS MA GRILLE
		 * maGrilleNavale.placementAuto(tableauDeTailleNavire);
		 * 
		 * // AUGMENTE LE NOMBRE DE TIR RECUS GRACE A MES COORDONNEES S'ILS NE
		 * CORRESPOND PAS DEJA A UN TIR RECUS ET S'ILS N'ONT PAS DEJA TOUCHER UN NAVIRE
		 * maGrilleNavale.recoitTir(mesCoordonnee);
		 * 
		 * // VERIFIE SI UN NAVIRE SUR LA GRILLE A ETE TOUCHE
		 * maGrilleNavale.estTouche(mesCoordonnee);
		 * 
		 * // VERIFIE SI MES COORDONNES CORRESPOND A UN TIR A L'EAU DANS MA GRILLE
		 * maGrilleNavale.estALEau(mesCoordonnee);
		 * 
		 * //// VERIFIE SI UN NAVIRE A ETE COULE maGrilleNavale.estCoule(mesCoordonnee);
		 * 
		 * // VERIFIE SI TOUS LES NAVIRES ONT ETE COULES maGrilleNavale.perdu();
		 */

		/*
		 * METHODES PRIVATES
		 * 
		 * // VERIFIE SI MES COORDONNES SONT DANS LA GRILLE
		 * estDansGrille(mesCoordonnee);
		 * 
		 * // VERIFIE QUE MES COORDONNES CORRESPOND A UN TIR RECUS SUR MA GRILLE
		 * estDansTirsRecus(mesCoordonnee);
		 * 
		 * // AUGMENTE LE NOMBRE DE TIR RECUS GRACE A MES COORDONNEES
		 * ajouteDansTirsRecus(mesCoordonnee);
		 */

	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * METHODE MATH.RADOM POUR GENERER DES VALEURS ALEATOIRES MIN +
	 * (MAX-MIN)*MATH.RANDOM + 1 EST UNE VALEUR ENTRE [MIN, MAX]
	 * 
	 * 
	 * //--------------------------- CREER_GRILLE_NAVALE
	 * -----------------------------------------------------------------------------
	 * ---------------------------------------------
	 * 
	 * public static GrilleNavale creerMoiGrilleNavale() { int taille = (int)(1 +
	 * (tailleMax-1)*Math.random() + 1); int nombreNavires = (int)(1 +
	 * (tailleMax-1)*Math.random() + 1); return new
	 * GrilleNavale(taille,nombreNavires); } //---------------------------
	 * CREER_MOI_NAVIRE
	 * -----------------------------------------------------------------------------
	 * ---------------------------------------------
	 * 
	 * public static Navire creerMoiNavire(int longueurMax) { int longueur = (int)(2
	 * + (longueurMax-2)*Math.random() + 1); boolean estVertical = (1 == (int)(0 +
	 * (1-0)*Math.random() + 1)); return new Navire(creerMoiCoordonnee(), longueur,
	 * estVertical); }
	 * 
	 * 
	 * //--------------------------- CREER_MOI_COORDONNEE
	 * -----------------------------------------------------------------------------
	 * --------------------------------------------- public static Coordonnee
	 * creerMoiCoordonnee() { int ligne = (int)(1 + (26-1)*Math.random() + 1); char
	 * colonne =(char)(int)(65 + (90-65)*Math.random() + 1); return new
	 * Coordonnee(""+colonne+ligne); }
	 */
	// -------------------------------------------------------------------------------------------------------------------------------------------------------

}