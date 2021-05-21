package plateauPackage;

import java.util.Scanner;

import partie.Menu;

public class UsePlateau {


    public static void main(String args[]) {
    	
    	Menu menu=new Menu();
    	Plateau plateau=new Plateau(menu.getConfig()[1]);
    	
        int joueur=1;
        Menu.affichage_menu();
        while(true){
        
        }
    }
}
