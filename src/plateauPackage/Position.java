package plateauPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Position, symbolise la position d'un Pion
 */
public class Position {
    
    int couche;
    int sommet;
    List<Integer> transition=new ArrayList<Integer>();
    
    /**
     * Constructeur Position
     * @param couche
     * @param sommet
     */
    public Position(int couche, int sommet){

        this.couche=couche-1;
        this.sommet=sommet-1;
        transition.add(2);
        transition.add(4);
        transition.add(6);
        transition.add(8);
    
    }

    /**
     * Détermine si le joueur peut se déplacer d'une case à une autre
     * @param p
     * @return boolean
     */
    public boolean passagePossible(Position p){
        if(this.couche==p.couche){
            if(this.sommet==p.getSommet() || this.sommet+1==p.getSommet() || this.sommet-1==p.getSommet()){
            return true;
            }
        }
        else if(this.couche+1==p.getCouche() || this.couche-1==p.getCouche()){
            if(this.sommet==p.getSommet() && transition.contains(p.getSommet()) && transition.contains(this.getSommet())){
            return true;
            };
        }
    return false;
    }

    /**
     * getter  position x
     * @return int
     */
    public int getCouche(){
        return this.couche;
    }
    
    /**
     * getter position y
     * @return int
     */
    public int getSommet(){
        return this.sommet;
    }

    /**
     * equals des Position
     * @param p
     * @return boolean
     */
    public boolean equals(Position p){
        return this.couche==p.getCouche() && this.sommet==p.getSommet() ? true : false;
    }
}
