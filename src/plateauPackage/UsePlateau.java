package plateauPackage;

import java.util.Scanner;

public class UsePlateau {


    public static void main(String args[]) {
    	
    	Menu menu=new Menu();
    	Plateau plateau=new Plateau(menu.getConfig()[1]);
    	
        int joueur=1;
        menu.affichage_menu();
        while(true){
            ;
        }
    }
}
