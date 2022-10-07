package com.khesam.gui.common.executor;

import javafx.application.Platform;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@ApplicationScoped
public class BackgroundTaskRunner {

    @Named("background-thread")
    @Inject Executor executor;

    public <R> void executeAsync(Callable<R> callable, Callback<R> callback) {
        executor.execute(() -> {
            try {
                R result = callable.call();
                Platform.runLater(() ->
                        callback.onSuccess(result)
                );
            } catch (Exception e) {
                Platform.runLater(() ->
                        callback.onFailed("Internal operation exception. Try later!")
                );
            }
        });
    }

    public interface Callback<R> {

        void onSuccess(R result);
        default void onFailed(String reason) {}
    }
}
