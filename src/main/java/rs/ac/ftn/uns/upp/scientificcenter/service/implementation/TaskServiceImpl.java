package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final org.camunda.bpm.engine.TaskService taskService;

    private final FormService formService;

    public TaskServiceImpl(FormService formService, org.camunda.bpm.engine.TaskService taskService) {
        this.formService = formService;
        this.taskService = taskService;
    }

    @Override
    public Task claim(String taskId, String userId) {
        Task task = getById(taskId);
        task.setAssignee(userId);
        taskService.claim(taskId, userId);

        return task;
    }

    @Override
    public Task getByProcess(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
    }

    @Override
    public TaskFormData formData(String taskId) {
        return formService.getTaskFormData(taskId);
    }

    @Override
    public Task getById(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public List<Task> getAllByProcess(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }

    @Override
    public List<Task> getAllByUsername(String username) {
        return taskService.createTaskQuery().taskAssignee(username).list();
    }
}
