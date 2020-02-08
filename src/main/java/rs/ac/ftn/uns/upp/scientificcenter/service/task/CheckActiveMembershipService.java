package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckActiveMembershipService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        final String magazineAuthor = (String) execution.getVariable("magazineAuthor");

        boolean membershipActive = false;

        if (magazineAuthor.contains("peter")) {
            membershipActive = true;
        }

        execution.setVariable("membershipActive", membershipActive);
    }
}
