package batailleNavale;
import java.awt.*;


public class GrilleNavaleGraphique extends GrilleNavale {
	private GrilleGraphique grille;

// CONSTRUCTEURS D UNE GRILLE NAVALE GRAPHIQUE QUI RECOIT JUSTE LA TAILLE

public GrilleNavaleGraphique(int taille) {
	
//si la taille de grille <5 on pourrait avoir que 2 navire possible sur la grille
//si la taille > = 5 le nombre de navire sera egale a (taille / 5) + 2)
//on prend 5 car : 2 >= nombre navire <= 5
	
	super(taille, ((taille / 5) == 0 ? 2 : taille / 5 + 2));
	this.grille = new GrilleGraphique(taille);
	this.grille.setClicActive(false);
	
	}

// RECUPERER LA TAILLE DE LA GRILLE

public GrilleGraphique getGrilleGraphique() {
	return this.grille;
}

// COLORER EN VERT LES CASE COMPORTANT DES NAVIRES

public boolean ajouteNavire(Navire n) {
	 if (super.ajouteNavire(n)) {
		 this.grille.colorie(n.getDebut(), n.getFin(), Color.GREEN);
		 return true;
	 }
		return false;
	}

// COLORER EN ROUGE LES CASE DU NAVIRE TOUCHE ET EN BLEU POUR UN TIR A L EAU

	public boolean recoitTir(Coordonnee c) {
		if (super.recoitTir(c)) {
			this.grille.colorie(c, Color.RED); 
			return true;
		}
		else {
			this.grille.colorie(c, Color.BLUE); 
			return false; 
		}}
	} 