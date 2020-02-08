package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.equalsAnyIgnoreCase;

public class CheckMemberShipService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        final String magazineAuthor = (String) execution.getVariable("magazineAuthor");

        boolean isOpenAccess = false;

        if (equalsAnyIgnoreCase(magazineAuthor, "john", "peter")) {
            isOpenAccess = true;
        }

        execution.setVariable("openAccess", isOpenAccess);
    }
}
