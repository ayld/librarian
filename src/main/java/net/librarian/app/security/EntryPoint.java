package net.librarian.app.security;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by siliev on 12/19/13.
 */
public class EntryPoint implements AuthenticationEntryPoint {

    private UrlMatcher matcher = new AntUrlPathMatcher();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private String loginUrl;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        System.out.println("authentication: " + SecurityContextHolder.getContext().getAuthentication());

        redirectStrategy.sendRedirect(request, response, loginUrl);
    }

    public void setMatcher(UrlMatcher matcher) {
        this.matcher = matcher;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Required
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
