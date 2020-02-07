package rs.ac.ftn.uns.upp.scientificcenter.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormField;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FormFieldsHelper {
    public static List<FormFieldDto> convertToDto(List<FormField> formFields) {
        List<FormFieldDto> formFieldDtos = new ArrayList<>();

        for (FormField formField : formFields) {
            FormFieldDto formFieldDto = new FormFieldDto();
            formFieldDto.setId(formField.getId());
            formFieldDto.setType(formField.getType().getName());
            formFieldDto.setTypeName(formField.getTypeName());
            formFieldDto.setLabel(formField.getLabel());
            formFieldDto.setAvailableValues(PropertyHelper.findAvailableValues(formField.getProperties()));

            formFieldDtos.add(formFieldDto);
        }

        return formFieldDtos;
    }
}
