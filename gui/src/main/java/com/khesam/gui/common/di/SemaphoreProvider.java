package com.khesam.gui.common.di;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.Semaphore;

public class SemaphoreProvider {

    @Produces
    @Singleton
    @Named("single-permit-semaphore")
    public Semaphore provideSinglePermitSemaphore() {
        return new Semaphore(1);
    }
}
