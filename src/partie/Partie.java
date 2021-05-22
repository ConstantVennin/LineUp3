package partie;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Exceptions.PositionNonExistanteException;
import joueurPackage.Joueur;
import plateauPackage.Pion;
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

    public Partie(Plateau plateau,int nbJoueurs,int nbPions){
        this.plateau=plateau;
        this.nbJoueurs=nbJoueurs;
        this.nbPions=nbPions*8;  // le nombre de pions correspond aux nombres de couches*8
        joueurs=new ArrayList<Joueur>();
        initialisationJoueurs();
    }

    public void initialisationJoueurs(){ //Initialisation automatique des joueurs, on pourra modif pour laisser la liberté aux joueurs de choisir leurs noms

        for(int indice=1;indice<nbJoueurs+1;indice++){

            joueurs.add(new Joueur("Joueur "+indice));
        }
    }

    public void PhaseDeploiement(){

        int pionsRestants=nbPions;

        while(pionsRestants>0){

            if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            System.out.println("Entrez la case ou vous voulez placer votre pion sous la forme x.y : Joueur "+ joueurs.get(joueur).getName());

            Position positionPion=verificationEntree();

            plateau.placer_pion(new Pion(joueurs.get(joueur),positionPion));
            joueur++;
            pionsRestants--;
        }
    System.out.println("\nTous les pions on été placés");
    }

    public void actionJoueur(){
        System.out.println("Sur quelle case souhaitez vous vous déplacer?");
        Position positionPion=verificationEntree();
    }

    public Position verificationEntree(){

        Position positionPion=null;

        do{
            entreeJoueur=actionJoueur.nextLine();
        try{
            
            positions=plateau.getArray(entreeJoueur); //transforme l'entrée du joueur en double afin de le convertir en Position
            positionPion=new Position(positions[0],positions[1]);
            plateau.positionExiste(positionPion);
            verif=true; 

            if(!plateau.case_libre(positionPion)){
                System.out.println("Cette case n'est pas libre");
                verif=false;
            }

        }catch(NumberFormatException | ArrayIndexOutOfBoundsException | NoSuchElementException e){
            System.out.println("Le nombre entré n'est pas sous la forme x.y\n");
            verif=false;
        }catch(PositionNonExistanteException e){
            System.out.println(e.getMessage());
            verif=false;
        }

        }while(!verif);
    
    return positionPion;
    }

}
