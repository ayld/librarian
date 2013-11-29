package net.librarian.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;
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
//        GitHubConnectionFactory connectionFactory

//        gitHub.restOperations().
    }
}
