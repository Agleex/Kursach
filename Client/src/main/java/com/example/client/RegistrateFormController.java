package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.socket.SocketReq.sendRegistrateRequest;

public class RegistrateFormController {

    @FXML
    private CheckBox isAdminCheckBox;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passField;

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
    void registrateBtnAction(ActionEvent event) throws IOException {
        if (loginField.getText().trim().equals("") && passField.getText().trim().equals("")) {
            showModal("Заполните все поля!!!");
            return;
        }

        String login = loginField.getText();
        String pass = passField.getText();
        String isAdmin;

        if (isAdminCheckBox.isSelected())
            isAdmin = "1";
        else
            isAdmin = "0";

        switch (sendRegistrateRequest("REGISTRATE", login, pass, isAdmin)) {
            case "SUCCESS" : {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageRoot = (Stage) loginField.getScene().getWindow();
                stageRoot.close();
                break;
            }
            case "ERROR" : {
                showModal("Такой пользователь уже существует!!!\nПовторите попытку");
                break;
            }
            default: {
                break;
            }
        }
    }

}