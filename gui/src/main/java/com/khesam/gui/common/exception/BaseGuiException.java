package com.khesam.gui.common.exception;

public abstract class BaseGuiException extends RuntimeException {

    public BaseGuiException(String message) {
        super(message);
    }

    public BaseGuiException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class LoadingFxmlException extends BaseGuiException {

        public LoadingFxmlException(Exception exception) {
            super("Failed to load fxml!", exception);
        }
    }

    public static class ThreadSynchronizationException extends BaseGuiException {

        public ThreadSynchronizationException(Exception exception) {
            super("Failed to synchronized threads", exception);
        }
    }

    public static class GuiConcurrentAccessException extends BaseGuiException {

        public GuiConcurrentAccessException() {
            super("Running multiple instances of Gui is prohibited");
        }
    }

    public static class UnknownException extends BaseGuiException {

        public UnknownException(Exception exception) {
            super("Unknown error!", exception);
        }
    }
}
