package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

    private final org.camunda.bpm.engine.TaskService taskService;

    private final FormService formService;

    private final IdentityService identityService;

    public TaskServiceImpl(FormService formService, org.camunda.bpm.engine.TaskService taskService, IdentityService identityService) {
        this.formService = formService;
        this.taskService = taskService;
        this.identityService = identityService;
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
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        final Task nextTask = tasks.iterator().next();
        LOGGER.info("[process] Next task: {}, assignee: {}", nextTask.getName(), nextTask.getAssignee());
        return tasks;
    }

    @Override
    public List<Task> getAllByUsername(String username) {
        List<Task> myTasks = taskService.createTaskQuery().taskAssignee(username).list();
        if (!myTasks.isEmpty()) {
            return myTasks;
        }

        final List<Group> groups = identityService.createGroupQuery().groupMember(username).orderByGroupName().asc().list();
        List<String> groupNames = new ArrayList<>();
        for (Group group : groups) {
            groupNames.add(group.getName());
        }

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroupIn(groupNames).list();
        final Task nextTask = tasks.iterator().next();
        LOGGER.info("[user {}] Next task: {}, assignee: {}", username, nextTask.getName(), nextTask.getAssignee());
        return tasks;
    }
}
