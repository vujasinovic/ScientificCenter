package rs.ac.ftn.uns.upp.scintificcenter.service.implementation;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scintificcenter.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final org.camunda.bpm.engine.TaskService taskService;

    private final FormService formService;

    public TaskServiceImpl(FormService formService, org.camunda.bpm.engine.TaskService taskService) {
        this.formService = formService;
        this.taskService = taskService;
    }

    @Override
    public Task get(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
    }

    @Override
    public TaskFormData formData(String taskId) {
        return formService.getTaskFormData(taskId);
    }
}
