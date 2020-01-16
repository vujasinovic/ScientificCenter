package rs.ac.ftn.uns.upp.scintificcenter.controller;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.rest.mapper.MultipartFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import rs.ac.ftn.uns.upp.scintificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scintificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scintificcenter.service.MagazineService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static rs.ac.ftn.uns.upp.scintificcenter.MapUtils.cast;

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
