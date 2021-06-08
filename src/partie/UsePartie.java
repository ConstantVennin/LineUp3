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

/**
 * Permet de jouer une partie
 * @author Victor_Bastien_Constant
 */
public class UsePartie{

    int nbCotes,nbCouches,nbJoueurs;
    Joueur joueurActuel;
    private int joueur=0;
    List<Joueur> joueurs;
    private int pionsRestants;

    Plateau plateau;
    Partie partie;

    /**
     * Constructeur UsePartie
     * @param nbCotes
     * @param nbCouches
     * @param nbJoueurs
     */
    public UsePartie(int nbCotes,int nbCouches,int nbJoueurs){
        this.nbCotes=nbCotes;
        this.nbCouches=nbCouches;
        this.nbJoueurs=nbJoueurs;
        plateau=new Plateau(nbCouches,nbCotes);
        partie=new Partie(plateau,2,nbCouches);
        this.joueurs=partie.getJoueurs();
        pionsRestants=joueurs.get(0).getNbPions()*2;
    }

    /**
     * Permet d'effectuer la phase de déploiement
     * @param positionPion
     * @return int
     */
    public int phaseDeploiement(Position positionPion){

        if(joueur>1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

        System.out.println(joueur);

        if(!plateau.placementPossible(positionPion)){
                
            Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas placer votre pion ici");
            alert.show();
        return -1;
        
        }
        if(pionsRestants==0){
            return 0;
        }

        Pion pionJoueur;
        Joueur joueurActuel;

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

    /**
     * Place un Pion sur le plateau à partir d'une position
     * @param positionPion
     */
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

    public int deplacerPion(Position anciennePosition,Position position,int idJoueur){

        if(!plateau.arcExiste(anciennePosition,position)){

            Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas deplacer votre pion ici");
            alert.show();
        return -1;
        }

        partie.deplacerPion(anciennePosition, position, idJoueur);
        System.out.println("deplacé");
    return 0;
    }

    /**
     * indique si une case est libre
     * @param p
     * @return case libre vaut true
     */
    public boolean caseLibre(Position p){
       return plateau.case_libre(p); 
    }

    /**
     * Vérifie l'alignement
     * @param p
     */
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

    public boolean pionJoueur(Position p, int joueur){

        try {
            joueurs.get(joueur).getPion(p);

        } catch (PionNonExistant e) {

            Alert alert=new Alert(AlertType.WARNING,"Ce n'est pas votre pion");
            alert.show();

            return false;
        }

    return true;
    }

}