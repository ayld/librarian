package net.librarian.app.controller.response;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/2/13
 * Time: 5:04 PM
 */
public class AuthorizationResponse extends OAuthResponse{

    private final String accessToken;

    public AuthorizationResponse(ResponseCode code, String accessToken) {
        super(code);
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
