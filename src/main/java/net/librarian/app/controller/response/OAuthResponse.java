package net.librarian.app.controller.response;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 11/29/13
 * Time: 2:12 PM
 */
public class OAuthResponse implements Serializable {

    private ResponseCode code;

    public OAuthResponse(ResponseCode code) {
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }
}
