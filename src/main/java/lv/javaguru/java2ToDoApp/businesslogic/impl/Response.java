package lv.javaguru.java2ToDoApp.businesslogic.impl;

import java.util.Map;

public class Response {
    private boolean success;
    private Map<String, Error> errors;

    public static Response createSuccessResponse() {

        return new Response(true, null);
    }

    public static Response createFailResponse(Map<String, Error> errors) {

        return new Response(false, errors);
    }

    public Response(boolean success, Map<String, Error> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess() {

        return success;
    }

    public boolean isFail() {

        return !success;
    }

    public Map<String, Error> getErrors() {
        return errors;
    }
}
