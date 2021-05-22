package plateauPackage;

import java.util.ArrayList;
import java.util.List;


public class Plateau{

    //Trouver un moyen de g�rer le blocage des arcs temporaires
    private int couches;
    private final int sommets;
    private final static int sommetsCarresDefault=8;
    private Situations[][] plateau;
    private boolean[][] arcs;
    List<Position> p;


    //Constructeur de la classe Plateau 
    Plateau(int couches,int nombreCote){
    	
        this.couches=couches;
        this.sommets=nombreCote*2;
        creation_plateau();
        p=new ArrayList<Position>();
        this.initialiser_positions();
    }

    //Cr�� un plateau avec un nombre de couches et de sommets pass� en param�tre ainsi que les arcs qui les relient
    public void creation_plateau(int couches, int sommets){
        this.plateau=new Situations[couches+1][sommets+1]; // J'ai mis les +1 pour éviter les index out of bound quand on met sommet 8, à modifier pour être plus opti
        this.arcs = creation_arcs();
    }
        
    Plateau(int couches){
        this(couches,sommetsCarresDefault);
    }

    //Creer un plateau avec un nombre de couches et de sommets passe en parametre ainsi que les arcs qui les relient
    public void creation_plateau(){
        plateau=new Situations[this.couches+1][this.sommets+1]; // J'ai mis les +1 pour éviter les index out of bound quand on met sommet 8, à modifier pour être plus opti
        this.arcs = creation_arcs();
        remplir_tableau();
        initialiser_arcs();

    }
    
    //Instancie une matrice d'adjacence symbolisant les connexion entre les points, vrai = passage autorisé, faux = passage
    public boolean[][] creation_arcs() {
    	int couches = this.couches;
    	int sommets = this.sommets;
		boolean[][] res = new boolean[couches*sommets][couches*sommets];
		int compteurX1 = 1;
		int compteurY1 = 0;
		int compteurX2 = 0;
		int compteurY2 = 0;
		
		for(int c = 0 ; c<(couches*sommets) ; c++) {
			compteurY1 ++;
			compteurX2 = 0;
			if(c%sommets == 0) {
					compteurX1 ++;
					compteurY1 = 1;
			}
			for(int s = 0 ; s<(couches*sommets) ; s++) {
				compteurY2 ++;
				if(s%sommets == 0) {
					compteurX2 ++;
					compteurY2 = 1;
				}
				res[c][s]=cellulesRelieesAuDepart(compteurX1, compteurY1, compteurX2, compteurY2, couches, sommets);
			}
		}
		return res;
    }

    //trouve les connexions de départ
	public boolean cellulesRelieesAuDepart(int x1, int y1, int x2, int y2, int couches, int sommets) {
		if(x1 == x2) {
			if(y1+1==y2 || y1-1==y2) {
				return true;
			}else if(y1==1&&y2==sommets || y2==1&&y1==sommets ) {
				return true;
			}else {
				return false;
			}
		}else if((y1==y2) && (y1%2==0) && (y2%2==0)) {
			if((x1+1==x2) || (x1-1==x2)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean deplacementAutorise(Position p1, Position p2) {
		Situations s = getCase(p2);
		if() {
			
		}
	}

    public void initialiser_positions(){
        for(int indice=1;indice<this.couches;indice++){
            for(int i=1;i<9;i++){ // nb de sommets toujours égal à 8
                p.add(new Position(indice,i));
            }
        }
    }
    //Fonction qui sert juste a afficher le tableau, ne sert à rien juste à part pour les test, might come in handy comme on dit dans le milieu
    public void Afficher_plateau(){

        for(int nb_couches=0;nb_couches<couches;nb_couches++){
            for(int nb_sommets=0;nb_sommets<sommets;nb_sommets++){
                System.out.println("Plateau ligne / colonne "+plateau[nb_couches][nb_sommets].name());
            }
        }
    }
    
    //Rempli le tableau par defaut avec des cases LIBRES
    private void remplir_tableau() {
    	
       for(int nb_couches=0;nb_couches<couches;nb_couches++){
    	   
    	   for(int nb_sommets=0;nb_sommets<sommets;nb_sommets++){
    		   
    		   plateau[nb_couches][nb_sommets]=Situations.LIBRE;
    	   }
       }
    }

    private void initialiser_arcs(){
        for(int nb_couches=0;nb_couches<arcs.length;nb_couches++){
    	   
            for(int nb_sommets=0;nb_sommets<arcs[0].length;nb_sommets++){
                
                arcs[nb_couches][nb_sommets]=false;
            }
        }  
    }

    //Place le pion du joueur sur le plateau
    public void placer_pion(Position p,int joueur){

        String nomJoueur= joueur==1 ? "JOUEUR1" : "JOUEUR2";
        System.out.println(p.getCouche()+" Sommet "+p.getSommet());
        plateau[p.getCouche()][p.getSommet()]=Situations.valueOf(nomJoueur);

    }

    //Lib�re la case, par exemple lorsqu'un joueur se deplace
    public void liberer_plateau(Position p){

        plateau[p.getCouche()][p.getSommet()]=Situations.LIBRE;
    }

    public void liberer_arc(Position p1, Position p2){
        
        int[] result=doubleToArc(p1,p2);
        arcs[result[0]][result[1]]=false;
    }

    //Regarde si la case donnee en parametre est libre
    public boolean case_libre(Position p){

        return plateau[p.getCouche()][p.getSommet()]==Situations.LIBRE ? true : false;
    }

    //Bloque l'arc passe en parametre
    public boolean bloquer_arc(Position p1,Position p2){
        if(!p1.passagePossible(p2)){
            return false;
        }
        int[] result=doubleToArc(p1,p2);
        arcs[result[0]][result[1]]=true;
    
    return true;
    }

    //Verifie si l'arc donne en parametre est bloque
    public boolean arc_bloque (Position p1, Position p2){

        int[] result=doubleToArc(p1,p2);

        return arcs[result[0]][result[1]];
    }

    //Transforme une chaine de charactere de type 1.1 et la transforme en tableau de int afin de pouvoir obtenir les informations dans le tableau
    public int[] getArray(String arc){ //public pour les tests, a retirer eventuellement

        String[] nombre=arc.split("\\.");

        //Essaye de convertir le String fourni en param�tre en Integer, si cela ne marche pas renvoie null 
        try{

            Integer.parseInt(nombre[0]);
            Integer.parseInt(nombre[1]);        //  A METTRE DANS AUTRE CLASSE

        }catch(NumberFormatException nombreInvalide){
            return null;    //� modifier �ventuellement
        }

        int a=Integer.parseInt(nombre[0]); //Convertit un String en int
        int b=Integer.parseInt(nombre[1]);

    return a<0 || b<0 ? null : new int[]{a,b};
    }
    
    public Situations getCase(Position p) {
    	
    	return plateau[p.getCouche()][p.getSommet()];
    }

    //Renvoie un tableau de int correspondant à l'emplacement de la liaisions des sommets passés en paramètre
    private int[] doubleToArc(Position p1,Position p2){
    
        return new int[]{p1.getCouche(),p2.getSommet()};
    }
    
    public void afficher_liaisons(){
        System.out.println(arcs[0].length);
        for(int indice=0;indice<arcs[0].length;indice++){
            for(int i=0;i<arcs[1].length;i++){
                System.out.println("Couche: "+indice+"Liaison : "+i);
            }
        }
    }
    public int getCouches() { 
    	return this.couches;
    }
    
    public int getSommets() {
    	return this.sommets;
    }

    public List<Position> getPosition(){
        return this.p;
    }

}