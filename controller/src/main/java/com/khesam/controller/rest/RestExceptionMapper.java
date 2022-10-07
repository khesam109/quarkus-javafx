package com.khesam.controller.rest;

import com.khesam.gui.common.exception.BaseGuiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<BaseGuiException> {

    @Override
    public Response toResponse(BaseGuiException e) {
        if (e instanceof BaseGuiException.GuiConcurrentAccessException) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
