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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BankruptcyFormController {
    private Company currentCompany;

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
    private Label nameCom;

    @FXML
    void exitAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stageRoot = (Stage) nameCom.getScene().getWindow();
        stageRoot.close();
    }

    @FXML
    void fellmerAction(ActionEvent event) {
        String message = "Для получения результата по формуле Фулмера вам необходимо заполнить: \n";
        boolean showMessage = false;

        if (currentCompany.getAssets().equals("пусто") || currentCompany.getAssets().equals("0")) {
            message += "-> Активы\n";
            showMessage = true;
        }

        if (currentCompany.getRetainedEarningsOfPreviousYears().equals("пусто")) {
            message += "-> Нерасспределенная прибыль прошлых лет\n";
            showMessage = true;
        }

        if (currentCompany.getRevenueFromSales().equals("пусто")) {
            message += "-> Прибыль от продаж\n";
            showMessage = true;
        }

        if (currentCompany.getProfitBeforeTax().equals("пусто")) {
            message += "-> Прибыль до налогооблажения\n";
            showMessage = true;
        }

        if (currentCompany.getPercentageToBePaid().equals("пусто")) {
            message += "-> Проценты к уплате\n";
            showMessage = true;
        }

        if (currentCompany.getEquity().equals("пусто") || currentCompany.getEquity().equals("0")) {
            message += "-> Собственный капитал\n";
            showMessage = true;
        }

        if (currentCompany.getCashFlow().equals("пусто")) {
            message += "-> Денежный поток\n";
            showMessage = true;
        }

        if (currentCompany.getShortTermLiabilities().equals("пусто")) {
            message += "-> Краткосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getLongTermDuties().equals("пусто")) {
            message += "-> Долгосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getTangibleAssets().equals("пусто")) {
            message += "-> Материальные активы\n";
            showMessage = true;
        }

        if (currentCompany.getWorkingСapital().equals("пусто")) {
            message += "-> Оборотный капитал\n";
            showMessage = true;
        }

        if (showMessage) {
            showModal(message);
            return;
        }

        double answer = 5.528F * (Float.parseFloat(currentCompany.getRetainedEarningsOfPreviousYears()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.212F * (Float.parseFloat(currentCompany.getRevenueFromSales()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.073F * ((Float.parseFloat(currentCompany.getProfitBeforeTax()) + Float.parseFloat(currentCompany.getPercentageToBePaid())) / Float.parseFloat(currentCompany.getEquity()))
                + 1.27F * (Float.parseFloat(currentCompany.getCashFlow()) / (Float.parseFloat(currentCompany.getShortTermLiabilities()) + Float.parseFloat(currentCompany.getLongTermDuties())))
                + 0.12F * (Float.parseFloat(currentCompany.getLongTermDuties()) / Float.parseFloat(currentCompany.getAssets()))
                + 2.235F * (Float.parseFloat(currentCompany.getShortTermLiabilities()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.575F * (Math.log(Float.parseFloat(currentCompany.getTangibleAssets())))
                + 1.083F * (Float.parseFloat(currentCompany.getWorkingСapital()) / (Float.parseFloat(currentCompany.getShortTermLiabilities()) + Float.parseFloat(currentCompany.getLongTermDuties())))
                + 0.984F * Math.log((Float.parseFloat(currentCompany.getProfitBeforeTax()) + Float.parseFloat(currentCompany.getPercentageToBePaid())) / Float.parseFloat(currentCompany.getPercentageToBePaid()))
                - 3.075F;

        if (answer > 0) {
            message = "H = " + answer + " \n-> Банкротство маловероятно";
            showModal(message);
        } else {
            message = "H = " + answer + " \n-> Банкротство вероятно";
            showModal(message);
        }
    }

    @FXML
    void lissAction(ActionEvent event) {
        String message = "Для получения результата по формуле Лисса вам необходимо заполнить: \n";
        boolean showMessage = false;

        if (currentCompany.getAssets().equals("пусто") || currentCompany.getAssets().equals("0")) {
            message += "-> Активы\n";
            showMessage = true;
        }

        if (currentCompany.getWorkingСapital().equals("пусто")) {
            message += "-> Оборотный капитал\n";
            showMessage = true;
        }

        if (currentCompany.getProfitBeforeTax().equals("пусто")) {
            message += "-> Прибыль до налогооблажения\n";
            showMessage = true;
        }

        if (currentCompany.getUndestributedProfits().equals("пусто")) {
            message += "-> Нераспределенная прибыль\n";
            showMessage = true;
        }

        if (currentCompany.getEquity().equals("пусто") || currentCompany.getEquity().equals("0")) {
            message += "-> Собственный капитал\n";
            showMessage = true;
        }

        if (currentCompany.getShortTermLiabilities().equals("пусто")) {
            message += "-> Краткосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getLongTermDuties().equals("пусто")) {
            message += "-> Долгосрочные обязательства\n";
            showMessage = true;
        }

        if (showMessage) {
            showModal(message);
            return;
        }

        double answer = 0.063F * (Float.parseFloat(currentCompany.getWorkingСapital()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.092F * (Float.parseFloat(currentCompany.getProfitBeforeTax()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.057F * (Float.parseFloat(currentCompany.getUndestributedProfits()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.001F * (Float.parseFloat(currentCompany.getEquity()) / (Float.parseFloat(currentCompany.getShortTermLiabilities()) + Float.parseFloat(currentCompany.getLongTermDuties())));

        if (answer > 0.037F) {
            message = "Z = " + answer + " \n-> Предприятие финансово устойчиво";
            showModal(message);
        } else {
            message = "Z = " + answer + " \n-> Банкротство вероятно";
            showModal(message);
        }
    }

    @FXML
    void springaiteAction(ActionEvent event) {
        String message = "Для получения результата по формуле Лисса вам необходимо заполнить: \n";
        boolean showMessage = false;

        if (currentCompany.getAssets().equals("пусто") || currentCompany.getAssets().equals("0")) {
            message += "-> Активы\n";
            showMessage = true;
        }

        if (currentCompany.getWorkingСapital().equals("пусто")) {
            message += "-> Оборотный капитал\n";
            showMessage = true;
        }

        if (currentCompany.getProfitBeforeTax().equals("пусто")) {
            message += "-> Прибыль до налогооблажения\n";
            showMessage = true;
        }

        if (currentCompany.getPercentageToBePaid().equals("пусто")) {
            message += "-> Проценты к уплате\n";
            showMessage = true;
        }

        if (currentCompany.getShortTermLiabilities().equals("пусто")) {
            message += "-> Краткосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getRevenue().equals("пусто")) {
            message += "-> Выручка\n";
            showMessage = true;
        }

        if (showMessage) {
            showModal(message);
            return;
        }

        double answer = 1.03F * (Float.parseFloat(currentCompany.getWorkingСapital()) / Float.parseFloat(currentCompany.getAssets()))
                + 3.07F * ((Float.parseFloat(currentCompany.getProfitBeforeTax()) + Float.parseFloat(currentCompany.getPercentageToBePaid())) / Float.parseFloat(currentCompany.getAssets()))
                + 0.66F * (Float.parseFloat(currentCompany.getProfitBeforeTax()) / Float.parseFloat(currentCompany.getShortTermLiabilities()))
                + 0.4F * (Float.parseFloat(currentCompany.getRevenue()) / Float.parseFloat(currentCompany.getAssets()));

        if (answer > 0.862F) {
            message = "Z = " + answer + " \n-> Банкротство маловероятно";
            showModal(message);
        } else {
            message = "Z = " + answer + " \n-> Банкротство вероятно";
            showModal(message);
        }
    }

    @FXML
    void tafflerAction(ActionEvent event) {
        String message = "Для получения результата по формуле Лисса вам необходимо заполнить: \n";
        boolean showMessage = false;

        if (currentCompany.getAssets().equals("пусто") || currentCompany.getAssets().equals("0")) {
            message += "-> Активы\n";
            showMessage = true;
        }

        if (currentCompany.getRevenueFromSales().equals("пусто")) {
            message += "-> Прибыль от продаж\n";
            showMessage = true;
        }

        if (currentCompany.getShortTermLiabilities().equals("пусто")) {
            message += "-> Краткосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getCurrentAssets().equals("пусто")) {
            message += "-> Оборотные активы\n";
            showMessage = true;
        }

        if (currentCompany.getLongTermDuties().equals("пусто")) {
            message += "-> Долгосрочные обязательства\n";
            showMessage = true;
        }

        if (currentCompany.getRevenue().equals("пусто")) {
            message += "-> Выручка\n";
            showMessage = true;
        }

        if (showMessage) {
            showModal(message);
            return;
        }

        double answer = 0.53F * (Float.parseFloat(currentCompany.getRevenueFromSales()) / Float.parseFloat(currentCompany.getShortTermLiabilities()))
                + 0.13F * (Float.parseFloat(currentCompany.getAssets()) / (Float.parseFloat(currentCompany.getLongTermDuties()) + Float.parseFloat(currentCompany.getShortTermLiabilities())))
                + 0.18F * (Float.parseFloat(currentCompany.getShortTermLiabilities()) / Float.parseFloat(currentCompany.getAssets()))
                + 0.16F * (Float.parseFloat(currentCompany.getRevenue()) / Float.parseFloat(currentCompany.getAssets()));

        if (answer < 0.2F) {
            message = "Z = " + answer + " \n-> Банкротство вероятно";
            showModal(message);
        } if (answer > 0.3F) {
            message = "Z = " + answer + " \n-> Банкротство маловероятно";
            showModal(message);
        } else {
            message = "Z = " + answer + " \n-> Зона неопределенности";
            showModal(message);
        }
    }

    @FXML
    void initialize() {
        nameCom.setText(CurrentUser.SelectedUser.login);

        currentCompany = SocketReq.sendCompanyFormRequest("COMPANY_FORM", CurrentUser.SelectedUser.login);
    }
}
