package plateauPackage;

public enum Situations {
    //ARC signifie que l'arc a ete bloque par l'un des joueurs
    //Ajouter "BLOQUE" pour toute autre raison qui ferait que la case n'est pas accessible
    JOUEUR1("(1)"),JOUEUR2("(2)"),JOUEUR3("(3)"),JOUEUR4("(4)"),JOUEUR5("(5)"),ARC("A"),BLOQUE("/"),LIBRE("");

    String representation;
    int joueur;

    Situations(String representation){
        this.representation=representation;
    }
}
