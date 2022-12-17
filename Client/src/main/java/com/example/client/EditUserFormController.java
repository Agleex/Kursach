package com.example.client;

import com.example.socket.SocketReq;
import com.example.user.CurrentUser;
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

public class EditUserFormController {

    @FXML
    private CheckBox isAdminField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;
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
    void changeCompanyAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCompanyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) loginField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void exitAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userControlMenu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) loginField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
        String oldLogin = CurrentUser.SelectedUser.login;
        String login = loginField.getText();
        String password = passwordField.getText();
        String isAdmin = "0";
        if (isAdminField.isSelected()) isAdmin = "1";

        switch (SocketReq.sendEditUserRequest("EDIT_USER", oldLogin, login, password, isAdmin)) {
            case "SUCCESS": {
                if (oldLogin.equals(CurrentUser.login)) CurrentUser.setCurrentUser(login, password, isAdmin);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userControlMenu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageRoot = (Stage) loginField.getScene().getWindow();
                stageRoot.close();
                break;
            }
            case "ERROR": {
                showModal("Такой пользователь уже существует");
                break;
            }
            default: {
                System.out.println("undefined error");
            }
        }
    }

    @FXML
    void initialize() {
        loginField.setText(CurrentUser.SelectedUser.login);
        passwordField.setText(CurrentUser.SelectedUser.password);
        if (CurrentUser.SelectedUser.isAdmin.equals("1")) {
            isAdminField.setSelected(true);
        } else {
            isAdminField.setSelected(false);
        }
    }
}
