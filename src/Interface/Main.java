package Interface;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * classe main, pour jouer au mode graphique
 */
public class Main extends Application{

	String musicFile = "res/medieval.wav";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

	public void start(Stage stage) throws IOException {
                
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        FXMLLoader loader = new FXMLLoader();

        URL fxmlFileUrl = getClass().getResource("menu.fxml");
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
	}
	
	
	/**
	 * main version graphique
	 * @param args
	 */
	public static void main(String[] args) {
                Application.launch(args);
	}
}
