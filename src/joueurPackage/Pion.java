package  joueurPackage;

import plateauPackage.Position;

public class Pion {

    Joueur joueur;
    Position p;

    public Pion(Joueur joueur){
        this.joueur=joueur;
    }

    public Position getPosition(){
        return this.p;
    }

    public void setPosition(Position p){
        this.p=p;
    }

    public int getJoueurId(){
        return this.joueur.getJoueurId();
    }

    public boolean equals(Pion pion){
        return this.p==pion.getPosition() ? true : false; // pas besoin de comparer le fait que le pion appartienne au même joueur
    }                                                     // car 2 pions ne peuvent pas être sur la même case

}
