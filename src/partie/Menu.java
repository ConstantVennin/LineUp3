package partie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import plateauPackage.Plateau;
import plateauPackage.Position;


/**
 * Classe Menu, pour naviguer dans le menu, avant de joueur dans une partie
 * @author Victor_Bastien_Constant
 */
public class Menu {


private static int[] config=new int[] {2,3,4,0};//[0]nbjoueur [1]nbcouche //[2] nombre de cote, ce qui va determiner le type du plateau

static String myPath=System.getProperty("user.dir")+File.separator+"res"+File.separator;
private static String typePlateau="carre3.txt";
private static boolean finMenu=false;

static String menu_LineUp3=  "           _      _              _    _         ____           \r\n"
					+ "          | |    (_)            | |  | |       |___ \\          \r\n"
					+ "  ______  | |     _ _ __   ___  | |  | |_ __     __) |  ______ \r\n"
					+ " |______| | |    | | '_ \\ / _ \\ | |  | | '_ \\   |__ <  |______|\r\n"
					+ "          | |____| | | | |  __/ | |__| | |_) |  ___) |         \r\n"
					+ "          |______|_|_| |_|\\___|  \\____/| .__/  |____/          \r\n"
					+ "                                       | |                     \r\n"
					+ "                                       |_|                     ";

static String menu_base=	 "-------------------------| MENU |-------------------------\n"
					+ "\t\t\t(1) Jouer\n"
					+ "\t\t\t(2) Règle\n"
					+ "\t\t\t(3) Quitter\n";
static String menu_regle=	 "------------------------| Règle |------------------------\n\n"
					+"Déroulement d’une partie: Une partie de LineUp3 se déroule en deux phases: une phrase de déploiement et une phase de confrontation.\n"
					+ "En début de partie, le plateau est vierge. Dans la première phase, la seule action utilisable parles joueurs est la pose d’un pion.\n"
					+ "Ils vont tour à tour poser un de leur pions sur le plateau. Une fois tous les pions en jeu, la seconde phase est enclenchée.\n"
					+ "Les joueurs disposent d’autres actions qu’ils peuvent utiliser à tour de rôle afin d’aligner leurs trois pions.\n"
					+ "Ces actions sont le déplacement d'un pion, le blocage d'un arc ou la pose d'un piège.\n\n"
					+ "Conditions de victoire: Un joueur gagne dès que ses trois pions sont alignés.\n\n"
					+ "\t\t\t(1) Jouer\n"
					+ "\t\t\t(2) Retour\n";
/**
 * Ecriture du menu jouer
 * @param nbjoueur
 * @param nbcouche
 * @return menu "Jouer"
 */
public static String menu_jouer(int nbjoueur,int nbcouche){return	 "------------------------| Jouer |------------------------\n\n"
															+"\t\t\t(1) Lancer la partie\n"
															+"\t\t\t(2) Changer le nombre de joueur\n"
															+"\t\t\t(3) Type de plateau\n"
															+"\t\t\t(4) Charger la partie\n"
															+"\t\t\t(5) Retour\n";}
	
	static Scanner entry = new Scanner(System.in);
	/**
	 * Scanner d'int
	 * @return entrée
	 */
	public static int scanInt () {
		int num = entry.nextInt();
		
		return num;
	}

	/**
	 * Affichage du menu principal
	 */
	public static void affichage_menu() {

		int num=0;

		do {

		if(finMenu){
			return;
		}
		
		System.out.println(menu_LineUp3);
		System.out.println(menu_base);

		num=scanInt();

		switch(num) {
		case 1: affichage_jouer(config);
				break;
			
		case 2 : affichage_regle();
				break;
		case 3 :System.exit(0);
				break;
		default: System.out.println("Entrée incorrect");}
		}while(num !=3);
	}
	
	/**
	 * Affichage des règles
	 */
	public static void affichage_regle() {
		
		int num=0;
		
		do {
			
		System.out.println(menu_regle);
		
		num=scanInt();
		
		do{
			System.out.println("Entree invalide, veuillez sélectionner un chiffre compris entre 1 et 2");
			num=scanInt();
		}while(num<0 || num>2);
		
		
		do{
			System.out.println("Entree invalide, veuillez sélectionner un chiffre compris entre 1 et 2");
			num=scanInt();
		}while(num<0 || num>2);

		switch(num) {
		case 1:affichage_jouer(config);
				break;
			
		case 2 : affichage_menu();
				break;
		default: System.out.println("Entrée incorrect");}
		}while(num !=2);
	}
	
	/**
	 * Pour sélectionner les paramètres de la partie
	 * @param config
	 */
	public static void affichage_jouer(int[] config) {
		int num=0;
		do {

		System.out.println(menu_jouer(config[0],config[1]));
		num=scanInt();
		
		do{
			System.out.println("Entree invalide, veuillez sélectionner un chiffre compris entre 1 et 5");
			num=scanInt();
		}while(num<1 || num>5);

		switch(num) {

		case 1: finMenu=true;
				return;

		case 2 : System.out.println("Entrez le nombre de joueur");
				num=scanInt();
				config[0]=num;
				break;
		case 3:
				System.out.println("Quel type de plateau souhaitez vous?\n\n1-Carre couche 3\n2-Carre couche 4\n3-Triangle couche 3\n4-Triangle couche 4");
				num=scanInt();
				
				do{
					System.out.println("Entree invalide, veuillez sélectionner un chiffre compris entre 1 et 4");
					num=scanInt();
				}while(num<0 || num>4);
				
				typePlateau(num);
				break;
		
		case 4:
			config[3]=1;
			finMenu=true;
			break;

		case 5: affichage_menu();
				break;

		default: System.out.println("Entrée incorrect");}
		}while(num !=5);
	}
	

	/**
	 * Affiche le plateau carré
	 * @param plateau
	 */
    public static void afficherPlateauCarre(Plateau plateau) {

        try {

            BufferedReader fr = new BufferedReader(new FileReader(myPath+typePlateau));
            String chaine="";
            int c = fr.read();
            while(c!=-1) {

                if(c>='A' && c<='`') {

					chaine+=plateau.getCase(new Position((c-'A')/8+1,(c-'A')%8+1)).getRepresentations();
					
                }else {

                    chaine+=(char) c;
                }
                c = fr.read();
            }
            System.out.println(chaine);
            fr.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found: "); e.printStackTrace();
        } catch(IOException e) {
            System.out.println("Reading error: " + e.getMessage());
            e.printStackTrace();
        }
    }

	/**
	 * Retourne un typePlateau
	 * @return the typePlateau
	 */
	public static String getTypePlateau() {
		return typePlateau;
	}

	/**
	 * Setter de typePlateau
	 * @param typePlateau the typePlateau to set
	 */
	public static void setTypePlateau(String typePlateau) {
		Menu.typePlateau = typePlateau;
	}

	/**
	 * Affiche le plateau Triangulaire
	 * @param plateau
	 */
    public static void afficherPlateauTriangle(Plateau plateau) {

		System.out.println(plateau.getCouches());
		System.out.println(plateau.getSommets());
        try {

            BufferedReader fr = new BufferedReader(new FileReader(myPath+typePlateau));
            String chaine="";
            int c = fr.read();
            while(c!=-1) {

                if(c>='A' && c<='X') {
					chaine+=plateau.getCase(new Position((c-'A')/6+1,(c-'A')%6+1)).getRepresentations();
					
                }else {

                    chaine+=(char) c;
                }
                c = fr.read();
            }
            System.out.println(chaine);
            fr.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found: "); e.printStackTrace();
        } catch(IOException e) {
            System.out.println("Reading error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Créé les bonnes configurations pour jouer la partie
     * @param idx
     */
	public static void typePlateau(int idx){

		switch(idx){

			case 1:
				typePlateau="carre3.txt";
				config[1]=3;
				config[2]=4;
				break;

			case 2:
				typePlateau="carre4.txt";
				config[1]=4;
				config[2]=4;
				break;

			case 3:
				typePlateau="triangle3.txt";
				config[1]=3;
				config[2]=3;
				break;

			case 4:
				typePlateau="triangle4.txt";
				config[1]=4;
				config[2]=3;
				break;
		}
	}
    /**
     * getter des configurations choisies
     * @return
     */
	public int[] getConfig() {
		return config;
	}
}
