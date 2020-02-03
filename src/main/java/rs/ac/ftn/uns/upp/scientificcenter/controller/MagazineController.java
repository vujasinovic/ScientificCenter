package rs.ac.ftn.uns.upp.scientificcenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.annotation.ProcessInstance;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessInstanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.ProcessInstanceServiceBeanName.CREATE_MAGAZINE_SERVICE;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.MapUtils.cast;

@RestController
@RequestMapping(value = "/api/magazine")
@RequiredArgsConstructor
public class MagazineController {

    @ProcessInstance(CREATE_MAGAZINE_SERVICE)
    private ProcessInstanceService createMagazineService;

    @GetMapping
    public ResponseEntity getStartProcess() {
        return new ResponseEntity<>(createMagazineService.startProcess(), HttpStatus.OK);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity postForm(HttpServletRequest request, @PathVariable String taskId) {
        String processId = createMagazineService.submitForm(taskId, cast(request.getParameterMap()));

        List<TaskDto> tasks = createMagazineService.findNextTasks(processId);

        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tasks.get(0), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTaskForm(@PathVariable String taskId) {
        FormFieldDto formFields = createMagazineService.getFormFields(taskId);

        return new ResponseEntity<>(formFields, HttpStatus.OK);
    }

}
