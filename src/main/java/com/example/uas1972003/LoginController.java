package com.example.uas1972003;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class LoginController {
    public TextField txtId;
    public TextField txtPassword;
    public void loginAction(ActionEvent actionEvent) throws IOException {
        if (txtId.getText().equals("1972003")&&txtPassword.getText().equals("123")){
            Stage new_stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main-view.fxml"));
            loader.setResources(ResourceBundle.getBundle("Bundle"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            new_stage.setTitle("Main");
            new_stage.initModality(Modality.WINDOW_MODAL);
            new_stage.initOwner(txtPassword.getScene().getWindow());
            new_stage.setScene(scene);
            new_stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password atau Username salah");
            alert.showAndWait();
        }
    }
}
