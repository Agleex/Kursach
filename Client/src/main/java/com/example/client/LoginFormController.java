package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private void showModal(String text) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
//        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    protected void onSingInBtnClick() {
            String login = loginField.getText();
            String pass = passwordField.getText();


            if (login.length() != 0 && pass.length() != 0) {
                SocketReq.sendSingInRequest("SING_IN", login, pass);
            } else {
                showModal("Заполните все поля!!!");
            }

//            writer.write("click");
//            writer.newLine();
//            writer.flush();

    }
}
