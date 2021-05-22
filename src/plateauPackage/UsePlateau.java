package plateauPackage;

import java.util.Scanner;

import partie.Menu;
import partie.Partie;

public class UsePlateau {


    public static void main(String args[]) {

    	Menu menu=new Menu();
        //int[] config=menu.getConfig();
        int[] config=new int[]{2,3,8}; // par défault pour test le main
        Plateau plateau=new Plateau(config[0],config[2]*2);
        Partie partie=new Partie(plateau);
        //Menu.affichage_menu();
        //Scanner entreeJoueur=new Scanner(System.in);

        partie.PhaseDeploiement();
        /*while(true){
            System.out.println("Entrez la valeur de l'arc sur le quel vous souhaitez vous déplacer");
            //entreeJoueur.nextDouble();
        }*/
    }
}
