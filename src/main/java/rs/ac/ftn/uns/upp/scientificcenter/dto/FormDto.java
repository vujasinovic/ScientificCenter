package rs.ac.ftn.uns.upp.scientificcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormField;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {
    private String processInstanceId;

    private String taskId;

    private Collection<FormFieldDto> formFields;
}
