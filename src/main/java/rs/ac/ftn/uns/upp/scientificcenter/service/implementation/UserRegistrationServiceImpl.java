package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessInstanceService;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessService;
import rs.ac.ftn.uns.upp.scientificcenter.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.ProcessInstanceServiceBeanName.USER_REGISTRATION_SERVICE;

@Service(USER_REGISTRATION_SERVICE)
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements ProcessInstanceService {
    private static final String PROCESS_NAME = "User_Registration";
    private static final String FORM_DATA = "formData";
    private static final String SCIENTIFIC_AREAS = "scientificAreas";

    private final ProcessService processService;

    private final TaskService taskService;

    private final RuntimeService runtimeService;

    private final FormService formService;

    @Override
    public FormFieldDto startProcess() {
        ProcessInstance processInstance = processService.start(PROCESS_NAME);
        String processInstanceId = processInstance.getId();

        Task task = taskService.getByProcess(processInstanceId);
        String taskId = task.getId();

        runtimeService.setVariable(processInstanceId, SCIENTIFIC_AREAS, new ArrayList<>());

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

        List<String> scientificAreas = (List<String>) runtimeService.getVariable(processInstanceId, SCIENTIFIC_AREAS);

        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            String key = entry.getKey();
            if (key.equalsIgnoreCase("scientificArea")) {
                scientificAreas.add((String) entry.getValue());
            }
        }

        runtimeService.setVariable(processInstanceId, SCIENTIFIC_AREAS, scientificAreas);
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

    @Override
    public List<TaskDto> getAllTasks(String name) {
        List<Task> tasks = taskService.getAllByUsername(name);
        List<TaskDto> dtos = new ArrayList<>();

        for (Task task : tasks) {
            dtos.add(new TaskDto(task.getId(), task.getName(), task.getAssignee()));
        }
        return dtos;
    }
}
