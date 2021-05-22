package plateauPackage;

import java.util.Scanner;

import partie.Menu;

public class UsePlateau {


    public static void main(String args[]) {

    	Menu menu=new Menu();
        int[] config=menu.getConfig();
        Plateau plateau=new Plateau(config[0],config[3]*2);
        Menu.affichage_menu();
        Scanner entreeJoueur=new Scanner(System.in);

        while(true){
            System.out.println("Entrez la valeur de l'arc sur le quel vous souhaitez vous déplacer");
            entreeJoueur.nextDouble();
        }
    }
}
