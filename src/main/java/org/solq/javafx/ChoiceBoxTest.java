package org.solq.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ChoiceBoxTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

	FlowPane fp = new FlowPane();

	ChoiceBox<Object> cb = new ChoiceBox<>();
	cb.setItems(FXCollections.observableArrayList("English", "Open ", new Separator(), "Save", "Save as"));

	final String[] greeting = { "aaa", "bbb", "ccc", "ddd", "eee", };

	final Label label = new Label();
	cb.getSelectionModel().selectedIndexProperty().addListener((ov, oldv, newv) -> {

	    label.setText(greeting[newv.intValue()]);
	});

	cb.setTooltip(new Tooltip("Select the language"));

	fp.getChildren().add(cb);
	fp.getChildren().add(label);

	Scene scene = new Scene(fp, 500, 300);
	primaryStage.setScene(scene);

	primaryStage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }
}