package rs.ac.ftn.uns.upp.scientificcenter.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormField;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.handler.UsersHandler;

import java.util.ArrayList;
import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.GroupName.*;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.ObjectUtils.equalsAny;

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

            String groupId = formField.getProperties().entrySet().iterator().next().getKey();
            if (equalsAny(groupId, REVIEWERS, EDITORS)) {
                formFieldDto.setAvailableValues(UsersHandler.getGroupMembersIds(groupId));
            }
            formFieldDtos.add(formFieldDto);
        }

        return formFieldDtos;
    }
}
