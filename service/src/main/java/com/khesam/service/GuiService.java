package com.khesam.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GuiService {

    @Inject LoginGui loginGui;

    public void gui() {
        loginGui.launchLoginGui();
    }
}
