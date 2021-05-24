package partie;

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

            if(!plateau.placementPossible(positionPion)){
                System.out.println("Vous ne pouvez pas vous déplacer ici..\n");
            };
            pionJoueur.setPosition(positionPion);
            plateau.placer_pion(pionJoueur);
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
            plateau.placer_pion(pionJoueur);
            joueur++;
            pionsRestants--;
        }
    System.out.println("\nTous les pions on été placés\n");

    plateau.Afficher_plateau();
    }


    public void actionJoueur(int idJoueur){

        Pion pionJoueur=null;
        Position positionPion=null;
        Joueur joueur=joueurs.get(idJoueur);
        boolean pionExistant=false;

        do{
            System.out.println("\nQuel pion souhaitez vous déplacer ? : "+joueur.getName());
            positionPion=verificationEntree();

            try{
                pionJoueur=joueur.getPion(positionPion);
                pionExistant=true;
            }catch(PionNonExistant e){
                System.out.println(e.getMessage());
            }
        }while(!pionExistant);

        System.out.println("\nSur quelle case souhaitez vous vous déplacer? : "+joueur.getName());
        positionPion=verificationEntree();

        while(!plateau.placementPossible(positionPion)){

            System.out.println("Vous ne pouvez pas vous déplacer ici..\n");
            System.out.println("\nSur quelle case souhaitez vous vous déplacer? : "+joueur.getName());
            positionPion=verificationEntree();
        }

        plateau.liberer_plateau(pionJoueur.getPosition());
        pionJoueur.setPosition(positionPion);
        plateau.placer_pion(pionJoueur);
    
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
    
    public boolean partieFinie(int idJoueur) {
    	List<Position> positions = joueurs.get(idJoueur).getPositions();
    	positions = this.plateau.getPosition();
    	
    	for(Position p1 : positions) {
    		for(Position p2 : positions) {
    			for(Position p3 : positions) {
    	    		if(!p1.equals(p2) && !p2.equals(p3) && !p3.equals(p1)) {
    	    			if(p1.getCouche()==p2.getCouche() && p1.getCouche()==p3.getCouche()) {
    	    				if(p1.getSommet()+1==p2.getSommet() && p1.getSommet()+2==p3.getSommet()) {
    	    					return true;
    	    				}else {
    	    					if(p1.getSommet()+1==p2.getSommet() && p2.getSommet()==(this.plateau.getSommets()-1) && p3.getSommet()==0) {//si la fonction ne marche pas retirer le "-1" et changer le 0 en 1
    	    						return true;
    	    					}
    	    				}
    	    			}else if(p1.getSommet()==p2.getSommet() && p1.getSommet()==p3.getSommet() && p1.getCouche()%2==0) {//si la fonction ne marche pas, ajouter "+1" avant le "%"
    	    				if(p1.getCouche()+1==p2.getCouche() && p1.getCouche()+2==p3.getCouche()) {
    	    					return true;
    	    				}
    	    			}
    	    		}
    	    	}
        	}
    	}
    	return false;
    }


}
