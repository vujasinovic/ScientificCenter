package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.exception.FieldNullException;
import rs.ac.ftn.uns.upp.scientificcenter.utils.ValidationHelper;

import java.util.Map;

@Service
public class DataValidationService implements JavaDelegate {
    private static final String FORM_DATA = "formData";
    private static final String VALIDATION_SUCCESS = "success";

    @Override
    public void execute(DelegateExecution execution) {
        Map<String, Object> formData = (Map<String, Object>) execution.getVariable(FORM_DATA);

        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            final Object value = entry.getValue();
            if (value == null || value.equals("")) {
                if (entry.getKey().equalsIgnoreCase("email")) {
                    execution.setVariable(VALIDATION_SUCCESS, ValidationHelper.validateEmail((String) value));
                }
                execution.setVariable(VALIDATION_SUCCESS, false);
                throw new FieldNullException(String.format("Field %s cannot be null.", entry.getKey()));
            }
            execution.setVariable(VALIDATION_SUCCESS, true);
        }
    }
}
