package rs.ac.ftn.uns.upp.scientificcenter.service;

import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;

import java.util.List;

public interface TaskService {
    Task getByProcess(String processInstanceId);

    TaskFormData formData(String taskId);

    Task getById(String taskId);

    List<Task> getAllByProcess(String processInstanceId);

    List<Task> getAllByUsername(String username);
}
