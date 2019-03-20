package me.repositoryLister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Service
public class GitHubService {

    private static final ParameterizedTypeReference<Map<String, Object>> MAP_RESPONSE_TYPE =
        new ParameterizedTypeReference<Map<String, Object>>() {
        };

    // Fill these out with the values from Github
    private static final String githubClientID = "ed00ba7ef6c9dee629c1";
    private static final String githubClientSecret = "0e681c2ef59ca0504748fbb5cf1460ad77f3dc40";

    // This is the URL we'll send the user to first
    // to get their authorization
    private static final String authorizeURL = "https://github.com/login/oauth/authorize";

    // This is the endpoint we'll request an access token from
    private static final String tokenURL = "https://github.com/login/oauth/access_token";

    // This is the Github base URL for API requests
    private static final String apiURLBase = "https://api.github.com/";

    // The URL for this script, used as the redirect URL
    private static final String baseURL = "https://localhost:8080/github-authorization-callback";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserContext userContext;

    void foo() {
        HttpHeaders headers = new HttpHeaders();
        exchange("http://github.com", HttpMethod.POST, headers);
    }

    public Map<String, Object> exchange(String url, HttpMethod method, MultiValueMap<String, String> headersArg) {

        HttpHeaders headers = new HttpHeaders(headersArg);

        // Accept
        headers.add(HttpHeaders.ACCEPT, "application/vnd.github.v3+json");
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        // User-Agent
        headers.add(HttpHeaders.USER_AGENT, "http://localhost:8080/home");

        // Authorization
        if (userContext.getGitHubAccessToken() != null) {
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userContext.getGitHubAccessToken());
        }

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, method, URI.create(url));

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(requestEntity, MAP_RESPONSE_TYPE);

        return responseEntity.getBody();
    }
}
