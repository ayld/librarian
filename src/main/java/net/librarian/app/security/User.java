package net.librarian.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by siliev on 12/19/13.
 */
public class User implements Principal, UserDetails {

    private String name;
    private final String token;

    public User(String token) {
        this.token = token;
    }

    public User(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name == null ? "unspecified" : name;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>() {{
            add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER";
                }
            });
        }};
    }

    @Override
    public String getPassword() {
        return "hidden";
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
