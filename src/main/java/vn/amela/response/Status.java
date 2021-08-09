package vn.amela.response;

public class Status {

    private final String code;
    private final String message;

    public Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
