package test_affichage_lineup;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller {
	public static String plateau="carre3";
	@FXML
    Button button_solo,button_multi,button_triangle,button_carre,button_regle,button_video,button_quitter;
	
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
	@FXML
	public void actionButtonRegle(ActionEvent event) throws IOException {
		Stage stage = (Stage) button_regle.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource("menu_regle.fxml");
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
		System.out.println("Regle choisis");
	}
	@FXML
	public void actionButtonTriangle3(ActionEvent event) {
		plateau="triangle3";
		System.out.println("Triangle 3 choisis");
	}
	@FXML
	public void actionButtonCarre3(ActionEvent event) {
		plateau="carre3";
		System.out.println("Carré 3 choisis");	
	}
	@FXML
	public void actionButtonTriangle4(ActionEvent event) {
		plateau="triangle4";
		System.out.println("Triangle 4 choisis");
	}
	@FXML
	public void actionButtonCarre4(ActionEvent event) {
		plateau="carre4";
		System.out.println("Carré 4 choisis");	
	}
	@FXML
	public void actionButtonSolo(ActionEvent event) throws IOException{
		Stage stage = (Stage) button_regle.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource(plateau+".fxml");
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Line Up 3 Solo");
        stage.show();
		switch( plateau ) {
			case "carre3":
				 System.out.println("Carre 3 solo choisis");
				 break;
			case "carre4":
				 System.out.println("Carre 4 solo choisis");
				 break;
			case "triangle3":
				 System.out.println("Triangle 3 solo choisis");
				 break;
			case "triangle4":
				 System.out.println("Triangle 4 solo choisis");
				 break;
			default:
				 System.out.println("Carre 3 solo choisis");	
		}
	}
	@FXML
	public void actionButtonMulti(ActionEvent event) throws IOException {
		Stage stage = (Stage) button_regle.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource(plateau+".fxml");
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Line Up 3 Multi");
        stage.show();
		switch( plateau ) {
			case "carre3":
				 System.out.println("Carre 3 multi choisis");
				 break;
			case "carre4":
				 System.out.println("Carre 4 multi choisis");
				 break;
			case "triangle3":
				 System.out.println("Triangle 3 multi choisis");
				 break;
			case "triangle4":
				 System.out.println("Triangle 4 multi choisis");
				 break;
			default:
				 System.out.println("Carre 3 multi choisis");	
		}
	}
	@FXML
	public void actionButtonVideo(ActionEvent event) {
		Stage video = new Stage();
		WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.oracle.com");
        video.setTitle(webEngine.getTitle());
        VBox root = new VBox();
        root.getChildren().add(webView);
        Scene scene = new Scene(root);
        video.setScene(scene);
        video.show();

	}
	
}
