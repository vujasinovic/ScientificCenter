package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;

import java.util.List;
import java.util.Map;

public interface ProcessInstanceService {
    FormFieldDto startProcess();

    FormFieldDto getFormFields(String taskId);

    String submitForm(String taskId, Map<String, Object> formData);

    List<TaskDto> findNextTasks(String processId);

    List<TaskDto> getAllTasks(String name);
}
