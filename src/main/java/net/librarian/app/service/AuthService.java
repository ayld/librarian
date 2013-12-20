package net.librarian.app.service;

import net.librarian.app.github.Scopes;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/6/13
 * Time: 4:20 PM
 */
public interface AuthService {

    String getGitHubAuthUrl(Scopes... scopes);

    /**
     * This method must be called only if the user has logged in to GihHub and GihHub has made an auth request to us.
     * If the user has not logged in the method will blow up (informatively) and I will hate you.
     *
     * @param authToken a (valid, non-expired) OAuth2 token
     *
     * @return the username of the authenticated user
     * */
    String getAuthenticatedUserName(String authToken);
}
