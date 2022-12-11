package com.example.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {

    @FXML
    private Button companyMenu;

    @FXML
    private Button toLogin;

    @FXML
    private Button userControlMenu;

    @FXML
    void companyMenuAction(ActionEvent event) {

    }

    @FXML
    void toLoginAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) toLogin.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void userControlMenuAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userControlMenu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) toLogin.getScene().getWindow();
        stageRoot.close();
    }

}
