package rs.ac.ftn.uns.upp.scientificcenter.config;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitialUsersConfig {
    @Autowired
    private IdentityService identityService;

    @PostConstruct
    public void setupUsers() {
        List<User> users = identityService.createUserQuery().userIdIn("reviewer", "editor", "guest", "demo").list();
        if(users.isEmpty() ) {
            User user1 = identityService.newUser("reviewer");
            user1.setEmail("reviewer@mail.com");
            user1.setFirstName("Reviewer");
            user1.setLastName("Reviewer");
            user1.setPassword("reviewer");
            identityService.saveUser(user1);

            User user2 = identityService.newUser("editor");
            user2.setEmail("editor@mail.com");
            user2.setFirstName("Editor");
            user2.setLastName("Editor");
            user2.setPassword("editor");
            identityService.saveUser(user2);

            User user3 = identityService.newUser("guest");
            user3.setEmail("guest@mail.com");
            user3.setFirstName("Guest");
            user3.setLastName("Guest");
            user3.setPassword("guest");

            identityService.saveUser(user3);

            User user4 = identityService.newUser("demo");
            user4.setEmail("demo@mail.com");
            user4.setFirstName("Demo");
            user4.setLastName("Demo");
            user4.setPassword("demo");

            identityService.saveUser(user4);
        }
    }
}
