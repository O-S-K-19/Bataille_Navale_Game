package batailleNavale;

public class TestGrilleNavale {

	public static void main(String[] args) {
		
		Navire n1 = new Navire(new Coordonnee("A2"),3,true);
		Navire n2 = new Navire(new Coordonnee("A3"),8,false);
		Navire n3 = new Navire(new Coordonnee("J5"),3,!false);
		Navire n4 = new Navire(new Coordonnee("I4"),4,!false);
		Navire n5 = new Navire(new Coordonnee("F2"),2,false);
		Navire n6 = new Navire(new Coordonnee("F9"),2,!false);
		Navire n7 = new Navire(new Coordonnee("B6"),5,!true);
		Navire n8 = new Navire(new Coordonnee("K1"),4,!false);
		Navire n9 = new Navire(new Coordonnee("C12"),9,false);
		
		
		// LOCALISER EN DEHORS DE LA GRILLE
					Coordonnee c01 = new Coordonnee(5,11);//L6
					Coordonnee c02 = new Coordonnee("D11");
				
				// LOCALISER DANS LA GRILLE
					//Coordonnee c1 = new Coordonnee("A3"); 
					//Coordonnee c2 = new Coordonnee("E9"); 
					//Coordonnee c3 = new Coordonnee(5,5);//F6 
					//Coordonnee c4 = new Coordonnee(7,2);//C8 
					//Coordonnee c5 = new Coordonnee("F9");
		
		//Navire n5 = new Navire(new Coordonnee("A11"),4,false);
		//Navire n6 = new Navire(new Coordonnee("A12"),3,false);
		
		/*GrilleNavale g = new GrilleNavale(10,7);
		Coordonnee c = new Coordonnee("D4");
		System.out.println(g.estTouche(c));*/
		
		
	int [] tab = {1,2,4,4,4,4,4};
	int i = 0;
	
	while(i<1000) {
		GrilleNavale h= new GrilleNavale(26,tab);
		h.placementAuto(tab);
		System.out.println(h.toString());
		
	}


		/*Navire n = new Navire(new Coordonnee("C6"),3, true);
		System.out.println(g.getTaille());
		System.out.println(g.ajouteNavire(n));
		//int tab []= {3};
		g.placementAuto(tab);
		Coordonnee c = new Coordonnee(6,2);
		Coordonnee c1 = new Coordonnee(5,3);
		System.out.println(g.estDansGrille(c));
		System.out.println(g.estDansTirsRecus(c));
		System.out.println(g.ajouteDansTirsRecus(c1));
		
		
		//test methode recoitTir
		//g.recoitTir(c1);
		
		//test methode perdu
		//g.estTouche(c1); a verifier 
		
		//test methode perdu
		//g.estALEau(c1); a verifer
		
		//test methode perdu
		//g.estCoule(c1)
		
		//test methode perdu
		//System.out.println(g.perdu());
		
	*/
	}
	

}
