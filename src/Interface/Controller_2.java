package Interface;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import partie.UsePartie;
import plateauPackage.Position;

public class Controller_2 {

	private int[] config=checkConfig(Controller.getConfig());

	UsePartie partie=new UsePartie(config[1], config[0], 2);
	
	private String action;

	@FXML
	Button button_11,button_12,button_13,button_14,button_15,button_16,button_17,button_18;
	@FXML
	Button button_21,button_22,button_23,button_24,button_25,button_26,button_27,button_28;
	@FXML
	Button button_31,button_32,button_33,button_34,button_35,button_36,button_37,button_38;
	@FXML
	Button button_41,button_42,button_43,button_44,button_45,button_46,button_47,button_48;
	@FXML
	ToggleButton button_deplacement,button_posepiege,button_bloquage,button_save,button_quitter;
	@FXML
	Label label_pionj1,label_pionj2,label_tourj1,label_tourj2;
	
	@FXML
	public void actionButtonCase(ActionEvent event) {
		
		String num= event.getSource().toString().substring(17,19);
		
		Position p=new Position(Integer.parseInt(num.substring(0,1)),Integer.parseInt(num.substring(1, 2)));

		partie.placerPion(p);

		
	}

	@FXML
	public void actionButtonDeplacement(ActionEvent event) {
		action="deplacement";
	}

	@FXML
	public void actionButtonPosepiege(ActionEvent event) {
		action="piege";
	}

	@FXML
	public void actionButtonBlocage(ActionEvent event) {
		action="blocage";
	}
	
	@FXML
	public void actionButtonSave(ActionEvent event) {
		action="save";
	}


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
}
