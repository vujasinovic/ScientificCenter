package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;
import rs.ac.ftn.uns.upp.scientificcenter.repository.ScientificAreaRepository;

import static java.util.Objects.isNull;

public class SetupEditorService implements JavaDelegate {
    @Autowired
    private ScientificAreaRepository scientificAreaRepository;

    @Override
    public void execute(DelegateExecution execution) {
        String area = (String) execution.getVariable("scientificArea");

        String mainEditor;

        ScientificArea scientificArea = scientificAreaRepository.findByTitleIgnoreCase(area);

        if (isNull(scientificArea)) {
            mainEditor = (String) execution.getVariable("magazineAuthor");
        } else {
            mainEditor = scientificArea.getEditor();
        }

        execution.setVariable("mainEditor", mainEditor);
    }
}
