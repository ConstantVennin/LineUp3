 package test_affichage;import java.util.Scanner;



public class menu {
static int[] config=new int[] {2,3};//[0]nbjoueur [1]nbcouche
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
															+"Nombre de joueur: "+nbjoueur+"\n"
															+"Nombre de joueur: "+nbcouche+"\n\n"
															+"\t\t\t(1) Lancer la partie\n"
															+"\t\t\t(2) Changer le nombre de joueur\n"
															+"\t\t\t(3) Changer le nombre de couche\n"
			
															+"\t\t\t(4) Retour\n";}
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
		String res=plateau.getCases()[0][0].getRepresentation()+"——————————"+plateau.getCases()[0][1].getRepresentation()+"——————————"+plateau.getCases()[0][2].getRepresentation()+'\n';
			  res+="|          |          |"+'\n';
			  res+="|   "+plateau.getCases()[1][0].getRepresentation()+"——————"+plateau.getCases()[1][1].getRepresentation()+"——————"+plateau.getCases()[1][2].getRepresentation()+"   |"+'\n';
			  res+="|   |      |      |   |"+'\n';
			  res+="|   |   "+plateau.getCases()[2][0].getRepresentation()+"——"+plateau.getCases()[2][1].getRepresentation()+"——"+plateau.getCases()[2][2].getRepresentation()+"   |   |"+'\n';
			  res+="|   |   |     |   |   |"+'\n';
			  res+=plateau.getCases()[0][7].getRepresentation()+"———"+plateau.getCases()[1][7].getRepresentation()+"———"+plateau.getCases()[2][7].getRepresentation()+"     "+plateau.getCases()[2][3].getRepresentation()+"———"+plateau.getCases()[1][3].getRepresentation()+"———"+plateau.getCases()[0][3].getRepresentation()+'\n';
			  res+="|   |   |     |   |   |"+'\n';
			  res+="|   |   "+plateau.getCases()[2][6].getRepresentation()+"——"+plateau.getCases()[2][5].getRepresentation()+"——"+plateau.getCases()[2][4].getRepresentation()+"   |   |"+'\n';
			  res+="|   |      |      |   |"+'\n';
			  res+="|   "+plateau.getCases()[1][6].getRepresentation()+"——————"+plateau.getCases()[1][5].getRepresentation()+"——————"+plateau.getCases()[1][4].getRepresentation()+"   |"+'\n';
			  res+="|          |          |"+'\n';
			  res+=plateau.getCases()[0][6].getRepresentation()+"——————————"+plateau.getCases()[0][5].getRepresentation()+"——————————"+plateau.getCases()[0][4].getRepresentation()+'\n';
			  System.out.println(res);
	}
	public static void afficherPlateauTriangle(Plateau plateau) {
		/*
		 * Renvoie un plateau triangulaire
		 */
		String res="            "+plateau.getCases()[0][0].getRepresentation()+"\n";
			  res+="           / \\\n";
			  res+="          / "+plateau.getCases()[1][0].getRepresentation()+" \\\n";
			  res+="         / / \\ \\\n";
			  res+="        / / "+plateau.getCases()[2][0].getRepresentation()+" \\ \\\n";
			  res+="       / / / \\ \\ \\\n";
			  res+="      "+plateau.getCases()[0][5].getRepresentation()+"-"+plateau.getCases()[1][5].getRepresentation()+"-"+plateau.getCases()[2][5].getRepresentation()+"   "+plateau.getCases()[2][1].getRepresentation()+"-"+plateau.getCases()[1][1].getRepresentation()+"-"+plateau.getCases()[0][1].getRepresentation()+"\n";
			  res+="     / / /     \\ \\ \\\n";
			  res+="    / / "+plateau.getCases()[2][4].getRepresentation()+"---"+plateau.getCases()[2][3].getRepresentation()+"---"+plateau.getCases()[2][2].getRepresentation()+" \\ \\\n";
			  res+="   / /      |      \\ \\\n";
			  res+="  / "+plateau.getCases()[1][4].getRepresentation()+"-------"+plateau.getCases()[1][3].getRepresentation()+"-------"+plateau.getCases()[1][2].getRepresentation()+" \\\n";
			  res+=" /          |          \\\n";
			  res+=plateau.getCases()[0][4].getRepresentation()+"-----------"+plateau.getCases()[0][3].getRepresentation()+"-----------"+plateau.getCases()[0][2].getRepresentation()+"\n";
			  System.out.println(res);
	}


}
