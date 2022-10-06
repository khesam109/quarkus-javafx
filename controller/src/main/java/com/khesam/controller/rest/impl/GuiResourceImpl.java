package com.khesam.controller.rest.impl;

import com.khesam.controller.rest.GuiResource;
import com.khesam.service.GuiService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class GuiResourceImpl implements GuiResource {

    @Inject GuiService guiService;

    @Override
    public Response launchLoginGui() {
        guiService.gui();
        return Response.ok("shod").build();
    }
}
