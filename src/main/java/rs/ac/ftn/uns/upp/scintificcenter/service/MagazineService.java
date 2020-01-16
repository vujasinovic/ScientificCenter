package rs.ac.ftn.uns.upp.scintificcenter.service;

import rs.ac.ftn.uns.upp.scintificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scintificcenter.dto.TaskDto;

import java.util.List;
import java.util.Map;

public interface MagazineService {
    FormFieldDto startProcess();

    FormFieldDto getFormFields(String taskId);

    String submitForm(String taskId, Map<String, Object> formData);

    List<TaskDto> findNextTasks(String processId);
}
