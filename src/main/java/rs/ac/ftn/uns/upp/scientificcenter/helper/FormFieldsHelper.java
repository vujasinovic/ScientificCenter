package rs.ac.ftn.uns.upp.scientificcenter.helper;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Component;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;

import java.util.ArrayList;
import java.util.List;

@Component
public final class FormFieldsHelper {
    private static RuntimeService runtimeService;

    private FormFieldsHelper(RuntimeService runtimeService) {
        FormFieldsHelper.runtimeService = runtimeService;
    }

    public static List<FormFieldDto> convertToDto(String processInstanceId, List<FormField> formFields) {
        List<FormFieldDto> formFieldDtos = new ArrayList<>();

        for (FormField formField : formFields) {
            FormFieldDto formFieldDto = new FormFieldDto();
            formFieldDto.setId(formField.getId());
            formFieldDto.setType(formField.getType());
            formFieldDto.setTypeName(formField.getTypeName());
            formFieldDto.setLabel(formField.getLabel());
            formFieldDto.setAvailableValues(PropertyHelper.findAvailableValues(formField.getProperties()));
            formFieldDto.setReadOnly(false);

            if (PropertyHelper.checkConstraints(formField.getValidationConstraints())) {
                final String variable = (String) runtimeService.getVariable(processInstanceId, formField.getId());
                formFieldDto.setValue(variable);
                formFieldDto.setReadOnly(true);
            }

            formFieldDtos.add(formFieldDto);
        }

        return formFieldDtos;
    }
}
