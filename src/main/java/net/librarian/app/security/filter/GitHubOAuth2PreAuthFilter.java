package net.librarian.app.security.filter;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by siliev on 12/19/13.
 */
public class GitHubOAuth2PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String oAuthCallbackUrl;
    private HazelcastInstance hazelcast;

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (skip(request)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        final String code = request.getParameter("code");
//
//        if (Strings.isNullOrEmpty(code)) {
//            throw new IllegalArgumentException("GitHub auth request does not contain code param");
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(new User(code), code));
//
//        chain.doFilter(request, response);
//    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//        hazelcast.getMap("loggedInUsers").get()
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    private boolean isAlreadyAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    private boolean skip(ServletRequest request) {
        final boolean requestIsOnOauthUrl = ((HttpServletRequest) request).getRequestURL().toString().contains(oAuthCallbackUrl);

        return !requestIsOnOauthUrl || isAlreadyAuthenticated();
    }

    @Required
    public void setHazelcast(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }

    @Required
    public void setoAuthCallbackUrl(String oAuthCallbackUrl) {
        this.oAuthCallbackUrl = oAuthCallbackUrl;
    }
}
