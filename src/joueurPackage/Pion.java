package  joueurPackage;

import plateauPackage.Position;

/**
 * Classe Pion, Les Pion sont des objets appartenants à des joueur, et sont posés sur un plateau
 * @author Victor_Bastien_Constant
 */
public class Pion {

    Joueur joueur;
    Position p;

    /**
     * Constructeur Pion
     * @param joueur
     */
    public Pion(Joueur joueur){
        this.joueur=joueur;
    }

    /**
     * Getter Position du Pion courant
     * @return Position du Pion
     */
    public Position getPosition(){
        return this.p;
    }

    /**
     * Setter Position du Pion courant
     * @param p
     */
    public void setPosition(Position p){
        this.p=p;
    }

    /**
     * Getter Id du joueur à qui le Pion appartient
     * @return joueurId
     */
    public int getJoueurId(){
        return this.joueur.getJoueurId();
    }

    /**
     * Methode equals pour Pion
     * @param pion
     * @return egalite
     */
    public boolean equals(Pion pion){
        return this.p==pion.getPosition() ? true : false; // pas besoin de comparer le fait que le pion appartienne au même joueur
    }                                                     // car 2 pions ne peuvent pas être sur la même case

}
