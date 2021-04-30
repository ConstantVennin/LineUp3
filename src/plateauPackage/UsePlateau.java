package plateauPackage;

import java.util.Scanner;

public class UsePlateau {


    public static void main(String args[]) {

        int joueur=1;
        
        System.out.println("Bienvenu sur Lineup3");
        System.out.println("Choisissez la taille de votre plateau (minimum 3)");
        Scanner Partie=new Scanner(System.in);
        int nombreCouches=Partie.nextInt();
        Plateau plateau=new Plateau(nombreCouches,nombreCouches*nombreCouches-1);
        System.out.println("Au cours de cette partie, vous devrez �crire la case ou vous voulez vous d�placer sous la forme num�ro couche.num�ro sommet (ex: 2.3)");

        while(true){
            
            System.out.println("Ou souhaitez vous placer votre pion? ");
            System.out.println("Au tours du joueur"+joueur);
            String localisation=Partie.nextLine();
            plateau.placer_pion(localisation,joueur);
        }
    }
}
