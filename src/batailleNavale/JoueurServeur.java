package batailleNavale;

import java.io.*;
import java.net.*;
import java.util.*;


public class JoueurServeur {
	
		public static void main(String[] args)throws IOException {
		
		// demmarage du serveur
		ServerSocket ss = new ServerSocket(5050);
		System.out.println("je suis le joueur serveur");
		System.out.println("En attente de connection ...");
		
		// accusé de connection du joueur client
		Socket s = ss.accept();
		System.out.println("Client connecter avec success !");
		
		//RECEPTION action du joueur client
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		String str = bf.readLine();
		System.out.println("message du client : " + str);

		//ENVOIE au joueur client
		System.out.println("j'envoie un message");
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		//saisie 
		Scanner sc1 = new Scanner(System.in);
		String str2 = sc1.next();
		sc1.close();
		pr.println(str2);
		pr.flush();

		

		
			
			//ss.setSoTimeout(10000);
		
		
	}

}
