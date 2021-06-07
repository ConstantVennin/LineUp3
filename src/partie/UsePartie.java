package partie;

import java.util.List;

import Exceptions.PionNonExistant;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import joueurPackage.Joueur;
import joueurPackage.Pion;
import plateauPackage.Plateau;
import plateauPackage.Position;
import plateauPackage.Situations;

public class UsePartie{

    int nbCotes,nbCouches,nbJoueurs;
    Joueur joueurActuel;
    private int joueur=0;
    List<Joueur> joueurs;

    Plateau plateau;
    Partie partie;

    public UsePartie(int nbCotes,int nbCouches,int nbJoueurs){
        this.nbCotes=nbCotes;
        this.nbCouches=nbCouches;
        this.nbJoueurs=nbJoueurs;
        plateau=new Plateau(nbCouches,nbCotes);
        partie=new Partie(plateau,2,nbCouches);
        this.joueurs=partie.getJoueurs();
    }

    public int phaseDeploiement(Position positionPion){

        int joueur=0;

        if(!plateau.placementPossible(positionPion)){
                
            Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas placer votre pion ici");
            alert.show();
            
        return -1;
        };

        Pion pionJoueur;
        Joueur joueurActuel;

        int pionsRestants=joueurs.get(0).getNbPions()*2;

        if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

        joueurActuel=joueurs.get(joueur);
    
        pionJoueur=joueurActuel.getPion(joueurActuel.getNbPionsDeploiement()-1);

        joueurActuel.diminuerPionsDeploiement();


        pionJoueur.setPosition(positionPion);
        plateau.placer_pion(pionJoueur,positionPion);
            
        if(partie.checkLineUp3(joueur)){
            lineUp3(positionPion);
        }

        joueur++;
        pionsRestants--;

    return 1;
    }

    public void placerPion(Position positionPion){

        if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

        joueurs=partie.getJoueurs();

        Pion pionJoueur=null; 

        joueurActuel=joueurs.get(joueur);

        pionJoueur=joueurActuel.getPion(joueurActuel.getNbPions()-1);

        pionJoueur.setPosition(positionPion);

        plateau.placer_pion(pionJoueur,positionPion);

        joueur++;

        joueurActuel.diminuerPionsRestants();

    }

    public boolean caseLibre(Position p){
       return plateau.case_libre(p); 
    }

    public void lineUp3(Position p){

        Pion pionJoueur=null;
        
        Situations situation=plateau.getCase(p); //Regarde à quel joueur appartient le pion
        
        Joueur joueur=joueurs.get(situation.ordinal()); //La joueur 1 correspond à l'ordinal 1, donc si situation=JOUEUR1, on va prendre la case 0 du tableau de joueurs

        try{

            pionJoueur=joueur.getPion(p);

        }catch(PionNonExistant e){} // N'est pas censé se déclencher, donc pas besoin de gérer l'exception

        plateau.liberer_plateau(p);
        
        joueur.retirerPion(pionJoueur);

        partie.partiFinie(joueur.getJoueurId());

    }


}