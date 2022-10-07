package com.khesam.gui.common.di;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorProvider {

    @Produces
    @ApplicationScoped
    @Named("background-thread")
    public Executor provideBackgroundThread() {
        return Executors.newSingleThreadExecutor();
    }
}
