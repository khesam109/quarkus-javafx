package com.khesam.gui.common.di;

import javafx.fxml.FXMLLoader;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

public class FxmlLoaderProvider {

    private static final String LOGIN_FXML_NAME = "/login-view.fxml";

    @Inject Instance<Object> instance;

    @Produces
    @Named("login-fxml-loader")
    public FXMLLoader provideSignFxmlLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_NAME));
        loader.setControllerFactory(param -> instance.select(param).get());
        loader.setClassLoader(Thread.currentThread().getContextClassLoader());
        return loader;
    }
}
