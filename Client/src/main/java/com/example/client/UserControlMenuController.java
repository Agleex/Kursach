package com.example.client;

import com.example.socket.SocketReq;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserControlMenuController {


    @FXML
    private Button exitBtn;

    @FXML
    private Label nameField;

    @FXML
    void changeUserDataAction(ActionEvent event) {

    }

    @FXML
    void deleteUserAction(ActionEvent event) {

    }

    @FXML
    void nextUserAction(ActionEvent event) {

    }

    @FXML
    void previosUserAction(ActionEvent event) {

    }

    @FXML
    void toAdminMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void toRegistrate(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registrateForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void initialize() {
        SocketReq.sendUserControlRequest("USER_CONTROL");
    }

}