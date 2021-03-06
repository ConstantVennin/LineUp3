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

/**
 * Classe Controller : Pour interragir avec le menu
 * @author Victor_Bastien_Constant
 */
public class Controller {
	public static String plateau="carre3";
	@FXML
    Button button_solo,button_multi,button_triangle,button_carre,button_regle,button_video,button_quitter;
	
	/**
	 * Bouton pour quitter le programme
	 * @param event
	 */
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
	
	/**
	 * Bouton d'affichage des règles
	 * @param event
	 * @throws IOException
	 */
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
	/**
	 * Bouton de sélection du triangle à 3 couches
	 * @param event
	 */
	@FXML
	public void actionButtonTriangle3(ActionEvent event) {
		plateau="triangle3";
		System.out.println("Triangle 3 choisis");
	}
	
	/**
	 * Bouton de sélection du carré à 3 couches
	 * @param event
	 */
	@FXML
	public void actionButtonCarre3(ActionEvent event) {
		plateau="carre3";
		System.out.println("Carr� 3 choisis");	
	}
	
	/**
	 * Bouton de sélection du triangle à 4 couches
	 * @param event
	 */
	@FXML
	public void actionButtonTriangle4(ActionEvent event) {
		plateau="triangle4";
		System.out.println("Triangle 4 choisis");
	}
	
	/**
	 * Bouton de sélection du carré à 4 couches
	 * @param event
	 */
	@FXML
	public void actionButtonCarre4(ActionEvent event) {
		plateau="carre4";
		System.out.println("Carr� 4 choisis");	
	}
	
	/**
	 * Bouton pour lancer une partie solo
	 * @param event
	 * @throws IOException
	 */
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
	
	/**
	 * Bouton pour lancer une partie multijoueur
	 * @param event
	 * @throws IOException
	 */
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
	
	/**
	 * Bouton pour lancer une vidéo de démonstration
	 * @param event
	 */
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

	/**
	 * Récupère la configuration
	 * @return plateau sous forme textuelle
	 */
	public static String getConfig(){
		return plateau;
	}
	
}
