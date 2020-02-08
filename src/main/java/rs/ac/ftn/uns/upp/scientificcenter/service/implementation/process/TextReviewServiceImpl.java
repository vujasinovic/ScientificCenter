package rs.ac.ftn.uns.upp.scientificcenter.service.implementation.process;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.helper.FormFieldsHelper;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessInstanceService;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessService;
import rs.ac.ftn.uns.upp.scientificcenter.service.TaskService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.Globals.ProcessName;
import static rs.ac.ftn.uns.upp.scientificcenter.globals.ProcessInstanceServiceBeanName.TEXT_REVIEW_SERVICE;

@Service(TEXT_REVIEW_SERVICE)
@RequiredArgsConstructor
public class TextReviewServiceImpl implements ProcessInstanceService {
    private static final Logger LOGGER = LogManager.getLogger(TextReviewServiceImpl.class);

    private final ProcessService processService;

    private final TaskService taskService;

    private final FormService formService;

    private final IdentityService identityService;

    private final RuntimeService runtimeService;

    @Override
    public FormDto startProcess(String username) {
        identityService.clearAuthentication();

        ProcessInstance processInstance = processService.start(ProcessName.TEXT_REVIEW);
        String processInstanceId = processInstance.getId();

        runtimeService.setVariable(processInstanceId, "initiator", username);

        Task task = taskService.getByProcess(processInstanceId);
        String taskId = task.getId();

        TaskFormData taskFormData = taskService.formData(taskId);
        List<FormFieldDto> formFieldDtos = FormFieldsHelper.convertToDto(task.getProcessInstanceId(), taskFormData.getFormFields());

        return new FormDto(processInstanceId, taskId, formFieldDtos);
    }

    @Override
    public FormDto getFormFields(String id) {
        Task task = taskService.getById(id);
        final String taskId = task.getId();

        TaskFormData taskFormData = taskService.formData(taskId);
        List<FormFieldDto> formFieldDtos = FormFieldsHelper.convertToDto(task.getProcessInstanceId(), taskFormData.getFormFields());

        return new FormDto(task.getProcessInstanceId(), taskId, formFieldDtos);
    }

    @Override
    public String submitForm(String taskId, Map<String, Object> formData) {
        Task task = taskService.getById(taskId);
        String processInstanceId = task.getProcessInstanceId();

        formService.submitTaskForm(taskId, formData);
        return processInstanceId;
    }

    @Override
    public List<TaskDto> findNextTasks(String processId) {
        List<Task> tasks = taskService.getAllByProcess(processId);

        return tasks.stream().map(t -> new TaskDto(t.getId(), t.getName(), t.getAssignee())).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllTasks(String name) {
        List<Task> tasks = taskService.getAllByUsername(name);

        return tasks.stream().map(t -> new TaskDto(t.getId(), t.getName(), t.getAssignee())).collect(Collectors.toList());
    }
}
