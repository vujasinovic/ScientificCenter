package rs.ac.ftn.uns.upp.scientificcenter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.annotation.ProcessHandler;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.ProcessInstanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.globals.ProcessInstanceServiceBeanName.CREATE_MAGAZINE_SERVICE;
import static rs.ac.ftn.uns.upp.scientificcenter.utils.MapperUtils.cast;

@RestController
@RequestMapping(value = "/api/magazine")
public class MagazineController {
    private final ProcessInstanceService createMagazineService;

    public MagazineController(@ProcessHandler(CREATE_MAGAZINE_SERVICE) ProcessInstanceService processInstanceService) {
        this.createMagazineService = processInstanceService;
    }

    @GetMapping
    public ResponseEntity getStartProcess(Authentication authentication) {
        return new ResponseEntity<>(createMagazineService.startProcess(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity postForm(HttpServletRequest request, @PathVariable String taskId) {
        String processId = createMagazineService.submitForm(taskId, cast(request.getParameterMap()));

        List<TaskDto> tasks = createMagazineService.findNextTasks(processId);

        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tasks.get(0), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTaskForm(@PathVariable String taskId) {
        FormDto formFields = createMagazineService.getFormFields(taskId);

        return new ResponseEntity<>(formFields, HttpStatus.OK);
    }

}
