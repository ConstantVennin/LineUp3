package plateauPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import joueurPackage.Joueur;
import partie.Menu;
import partie.Partie;
import partie.Sauvegarde;
import partie.IA;

/**
 * Classe d'utilisation du plateau
 * @author Victor_Bastien_Constant
 */
public class UsePlateau {

	/**
	 * Main de UsePlateau pour partie textuelle
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
        boolean ia=false;
        
        if(config[3]==1) {

        	partie = Sauvegarde.dernierePartie();
        	plateau = partie.getPlateau();
        	if(plateau.getSommets()==6 && plateau.getCouches()==3) {
        		config[2]=3;
        		config[1]=3;
        		Menu.setTypePlateau("triangle3.txt");
        	}else if(plateau.getSommets()==6 && plateau.getCouches()==4) {
        		config[2]=3;
        		config[1]=4;
        		Menu.setTypePlateau("triangle4.txt");
        	}else if(plateau.getSommets()==8 && plateau.getCouches()==3) {
        		config[2]=4;
        		config[1]=3;
        		Menu.setTypePlateau("carre3.txt");
        	}else if(plateau.getSommets()==8 && plateau.getCouches()==4) {
        		config[2]=4;
        		config[1]=4;
        		Menu.setTypePlateau("carre4.txt");
        	}
        	
	    }else {
//	    	System.out.println("Jouez vous à deux ou contre une IA ? 1:Oui 2:Non");
//        	choix=entreeJoueur.nextInt();
//        	if(choix==1) {
//        		ia = true;
//        		partie.PhaseDeploiementAleatoire(typePlateau);
//        	}else {
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
//        	}
	    }

        while(!partie.getStatutPatie()){

        	//partie.sauvergarder();
        	//Partie test = Partie.dernierePartie();

            if(joueurActuel>1) {joueurActuel=0;}
            System.out.println(config[2] + " " + config[3]);
            if(config[2]==4){

                Menu.afficherPlateauCarre(plateau);
            }
            else{

                Menu.afficherPlateauTriangle(plateau);
            }
            if(joueurActuel==1&&ia) {
            	Position[] pos = IA.placerPionIA(partie.getPlateau(), Situations.JOUEUR2);
            	partie.deplacerPion(pos[0], pos[1], joueurActuel);;
            }else {
            	partie.actionJoueur(joueurActuel);
            }
            

            System.out.print("\n");

            
            joueurActuel++;
        }
    }
}
