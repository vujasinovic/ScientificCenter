package rs.ac.ftn.uns.upp.scientificcenter.service.implementation.process;

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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.Globals.ProcessName;

@Service
@RequiredArgsConstructor
public class TextReviewServiceImpl implements ProcessInstanceService {
    private final ProcessService processService;

    private final TaskService taskService;

    private final RuntimeService runtimeService;

    private final FormService formService;

    @Override
    public FormFieldDto startProcess() {
        ProcessInstance processInstance = processService.start(ProcessName.TEXT_REVIEW);
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
