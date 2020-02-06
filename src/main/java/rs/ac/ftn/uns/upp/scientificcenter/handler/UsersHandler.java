package rs.ac.ftn.uns.upp.scientificcenter.handler;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class UsersHandler {
    private static IdentityService identityService;

    private UsersHandler(IdentityService identityService) {
        UsersHandler.identityService = identityService;
    }

    public static List<String> getGroupMembersIds(String groupId) {
        List<User> groupMembers = identityService.createUserQuery().memberOfGroup(groupId).list();
        List<String> availableValues = new ArrayList<>();

        for (User user : groupMembers) {
            availableValues.add(user.getId());
        }

        return availableValues;
    }
}
