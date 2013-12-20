package net.librarian.app.controller;

import com.google.common.base.Strings;
import com.hazelcast.core.HazelcastInstance;
import net.librarian.app.controller.response.AuthorizationResponse;
import net.librarian.app.controller.response.OAuthResponse;
import net.librarian.app.controller.response.ResponseCode;
import net.librarian.app.security.User;
import net.librarian.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 11/29/13
 * Time: 1:59 PM
 */
@Controller
@RequestMapping(value = "oauth")
public class GithubAuthCallbackController {

    @Autowired
    private HazelcastInstance hzInstance;

    @Autowired
    private AuthService authService;

    @RequestMapping(value="/auth", method = RequestMethod.GET , produces = "application/json")
    public OAuthResponse auth() {
        return new OAuthResponse(ResponseCode.OK);
    }

    @RequestMapping(value="/authorize", method = RequestMethod.GET , produces = "application/json", params = "code")
    public AuthorizationResponse authorize(@RequestParam String code) {
        if (Strings.isNullOrEmpty(code)) {
            return new AuthorizationResponse(ResponseCode.FAILED, "missing request param code");
        }

        final Map<String, User> loggedInUsers = hzInstance.getMap("loggedInUsers");

        final String username = authService.getAuthenticatedUserName(code);
        loggedInUsers.put(username, new User(code, username));

        return new AuthorizationResponse(ResponseCode.OK, code);
    }
}
