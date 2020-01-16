package rs.ac.ftn.uns.upp.scientificcenter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.MagazineService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.MapUtils.cast;

@RestController
@RequestMapping(value = "/api/magazine")
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping
    public ResponseEntity getStartProcess() {
        return new ResponseEntity<>(magazineService.startProcess(), HttpStatus.OK);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity postForm(HttpServletRequest request, @PathVariable String taskId) {
        String processId = magazineService.submitForm(taskId, cast(request.getParameterMap()));

        List<TaskDto> tasks = magazineService.findNextTasks(processId);

        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tasks.get(0), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTaskForm(@PathVariable String taskId) {
        FormFieldDto formFields = magazineService.getFormFields(taskId);

        return new ResponseEntity<>(formFields, HttpStatus.OK);
    }

}
