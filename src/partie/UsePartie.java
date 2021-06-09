package partie;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Exceptions.PionNonExistant;
import Interface.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
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
    private int nbPions;

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
        nbPions=joueurs.get(0).getNbPions()*2;
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


        Pion pionJoueur;
        Joueur joueurActuel;

        joueurActuel=joueurs.get(joueur);
    
        pionJoueur=joueurActuel.getPion(joueurActuel.getNbPionsDeploiement()-1);

        joueurActuel.diminuerPionsDeploiement();


        pionJoueur.setPosition(positionPion);
        plateau.placer_pion(pionJoueur,positionPion);
            
        if(partie.checkLineUp3(joueur)){
            return 2;
        }

        joueur++;
        pionsRestants--;

        if(pionsRestants==0){
            return 0;
        }

    return 1;
    }

    public void placementAleatoire(Scene scene){

        Pion pionJoueur;

        Joueur joueurActuel;

        while(pionsRestants>0){

            if(joueur>nbJoueurs-1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            joueurActuel=joueurs.get(joueur);
        
            pionJoueur=joueurActuel.getPion(joueurActuel.getNbPionsDeploiement()-1);

            joueurActuel.diminuerPionsDeploiement();

            Random random=new Random();

            int couche,sommet;

            Position positionPion;


            couche=random.nextInt(plateau.getCouches())+1; // pour éviter d'avoir couche=0 et donc -1 à cause du constructeur de Position
            sommet=random.nextInt(plateau.getSommets())+1;
            positionPion=new Position(couche,sommet);

            Button bouttonAChanger=null;

            while(!plateau.placementPossible(positionPion) || bouttonAChanger==null){

                couche=random.nextInt(plateau.getCouches())+1;
                sommet=random.nextInt(plateau.getSommets())+1;
                positionPion=new Position(couche,sommet);

                bouttonAChanger=(Button) scene.lookup("#button_"+(positionPion.getCouche()+1)+""+(positionPion.getSommet()+1));
            }

            
            pionJoueur.setPosition(positionPion);

            plateau.placer_pion(pionJoueur,positionPion);

            if(joueur==0){

                bouttonAChanger.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3; -fx-background-color: orange;");
            }
            else{
                bouttonAChanger.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3; -fx-background-color: darkcyan;");
            }   

            joueur++;
            pionsRestants--;
        }
    }

    /**
     * Deplace le pion passe en parametre, de la position ancienne position à la position position, en verifiant que cela est possible
     * @param anciennePosition
     * @param position
     * @param idJoueur
     * @param scene
     * @return
     */
    public int deplacerPion(Position anciennePosition,Position position,int idJoueur,Scene scene){

        Button bouttonPiege=(Button) scene.lookup("#button_"+(position.getCouche()+1)+""+(position.getSommet()+1));
        Pion p=null;
        Joueur joueur=joueurs.get(idJoueur);

        if(!plateau.arcExiste(anciennePosition,position) || !plateau.case_libre(position)){

            Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas deplacer votre pion ici");
            alert.show();
        return -1;
        }

        if(partie.deplacerPion(anciennePosition, position, idJoueur)==-1){
            return 1;
        }

        try {
            p = joueur.getPion(position);
        } catch (PionNonExistant e){}

        if(bouttonPiege.getStyle().equals("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 7;")){

            joueur.bloquerPion(p);
        }
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
    public int lineUp3(Position p,Button b,int idJoueur){

        Pion pionJoueur=null;
        
        Situations situation=plateau.getCase(p); //Regarde à quel joueur appartient le pion
        
        
        Joueur joueur=null;;

        try{

            joueur=joueurs.get(situation.ordinal());
            pionJoueur=joueur.getPion(p);

        }catch(PionNonExistant | IndexOutOfBoundsException e){
            Alert alert=new Alert(AlertType.ERROR,"Ce pion n'existe pas !");
			alert.show();
        return -1;
        }

        if(pionJoueur.getJoueurId()!=idJoueur){
            Alert alert=new Alert(AlertType.ERROR,"Vous ne pouvez pas retirer votre propre pion..");
			alert.show(); 
        return -1;
        }

        plateau.liberer_plateau(p);
        
        joueur.retirerPion(pionJoueur);

        b.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3;");

        if(partie.partiFinie(joueur.getJoueurId())){

            int joueurActuel=idJoueur;
            joueurActuel++;
            if(joueurActuel>1){joueurActuel=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

            reboot(b);
        }
    
    return 0;
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

    public void bloquerArc(Position p,Position p2,int idJoueur,Scene scene){

        int[] ordre=positionsEnOrdre(p, p2);
        
        plateau.couperArc(p, p2);

        joueurs.get(idJoueur).diminuerPiegeArc();

        Polygon poly=(Polygon) scene.lookup("#arc"+ordre[0]+""+ordre[1]);
        System.out.println("#arc"+ordre[0]+""+ordre[1]);
        poly.setVisible(false);

    }

    public int getNbPiegeArc(int idjoueur){
        return joueurs.get(idjoueur).getPiegesArc();
    }

    public int getNbPiegeCase(int idjoueur){
        return joueurs.get(idjoueur).getPiegesCase();
    }

    public int[] positionsEnOrdre(Position p,Position p2){

        int coucheP1=p.getCouche()+1;
        int coucheP2=p2.getCouche()+1;
        int sommetP1=p.getSommet()+1;
        int sommetP2=p2.getSommet()+1;
        int couple=(coucheP1*10)+sommetP1;
        int couple2=(coucheP2*10)+sommetP2;

    return couple>couple2 ? new int[]{couple2,couple} : new int[]{couple,couple2};
    }

    public boolean pionBloque(int idJoueur,Scene scene,Position position){

        Joueur joueur=joueurs.get(idJoueur);
        Pion p=null;

        try {
            p = joueur.getPion(position);
        } catch (PionNonExistant e){}

        if(joueur.pionBloque(p)){

            Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas deplacer ce pion car il est bloqué");
            alert.show();
            
        return true;
        }

    return false;
    }

    private void reboot(Button b){

        Stage stagePrincipal = (Stage) b.getScene().getWindow();
        stagePrincipal.close();

        Stage stage=new Stage();
        
        Main main=new Main();

        try {
            main.start(stage);
        } catch (IOException e) {}
    }
}