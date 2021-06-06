package plateauPackage;

/**
 * Classe Situation, qui permet de préciser ce qu'il y a sur une case du plateau
 */
public enum Situations {
    //ARC signifie que l'arc a ete bloque par l'un des joueurs
    //Ajouter "BLOQUE" pour toute autre raison qui ferait que la case n'est pas accessible
    JOUEUR1("1"),JOUEUR2("2"),ARC("A"),BLOQUE("/"),LIBRE("0");

    String representation;

    /**
     * Constructeur Situation
     * @param representation
     */
    Situations(String representation){
        this.representation=representation;
    }

    /**
     * getter Situation
     * @return String
     */
    public String getRepresentations(){
        return this.representation;
    }
}
