package rs.ac.ftn.uns.upp.scintificcenter.service.implementation;

import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scintificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scintificcenter.service.MagazineService;
import rs.ac.ftn.uns.upp.scintificcenter.service.ProcessService;
import rs.ac.ftn.uns.upp.scintificcenter.service.TaskService;

@Service
public class MagazineServiceImpl implements MagazineService {
    private static final String PROCESS_NAME = "createMagazine";

    private final ProcessService processService;

    private final TaskService taskService;

    public MagazineServiceImpl(ProcessService processService, TaskService taskService) {
        this.processService = processService;
        this.taskService = taskService;
    }

    @Override
    public FormFieldDto startProcess() {
        ProcessInstance processInstance = processService.start(PROCESS_NAME);
        String processInstanceId = processInstance.getId();

        Task task = taskService.get(processInstanceId);
        String taskId = task.getId();

        TaskFormData taskFormData = taskService.formData(taskId);

        return new FormFieldDto(processInstanceId, taskId, taskFormData.getFormFields());
    }
}
