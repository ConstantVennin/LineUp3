package partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plateauPackage.Plateau;
import plateauPackage.Position;



public class Menu {

private static int[] config=new int[] {2,3,1};//[0]nbjoueur [1]nbcouche //[2] nombre de cote, ce qui va determiner le type du plateau


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
public static String menu_jouer(int nbjoueur,int nbcouche){return	 "------------------------| Jouer |------------------------\n"
															+"\t\t\tNombre de joueur: "+nbjoueur+"\n"
															+"\t\t\tNombre de couche: "+nbcouche+"\n\n"
															+"\t\t\t(1) Lancer la partie\n"
															+"\t\t\t(2) Changer le nombre de joueur\n"
															+"\t\t\t(3) Changer le nombre de couche\n"
															+"\t\t\t(4) Type de plateau\n"
															+"\t\t\t(5) Retour\n";}
	static Scanner entry = new Scanner(System.in);
	public static int scanInt () {
		int num = entry.nextInt();
		
		return num;
	}

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
	
	public static void main(String[] args) {
		affichage_menu();
		entry.close();
	}

	public static void afficherPlateauCarre(Plateau plateau) {
		/*Affiche un plateau carré
		 * 
		 */
		String res=plateau.getCases(p.get(17)).situation+"——————————"+plateau.getCases(p.get(18)).situation+"——————————"+plateau.getCases(p.get(19)).situation+'\n';
			  res+="|          |          |"+'\n';
			  res+="|   "+plateau.getCases(p.get(9)).situation+"——————"+plateau.getCases(p.get(10)).situation+"——————"+plateau.getCases(p.get(11)).situation+"   |"+'\n';
			  res+="|   |      |      |   |"+'\n';
			  res+="|   |   "+plateau.getCases(p.get(1)).situation+"——"+plateau.getCases(p.get(2)).situation+"——"+plateau.getCases(p.get(3)).situation+"   |   |"+'\n';
			  res+="|   |   |     |   |   |"+'\n';
			  res+=plateau.getCases(p.get(24)).situation+"———"+plateau.getCases(p.get(16)).situation+"———"+plateau.getCases(p.get(8)).situation+"     "+plateau.getCases(p.get(4)).situation+"———"+plateau.getCases(p.get(12)).situation+"———"+plateau.getCases(p.get(20)).situation+'\n';
			  res+="|   |   |     |   |   |"+'\n';
			  res+="|   |   "+plateau.getCases(p.get(7)).situation+"——"+plateau.getCases(p.get(6)).situation+"——"+plateau.getCases(p.get(5)).situation+"   |   |"+'\n';
			  res+="|   |      |      |   |"+'\n';
			  res+="|   "+plateau.getCases(p.get(15)).situation+"——————"+plateau.getCases(p.get(14)).situation+"——————"+plateau.getCases(p.get(13)).situation+"   |"+'\n';
			  res+="|          |          |"+'\n';
			  res+=plateau.getCases(p.get(23)).situation+"——————————"+plateau.getCases(p.get(22)).situation+"——————————"+plateau.getCases(p.get(21)).situation+'\n';
			  System.out.println(res);
	}
	public static void afficherPlateauTriangle(Plateau plateau) {
		/*
		 * Renvoie un plateau triangulaire
		 */
		String res="            "+plateau.getCases(p.get(13)).situation+"\n";
			  res+="           / \\\n";
			  res+="          / "+plateau.getCases(p.get(7)).situation+" \\\n";
			  res+="         / / \\ \\\n";
			  res+="        / / "+plateau.getCases(p.get(1)).situation+" \\ \\\n";
			  res+="       / / / \\ \\ \\\n";
			  res+="      "+plateau.getCases(p.get(18)).situation+"-"+plateau.getCases(p.get(12)).situation+"-"+plateau.getCases(p.get(6)).situation+"   "+plateau.getCases(p.get(2)).situation+"-"+plateau.getCases(p.get(8)).situation+"-"+plateau.getCases(p.get(14)).situation+"\n";
			  res+="     / / /     \\ \\ \\\n";
			  res+="    / / "+plateau.getCases(p.get(5)).situation+"---"+plateau.getCases(p.get(4)).situation+"---"+plateau.getCases(p.get(3)).situation+" \\ \\\n";
			  res+="   / /      |      \\ \\\n";
			  res+="  / "+plateau.getCases(p.get(11)).situation+"-------"+plateau.getCases(p.get(10)).situation+"-------"+plateau.getCases(p.get(9)).situation+" \\\n";
			  res+=" /          |          \\\n";
			  res+=plateau.getCases(p.get(17)).situation+"-----------"+plateau.getCases(p.get(16)).situation+"-----------"+plateau.getCases(p.get(15)).situation+"\n";
			  System.out.println(res);
	}

	public int[] getConfig() {
		return this.config;
	}
}
