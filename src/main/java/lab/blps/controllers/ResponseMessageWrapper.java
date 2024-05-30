package lab.blps.controllers;

public record ResponseMessageWrapper(String responseMessage) {
    public String responseMessage() {
        return responseMessage;
    }
}
