package net.librarian.app.security;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BasicDetailsService implements UserDetailsService{

    private HazelcastInstance hazelcast;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        final User result = (User) hazelcast.getMap("loggedInUsers").get(username);

        if (result == null) {
            throw new UsernameNotFoundException("user " + username + " is not logged in");
        }

        return result;
	}

    @Required
    public void setHazelcast(HazelcastInstance hazelcast) {
        this.hazelcast = hazelcast;
    }
}
