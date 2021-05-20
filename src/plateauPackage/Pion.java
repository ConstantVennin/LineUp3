package Pion;

import joueurPackage.Joueur;
import plateauPackage.Position;

public class Pion {

    Joueur joueur;
    Position p;

    public Pion(Joueur joueur, Position p){
        this.joueur=joueur;
        this.p=p;
    }
}
