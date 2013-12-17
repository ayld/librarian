package net.librarian.app.service.impl;

import net.librarian.app.github.Scopes;
import net.librarian.app.service.AuthService;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/6/13
 * Time: 5:11 PM
 */
public class OAuth2Service implements AuthService{

    // hardcoded because why not ?
    // no seriously, this doesnt change ...
    private static final String APP_ID = "53a67a09360185f04549";
    private static final String APP_SECRET = "0b01f6fe72cde13c9bcb741c20c988eafe9b7f9c";

    @Override
    public String getGitHubAuthUrl(Scopes... scopes) {
        final GitHubConnectionFactory connectionFactory = new GitHubConnectionFactory(APP_ID, APP_SECRET);

        final OAuth2Parameters params = new OAuth2Parameters();
        params.setScope(Scopes.asString(scopes));

        final OAuth2Operations operations = connectionFactory.getOAuthOperations();

        return operations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
    }
}
