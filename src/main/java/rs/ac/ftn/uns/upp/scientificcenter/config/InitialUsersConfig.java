package rs.ac.ftn.uns.upp.scientificcenter.config;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;

@Component
public class InitialUsersConfig {
    private static final String GROUP_TYPE = "WORKFLOW";
    private static final String REVIEWERS = "reviewers";
    private static final String EDITORS = "editors";
    private static final String REVIEWER_PASSWORD = "reviewer";
    private static final String EDITOR_PASSWORD = "editor";
    private static final String TASKLIST = "tasklist";

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostConstruct
    public void setupUsers() {
        List<User> users = identityService.createUserQuery().userIdIn("editor", "guest", "demo", "john", "peter", "mary").list();
        if (users.isEmpty()) {
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

            User user5 = identityService.newUser("john");
            user5.setEmail("john@mail.com");
            user5.setFirstName("John");
            user5.setLastName("John");
            user5.setPassword("john");

            identityService.saveUser(user5);

            User user6 = identityService.newUser("peter");
            user6.setEmail("peter@mail.com");
            user6.setFirstName("Peter");
            user6.setLastName("Peter");
            user6.setPassword("peter");

            identityService.saveUser(user6);

            User user7 = identityService.newUser("mary");
            user7.setEmail("mary@mail.com");
            user7.setFirstName("Mary");
            user7.setLastName("Mary");
            user7.setPassword("mary");

            identityService.saveUser(user7);
        }

        createReviewers();
        createEditors();

    }

    private void createReviewers() {
        List<User> users = identityService.createUserQuery().userIdIn("reviewer1", "reviewer2", "reviewer3", "reviewer4").list();

        if (users.isEmpty()) {
            User user1 = identityService.newUser("reviewer1");
            user1.setEmail("reviewer1@mail.com");
            user1.setFirstName("Reviewer1");
            user1.setLastName("Reviewer1");
            user1.setPassword(REVIEWER_PASSWORD);
            identityService.saveUser(user1);

            User user2 = identityService.newUser("reviewer2");
            user2.setEmail("reviewer2@mail.com");
            user2.setFirstName("Reviewer2");
            user2.setLastName("Reviewer2");
            user2.setPassword(REVIEWER_PASSWORD);
            identityService.saveUser(user2);

            User user3 = identityService.newUser("reviewer3");
            user3.setEmail("reviewer3@mail.com");
            user3.setFirstName("Reviewer3");
            user3.setLastName("Reviewer3");
            user3.setPassword(REVIEWER_PASSWORD);

            identityService.saveUser(user3);

            User user4 = identityService.newUser("reviewer4");
            user4.setEmail("reviewer4@mail.com");
            user4.setFirstName("Reviewer4");
            user4.setLastName("Reviewer4");
            user4.setPassword(REVIEWER_PASSWORD);

            identityService.saveUser(user4);

            Group reviewers = identityService.newGroup(REVIEWERS);
            reviewers.setName("reviewers");
            reviewers.setType(GROUP_TYPE);
            identityService.saveGroup(reviewers);

            identityService.createMembership("reviewer1", REVIEWERS);
            identityService.createMembership("reviewer2", REVIEWERS);
            identityService.createMembership("reviewer3", REVIEWERS);
            identityService.createMembership("reviewer4", REVIEWERS);

            createAuthorizations(REVIEWERS);
        }
    }

    private void createEditors() {
        List<User> users = identityService.createUserQuery().userIdIn("editor1", "editor2", "editor3", "editor4").list();

        if (users.isEmpty()) {
            User user1 = identityService.newUser("editor1");
            user1.setEmail("editor1@mail.com");
            user1.setFirstName("Editor1");
            user1.setLastName("Editor1");
            user1.setPassword(EDITOR_PASSWORD);
            identityService.saveUser(user1);

            User user2 = identityService.newUser("editor2");
            user2.setEmail("editor2@mail.com");
            user2.setFirstName("Editor2");
            user2.setLastName("Editor2");
            user2.setPassword(EDITOR_PASSWORD);
            identityService.saveUser(user2);

            User user3 = identityService.newUser("editor3");
            user3.setEmail("editor3@mail.com");
            user3.setFirstName("Editor3");
            user3.setLastName("Editor3");
            user3.setPassword(EDITOR_PASSWORD);

            identityService.saveUser(user3);

            User user4 = identityService.newUser("editor4");
            user4.setEmail("editor4@mail.com");
            user4.setFirstName("Editor4");
            user4.setLastName("Editor4");
            user4.setPassword(EDITOR_PASSWORD);

            identityService.saveUser(user4);

            Group editors = identityService.newGroup(EDITORS);
            editors.setName("editors");
            editors.setType(GROUP_TYPE);
            identityService.saveGroup(editors);

            identityService.createMembership("editor1", EDITORS);
            identityService.createMembership("editor2", EDITORS);
            identityService.createMembership("editor3", EDITORS);
            identityService.createMembership("editor4", EDITORS);

            createAuthorizations(EDITORS);
        }
    }

    private void createAuthorizations(String groupId) {
        Authorization applicationAuthorization = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        applicationAuthorization.setGroupId(groupId);
        applicationAuthorization.addPermission(ALL);
        applicationAuthorization.setResourceId(TASKLIST);
        applicationAuthorization.setResource(APPLICATION);
        authorizationService.saveAuthorization(applicationAuthorization);

        Authorization processInstanceAuthorization = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        processInstanceAuthorization.setGroupId(groupId);
        processInstanceAuthorization.addPermission(ALL);
        processInstanceAuthorization.setResource(Resources.PROCESS_INSTANCE);
        authorizationService.saveAuthorization(processInstanceAuthorization);

        Authorization processDefinitionAuthorization = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        processDefinitionAuthorization.setGroupId(groupId);
        processDefinitionAuthorization.addPermission(ALL);
        processDefinitionAuthorization.setResource(Resources.PROCESS_DEFINITION);
        processDefinitionAuthorization.setResourceId("textReview");
        authorizationService.saveAuthorization(processDefinitionAuthorization);

        Authorization taskAuthorization = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        taskAuthorization.setGroupId(groupId);
        taskAuthorization.addPermission(ALL);
        taskAuthorization.setResource(Resources.TASK);
        authorizationService.saveAuthorization(taskAuthorization);
    }
}