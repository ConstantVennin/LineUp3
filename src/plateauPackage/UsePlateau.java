package plateauPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import joueurPackage.Joueur;
import partie.Menu;
import partie.Partie;

/**
 * Classe d'utilisation du plateau
 */
public class UsePlateau {

	/**
	 * Main de UsePlateau
	 * @param args
	 */
    public static void main(String args[]) {

        int joueurActuel=0;

        Scanner entreeJoueur=new Scanner(System.in);

        boolean verif=false;
        int choix=0;
    	Menu menu=new Menu();
        Menu.affichage_menu();
        int[] config=menu.getConfig();
        Plateau plateau=new Plateau(config[1],config[2]);
        Partie partie=new Partie(plateau,config[0],config[1]);
        int typePlateau= config[2]==4 ? 1 : 2;
        
        do{
            try{
                System.out.println("\nSouhaitez vous que la phase de deploiement soit aléatoire ? 1: Oui 2: Non");
                choix=entreeJoueur.nextInt();
                verif=true;
            }catch(InputMismatchException e){
                System.out.println("Veuillez entrez 1 ou 2");
            }
        }while(!verif);

        switch(choix){
            case 1:
                partie.PhaseDeploiementAleatoire(typePlateau);
                break;
            case 2:
                partie.PhaseDeploiement(typePlateau);
                break;
        }

        while(!partie.getStatutPatie()){

        	//partie.sauvergarder();
        	//Partie test = Partie.dernierePartie();

            if(joueurActuel>1) {joueurActuel=0;}
            
            if(config[2]==4){

                Menu.afficherPlateauCarre(plateau);
            }
            else{

                Menu.afficherPlateauTriangle(plateau);
            }
            
            partie.actionJoueur(joueurActuel);

            System.out.print("\n");

            
            joueurActuel++;
        }
    }
}
