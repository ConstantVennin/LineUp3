package plateauPackage;

import java.util.ArrayList;
import java.util.List;

public class Position {
    
    int couche;
    int sommet;
    List<Integer> transition=new ArrayList<Integer>();
    
    public Position(int couche, int sommet){

        this.couche=couche;
        this.sommet=sommet;

        transition.add(2);
        transition.add(4);
        transition.add(6);
        transition.add(8);
    
    }

    //Détermine si le joueur peut se déplacer d'une case à une autre
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

    public int getCouche(){
        return this.couche;
    }

    public int getSommet(){
        return this.sommet;
    }

    public boolean equals(Position p){
        return this.couche==p.getCouche() && this.sommet==p.getSommet() ? true : false;
    }
}
