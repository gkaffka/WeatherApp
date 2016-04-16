package events;

final public class EventError {
    final private String errorMessage;

    public EventError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
