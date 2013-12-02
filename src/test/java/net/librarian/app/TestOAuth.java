package net.librarian.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 11/29/13
 * Time: 1:00 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/test-contexts/testOauth.xml"})
public class TestOAuth {

//    @Autowired
    private GitHub gitHub;

    @Test
    public void githubOAuth() {
        final String appId = "53a67a09360185f04549";
        final String appSecret = "0b01f6fe72cde13c9bcb741c20c988eafe9b7f9c";

        final GitHubConnectionFactory connectionFactory = new GitHubConnectionFactory(appId, appSecret);

        final String redirectUri = "https://www.facebook.com/connect/login_success.html";
        final String permissionScope = "user";

//        new OAuth2Parameters(redirectUri, permissionScope, null, null);
        final OAuth2Parameters params = new OAuth2Parameters();
        final OAuth2Operations operations = connectionFactory.getOAuthOperations();

        final String authorizeUrl = operations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);

        System.out.println(authorizeUrl);

//        gitHub.restOperations().
    }
}
