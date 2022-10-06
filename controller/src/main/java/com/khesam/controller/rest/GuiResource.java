package com.khesam.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("quarkus-javafx")
public interface GuiResource {

    @GET
    @Path("/gui/login")
    Response launchLoginGui();
}
