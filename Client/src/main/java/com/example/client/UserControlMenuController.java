package com.example.client;

import com.example.socket.SocketReq;
import com.example.user.CurrentUser;
import com.example.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UserControlMenuController {

    ArrayList<User> users = new ArrayList<>();
    int currentUserIndex = 0;

    @FXML
    private Button exitBtn;
    @FXML
    private Label nameField;
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
    void changeUserDataAction(ActionEvent event) throws IOException {
        CurrentUser.SelectedUser.setSelectedUser(users.get(currentUserIndex).getLogin(), users.get(currentUserIndex).getPassword(), users.get(currentUserIndex).getAdmin());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editUserForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameField.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void deleteUserAction(ActionEvent event) {
        if (users.get(currentUserIndex).getLogin().trim().equals(CurrentUser.login.trim())) {
            System.out.println(users.get(currentUserIndex).getLogin());
            System.out.println(CurrentUser.login);
            showModal("Вы не можете удалить текущего пользователя");
            return;
        }

        SocketReq.sendDeleteRequest("DELETE_USER", users.get(currentUserIndex).getLogin());
        if (users.size() - 1 == currentUserIndex) {
            users.remove(currentUserIndex);
            currentUserIndex--;
        } else {
            users.remove(currentUserIndex);
        }


        nameField.setText(users.get(currentUserIndex).getLogin());
    }

    @FXML
    void nextUserAction(ActionEvent event) {
        currentUserIndex++;
        if (currentUserIndex >= users.size()) {
            currentUserIndex = 0;
        }

        nameField.setText(users.get(currentUserIndex).getLogin());
    }

    @FXML
    void previosUserAction(ActionEvent event) {
        currentUserIndex--;
        if (currentUserIndex < 0) {
            currentUserIndex = users.size() - 1;
        }

        nameField.setText(users.get(currentUserIndex).getLogin());
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
    void initialize() throws IOException {
        users = SocketReq.sendUserControlRequest("USER_CONTROL");

        nameField.setText(users.get(currentUserIndex).getLogin());

        System.out.println("Current User " + CurrentUser.login);
    }

}