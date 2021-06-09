package Interface;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import joueurPackage.Joueur;
import partie.UsePartie;
import plateauPackage.Position;
import plateauPackage.Situations;

/**
 * Classe Controller_2 : pour utiliser les boutons de l'interface lors du jeu
 * @author Victor_Bastien_Constant
 */
public class Controller_2 {

	private int[] config=checkConfig(Controller.getConfig());

	UsePartie partie=new UsePartie(config[1], config[0], 2);
	
	private String action="";

	private int joueur=0;

	private String phase="deploiement";

	private Position anciennePosition;

	private Position positionPion;

	private int compteur;
	
	private int nombrePosition=0;

	private int compteurDeTours=0;

	private int tourActuel;


	@FXML
	ToggleButton button_deplacement,button_posepiege,button_bloquage,button_save,button_quitter,button_charger,button_alea;
	@FXML
	Label label_pionj1,label_pionj2,label_tourj1,label_tourj2;
	@FXML
	Group phase_deploiement,phase_confrontation;
	

	public void changerJoueur(){

		if(joueur>1){joueur=0;}  //Retourne à 1 quand le nombre de joueurs a été dépassé

		if(joueur==0){
			label_tourj1.setVisible(true);
			label_tourj2.setVisible(false);
		}
		if(joueur==1){
			label_tourj1.setVisible(false);
			label_tourj2.setVisible(true);
		}
	
	}

	/**
	 * Bouton d'action pour Case
	 * @param event
	 */
	@FXML
	public void actionButtonCase(ActionEvent event) {

		compteurDeTours++;

		changerJoueur();

		String num= event.getSource().toString().substring(17,19);
		
		Position p=new Position(Integer.parseInt(num.substring(0,1)),Integer.parseInt(num.substring(1, 2)));
		
		Button b=(Button) event.getSource();

		switch(phase){

			case "deploiement":
			deploiement(p,b);
			break;

			case "confrontation":
			confrontation(p, b);
			break;

			case "lineup":
			partie.lineUp3(p, b,joueur);
			break;

		}

		switch(action){

			case "piege":
			piege(p, b);
			break;

			case "blocage":
			bloquerArc(p,b.getScene());
			break;
		}

	}

	@FXML
	public void actionButtonPlacer(ActionEvent event){
		phase="deploiement";
	}

	@FXML
	public void actionButtonAlea(ActionEvent event){

		ToggleButton toggle=(ToggleButton) event.getSource();
		Scene scene=(Scene) toggle.getScene();
		partie.placementAleatoire(scene);
		phase="confrontation";
		phase_deploiement.setVisible(false);
		phase_confrontation.setVisible(true);
	}

	@FXML 
	public void actionButtonCharger(ActionEvent event){

	}
	/**
	 * Bouton d'action de déplacement
	 * @param event
	 */
	@FXML
	public void actionButtonDeplacement(ActionEvent event) {
		action="deplacement";
	}

	/**
	 * Bouton d'action pour pose de piège
	 * @param event
	 */
	@FXML
	public void actionButtonPosepiege(ActionEvent event) {
		action="piege";
	}

	/**
	 * Bouton d'action pour blocage
	 * @param event
	 */
	@FXML
	public void actionButtonBlocage(ActionEvent event) {
		action="blocage";
	}
	
	/**
	 * Bouton d'action pour sauvegarde
	 * @param event
	 */
	@FXML
	public void actionButtonSave(ActionEvent event) {
		action="save";
	}


	/**
	 * Bouton d'action pour quitter
	 * @param event
	 */
	@FXML
	public void actionButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setHeaderText("Etes vous sur de vouloir quitter le jeu ?\nAvez-vous sauvegarde ?");
	 
	      // option != null.
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == null) {
	      } else if (option.get() == ButtonType.OK) {
	    	  Platform.exit();
	      } else if (option.get() == ButtonType.CANCEL) {
	      } else {
	      }
	}

	/**
	 * Vérification des configurations
	 * @param config
	 * @return Tableau de configuration, int[]
	 */
	public int[] checkConfig(String config){

		int[] configs=new int[2];

		if(config.equals("carre3")){

			configs[0]=3;			
			configs[1]=4;

		}else if(config.equals("carre4")){
			configs[0]=4;			
			configs[1]=4;
		}
		else{
			configs[0]= config.equals("triangle3") ? 3 : 4;
			configs[1]= 3;
		}

	return configs;
	}

	public void setStyle(Button b){

		if(joueur==0){

			b.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3; -fx-background-color: orange;");
		}
		else{
			b.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3; -fx-background-color: darkcyan;");
		}
	}

	public void confrontation(Position p,Button b){

		int deplacerPion;

		if(action.equals("deplacement")){

			if(compteur==1){

				positionPion=p;
				deplacerPion=partie.deplacerPion(anciennePosition, positionPion, joueur,b.getScene());

				if(deplacerPion==0){

					setStyle(b);
					joueur++;
					changerJoueur();
					compteur=0;
				}
				else if(deplacerPion==1){
					setStyle(b);
					joueur++;
					lineup();
				}
			}

			else if(compteur==0){

				if(partie.pionBloque(joueur, b.getScene(), p)){
					return;

				}

				if(partie.pionJoueur(p, joueur)){

					b.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 3;");
					anciennePosition=p;
					compteur++;

				}
			}
			
		}
	}

	public void piege(Position p,Button b){

		if(partie.caseLibre(p)){
			b.setStyle("-fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black; -fx-border-width: 7;");
			joueur++;
			changerJoueur();
		}
		else{
		
			Alert alert=new Alert(AlertType.WARNING,"Vous ne pouvez pas poser un piège sur un joueur");
			alert.show();
		}
	}
	
	public void deploiement(Position p,Button b){

		button_alea.setVisible(false);
		
		int deploiement;
		deploiement=partie.phaseDeploiement(p);

		setStyle(b);
		joueur++;

		if(deploiement==0){

			joueur=0;
			phase="confrontation";
			phase_deploiement.setVisible(false);
			phase_confrontation.setVisible(true);
			changerJoueur();
		
		}

		else if(deploiement==2){
			lineup();
		}

	}

	public void lineup(){

		int joueurActuel=joueur;
		joueurActuel++;
		if(joueurActuel>1){joueurActuel=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé

		Alert alert=new Alert(AlertType.INFORMATION,"Le joueur "+Situations.values()[joueurActuel].getRepresentations()+" a aligné 3 pions ! il peut donc retirer un pion adverse, selectionnez le pion à retirer\n\n(Attention, si le joueur en face n'a plus que 2 pions, la partie se terminera et vous retournerez au menu)");
		alert.show();
		phase="lineup";
	}

	public void bloquerArc(Position p,Scene scene){
		
		if(partie.getNbPiegeArc(joueur)<1){
			Alert alert=new Alert(AlertType.ERROR,"Vous ne pouvez plus bloquer d'arc");
			alert.show();

		return;
		}

		tourActuel=compteurDeTours;

		if(nombrePosition==1){

			positionPion=p;
			partie.bloquerArc(anciennePosition, positionPion,joueur,scene);
			joueur++;
			changerJoueur();
			nombrePosition=-1;
		}
		else{
			anciennePosition=p;
		}

	nombrePosition++;
	}
}
