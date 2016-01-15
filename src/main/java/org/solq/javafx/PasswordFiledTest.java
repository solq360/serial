package org.solq.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PasswordFiledTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	final Label message = new Label("");

	VBox vb = new VBox();
	vb.setPadding(new Insets(10, 0, 0, 10));
	vb.setSpacing(10);
	HBox hb = new HBox();
	hb.setSpacing(10);
	hb.setAlignment(Pos.CENTER_LEFT);

	Label label = new Label("Password");
	final PasswordField pb = new PasswordField();

	pb.setOnAction((ActionEvent e) -> {
	    if (!pb.getText().equals("aaa")) {
		message.setText("Your password is incorrect!");
		message.setTextFill(Color.rgb(210, 39, 30));
	    } else {
		message.setText("Your password has been confirmed");
		message.setTextFill(Color.rgb(21, 117, 84));
	    }
	    pb.clear();
	});

	hb.getChildren().addAll(label, pb);
	vb.getChildren().addAll(hb, message);

	Scene s = new Scene(vb, 300, 200);
	primaryStage.setScene(s);

	primaryStage.show();

    }

    public static void main(String[] args) {
	launch(args);
    }
}
