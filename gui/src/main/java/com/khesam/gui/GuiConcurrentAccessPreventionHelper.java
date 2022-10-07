package com.khesam.gui;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.Semaphore;

@ApplicationScoped
public class GuiConcurrentAccessPreventionHelper {

    @Named("single-permit-semaphore")
    @Inject Semaphore guiRunningLock;

    public void lockToRunGui() throws InterruptedException {
        guiRunningLock.acquire();
    }

    public void releaseGuiLock() {
        guiRunningLock.release();
    }

    public boolean isGuiRunning() {
        return guiRunningLock.availablePermits() == 0;
    }
}
