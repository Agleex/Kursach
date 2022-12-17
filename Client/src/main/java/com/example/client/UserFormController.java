package com.example.client;

import com.example.company.Company;
import com.example.socket.SocketReq;
import com.example.user.CurrentUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController {
    private Company currentCompany;

    @FXML
    private Label descriptionCom;

    @FXML
    private Label nameCom;

    @FXML
    void addComDesriptionAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setDescriptionForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameCom.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void bankruptcyAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bankruptcyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameCom.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void changeComInfAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCompanyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameCom.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void exitAction(ActionEvent event) throws IOException {
        if (CurrentUser.SelectedUser.isAdmin.equals('1')) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageRoot = (Stage) nameCom.getScene().getWindow();
            stageRoot.close();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageRoot = (Stage) nameCom.getScene().getWindow();
            stageRoot.close();
        }

    }

    @FXML
    void initialize() {
        nameCom.setText(CurrentUser.SelectedUser.login);
        descriptionCom.setText(SocketReq.sendCompanyInfoRequest("GET_COMPANY_INFO", CurrentUser.SelectedUser.login));

        currentCompany = SocketReq.sendCompanyFormRequest("COMPANY_FORM", CurrentUser.SelectedUser.login);
    }
}
