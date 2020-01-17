package rs.ac.ftn.uns.upp.scientificcenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.dto.FormFieldDto;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TaskDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static rs.ac.ftn.uns.upp.scientificcenter.utils.MapUtils.cast;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getStartProcess() {
        return new ResponseEntity<>(userService.startProcess(), HttpStatus.OK);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity postForm(HttpServletRequest request, @PathVariable String taskId) {
        String processId = userService.submitForm(taskId, cast(request.getParameterMap()));

        List<TaskDto> tasks = userService.findNextTasks(processId);

        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tasks.get(0), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTaskForm(@PathVariable String taskId) {
        FormFieldDto formFields = userService.getFormFields(taskId);

        return new ResponseEntity<>(formFields, HttpStatus.OK);
    }

    @GetMapping("/activation/{processId}")
    public ResponseEntity getActivation(@PathVariable String processId) {
        userService.activateUser(processId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
