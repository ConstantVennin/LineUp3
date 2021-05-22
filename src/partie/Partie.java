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
    
    private int joueur=1;
    private Scanner deploiement=new Scanner(System.in);
    private boolean verif;
    private int[] positions;
    private Plateau plateau;
    private String entreeJoueur;
    private Position positionPion;
    int nbJoueurs;
    List<Joueur> joueurs;

    public Partie(Plateau plateau,int nbJoueurs){
        this.plateau=plateau;
        this.nbJoueurs=nbJoueurs;
        joueurs=new ArrayList<Joueur>();
    }

    public void initialisationJoueurs(){ //Initialisation automatique des joueurs, on pourra modif pour laisser la liberté aux joueurs de choisir leurs noms

        for(int indice=0;indice<nbJoueurs;indice++){

            joueurs.add(new Joueur("Joueur "+indice));
        }
    }

    public void PhaseDeploiement(int nbPions){

        int pionsRestant=nbPions;

        while(pionsRestant>0){

            System.out.println("Entrez la case ou vous voulez placer votre pion sous la forme x.y : Joueur "+ joueurs.get(joueur).getName());

                do{
                    entreeJoueur=deploiement.nextLine();
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

            joueur= nbJoueurs>joueur ? joueur+1 : 1; //Retourne à 1 quand le nombre de joueurs a été dépassé
            plateau.placer_pion(new Pion(joueurs.get(joueur),positionPion));
            pionsRestant--;
        }
    System.out.println("\nTous les pions on été placés");
    }

}
