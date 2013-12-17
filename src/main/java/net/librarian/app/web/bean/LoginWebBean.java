package net.librarian.app.web.bean;

import net.librarian.app.github.Scopes;
import net.librarian.app.service.AuthService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/6/13
 * Time: 12:08 PM
 */
public class LoginWebBean {

    private transient AuthService authService;

    public String authUrl() {
        return authService.getGitHubAuthUrl(Scopes.PUBLIC_REPO, Scopes.USER);
    }

    @Required
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
