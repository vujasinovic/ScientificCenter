package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.InitialMagazine;
import rs.ac.ftn.uns.upp.scientificcenter.repository.InitialMagazineRepository;

public class SetAuthorService implements JavaDelegate {
    @Autowired
    private InitialMagazineRepository magazineRepository;

    @Override
    public void execute(DelegateExecution execution) {
        String magazineName = (String) execution.getVariable("magazineAuthor");

        InitialMagazine magazine = magazineRepository.findByNameIgnoreCase(magazineName);

        execution.setVariable("magazineAuthor", magazine.getAuthor());
    }
}
