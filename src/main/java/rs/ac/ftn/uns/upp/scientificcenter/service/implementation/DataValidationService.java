package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.exception.FieldNullException;

import java.util.Map;

@Service
public class DataValidationService implements JavaDelegate {
    private static final String FORM_DATA = "formData";
    private static final String VALIDATION_SUCCESS = "success";

    @Override
    public void execute(DelegateExecution execution) {
        Map<String, Object> formData = (Map<String, Object>) execution.getVariable(FORM_DATA);

        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            if (entry.getValue() == null || entry.getValue().equals("")) {
                execution.setVariable("success", false);
                throw new FieldNullException(String.format("Field %s cannot be null.", entry.getKey()));
            }
            execution.setVariable(VALIDATION_SUCCESS, true);
        }
    }
}
