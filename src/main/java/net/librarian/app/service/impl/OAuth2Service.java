package net.librarian.app.service.impl;

import net.librarian.app.github.Scopes;
import net.librarian.app.service.AuthService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
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

    private GitHubConnectionFactory connectionFactory;

    @Override
    public String getGitHubAuthUrl(Scopes... scopes) {

        final OAuth2Parameters params = new OAuth2Parameters();
        params.setScope(Scopes.asString(scopes));

        final OAuth2Operations operations = connectionFactory.getOAuthOperations();

        return operations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
    }

    @Override
    public String getAuthenticatedUserName(String authToken) {
        final GitHub gitHub = connectionFactory.createConnection(new AccessGrant(authToken)).getApi();
        return gitHub.userOperations().getProfileId();
    }

    @Required
    public void setConnectionFactory(GitHubConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
