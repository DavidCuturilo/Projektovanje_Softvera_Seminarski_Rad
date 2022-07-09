/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Response implements Serializable{
    private ResponseType responseType;
    private Object data;
    private Exception error;

    public Response(ResponseType responseType, Object data, Exception error) {
        this.responseType = responseType;
        this.data = data;
        this.error = error;
    }

    public Response() {
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
}
