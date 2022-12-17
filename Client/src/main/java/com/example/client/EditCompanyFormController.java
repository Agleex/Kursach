package com.example.client;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.company.Company;
import com.example.socket.SocketReq;
import com.example.user.CurrentUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCompanyFormController {

    private Company currentCompany;
    @FXML
    private Label companyName;

    @FXML
    private TextField assetsField;

    @FXML
    private TextField cashFlowField;

    @FXML
    private TextField currentAssetsField;

    @FXML
    private TextField equityField;

    @FXML
    private TextField longTermDutiesField;

    @FXML
    private TextField percentageToBePaidField;

    @FXML
    private TextField profitBeforeTax;

    @FXML
    private TextField retainedEarningsOfPreviousYearsField;

    @FXML
    private TextField revenueField;

    @FXML
    private TextField revenueFromSalesField;

    @FXML
    private TextField shortTermLiabilitiesFeild;

    @FXML
    private TextField tangibleAssetsField;

    @FXML
    private TextField undestributedProfitsField;

    @FXML
    private TextField workingСapitalField;

    private boolean isFormValid () {
        boolean isValid = true;

        try {
            if (!(assetsField.getText().trim().equals("") || assetsField.getText().trim().equals("пусто"))) Integer.parseInt(assetsField.getText().trim());
            if (!(cashFlowField.getText().trim().equals("") || cashFlowField.getText().trim().equals("пусто"))) Integer.parseInt(cashFlowField.getText().trim());
            if (!(currentAssetsField.getText().trim().equals("") || currentAssetsField.getText().trim().equals("пусто"))) Integer.parseInt(currentAssetsField.getText().trim());
            if (!(equityField.getText().trim().equals("") || equityField.getText().trim().equals("пусто"))) Integer.parseInt(equityField.getText().trim());
            if (!(longTermDutiesField.getText().trim().equals("") || longTermDutiesField.getText().trim().equals("пусто"))) Integer.parseInt(longTermDutiesField.getText().trim());
            if (!(percentageToBePaidField.getText().trim().equals("") || percentageToBePaidField.getText().trim().equals("пусто"))) Integer.parseInt(percentageToBePaidField.getText().trim());
            if (!(profitBeforeTax.getText().trim().equals("") || profitBeforeTax.getText().trim().equals("пусто"))) Integer.parseInt(profitBeforeTax.getText().trim());
            if (!(retainedEarningsOfPreviousYearsField.getText().trim().equals("") || retainedEarningsOfPreviousYearsField.getText().trim().equals("пусто"))) Integer.parseInt(retainedEarningsOfPreviousYearsField.getText().trim());
            if (!(revenueField.getText().trim().equals("") || revenueField.getText().trim().equals("пусто"))) Integer.parseInt(revenueField.getText().trim());
            if (!(revenueFromSalesField.getText().trim().equals("") || revenueFromSalesField.getText().trim().equals("пусто"))) Integer.parseInt(revenueFromSalesField.getText().trim());
            if (!(shortTermLiabilitiesFeild.getText().trim().equals("") || shortTermLiabilitiesFeild.getText().trim().equals("пусто"))) Integer.parseInt(shortTermLiabilitiesFeild.getText().trim());
            if (!(tangibleAssetsField.getText().trim().equals("") || tangibleAssetsField.getText().trim().equals("пусто"))) Integer.parseInt(tangibleAssetsField.getText().trim());
            if (!(undestributedProfitsField.getText().trim().equals("") || undestributedProfitsField.getText().trim().equals("пусто"))) Integer.parseInt(undestributedProfitsField.getText().trim());
            if (!(workingСapitalField.getText().trim().equals("") || workingСapitalField.getText().trim().equals("пусто"))) Integer.parseInt(workingСapitalField.getText().trim());
        } catch (NumberFormatException exception) {
            isValid = false;
        }

        return isValid;
    }

    private void prepareValues() {
        if ((assetsField.getText().trim().equals(""))) assetsField.setText("пусто");
        if ((cashFlowField.getText().trim().equals(""))) cashFlowField.setText("пусто");
        if ((currentAssetsField.getText().trim().equals(""))) currentAssetsField.setText("пусто");
        if ((equityField.getText().trim().equals(""))) equityField.setText("пусто");
        if ((longTermDutiesField.getText().trim().equals(""))) longTermDutiesField.setText("пусто");
        if ((percentageToBePaidField.getText().trim().equals(""))) percentageToBePaidField.setText("пусто");
        if ((profitBeforeTax.getText().trim().equals(""))) profitBeforeTax.setText("пусто");
        if ((retainedEarningsOfPreviousYearsField.getText().trim().equals(""))) retainedEarningsOfPreviousYearsField.setText("пусто");
        if ((revenueField.getText().trim().equals(""))) revenueField.setText("пусто");
        if ((revenueFromSalesField.getText().trim().equals(""))) revenueFromSalesField.setText("пусто");
        if ((shortTermLiabilitiesFeild.getText().trim().equals(""))) shortTermLiabilitiesFeild.setText("пусто");
        if ((tangibleAssetsField.getText().trim().equals(""))) tangibleAssetsField.setText("пусто");
        if ((undestributedProfitsField.getText().trim().equals(""))) undestributedProfitsField.setText("пусто");
        if ((workingСapitalField.getText().trim().equals(""))) workingСapitalField.setText("пусто");
    }
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
    void exitAction(ActionEvent event) throws IOException {
        if (CurrentUser.isAdmin.equals("1")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageRoot = (Stage) workingСapitalField.getScene().getWindow();
            stageRoot.close();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageRoot = (Stage) workingСapitalField.getScene().getWindow();
            stageRoot.close();
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
        if (!isFormValid()) {
            showModal("Значения во всех полях должны быть численными!");
            return;
        }

        prepareValues();

        Company updatedCompany = new Company(
        assetsField.getText().trim(),
        cashFlowField.getText().trim(),
        currentAssetsField.getText().trim(),
        equityField.getText().trim(),
        longTermDutiesField.getText().trim(),
        percentageToBePaidField.getText().trim(),
        profitBeforeTax.getText().trim(),
        retainedEarningsOfPreviousYearsField.getText().trim(),
        revenueField.getText().trim(),
        revenueFromSalesField.getText().trim(),
        shortTermLiabilitiesFeild.getText().trim(),
        tangibleAssetsField.getText().trim(),
        undestributedProfitsField.getText().trim(),
        workingСapitalField.getText().trim()
        );
        SocketReq.sendUpdateCompanyRequest("UPDATE_COMPANY", CurrentUser.SelectedUser.login, updatedCompany);
        showModal("Изменения успешно сохранены!!!");
    }

    @FXML
    void initialize() {
        companyName.setText(CurrentUser.SelectedUser.login);

        currentCompany = SocketReq.sendCompanyFormRequest("COMPANY_FORM", CurrentUser.SelectedUser.login);

        assetsField.setText(currentCompany.getAssets());
        cashFlowField.setText(currentCompany.getCashFlow());
        currentAssetsField.setText(currentCompany.getCurrentAssets());
        equityField.setText(currentCompany.getEquity());
        longTermDutiesField.setText(currentCompany.getLongTermDuties());
        percentageToBePaidField.setText(currentCompany.getPercentageToBePaid());
        profitBeforeTax.setText(currentCompany.getProfitBeforeTax());
        retainedEarningsOfPreviousYearsField.setText(currentCompany.getRetainedEarningsOfPreviousYears());
        revenueField.setText(currentCompany.getRevenue());
        revenueFromSalesField.setText(currentCompany.getRevenueFromSales());
        shortTermLiabilitiesFeild.setText(currentCompany.getShortTermLiabilities());
        tangibleAssetsField.setText(currentCompany.getTangibleAssets());
        undestributedProfitsField.setText(currentCompany.getUndestributedProfits());
        workingСapitalField.setText(currentCompany.getWorkingСapital());
    }
}
