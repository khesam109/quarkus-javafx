package com.khesam.gui.common.view;

import javafx.scene.control.Alert;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlertDialogFactory {

    public Alert build(AlertDialogModel alertDialogModel) {
        Alert alert = new Alert(alertDialogModel.getType());
        alert.setTitle(alertDialogModel.getTitle());
        alert.setHeaderText(alertDialogModel.getHeader());
        alert.setContentText(alertDialogModel.getContent());

        return alert;
    }
}