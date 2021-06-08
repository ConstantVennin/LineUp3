package Interface;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import partie.UsePartie;
import plateauPackage.Position;

/**
 * Classe Controller_2 : pour utiliser les boutons de l'interface lors du jeu
 * @author Victor_Bastien_Constant
 */
public class Controller_2 {

	private int[] config=checkConfig(Controller.getConfig());

	UsePartie partie=new UsePartie(config[1], config[0], 2);
	
	private String action;

	private int nbPions;

	private int joueur=0;

	private String phase="deploiement";

	private Position anciennePosition;

	private Position positionPion;

	private int compteur;

	private int prev;

	@FXML
	Button button_11,button_12,button_13,button_14,button_15,button_16,button_17,button_18;
	@FXML
	Button button_21,button_22,button_23,button_24,button_25,button_26,button_27,button_28;
	@FXML
	Button button_31,button_32,button_33,button_34,button_35,button_36,button_37,button_38;
	@FXML
	Button button_41,button_42,button_43,button_44,button_45,button_46,button_47,button_48;
	@FXML
	ToggleButton button_deplacement,button_posepiege,button_bloquage,button_save,button_quitter,button_charger;
	@FXML
	Label label_pionj1,label_pionj2,label_tourj1,label_tourj2;
	@FXML
	Group phase_deploiement,phase_confrontation;
	

	public void changerJoueur(){

		if(joueur==0){
			label_tourj1.setVisible(false);
			label_tourj2.setVisible(true);
		}
		if(joueur==1){
			label_tourj1.setVisible(true);
			label_tourj2.setVisible(false);
		}
	}

	/**
	 * Bouton d'action pour Case
	 * @param event
	 */
	@FXML
	public void actionButtonCase(ActionEvent event) {

		int deploiement;

		if(joueur>1){joueur=0;} //Retourne à 1 quand le nombre de joueurs a été dépassé
		
		changerJoueur();

		String num= event.getSource().toString().substring(17,19);
		
		Position p=new Position(Integer.parseInt(num.substring(0,1)),Integer.parseInt(num.substring(1, 2)));
		
		Button b=(Button) event.getSource();

		if(phase.equals("deploiement")){

			deploiement=partie.phaseDeploiement(p);

			if(deploiement==0){

				phase="confrontation";
				phase_deploiement.setVisible(false);
				phase_confrontation.setVisible(true);
				changerJoueur();
			}

			else if(deploiement==1){

				setStyle(b);

				joueur++;
			}
		}

		else if(phase.equals("confrontation")){

			System.out.println(compteur);

			if(compteur==1){

				positionPion=p;
				
				compteur=-1;

				System.out.println(p.getCouche()+" "+p.getSommet());

				if(partie.deplacerPion(anciennePosition, positionPion, joueur)==0){

					setStyle(b);
					System.out.println("ntmqgqgqh");

					joueur++;
				}


			}

			else{

				if(partie.pionJoueur(p, joueur)){

					System.out.println(p.getCouche()+" "+p.getSommet());
					anciennePosition=p;
				}

				else{
					compteur++;
				}
			}
		}
	}

	@FXML
	public void actionButtonPlacer(ActionEvent event){

		
	}

	@FXML
	public void actionButtonAlea(ActionEvent event){

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
	      alert.setHeaderText("�tes vous s�r de vouloir quitter le jeu ?\nAvez-vous sauvegard� ?");
	 
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
}

