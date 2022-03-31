package batailleNavale;

import java.io.*;
import java.net.*;
import java.util.*;

public class JoueurClient {

	public static void main(String[] args) throws IOException {
		// se connecter
		System.out.println("je suis le joueur client je veux me connecter");
		//System.out.println("saisir svp le port pour se connecter :) ");
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		sc.close();
		// saisie port de connection
		int i = 0;
		while(i < 1){
			try{
				Integer.parseInt(str1);
			}catch(NumberFormatException e){
				continue;
			}
			i++;
		}
		int port = Integer.parseInt(str1);

		 // supposons le bon port est donne
		Socket s = new Socket("localhost", port);
		System.out.println("je me connecte");
		
		//ENVOIE au joueur serveur
		System.out.println("j'envoie un message");
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		//saisie 
		Scanner sc1 = new Scanner(System.in);
		String str2 = sc1.next();
		sc1.close();
		pr.println(str2);
		pr.flush();

		//RECEPTION action du joueur serveur
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		String str = bf.readLine();
		System.out.println("message du client : " + str);

		// SAISIE PAR LE CLIENT

		
		//pr.printIn(sc.next());
		//sc.close();
		
		
		
	}

}
