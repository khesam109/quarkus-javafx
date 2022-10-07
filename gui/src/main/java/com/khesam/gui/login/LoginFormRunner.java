package com.khesam.gui.login;

import com.khesam.gui.common.Constants;
import com.khesam.gui.common.di.LaunchScene;
import com.khesam.gui.common.exception.BaseGuiException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

public class LoginFormRunner {

    @Named("login-fxml-loader")
    @Inject FXMLLoader fxmlLoader;

    public void launchSceneHandler(@Observes @LaunchScene Stage stage) {
        showForm(stage);
    }

    private void showForm(Stage stage) {
        try {
            Parent parent = fxmlLoader.load();
            stage.setScene(
                    new Scene(
                            parent, Constants.LOGIN_FORM_WIDTH, Constants.LOGIN_FORM_HEIGHT
                    )
            );

            stage.setTitle(Constants.LOGIN_FORM_TITLE);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new BaseGuiException.LoadingFxmlException(e);
        }
    }
}
