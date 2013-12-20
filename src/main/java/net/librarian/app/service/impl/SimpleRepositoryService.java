package net.librarian.app.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.librarian.app.service.RepositoryService;
import net.librarian.app.web.model.Repo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by siliev on 12/18/13.
 */
public class SimpleRepositoryService implements RepositoryService {

    private GitHubConnectionFactory connectionFactory;
    private ObjectMapper jsonMapper;

    @Override
    public List<Repo> list() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String accessToken = authentication.getCredentials().toString();

        final GitHub gitHub = connectionFactory.createConnection(new AccessGrant(accessToken)).getApi();

        final String requestUrl = "https://api.github.com/user/repos";
        final Class<String> responseType = String.class;
        final Map<String, String> requestParams = new HashMap<String, String>() {{ put("type", "owner"); }};

        final ResponseEntity<String> response = gitHub.restOperations().getForEntity(requestUrl, responseType, requestParams);

        final HttpStatus responseStatusCode = response.getStatusCode();
        if (responseStatusCode != HttpStatus.OK) {
            throw new IllegalStateException("repo list request error, code: " + responseStatusCode + ", headers: " + response.getHeaders() + ", body: " + response.getBody());
        }

        final String rawResponse = response.getBody();

        final List<String> reposInfoJson = splitToSeparateRepoResponses(rawResponse);
        final List<Repo> result = Lists.newArrayListWithExpectedSize(reposInfoJson.size());

        for (String repoInfo : reposInfoJson) {
            try {
                result.add(jsonMapper.readValue(repoInfo, Repo.class));
            } catch (IOException e) {
                throw new IllegalStateException("can not deserialize repo list response to: " + Repo.class.getSimpleName() + ", response body: " + rawResponse);
            }
        }

        return result;
    }

    private List<String> splitToSeparateRepoResponses(String rawResponse) {
        final String repoDelimiter = "{\n    \"id\"";
        final List<String> result = Lists.newLinkedList();

        for (String singleRepoInfo : Splitter.on(repoDelimiter).split(rawResponse)) {
            result.add(repoDelimiter + singleRepoInfo);
        }

        return result;
    }

    @Required
    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Required
    public void setConnectionFactory(GitHubConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
