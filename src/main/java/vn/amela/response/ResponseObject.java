package vn.amela.response;

/*
{
    data:{
        ...
    }
    status:{
        code:
        message:
    }
}
*/

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObject {

    private Object data;
    private Status status;

    public ResponseObject(Object data, Status status) {
        this.data = data;
        this.status = status;
    }
}