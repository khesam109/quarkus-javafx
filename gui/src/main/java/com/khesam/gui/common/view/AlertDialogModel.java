package com.khesam.gui.common.view;

import javafx.scene.control.Alert;

public class AlertDialogModel {

    private final String title;
    private final String header;
    private final String content;
    private final Alert.AlertType type;

    private AlertDialogModel(Builder builder) {
        title = builder.title;
        header = builder.header;
        content = builder.content;
        type = builder.type;
    }

    public static Builder builder(Alert.AlertType type) {
        return new Builder(type);
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public Alert.AlertType getType() {
        return type;
    }

    public static final class Builder {
        private String title;
        private String header;
        private String content;
        private final Alert.AlertType type;

        private Builder(Alert.AlertType type) {
            this.type = type;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder header(String val) {
            header = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public AlertDialogModel build() {
            return new AlertDialogModel(this);
        }
    }
}
