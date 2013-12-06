package net.librarian.app.controller;

import com.google.common.base.Strings;
import net.librarian.app.controller.response.AuthorizationResponse;
import net.librarian.app.controller.response.OAuthResponse;
import net.librarian.app.controller.response.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 11/29/13
 * Time: 1:59 PM
 */
@Controller
@RequestMapping(value = "oauth")
public class GithubAuthCallbackController {

    @RequestMapping(value="/auth", method = RequestMethod.GET , produces = "application/json")
    public OAuthResponse auth() {
        return new OAuthResponse(ResponseCode.OK);
    }

    @RequestMapping(value="/authorize", method = RequestMethod.GET , produces = "application/json", params = "code")
    public AuthorizationResponse authorize(@RequestParam String code) {
        if (Strings.isNullOrEmpty(code)) {
            return new AuthorizationResponse(ResponseCode.FAILED, "missing request param code");
        }
        return new AuthorizationResponse(ResponseCode.OK, code);
    }
}
