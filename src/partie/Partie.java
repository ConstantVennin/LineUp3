package partie;

import java.lang.reflect.Array;
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

    public Partie(Plateau plateau){
        this.plateau=plateau;
    }

    public void PhaseDeploiement(){

        System.out.println("Entrez la case ou vous voulez placer votre pion sous la forme x.y : Joueur "+ joueur);

            do{
                entreeJoueur=deploiement.nextLine();
            try{
                
                positions=plateau.getArray(entreeJoueur);
                positionPion=new Position(positions[0],positions[1]);
                plateau.positionExiste(positionPion);
                verif=true; 

            }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                System.out.println("Le nombre entré n'est pas sous la forme x.y");
            }catch(PositionNonExistanteException e){
                System.out.println(e.getMessage()); 
            }

        }while(!verif);

        joueur++;
        plateau.placer_pion(new Pion(new Joueur(),positionPion));

    }

}
