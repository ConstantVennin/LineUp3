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
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import Exceptions.PionNonExistant;
import joueurPackage.Joueur;
import joueurPackage.Pion;
import plateauPackage.Plateau;
import plateauPackage.Position;
import plateauPackage.Situations;

public class Partie {
    
    private int joueur=0;
    private Scanner actionJoueur=new Scanner(System.in);
    private boolean verif;
    private int[] positions;
    private Plateau plateau;
    private String entreeJoueur;
    int nbJoueurs;
    int nbPions;
    List<Joueur> joueurs;
    private boolean finPartie;
    
    //Attributs pour la sauvegarde
    private static String chemin = System.getProperty("user.dir") + File.separator + "res" + File.separator;
	private static String nom = "sauvegarde.txt";

    public Partie(Plateau plateau,int nbJoueurs,int nbcouches){
        this.plateau=plateau;
        this.nbJoueurs=nbJoueurs;
        this.nbPions=plateau.getCouches()*6;  // le nombre de pions correspond aux nombres de couches*3
        joueurs=new ArrayList<Joueur>();
        initialisationJoueurs();
    }

    public void initialisationJoueurs(){ //Initialisation automatique des joueurs, on pourra modif pour laisser la liberté aux joueurs de choisir leurs noms

        for(int indice=1;indice<nbJoueurs+1;indice++){
            joueurs.add(new Joueur("Joueur "+indice,nbPions/nbJoueurs));
        }
    }

    public void PhaseDeploiement(){

        Menu.afficherPlateauCarre(plateau);
        
        Pion pionJoueur;
        Joueur joueurActuel;

        int pionsRestants=nbPions;

        while(pionsRestants>0){

            System.out.println("\n");
            plateau.Afficher_plateau();

            if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            joueurActuel=joueurs.get(joueur);
            pionJoueur=joueurActuel.getPion(joueurActuel.getNbPions()-1);
            joueurActuel.diminuerPionsRestants();
            
            System.out.println("Entrez la case ou vous voulez placer votre pion sous la forme x.y : Joueur "+ joueurs.get(joueur).getName());

            Position positionPion=verificationEntree();

            while(!plateau.placementPossible(positionPion)){
                
                System.out.println("Vous ne pouvez pas vous déplacer ici..\n");
                positionPion=verificationEntree();
            };

            pionJoueur.setPosition(positionPion);
            plateau.placer_pion(pionJoueur,positionPion);
            
            if(checkLineUp3(joueur)){
                lineUp3(joueur);
            }

            joueur++;
            pionsRestants--;

        }
    System.out.println("\nTous les pions on été placés\n");
    }

    public void PhaseDeploiementAleatoire(){

        Pion pionJoueur;
        Joueur joueurActuel;

        int pionsRestants=nbPions;

        Random random=new Random();

        int couche,sommet;

        Position positionPion;

        while(pionsRestants>0){

            if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            joueurActuel=joueurs.get(joueur);
            pionJoueur=joueurActuel.getPion(joueurActuel.getNbPions()-1);
            joueurActuel.diminuerPionsRestants();

            couche=random.nextInt(plateau.getCouches())+1; // pour éviter d'avoir couche=0 et donc -1 à cause du constructeur de Position
            sommet=random.nextInt(plateau.getSommets())+1;
            positionPion=new Position(couche,sommet);

            while(!plateau.placementPossible(positionPion)){

                couche=random.nextInt(plateau.getCouches())+1;
                sommet=random.nextInt(plateau.getSommets())+1;
                positionPion=new Position(couche,sommet);
            }


            pionJoueur.setPosition(positionPion);
            plateau.placer_pion(pionJoueur,positionPion);
            joueur++;
            pionsRestants--;
        }
    System.out.println("\nTous les pions on été placés\n");

    Menu.afficherPlateauCarre(plateau);

    }


    //Demande au joueur les pion qu'il veut déplacer et ou il veut le déplacer, avec les vérifications
    public void actionJoueur(int idJoueur){

        Pion pionJoueur=null;
        Position positionPion=null;
        Position anciennePosition=null;
        Joueur joueur=joueurs.get(idJoueur);
        boolean pionExistant=false;

        do{
            System.out.println("\nQuel pion souhaitez vous déplacer ? : "+joueur.getName());
            anciennePosition=verificationEntree();

            try{
                pionJoueur=joueur.getPion(anciennePosition);
                pionExistant=true;
            }catch(PionNonExistant e){
                System.out.println(e.getMessage());
            }
        }while(!pionExistant);

        System.out.println("\nSur quelle case souhaitez vous vous déplacer? : "+joueur.getName());
        positionPion=verificationEntree();

        while(!plateau.placementPossible(positionPion) && !plateau.arcExiste(anciennePosition, positionPion)){

            System.out.println("Vous ne pouvez pas vous déplacer ici..\n");
            System.out.println("\nSur quelle case souhaitez vous vous déplacer? : "+joueur.getName());
            positionPion=verificationEntree();
        }

        plateau.liberer_plateau(pionJoueur.getPosition());
        pionJoueur.setPosition(positionPion);
        plateau.placer_pion(pionJoueur,positionPion);

       if(checkLineUp3(idJoueur)){
        lineUp3(idJoueur);
       }
    
    }

    public Position verificationEntree(){

        Position positionPion=null;


        do{
            entreeJoueur=actionJoueur.nextLine();
        try{
            
            positions=plateau.getArray(entreeJoueur); //transforme l'entrée du joueur en double afin de le convertir en Position
            positionPion=new Position(positions[0],positions[1]);
            verif=true; 

        }catch(NumberFormatException | ArrayIndexOutOfBoundsException | NoSuchElementException e){
            System.out.println("Le nombre entré n'est pas sous la forme x.y");
            verif=false;
        }

        }while(!verif);
    
    return positionPion;
    }
    
    public void lineUp3(int idJoueur){

        Position positionPion;
        Pion pionJoueur=null;

        System.out.println("\nLe joueur "+idJoueur+1+ " a aligné 3 pions, il a donc la possibilité de retier un des pions adverse");
        System.out.println("\nQuel pion souhaitez vous retirer ?");

        positionPion=verificationEntree();

        while(plateau.case_libre(positionPion)){
            System.out.println("Il n'y a aucun joueur sur cette case");
            positionPion=verificationEntree();
        };

        Situations situation=plateau.getCase(positionPion); //Regarde à quel joueur appartient le pion
        Joueur joueur=joueurs.get(situation.ordinal()); //La joueur 1 correspond à l'ordinal 1, donc si situation=JOUEUR1, on va prendre la case 0 du tableau de joueurs

        try{

            pionJoueur=joueur.getPion(positionPion);
        }catch(PionNonExistant e){} // N'est pas censé se déclencher, donc pas besoin de gérer l'exception

        plateau.liberer_plateau(positionPion);
        joueur.retirerPion(pionJoueur);

        partiFinie(joueur.getJoueurId());
    }

    public boolean checkLineUp3(int idJoueur) {
        
    	List<Position> positions = joueurs.get(idJoueur).getPositions();
    	//positions = this.plateau.getPosition();
    	
    	for(Position p1 : positions) {
    		for(Position p2 : positions) {
    			for(Position p3 : positions) {
	    			if(p1!=null && p2!= null && p3!=null) {	
	    	    		if(!p1.equals(p2) && !p2.equals(p3) && !p3.equals(p1)) {
	    	    			if(p1.getCouche()==p2.getCouche() && p1.getCouche()==p3.getCouche()) { //alignement de couche
	    	    				if(p1.getSommet()+1==p2.getSommet() && p1.getSommet()+2==p3.getSommet()) { //alignement de couche et sommet cohérents
	    	    					return true;
	    	    				}else {//cas où le sommet vaut 6 ou 8 (le 6 ou le 8 est à côté du 1) :
	    	    					if(p1.getSommet()+1==p2.getSommet() && p2.getSommet()==(this.plateau.getSommets()-1) && p3.getSommet()==0) {//si la fonction ne marche pas retirer le "-1" et changer le 0 en 1
	    	    						return true;
	    	    					}
	    	    				}//alignement inter-couche :
	    	    			}else if(p1.getSommet()==p2.getSommet() && p1.getSommet()==p3.getSommet() && p1.getCouche()%2==0) {//si la fonction ne marche pas, ajouter "+1" avant le "%"
	    	    				if(p1.getCouche()+1==p2.getCouche() && p1.getCouche()+2==p3.getCouche()) {
	    	    					return true;
	    	    				}
	    	    			}
	    	    		}
	    			}
    	    	}
        	}
    	}
    	return false;
    }

    public void partiFinie(int idJoueur){
    	System.out.println(joueurs.get(idJoueur).getNbPions());
        finPartie= joueurs.get(idJoueur).getNbPions()<3 ? true : false;
    }

    public boolean getStatutPatie(){
        return finPartie;
    }
    
    public void sauvergarder() {
		String ecriture = "";
		ecriture += this.nbJoueurs + "\n";
		ecriture += "~" + this.plateau.getCouches() + "\n";
		ecriture += "~" + this.plateau.getSommets() + "\n";
		for(int j = 0 ; j<this.nbJoueurs ; j++) {
			Joueur currentPlayer;
			currentPlayer = this.joueurs.get(j);
			ecriture += "~N" + j + currentPlayer.getName()+"\n";
			ecriture += "~" + currentPlayer.getJoueurId() + "\n";
			ecriture += "~P" + j + currentPlayer.getPositions().size() + "\n";
			for(int p = 0 ; p<currentPlayer.getPositions().size() ; p++) {
				ecriture += "~" + currentPlayer.getPositions().get(p).getCouche() + "." + currentPlayer.getPositions().get(p).getSommet() + "\n";
			}
		}
		System.out.println(ecriture);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(chemin+nom))) {
			bw.write(ecriture);
		} catch(IOException e) {
			System.out.println("Writing error: " + e.getMessage());
			e.printStackTrace();
		}
	}
    
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
			System.out.println("File not found: ");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("Reading error: " + e.getMessage());
			e.printStackTrace();
		}
    	System.out.println(fichier);
    	String[] fichierClasse = fichier.split("~");
    	for(int i = 0 ;i<fichierClasse.length ; i++) {
    		System.out.println(fichierClasse[i]);
    	}
    	int nombreJoueur = Integer.parseInt(fichierClasse[0]);
    	int nombreCouche = Integer.parseInt(fichierClasse[1]);
    	int nombreSommet = Integer.parseInt(fichierClasse[2]);
    	System.out.println("nb joueur : "+nombreJoueur + "\n" + "Couche.sommet : "+nombreCouche + "."+nombreSommet);
    	
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
    		System.out.println(compteurDetec);
    		String name = fichierClasse[compteurDetec-1].substring(2);
    		System.out.println(name);
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
    			System.out.println(couchePion + " ; " + sommetPion);
    			j0.setPosition(new Pion(j0), new Position(couchePion,sommetPion));
    			savePlateau.placer_pion(new Pion(j0), new Position(couchePion+1,sommetPion+1));//j0.getPion(pNb)
    		}
    		joueurList.add(j0);
    	}
    	Partie savePartie = new Partie(savePlateau, nombreJoueur,nombreCouche);
    	savePartie.joueurs = joueurList;
    	Menu.afficherPlateauCarre(savePlateau);
    	return savePartie;
    }


}
