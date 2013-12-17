package net.librarian.app.service;

import net.librarian.app.github.Scopes;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/6/13
 * Time: 4:20 PM
 */
public interface AuthService {

    public String getGitHubAuthUrl(Scopes... scopes);
}
