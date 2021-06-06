package partie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plateauPackage.Plateau;
import plateauPackage.Position;


/**
 * Classe Menu, pour naviguer dans le menu, avant de joueur dans une partie
 */
public class Menu {


private static int[] config=new int[] {2,3,1};//[0]nbjoueur [1]nbcouche //[2] nombre de cote, ce qui va determiner le type du plateau

static String myPath=System.getProperty("user.dir")+File.separator+"res"+File.separator;
static String Carre3File = "carre3.txt";
static String Carre4File = "carre4.txt";
static String Triangle3File = "triangle3.txt";
static String Triangle4File = "triangle4.txt";


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
 * @param nbjoueur
 * @param nbcouche
 * @return menu "Jouer"
 */
public static String menu_jouer(int nbjoueur,int nbcouche){return	 "------------------------| Jouer |------------------------\n"
															+"\t\t\tNombre de joueur: "+nbjoueur+"\n"
															+"\t\t\tNombre de couche: "+nbcouche+"\n\n"
															+"\t\t\t(1) Lancer la partie\n"
															+"\t\t\t(2) Changer le nombre de joueur\n"
															+"\t\t\t(3) Changer le nombre de couche\n"
															+"\t\t\t(4) Type de plateau\n"
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
	 * @return tableau int
	 */
	public static int[] affichage_jouer(int[] config) {
		int num=0;
		do {
		System.out.println(menu_jouer(config[0],config[1]));
		num=scanInt();
		switch(num) {
		case 1: System.out.println("Lancement de la partie en cours ...");
				break;
			
		case 2 : System.out.println("Entrée nombre de joueur");
				num=scanInt();
				config[0]=num;
				break;
		case 3:
				System.out.println("Entrée nombre de couche");
				num=scanInt();
				config[1]=num;
				break;
		case 4: affichage_menu();
				break;
		default: System.out.println("Entrée incorrect");}
		}while(num !=4);
	return config;
	}
	
	/**
	 * main de Menu
	 * @param args
	 */
	public static void main(String[] args) {
		affichage_menu();
		entry.close();
	}

	/**
	 * Affiche le plateau carré
	 * @param plateau
	 */
    public static void afficherPlateauCarre(Plateau plateau) {

        try {

            BufferedReader fr = new BufferedReader(new FileReader(myPath+Carre3File));
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
     * getter des configurations choisies
     * @return
     */
	public int[] getConfig() {
		return this.config;
	}
}
