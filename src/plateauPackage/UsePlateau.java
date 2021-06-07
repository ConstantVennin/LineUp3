package plateauPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import joueurPackage.Joueur;
import partie.Menu;
import partie.Partie;
import partie.Sauvegarde;

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
    	/*Menu menu=new Menu();
        Menu.affichage_menu();
        int[] config=menu.getConfig();
        Plateau plateau=new Plateau(config[0],config[2]*2);
        Partie partie=new Partie(plateau);*/
        int[] config=new int[]{2,3,2}; // par défault pour test le main
        Plateau plateau=new Plateau(config[1]);
        Partie partie=new Partie(plateau,config[0],config[1]);
        do{
            try{
                System.out.println("Souhaitez vous jouer la dernière partie sauvegardée ? 1: Oui 2: Non");
                choix=entreeJoueur.nextInt();
                verif=true;
            }catch(InputMismatchException e){
                System.out.println("Veuillez entrez 1 ou 2");
            }
        }while(!verif);
        
        if(choix==2) {
	        do{
	            try{
	                System.out.println("Souhaitez vous que la phase de deploiement soit aléatoire ? 1: Oui 2: Non");
	                choix=entreeJoueur.nextInt();
	                verif=true;
	            }catch(InputMismatchException e){
	                System.out.println("Veuillez entrez 1 ou 2");
	            }
	        }while(!verif);
	
	        switch(choix){
	            case 1:
	                partie.PhaseDeploiementAleatoire();
	                break;
	            case 2:
	                partie.PhaseDeploiement();
	                break;
	        }
        }else if(choix==1) {
        	partie = Sauvegarde.dernierePartie();
        	plateau = partie.getPlateau();
        	
        }

        while(!partie.getStatutPatie()){

            Position[] positionsJoueurs;

        	//partie.sauvergarder();
        	//Partie test = Partie.dernierePartie();

            if(joueurActuel>1) {joueurActuel=0;}
            Menu.afficherPlateauCarre(plateau);
            partie.actionJoueur(joueurActuel);

            System.out.print("\n");

            joueurActuel++;
        }
    }
}
