public class Plateau{

    //Trouver un moyen de g�rer le blocage des arcs temporaires
    int couches;
    int sommets;
    private Situations[][] plateau;
    private boolean[][] arcs;


    //Constructeur de la classe Plateau 
    Plateau(int couches, int sommets){
    	
        this.couches=couches;
        this.sommets=sommets;
        creation_plateau(couches, sommets);
    }

    //Cr�� un plateau avec un nombre de couches et de sommets pass� en param�tre ainsi que les arcs qui les relient
    public void creation_plateau(int couches,int sommets){
        plateau=new Situations[couches][sommets];
        arcs=new boolean[1][sommets+4];
        remplir_tableau();
        initialiser_arcs();

    }

    //Fonction qui sert juste a afficher le tableau, ne sert à rien juste à part pour les test, might come in handy comme on dit dans le milieu
    public void Afficher_plateau(){

        for(int nb_couches=0;nb_couches<couches;nb_couches++){
            for(int nb_sommets=0;nb_sommets<sommets;nb_sommets++){
                System.out.println("Plateau ligne / colonne "+plateau[nb_couches][nb_sommets].name());
            }
        }
    }
    
    //Rempli le tableau par d�faut avec des cases LIBRES
    private void remplir_tableau() {
    	
       for(int nb_couches=0;nb_couches<couches;nb_couches++){
    	   
    	   for(int nb_sommets=0;nb_sommets<sommets;nb_sommets++){
    		   
    		   plateau[nb_couches][nb_sommets]=Situations.LIBRE;
    	   }
       }
    }

    private void initialiser_arcs(){
        //System.out.println(arcs[0].length+" "+arcs[1].length);
        /*for(int nb_couches=0;nb_couches<arcs[0].length;nb_couches++){
    	   
            for(int nb_sommets=0;nb_sommets<arcs[1].length;nb_sommets++){
                
                arcs[nb_couches][nb_sommets]=false;
            }
        }*/  
    }

    //Place le pion du joueur sur le plateau
    public void placer_pion(String localisation,int joueur){

        String nomJoueur= joueur==1 ? "JOUEUR1" : "JOUEUR2";

        int[]nombre=getArray(localisation);
        plateau[nombre[0]][nombre[1]]=Situations.valueOf(nomJoueur);

    }

    //Lib�re la case, par exemple lorsqu'un joueur se d�place
    public void liberer_plateau(String localisation){

        int[] nombre=getArray(localisation);
        plateau[nombre[0]][nombre[1]]=Situations.LIBRE;
    }

    public void liberer_arc(double localisation,double localisation2){
        
        int[] result=doubleToArc(localisation, localisation2);
        arcs[result[0]][result[1]]=false;
    }

    //Regarde si la case donn�e en param�tre est libre
    public boolean case_libre(String localisation){

        int[] nombre=getArray(localisation);
        return plateau[nombre[0]][nombre[1]]==Situations.LIBRE ? true : false;
    }

    //Bloque l'arc pass� en param�tre
    public void bloquer_arc(double localisation,double localisation2){

        int[] result=doubleToArc(localisation, localisation2);
        arcs[result[0]][result[1]]=true;

    }

    //Verifie si l'arc donn� en param�tre est bloqu�
    public boolean arc_bloque (double localisation,double localisation2){

        int[] result=doubleToArc(localisation, localisation2);

        return arcs[result[0]][result[1]];
    }

    //Transforme une chaine de charact�re de type 1.1 et la transforme en tableau de int afin de pouvoir obtenir les informations dans le tableau
    public int[] getArray(String arc){ //public pour les tests, � retirer �ventuellement

        String[] nombre=arc.split("\\.");

        //Essaye de convertir le String fourni en param�tre en Integer, si cela ne marche pas renvoie null 
        try{

            Integer.parseInt(nombre[0]);
            Integer.parseInt(nombre[1]);

        }catch(NumberFormatException nombreInvalide){
            return null;    //� modifier �ventuellement
        }

        int a=Integer.parseInt(nombre[0]); //Convertit un String en int
        int b=Integer.parseInt(nombre[1]);

    return a<0 || b<0 ? null : new int[]{a,b};
    }
    
    public Situations getCase(int couche, int sommet) {
    	
    	return plateau[couche][sommet];
    }

    //Renvoie un tableau de int correspondant à l'emplacement de la liaisions des sommets passés en paramètre
    private int[] doubleToArc(double localisation,double localisation2){
        
        int entier=(int) localisation;
        int resteLocalisation2=(int) (localisation2-(int)localisation2)*10;

    return new int[]{entier,resteLocalisation2};
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

}