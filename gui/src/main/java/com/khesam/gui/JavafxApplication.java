package com.khesam.gui;


import com.khesam.gui.common.di.LaunchScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.stage.Stage;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;

/**
 * <a href="https://quarkus.io/guides/cdi-reference#remove_unused_beans">CDI removing unused beans approach</a>
 * <a href="https://github.com/quarkusio/quarkus/issues/9313">Suggested a mechanism to use CDI in javafx application</a>
 */
public class JavafxApplication extends Application {

    /**
     * Keep the JavaFX runtime running in the background using Platform.setImplicitExit(false),
     * so that JavaFX does not shut down automatically when you hide the last application window.
     */
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);

        //discard close window event
        stage.setOnCloseRequest(Event::consume);

        CDI.current().getBeanManager().fireEvent(
                stage, new AnnotationLiteral<LaunchScene>() {
                }
        );
    }
}
