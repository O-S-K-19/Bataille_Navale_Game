package batailleNavale;

public class Navire {

	/*------------------- ATTRIBUTS --------------------------------------------------------------------------------------------------------*/

	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	/*------------------- CONSTRUCTEURS ----------------------------------------------------------------------------------------------------*/

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		// chercher a lever le maximum d'exceptions --- Anis husseyni

		if (longueur > 26 || longueur <= 0) {
			// verifier la taille
			throw new IllegalArgumentException("longueur du Navire erronée");
		} else if ((debut instanceof Coordonnee) == false) {
			// verifier si debut est une instance de Coordonnee
			throw new IllegalArgumentException("le parametre :" + debut + "est erronée");
		} else {

			this.debut = debut;
			if (estVertical) {
				fin = new Coordonnee(longueur - 1 + debut.getLigne(), debut.getColonne());
			} else {
				fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
			}

			partiesTouchees = new Coordonnee[longueur];
			nbTouchees = 0;

		}
	}

	/*--------------------------- LES METHODES -----------------------------------------------------------------------------------------------*/

	/*
	 * public String toString() {
	 * 
	 * Retourne une String représentant this. On souhaite obtenir une représentation
	 * de la forme "Navire(B1, 4, horizontal)" (pour un navire de taille 4 placé
	 * horizontalement par exemple).
	 */

	// proposition de Anis : code long, le prof a proposé de le retravailler

	/*
	 * String position_navire = ""; int a = this.debut.getColonne(); int longueur =
	 * 0;
	 * 
	 * if (this.debut.getColonne() != this.fin.getColonne()) { longueur =
	 * this.debut.getColonne() - this.fin.getColonne(); longueur = longueur < 0 ?
	 * -longueur : longueur; longueur++; }
	 * 
	 * else if (this.debut.getLigne() != this.fin.getLigne()) { longueur =
	 * this.debut.getLigne() - this.fin.getLigne(); longueur = longueur < 0 ?
	 * -longueur : longueur; longueur++; }
	 * 
	 * // String cor = ""+a+this.debut.getLigne(); if (this.debut.getColonne() ==
	 * this.fin.getColonne()) position_navire = "vertical";
	 * 
	 * else if (this.debut.getLigne() == this.fin.getLigne()) position_navire =
	 * "Horizontal";
	 * 
	 * return ("Navire" + "(" + this.debut.toString() + ", " + longueur + ", " +
	 * position_navire + ")"); }
	 */

	public String toString() {
		// proposition du prof
		String position;
		int longueur;
		if (this.debut.getLigne() == this.fin.getLigne()) {
			position = "horizontal";
			longueur = this.fin.getColonne() - this.debut.getColonne() + 1;
		} else {
			position = "vertical";
			longueur = this.fin.getLigne() - this.debut.getLigne() + 1;
		}
		return "Navire(" + this.debut.toString() + ", " + longueur + ", " + position + ")";
	}

	public Coordonnee getDebut() {
		// Accesseur en lecture.
		return this.debut;
	}

	public Coordonnee getFin() {
		// Accesseur en lecture.
		return this.fin;
	}

	public boolean contient(Coordonnee c) {
		// Retourne true si et seulement si this occupe c.
		// proposition de Massi

		return ((c.getLigne() >= debut.getLigne() && c.getLigne() <= fin.getLigne())
				&& (c.getColonne() >= debut.getColonne() && c.getColonne() <= fin.getColonne()));
	}

	public boolean touche(Navire n) {
		// Retourne true si et seulement si this est adjacent à n.
		// on compare

		int thisL_LigneDebut = this.debut.getLigne();
		int thisL_LigneFin = this.fin.getLigne();
		int thisL_ColonneDebut = this.debut.getColonne();
		int thisL_ColonneFin = this.fin.getColonne();

		boolean toucheEnLigne = ((thisL_LigneDebut == n.fin.getLigne() + 1)
				|| (thisL_LigneFin == n.debut.getLigne() - 1) || (n.debut.getLigne() == thisL_LigneFin + 1)
				|| (n.fin.getLigne() == thisL_LigneDebut - 1));
		boolean toucheColonne = ((thisL_ColonneFin == n.debut.getColonne() - 1)
				|| (thisL_ColonneDebut == n.fin.getColonne() + 1) || (n.fin.getColonne() == thisL_ColonneDebut - 1)
				|| (n.debut.getColonne() == thisL_ColonneFin + 1));
		boolean colonneCommune = (((thisL_ColonneDebut >= n.debut.getColonne())
				&& (thisL_ColonneDebut <= n.fin.getColonne()))
				|| ((thisL_ColonneFin >= n.debut.getColonne()) && (thisL_ColonneFin <= n.fin.getColonne()))
				|| ((n.debut.getColonne() >= thisL_ColonneDebut) && (n.debut.getColonne() <= thisL_ColonneFin))
				|| ((n.fin.getColonne() >= thisL_ColonneDebut) && (n.fin.getColonne() <= thisL_ColonneFin)));
		boolean ligneCommune = (((thisL_LigneDebut >= n.debut.getLigne()) && (thisL_LigneDebut <= n.fin.getLigne()))
				|| ((thisL_LigneDebut >= n.debut.getLigne()) && (thisL_LigneDebut < n.fin.getLigne()))
				|| ((n.debut.getLigne() >= thisL_LigneDebut) && (n.debut.getLigne() <= thisL_LigneFin))
				|| ((n.fin.getLigne() >= thisL_LigneDebut) && (n.fin.getLigne() <= thisL_LigneFin)));

		if ((toucheEnLigne && colonneCommune) || (toucheColonne && ligneCommune)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean chevauche(Navire n) {
		if ((
		// 1er cas

		n.debut.getLigne() <= this.debut.getLigne() && n.fin.getLigne() >= this.debut.getLigne()
				&& n.debut.getColonne() >= this.debut.getColonne() && n.debut.getColonne() <= this.fin.getColonne()

		)

				||

				(
				// 2eme cas

				this.debut.getLigne() <= n.debut.getLigne() && this.fin.getLigne() >= n.debut.getLigne()
						&& this.debut.getColonne() >= n.debut.getColonne()
						&& this.debut.getColonne() <= n.fin.getColonne())

		)

			return true;
		else {
			return false;
		}
	}

	public boolean recoitTir(Coordonnee c) {
		// refaire
		if (this.contient(c)) {
			for (int i = 0; i < nbTouchees; i++) {
				if (partiesTouchees[i].equals(c)) {
					return true;
				}
			}
			nbTouchees = nbTouchees + 1;
			partiesTouchees[nbTouchees - 1] = c;
			return true;

		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		// Retourne true si et seulement si this a été touché par un tir en c
		// exception ou c est null
		for (int i = 0; i < nbTouchees; i++) { // ou i < nbtouchées
			if (this.partiesTouchees[i].equals(c))
				return true;
		}
		return false;
	}

	public boolean estTouche() {
		// Retourne true si et seulement si this a au moins une partie touchée.
		return (this.nbTouchees != 0); // ça remplace if (this.nbTouchees > 0) {return true; } return false;
	}

	/*
	 * public boolean estCoule() { // Retourne true si et seulement si this est
	 * coulé, c'est-à-dire que toutes ses parties ont été touchées int longueur =
	 * this.debut.getLigne() - this.fin.getLigne() == 0 ? this.debut.getColonne() -
	 * this.fin.getColonne() : this.debut.getLigne() - this.fin.getLigne();
	 * System.out.println(longueur); // pour tester la methode et apparement ça
	 * renvois un truc bizzar return (this.nbTouchees == longueur); }
	 */
	public boolean estCoule() {

		if (this.partiesTouchees.length == this.nbTouchees)
			return true;
		else {
			return false;
		}
	}

	/*--------------------------- PRINCIPAL -----------------------------------------------------------------------------------------------*/

	public static void main(String[] args) {
		Navire a, b;
		a = new Navire(new Coordonnee("A5"), 5, true);
		b = new Navire(new Coordonnee("A4"), 4, false);
		b = new Navire(new Coordonnee("A4"), 4, false);
		b = new Navire(new Coordonnee("A4"), 4, false);
		System.out.println("a : " + a.toString());

		// System.out.println(a.contient(new Coordonnee("A5")));
	}
}
