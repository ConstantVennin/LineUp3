package test_affichage_lineup;

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

public class Controller_2 {
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
		System.out.println("Case n°"+num+" choisis");
	}
	@FXML
	public void actionButtonDeplacement(ActionEvent event) {
		System.out.println("Deplacement choisis");
	}
	@FXML
	public void actionButtonPosepiege(ActionEvent event) {
		System.out.println("Pose Piege choisis");
	}

	@FXML
	public void actionButtonBlocage(ActionEvent event) {
		System.out.println("Blocage d'arc choisis");
	}
	
	@FXML
	public void actionButtonSave(ActionEvent event) {
		System.out.println("Sauvegarde choisis");
	}


	@FXML
	public void actionButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setHeaderText("Êtes vous sûr de vouloir quitter le jeu ?");
	 
	      // option != null.
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == null) {
	      } else if (option.get() == ButtonType.OK) {
	    	  Platform.exit();
	      } else if (option.get() == ButtonType.CANCEL) {
	      } else {
	      }
	}



}
