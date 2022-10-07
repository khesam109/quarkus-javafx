package com.khesam.gui;

import com.khesam.gui.common.exception.BaseGuiException;
import com.khesam.gui.common.utils.ResettableCountDownLatch;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * <a href="https://stackoverflow.com/a/61771424/4243558">Handle JavaFx runtime</a>
 */
@ApplicationScoped
public class JavafxGuiRunnerHandler {

    private volatile boolean isJavafxLaunched = false;

    @Named("synchronized-gui-thread")
    @Inject ResettableCountDownLatch synchronizedGuiThread;

    public void handle(Class<? extends Application> applicationClass) {
        synchronizedGuiThread.reset();
        try {
            if (!isJavafxLaunched) {
                launchJavafxRuntime(applicationClass);
            } else {
                startJavafxRuntime(applicationClass);
            }
        } catch (InterruptedException e) {
            throw new BaseGuiException.ThreadSynchronizationException(e);
        }
    }

    private void launchJavafxRuntime(Class<? extends Application> applicationClass) throws InterruptedException {
        new Thread(() ->
                Application.launch(applicationClass)
        ).start();

        synchronizedGuiThread.await();
        isJavafxLaunched = true;
    }

    private void startJavafxRuntime(Class<? extends Application> applicationClass) throws InterruptedException {
        Platform.runLater(() -> {
            try {
                Application application = applicationClass.getConstructor().newInstance();
                Stage primaryStage = new Stage();
                application.start(primaryStage);
            } catch (Exception e) {
                throw new BaseGuiException.UnknownException(e);
            }
        });

        synchronizedGuiThread.await();
    }
}
