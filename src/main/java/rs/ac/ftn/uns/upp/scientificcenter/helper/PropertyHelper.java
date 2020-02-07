package rs.ac.ftn.uns.upp.scientificcenter.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.ac.ftn.uns.upp.scientificcenter.handler.MagazineHandler;
import rs.ac.ftn.uns.upp.scientificcenter.handler.UsersHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.PropertyName.Group.EDITORS;
import static rs.ac.ftn.uns.upp.scientificcenter.globals.PropertyName.Group.REVIEWERS;
import static rs.ac.ftn.uns.upp.scientificcenter.globals.PropertyName.Magazine.MAGAZINE;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyHelper {
    public static List<String> findAvailableValues(Map<String, String> properties) {
        List<String> availableValues = new ArrayList<>();

        if (notNullOrEmpty(properties)) {
            String key = properties.entrySet().iterator().next().getKey();

            if (equalsAny(key, REVIEWERS, EDITORS)) {
                availableValues = UsersHandler.getGroupMembersIds(key);
            } else if (equalsAny(key, MAGAZINE)) {
                availableValues = MagazineHandler.getAvailableMagazines();
            }
        }
        return availableValues;
    }
}
