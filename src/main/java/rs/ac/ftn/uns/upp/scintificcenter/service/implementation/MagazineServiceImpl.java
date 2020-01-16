package rs.ac.ftn.uns.upp.scintificcenter.service.implementation;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scintificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scintificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scintificcenter.service.MagazineService;
import rs.ac.ftn.uns.upp.scintificcenter.service.ProcessService;
import rs.ac.ftn.uns.upp.scintificcenter.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MagazineServiceImpl implements MagazineService {
    private static final String PROCESS_NAME = "createMagazine";

    private final ProcessService processService;

    private final TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    FormService formService;

    public MagazineServiceImpl(ProcessService processService, TaskService taskService) {
        this.processService = processService;
        this.taskService = taskService;
    }

    @Override
    public FormFieldDto startProcess() {
        ProcessInstance processInstance = processService.start(PROCESS_NAME);
        String processInstanceId = processInstance.getId();

        Task task = taskService.getByProcess(processInstanceId);
        String taskId = task.getId();

        TaskFormData taskFormData = taskService.formData(taskId);

        return new FormFieldDto(processInstanceId, taskId, taskFormData.getFormFields());
    }

    @Override
    public FormFieldDto getFormFields(String taskId) {
        Task task = taskService.getById(taskId);
        return new FormFieldDto(task.getProcessInstanceId(), task.getId(), taskService.formData(taskId).getFormFields());
    }

    @Override
    public String submitForm(String taskId, Map<String, Object> formData) {
        Task task = taskService.getById(taskId);

        String processInstanceId = task.getProcessInstanceId();

        runtimeService.setVariable(processInstanceId, "registration", formData);
        formService.submitTaskForm(taskId, formData);

        return processInstanceId;
    }

    @Override
    public List<TaskDto> findNextTasks(String processId) {

        List<Task> tasks = taskService.getAllByProcess(processId);
        List<TaskDto> dtos = new ArrayList<>();

        for (Task task : tasks) {
            dtos.add(new TaskDto(task.getId(), task.getName(), task.getAssignee()));
        }

        return dtos;
    }
}
