package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.Globals;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.MagazineService;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessService;
import rs.ac.ftn.uns.upp.scientificcenter.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MagazineServiceImpl implements MagazineService {
    private static final String PROCESS_NAME = "createMagazine";
    private static final String FORM_DATA = "formData";

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

        runtimeService.setVariable(processInstanceId, "foo", "bar");

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

        runtimeService.setVariable(processInstanceId, FORM_DATA, formData);

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
