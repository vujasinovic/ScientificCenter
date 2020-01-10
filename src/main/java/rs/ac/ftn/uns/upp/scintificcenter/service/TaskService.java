package rs.ac.ftn.uns.upp.scintificcenter.service;

import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;

public interface TaskService {
    Task get(String processInstanceId);

    TaskFormData formData(String taskId);
}
