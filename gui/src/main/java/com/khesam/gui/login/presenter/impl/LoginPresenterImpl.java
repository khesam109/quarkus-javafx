package com.khesam.gui.login.presenter.impl;

import com.khesam.gui.common.executor.BackgroundTaskRunner;
import com.khesam.gui.login.presenter.LoginPresenter;
import com.khesam.gui.login.view.LoginView;
import javafx.application.Platform;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LoginPresenterImpl implements LoginPresenter {

    @Inject LoginView loginView;
    @Inject BackgroundTaskRunner backgroundTaskRunner;

    @Override
    public void loginEvent(String username, String password) {
        backgroundTaskRunner.executeAsync(() -> {
            Thread.sleep(3000L);
            return username.equals("admin") && password.equals("admin");
        }, result -> {
            if (result) {
                Platform.runLater(() -> loginView.onLoginSuccess(username));
            } else {
                Platform.runLater(() -> loginView.onLoginFailed());
            }
        });
    }
}
