package rs.ac.ftn.uns.upp.scientificcenter.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import rs.ac.ftn.uns.upp.scientificcenter.handler.EvaluationHandler;
import rs.ac.ftn.uns.upp.scientificcenter.handler.MagazineHandler;
import rs.ac.ftn.uns.upp.scientificcenter.handler.ScientificAreaHandler;
import rs.ac.ftn.uns.upp.scientificcenter.handler.UsersHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.PropertyName.*;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PropertyHelper {
    static List<String> findAvailableValues(Map<String, String> properties) {
        List<String> availableValues = new ArrayList<>();

        if (notNullOrEmpty(properties)) {
            String key = properties.entrySet().iterator().next().getKey();

            if (equalsAny(key, REVIEWERS, EDITORS)) {
                availableValues = UsersHandler.getGroupMembersIds(key);
            } else if (equalsAny(key, MAGAZINE)) {
                availableValues = MagazineHandler.getAvailableMagazines();
            } else if (equalsAny(key, SCIENTIFIC_AREA)) {
                availableValues = ScientificAreaHandler.getScientificAreas();
            } else if (equalsAny(key, EVALUATION)) {
                availableValues = EvaluationHandler.getEvaluations();
            }
        }
        return availableValues;
    }

    static Boolean checkConstraints(List<FormFieldValidationConstraint> validationConstraints) {
        boolean retVal = false;

        if (!nullOrEmpty(validationConstraints)) {
            for (FormFieldValidationConstraint formFieldValidationConstraint : validationConstraints) {
                if (formFieldValidationConstraint.getName().equalsIgnoreCase("readonly")) {
                    retVal = true;
                    break;
                }
            }
        }

        return retVal;
    }
}
