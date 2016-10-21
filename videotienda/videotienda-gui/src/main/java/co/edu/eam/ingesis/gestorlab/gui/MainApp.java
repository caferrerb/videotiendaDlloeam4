package co.edu.eam.ingesis.gestorlab.gui;

import javax.naming.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edu.eam.ingesoft.videotienda.main.ContextFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private static Stage stage;
	public static void main(String[] args) throws Exception {
		
		ContextFactory.getContext();

		launch(args);

	}

	public void start(Stage stage) throws Exception {

		String fxmlFile = "/fxml/principal.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

		Scene scene = new Scene(rootNode, 400, 200);
		scene.getStylesheets().add("/styles/styles.css");
		// setUserAgentStylesheet(STYLESHEET_MODENA);

		stage.setTitle("Video-tienda");
		stage.setScene(scene);
		stage.show();
		MainApp.stage=stage;

	}
	
	public static Stage getStage() {
		return stage;
	}
}
