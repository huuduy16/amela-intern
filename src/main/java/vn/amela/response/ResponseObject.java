package vn.amela.response;

public class ResponseObject {

    private Object data;
    private Status status;

    public ResponseObject(Object data, Status status) {
        this.data = data;
        this.status = status;
    }
}
