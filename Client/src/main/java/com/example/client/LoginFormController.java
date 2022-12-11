package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.example.socket.SocketReq;

import java.io.*;
import java.net.Socket;

public class LoginFormController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button SingInBtn;

    @FXML
    private void showModal(String text) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    @FXML
    void toRegistrate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrateForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) SingInBtn.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void singInAction(ActionEvent event) throws IOException {
            String login = loginField.getText();
            String pass = passwordField.getText();


            if (login.length() != 0 && pass.length() != 0) {

                switch (SocketReq.sendSingInRequest("SING_IN", login, pass)) {
                    case "1": {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminForm.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                        Stage stageRoot = (Stage) SingInBtn.getScene().getWindow();
                        stageRoot.close();
                        break;
                    }
                    case "0": {
                        System.out.println("User");
                        break;
                    }
                    default: {
                        showModal("Неправильный логин или пароль!!!");
                    }
                }
            } else {
                showModal("Заполните все поля!!!");
            }
    }
    @FXML
    void initialize() {}
}