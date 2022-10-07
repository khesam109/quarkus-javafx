package com.khesam.gui.login;

import com.khesam.gui.GuiConcurrentAccessPreventionHelper;
import com.khesam.gui.JavafxApplication;
import com.khesam.gui.JavafxGuiRunnerHandler;
import com.khesam.gui.common.exception.BaseGuiException;
import com.khesam.service.LoginGui;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LoginGuiImpl implements LoginGui {

    @Inject GuiConcurrentAccessPreventionHelper guiConcurrentAccessPreventionHelper;
    @Inject JavafxGuiRunnerHandler javafxGuiRunnerHandler;

    @Override
    public void launchLoginGui() {
        if (guiConcurrentAccessPreventionHelper.isGuiRunning()) {
            throw new BaseGuiException.GuiConcurrentAccessException();
        }

        try {
            guiConcurrentAccessPreventionHelper.lockToRunGui();
        } catch (InterruptedException ex) {
            throw new BaseGuiException.ThreadSynchronizationException(ex);
        }
        javafxGuiRunnerHandler.handle(JavafxApplication.class);

        guiConcurrentAccessPreventionHelper.releaseGuiLock();
    }
}
