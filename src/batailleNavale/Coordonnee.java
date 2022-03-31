package batailleNavale;

public class Coordonnee {

/*------------------- ATTRIBUTS ----------------------------------------------------------------------------------------------------------------------------------*/
	
	private int ligne;
	private int colonne;
	
/*------------------- CONSTRUCTEURS ------------------------------------------------------------------------------------------------------------------------------*/	


// CONSTRUCTEURS DE FORME (x,y) :	

	public Coordonnee(int ligne, int colonne) {
		
		if (ligne<0 || ligne >25  || colonne <0 || colonne >25 ) {
			throw new IllegalArgumentException("la ligne "+ ligne +" et la colonne "+colonne+" doivent être entre 0 et 25");
			}
		 this.ligne=ligne; // initialisation de l'attribt ligne
		 this.colonne = colonne; // initialisation de l'attribut colonne
	}

// CONSTRUCTEURS DE FORME ("A1")

	public Coordonnee(String s) {
		if (s.equals(""))
			throw new IllegalArgumentException ("Parametre incorrect, \"" + s + "\" ne peut pas etre vide");
		if (s.length() > 3 || s.length() < 2)
			throw new IllegalArgumentException ("Parametre \"" + s + "\" incorrect" + " : \nLa chaine doit contenir 2 à 3 caracteres au maximum et doit etre de forme (\"CaractereInteger\") par exple : \"C6\"");
		else if(s.charAt(0) < 'A' || s.charAt(0) > 'Z')
			throw new IllegalArgumentException ("Dans " + s + " le premier caractere doit etre de type (\"Lettre Majuscule\") de \"A --> Z\"");
		try {
			Integer.parseInt(s.substring(1));
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException ("Dans " + s + " le deuxieme caractere " + "\""+ s.substring(1) +"\" " + "doit etre de type (\"Integer\")"); 
			} 
		if(Integer.parseInt(s.substring(1)) > 26 || Integer.parseInt(s.substring(1)) < 1){
			throw new IllegalArgumentException ("Dans " + s + " le deuxieme caractere " + "\""+ s.substring(1) +"\"" + "doit etre compris entre [1, 26]");
		}

			//throw new NullPointerException("longueur érronnée");
			this.colonne = s.charAt(0) -'A';
			this.ligne = Integer.parseInt(s.substring(1))-1;
		
	
}

/*--------------------------- LES METHODES -----------------------------------------------------------------------------------------------*/
	
//Retourne une String exprimant this dans le système de coordonnée de la bataille navale (exemple : "C6").

public String toString() {
	return "" + (char)(this.colonne + 65) + (int)(this.ligne + 1);
}


//Accesseur en lecture pour l'indice de ligne.
public int getLigne() {
	return this.ligne;
}

//Accesseur en lecture pour l'indice de colonne.

public int getColonne() {
	return this.colonne;
}

//

public boolean equals(Object obj) {
	if (!(obj instanceof Coordonnee))
		return false;
	Coordonnee c = (Coordonnee) obj;
		return (c.ligne == this.ligne) && (c.colonne == this.colonne);

}


// Retourne true si et seulement si this est une coordonnée voisine (verticalement ou horizontalement) de c.

public boolean voisine(Coordonnee c) {
	return (((this.colonne == c.colonne) && ((this.ligne == c.ligne + 1) || (this.ligne == c.ligne - 1))) || 
			((this.ligne == c.ligne) && ((this.colonne == c.colonne+1) || (this.colonne == c.colonne - 1))));
}

//Retourne le résultat de la comparaison de this et de c. Une coordonnée est considérée
//inférieure à une autre, si elle se trouve sur une ligne inférieure ou si elle se trouve sur la
//même ligne mais sur une colonne inférieure.

public int compareTo(Coordonnee c) {
		if ((this.ligne == c.ligne) && (this.colonne == c.colonne))
			return 0;
	    else if (this.ligne != c.ligne && this.colonne == c.colonne)
			return this.ligne - c.ligne;
		else 
			return this.colonne - c.colonne;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coordonnee a,b,c;
		a = new Coordonnee(25,25);
		//b = new Coordonnee("M5");
		//c = new Coordonnee(2,2);
		//System.out.println("a voisine de b ? : " + a.voisine(b));
		//System.out.println("a voisine de c ? : " + a.voisine(c));
		System.out.println("a : " +a.toString());
		//System.out.println("b : "+b.toString());
	}

}








		
	
	
	
