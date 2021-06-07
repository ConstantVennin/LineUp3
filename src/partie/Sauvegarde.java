package partie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import joueurPackage.Joueur;
import joueurPackage.Pion;
import plateauPackage.Plateau;
import plateauPackage.Position;
import plateauPackage.Situations;

public class Sauvegarde {
    private static String chemin = System.getProperty("user.dir") + File.separator + "res" + File.separator;
	private static String nom = "sauvegarde.txt";
	
	/**
     * sauvegarde l'état présent de la partie
     */
    public static void sauvergarder(Partie partie) {
    	
		String ecriture = "";
		ecriture += partie.nbJoueurs + "\n";
		ecriture += "~" + partie.getPlateau().getCouches() + "\n";
		ecriture += "~" + partie.getPlateau().getSommets() + "\n";
		for(int j = 0 ; j<partie.nbJoueurs ; j++) {
			Joueur currentPlayer;
			currentPlayer = partie.getJoueurs().get(j);
			ecriture += "~N" + j + currentPlayer.getName()+"\n";
			ecriture += "~" + currentPlayer.getJoueurId() + "\n";
			ecriture += "~P" + j + currentPlayer.getPositions().size() + "\n";
			for(int p = 0 ; p<currentPlayer.getPositions().size() ; p++) {
				ecriture += "~" + currentPlayer.getPositions().get(p).getCouche() + "." + currentPlayer.getPositions().get(p).getSommet() + "\n";
			}
		}
		ecriture += "~ARC~";
		for(int p1 = 0 ; p1<partie.getPlateau().getCouches()*partie.getPlateau().getSommets(); p1++) {
			for(int p2 = 0 ; p2<partie.getPlateau().getCouches()*partie.getPlateau().getSommets(); p2++) {
				ecriture += partie.getPlateau().getArcs(p1, p2) + " ";
			}
		}
		ecriture += "\n~PIEGE";
		int nombrePiege = 0;
		String placePiege = "";
		for(int p1 = 0 ; p1<partie.getPlateau().getCouches(); p1++) {
			for(int p2 = 0 ; p2<partie.getPlateau().getSommets(); p2++) {
				if(partie.getPlateau().getPlateau(p1, p2).equals(Situations.BLOQUE)) {
					nombrePiege++;
					placePiege += "~" + p1 + " " + p2;
				}
			}
		}
		ecriture += "~" + nombrePiege + "~" + placePiege;
		//System.out.println(ecriture);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(chemin+nom))) {
			bw.write(ecriture);
		} catch(IOException e) {
			System.out.println("Writing error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
     * retourne la dernière partie sauvegardée
     * @return Partie
     */
    public static Partie dernierePartie() {

    	String fichier = "";
    	try(BufferedReader br = new BufferedReader(new FileReader(chemin+nom))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line != null) {
			sb.append(line);
			line = br.readLine() ;
			}
			fichier = sb.toString();
		} catch(FileNotFoundException e) {
			//System.out.println("File not found: ");
			e.printStackTrace();
		} catch(IOException e) {
			//System.out.println("Reading error: " + e.getMessage());
			e.printStackTrace();
		}
    	//System.out.println(fichier);
    	String[] fichierClasse = fichier.split("~");
    	for(int i = 0 ;i<fichierClasse.length ; i++) {
    		//System.out.println(fichierClasse[i]);
    	}
    	int nombreJoueur = Integer.parseInt(fichierClasse[0]);
    	int nombreCouche = Integer.parseInt(fichierClasse[1]);
    	int nombreSommet = Integer.parseInt(fichierClasse[2]);
    	//System.out.println("nb joueur : "+nombreJoueur + "\n" + "Couche.sommet : "+nombreCouche + "."+nombreSommet);
    	
    	List<Joueur> joueurList = new ArrayList<Joueur>();
    	Plateau savePlateau = new Plateau(nombreCouche, nombreSommet/2);
    	for(int compteurJ = 0 ; compteurJ<nombreJoueur ; compteurJ++ ) {
    		int nombrePion;
    		int compteurDetec = 0;
    		boolean founded = true;
    		while(founded) {
    			if(fichierClasse[compteurDetec].length()>3) {
    				if(fichierClasse[compteurDetec].substring(0, 2).equals("N"+compteurJ)) {
    					founded = false;
    				}
    			}
    			compteurDetec++;
    		}
    		//System.out.println(compteurDetec);
    		String name = fichierClasse[compteurDetec-1].substring(2);
    		//System.out.println(name);
    		founded = true;
    		compteurDetec = 0;
    		while(founded) {
    			if(fichierClasse[compteurDetec].length()>2) {
    				if(fichierClasse[compteurDetec].substring(0, 2).equals("P"+compteurJ)) {
    					founded = false;
    				}
    			}
    			compteurDetec++;
    		}
    		nombrePion = Integer.parseInt(fichierClasse[compteurDetec-1].substring(2));
    		Joueur j0 = new Joueur(name,0);
    		j0.setJoueurId(compteurJ);
    		for(int pNb = 0 ; pNb<nombrePion; pNb++) {
    			int couchePion = Integer.parseInt(fichierClasse[pNb+compteurDetec].substring(0, 1));
    			int sommetPion = Integer.parseInt(fichierClasse[pNb+compteurDetec].substring(2, 3));
    			//System.out.println(couchePion + " ; " + sommetPion);
    			j0.setPosition(new Pion(j0), new Position(couchePion,sommetPion));
    			savePlateau.placer_pion(new Pion(j0), new Position(couchePion+1,sommetPion+1));//j0.getPion(pNb)
    		}
    		joueurList.add(j0);
    	}
    	int compteurDetec = 0;
    	boolean founded = true;
    	while(founded) {
    		if(fichierClasse[compteurDetec].length()>2) {
    			
    			if(fichierClasse[compteurDetec].equals("ARC")) {
    				founded = false;
    			}
    		}
    		compteurDetec++;
    	}
    	//System.out.println(fichierClasse[compteurDetec] + " : " + compteurDetec);
    	String[] etatArcs = fichierClasse[compteurDetec].split(" ");
    	int coucheX = 0;
    	int coucheY = 0;
    	//System.out.println(etatArcs.length);
    	for(int i = 0 ; i<etatArcs.length ; i++) {
    		if(coucheX==nombreCouche*nombreSommet) {
    			coucheY++;
    			coucheX = 0;
    		}
    		if(etatArcs[i].equals("true")) {
    			savePlateau.setArcs(true, coucheX, coucheY);
    		}else {
    			savePlateau.setArcs(false, coucheX, coucheY);
    		}
    		coucheX++;
    	}
    	int compteurPiege = 0;
    	while(!fichierClasse[compteurPiege].equals("PIEGE")) {
    		compteurPiege++;
    	}
    	int nombreDePiege = Integer.parseInt(fichierClasse[compteurPiege+1]);
    	if(nombreDePiege>0) {
    		for(int i = 0 ; i<nombreDePiege; i++) {
    			String[] positionPiege = fichierClasse[compteurPiege+2+i].split(" ");
    			int x = Integer.parseInt(positionPiege[0]);
    			int y = Integer.parseInt(positionPiege[1]);
    			savePlateau.setPlateau(x, y, Situations.BLOQUE);
    		}
    	}
//    	for(int p1 = 0 ; p1<nombreCouche*nombreSommet; p1++) {
//			for(int p2 = 0 ; p2<nombreCouche*nombreSommet; p2++) {
//				if(savePlateau.getArcs(p1, p2)) {
//					System.out.print("1 ");
//				}else {
//					System.out.print("0 ");
//				}
//			}
//			System.out.println("");
//		}
    	Partie savePartie = new Partie(savePlateau, nombreJoueur,nombreCouche);
    	savePartie.joueurs = joueurList;
    	//Menu.afficherPlateauCarre(savePlateau);
    	return savePartie;
    }

}
