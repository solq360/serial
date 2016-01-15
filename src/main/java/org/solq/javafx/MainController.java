package org.solq.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button userSubmit;
    // @FXML private DialogController dialogController;
    @FXML
    private void handleSubmitButtonAction(ActionEvent even){
	System.out.println(even.getSource());
    }
}