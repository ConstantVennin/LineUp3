package  plateauPackage;

import joueurPackage.Joueur;

public class Pion {

    Joueur joueur;
    Position p;

    public Pion(Joueur joueur, Position p){
        this.joueur=joueur;
        this.p=p;
    }

    public Position getPosition(){
        return this.p;
    }

    public int getJoueurId(){
        return this.joueur.getJoueurId();
    }

}
