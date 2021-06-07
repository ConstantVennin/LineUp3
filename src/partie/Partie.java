package partie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Action;

import Exceptions.PionNonExistant;
import joueurPackage.Joueur;
import joueurPackage.Pion;
import plateauPackage.Plateau;
import plateauPackage.Position;
import plateauPackage.Situations;

/**
 * Classe Partie, organise les éléments nécessaire pour jouer une Partie
 */
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

	/**
	 * Constructeur d'une Partie
	 * @param plateau
	 * @param nbJoueurs
	 * @param nbcouches
	 */
    public Partie(Plateau plateau,int nbJoueurs,int nbcouches){
        this.plateau=plateau;
        this.nbJoueurs=nbJoueurs;
        this.nbPions=plateau.getCouches()*6;  // le nombre de pions correspond aux nombres de couches*3
        joueurs=new ArrayList<Joueur>();
        initialisationJoueurs();
    }

    /**
     * Initialise des joueur en prenant en compte les configurations
     */
    public void initialisationJoueurs(){ //Initialisation automatique des joueurs, on pourra modif pour laisser la liberté aux joueurs de choisir leurs noms

        for(int indice=1;indice<nbJoueurs+1;indice++){
            joueurs.add(new Joueur("Joueur "+indice,nbPions/2));
        }
    }

    /**
     * Phase de déploiement du Jeu, les joueurs posent un à un leur pion
     */
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

    /**
     * Place aléatoirement les pions
     */
    public void PhaseDeploiementAleatoire(){

        System.out.println("RESET");
        Pion pionJoueur;
        Joueur joueurActuel;

        int pionsRestants=nbPions;

        Random random=new Random();

        int couche,sommet;

        Position positionPion;

        while(pionsRestants>0){

            if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            joueurActuel=joueurs.get(joueur);
            pionJoueur=joueurActuel.getPion(joueurActuel.getNbPionsDeploiement()-1);
            joueurActuel.diminuerPionsDeploiement();

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



    public void deplacerPion(Position anciennePosition,Position positionPion, int idJoueur){
        
        Joueur joueur=joueurs.get(idJoueur);
        
        Pion pionJoueur=null;

        try {
            pionJoueur = joueur.getPion(anciennePosition);
        } catch (PionNonExistant e) {
            e.printStackTrace();
        }

        plateau.liberer_plateau(pionJoueur.getPosition());
        pionJoueur.setPosition(positionPion);
        plateau.placer_pion(pionJoueur,positionPion);

       if(checkLineUp3(idJoueur)){
        lineUp3(idJoueur);
       }
    
    }


    public void actionJoueur(int idJoueur){

        int action=demanderAction(idJoueur);

        if(action==1){
            deplacementPion(idJoueur);
        }
        else if(action==2){

            System.out.println("Ou souhaitez vous poser un piège ? ");

            placerPiege();
        }
        else if(action==3){
            System.out.println("Entrez les 2 position qui formenty l'arc que vous souahitez couper");
        }
    }

    public void placerPiege(){

        Position p=verificationEntree();

        while(!plateau.case_libre(p)){

            System.out.println("Cette case n'est pas libre\n");
            p=verificationEntree();
        }

    plateau.placerPiege(p);
    }

    public void couperArc(){

        Position p=verificationEntree();
        Position p2=verificationEntree();

        while(!plateau.arcExiste(p, p2)){

            System.out.println("Cette arc n'existe pas\n");
            System.out.println("Veuillez entrer à nouveau les deux positions\n");

            p=verificationEntree();
            p2=verificationEntree();
        }

    plateau.couperArc(p, p2);
    }

    /**
     * Demande au joueur les pions qu'il veut déplacer et où il veut le déplacer, avec les vérifications
     * @param idJoueur
     */
    public void deplacementPion(int idJoueur){  
        
        Pion pionJoueur=null;
        Position positionPion=null;
        Position anciennePosition=null;
        System.out.println(idJoueur);
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

        while(!plateau.placementPossible(positionPion) || !plateau.arcExiste(anciennePosition, positionPion)){

            System.out.println("Vous ne pouvez pas vous déplacer ici..\n");
            System.out.println("\nSur quelle case souhaitez vous vous déplacer? : "+joueur.getName());
            positionPion=verificationEntree();
        }
    
    deplacerPion(anciennePosition,positionPion,idJoueur);
    }

    public int demanderAction(int idJoueur){

        boolean verif=false;
        String choixJoueur="";

        System.out.println("\nQuel action souhaitez vous effectuer ? : "+joueurs.get(idJoueur).getName()+"\n");
        System.out.println("1-Déplacer un pion\n2-Poser un piège\n3-Couper un arc");

        do{

            try{

                choixJoueur=actionJoueur.nextLine();
                Integer.parseInt(choixJoueur);
                verif=true;

            }catch(NumberFormatException e){
                System.out.println("\nEntree invalide, veuillez entrer 1, 2 ou 3");
            }

        }while(!verif);

    return Integer.parseInt(choixJoueur);
    }

    /**
     * Sécurise l'entrée dans le terminal
     * @return Posisition de l'entrée
     */
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
    
    /**
     * Lorsqu'un joueur aligne 3 pions, cette focntion est lancée
     * @param idJoueur
     */
    public void lineUp3(int idJoueur){

        Menu.afficherPlateauCarre(plateau);

        Position positionPion;
        Pion pionJoueur=null;
        int joueurLineup=idJoueur+1;

        System.out.println("\nLe joueur "+joueurLineup+ " a aligné 3 pions, il a donc la possibilité de retier un des pions adverse");
        System.out.println("\nQuel pion souhaitez vous retirer ?");

        positionPion=verificationEntree();

        while(plateau.case_libre(positionPion)){
            System.out.println("Il n'y a aucun joueur sur cette case");
            positionPion=verificationEntree();
        };

        while(plateau.getCase(positionPion).ordinal()==idJoueur){
            System.out.println("\nVous voulez retirer votre propre pion?...\n");
            System.out.println("\nQuel pion souhaitez vous retirer ?");
            positionPion=verificationEntree();
        }

        Situations situation=plateau.getCase(positionPion); //Regarde à quel joueur appartient le pion
        Joueur joueur=joueurs.get(situation.ordinal()); //La joueur 1 correspond à l'ordinal 1, donc si situation=JOUEUR1, on va prendre la case 0 du tableau de joueurs

        try{

            pionJoueur=joueur.getPion(positionPion);
        }catch(PionNonExistant e){} // N'est pas censé se déclencher, donc pas besoin de gérer l'exception

        plateau.liberer_plateau(positionPion);

        joueur.retirerPion(pionJoueur);

        partiFinie(joueur.getJoueurId());
    }

    /**
     * Verifie si le joueur a aligné 3 pions
     * @param idJoueur
     * @return boolean
     */
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

    /**
     * Vérifie si la partie est finie
     * @param idJoueur
     */
    public void partiFinie(int idJoueur){
        finPartie= joueurs.get(idJoueur).getNbPions()<3 ? true : false;
    }

    /**
     * getter statut de la partie
     * @return
     */
    public boolean getStatutPatie(){
        return finPartie;
    }
    
    /**
     * sauvegarde l'état présent de la partie
     */
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
		ecriture += "~ARC~";
		for(int p1 = 0 ; p1<this.plateau.getCouches()*this.plateau.getSommets(); p1++) {
			for(int p2 = 0 ; p2<this.plateau.getCouches()*this.plateau.getSommets(); p2++) {
				ecriture += this.plateau.getArcs(p1, p2) + " ";
			}
		}
		ecriture += "\n~PIEGE";
		int nombrePiege = 0;
		String placePiege = "";
		for(int p1 = 0 ; p1<this.plateau.getCouches(); p1++) {
			for(int p2 = 0 ; p2<this.plateau.getSommets(); p2++) {
				if(this.plateau.getPlateau(p1, p2).equals(Situations.BLOQUE)) {
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

    public List<Joueur> getlistJoueurs(){
        return joueurs;
    }


}
