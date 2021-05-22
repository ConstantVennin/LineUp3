package plateauPackage;

import java.util.Scanner;

import partie.Menu;
import partie.Partie;

public class UsePlateau {


    public static void main(String args[]) {

    	/*Menu menu=new Menu();
        Menu.affichage_menu();
        int[] config=menu.getConfig();
        Plateau plateau=new Plateau(config[0],config[2]*2);
        Partie partie=new Partie(plateau);*/
        int[] config=new int[]{2,3,2}; // par défault pour test le main
        Plateau plateau=new Plateau(config[1]);
        Partie partie=new Partie(plateau,config[0],config[1]);
        partie.PhaseDeploiement();
        plateau.Afficher_plateau();
        while(true){
            System.out.println("Entrez la valeur de l'arc sur le quel vous souhaitez vous déplacer");
            //entreeJoueur.nextDouble();
        }
    }
}
