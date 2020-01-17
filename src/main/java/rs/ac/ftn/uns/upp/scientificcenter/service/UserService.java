package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.User;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    FormFieldDto startProcess();

    FormFieldDto getFormFields(String taskId);

    String submitForm(String taskId, Map<String, Object> formData);

    List<TaskDto> findNextTasks(String processId);

    User save(User user);

    User getOne(Long id);

    List<User> findAll();

    void activateUser(String processId);

    List<TaskDto> getAllTasks(String name);
}
