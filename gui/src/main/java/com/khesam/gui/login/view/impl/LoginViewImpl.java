package com.khesam.gui.login.view.impl;

import com.khesam.gui.common.utils.ResettableCountDownLatch;
import com.khesam.gui.common.view.AlertDialogFactory;
import com.khesam.gui.common.view.AlertDialogModel;
import com.khesam.gui.login.presenter.LoginPresenter;
import com.khesam.gui.login.view.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class LoginViewImpl implements LoginView {

    @FXML TextField editTextUsername;
    @FXML PasswordField editTextPassword;
    @FXML Button btnLogin;
    @FXML Button btnCancel;
    @FXML ProgressIndicator progressLoading;

    @Inject LoginPresenter loginPresenter;

    @Named("synchronized-gui-thread")
    @Inject ResettableCountDownLatch synchronizedGuiThread;

    @Inject AlertDialogFactory alertDialogFactory;

    /**
     * This method will be found and executed automatically inf exist
     */
    public void initialize() {
        stopLoading();
    }

    public void onLoginClicked() {
        startLoading();
        loginPresenter.loginEvent(
                editTextUsername.getText(),
                editTextPassword.getText()
        );
    }

    public void onCancelClicked() {
        stopLoading();
        closeWindow();
    }

    @Override
    public void onLoginSuccess(String username) {
        stopLoading();
        alertDialogFactory.build(AlertDialogModel.builder(Alert.AlertType.CONFIRMATION)
                .title("Login")
                .header("Success")
                .content("Welcome " + username)
                .build()
        ).show();
    }

    @Override
    public void onLoginFailed() {
        stopLoading();
        alertDialogFactory.build(AlertDialogModel.builder(Alert.AlertType.ERROR)
                .title("Login")
                .header("Failed")
                .content("Invalid username/password")
                .build()
        ).show();
    }

    private void startLoading() {
        componentViewStateOnLoading(true);
    }

    private void stopLoading() {
        componentViewStateOnLoading(false);
    }

    private void componentViewStateOnLoading(boolean loading) {
        btnLogin.setDisable(loading);
        btnCancel.setDisable(loading);
        btnCancel.setDisable(loading);
        editTextUsername.setDisable(loading);
        editTextPassword.setDisable(loading);

        progressLoading.setVisible(loading);
    }

    private void closeWindow() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        synchronizedGuiThread.countDown();
        stage.close();
    }
}
