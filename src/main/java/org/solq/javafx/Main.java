package org.solq.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
	try {
	    // BorderPane root = new BorderPane();

	    Pane root = (Pane) FXMLLoader.load(getClass().getResource("test.fxml"));

	    Scene scene = new Scene(root, 400, 400);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	    primaryStage.setScene(scene);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
