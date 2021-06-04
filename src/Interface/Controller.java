package Interface;

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
	@FXML
    Button button_solo,button_multi,button_triangle,button_carre,button_regle,button_video,button_quitter;
	
	public void actionButtonQuitter(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setHeaderText("�tes vous s�r de vouloir quitter le jeu ?");
	 
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
		stage.close();
		FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource("menu_regle.fxml");
        if (fxmlFileUrl == null) {
                System.out.println("Impossible de charger le fichier fxml");
                System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
		System.out.println("Regle choisis");
	}
	@FXML
	public void actionButtonTriangle(ActionEvent event) {
		System.out.println("Triangle choisis");
	}
	@FXML
	public void actionButtonCarre(ActionEvent event) {
		System.out.println("Carr� choisis");
	}
	@FXML
	public void actionButtonSolo(ActionEvent event) {
		System.out.println("Solo choisis");
	}
	@FXML
	public void actionButtonMulti(ActionEvent event) {
		System.out.println("Multi choisis");
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
