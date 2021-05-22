package partie;

import java.util.Scanner;

import joueurPackage.Joueur;
import plateauPackage.Pion;
import plateauPackage.Plateau;
import plateauPackage.Position;

public class Partie {
    
    private int joueur;
    private Scanner deploiement=new Scanner(System.in);
    private boolean verif;
    private int[] positions;
    Plateau plateau;

    public Partie(Plateau plateau){
        this.plateau=plateau;
    }

    public void PhaseDeploiement(){
        System.out.println("Entrez la case ou vous voulez placer votre pion sous la forme x.y : Joueur "+ joueur);

        do{
            try{

                positions=plateau.getArray(deploiement.nextLine());
                plateau.placer_pion(new Pion(new Joueur(),new Position(positions[0],positions[1])));
                verif=true; 
            }catch(NumberFormatException e){
                System.out.println("Le nombre entré n'est pas sous la forme x.y");
            }
        }while(!verif);

    }

}
