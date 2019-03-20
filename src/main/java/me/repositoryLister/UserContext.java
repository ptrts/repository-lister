package me.repositoryLister;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
@Getter
@Setter
public class UserContext {

    private String gitHubAccessToken;
}
