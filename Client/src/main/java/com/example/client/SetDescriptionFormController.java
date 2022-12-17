package com.example.client;

import com.example.socket.SocketReq;
import com.example.user.CurrentUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetDescriptionFormController {
    @FXML
    private TextArea descriptionField;
    @FXML
    void exitAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) descriptionField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void resetAction(ActionEvent event) {
        descriptionField.setText("");
    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
        SocketReq.sendCompanyInfoUpdateRequest("UPDATE_COMPANY_INFO", CurrentUser.SelectedUser.login, descriptionField.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) descriptionField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void initialize() {
        descriptionField.setText(SocketReq.sendCompanyInfoRequest("GET_COMPANY_INFO", CurrentUser.SelectedUser.login));
    }
}
