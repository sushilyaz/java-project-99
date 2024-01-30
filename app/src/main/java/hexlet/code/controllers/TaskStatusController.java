package hexlet.code.controllers;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.TaskStatusDTO;
import hexlet.code.dto.TaskStatusUpdateDTO;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.service.TaskStatusService;
import hexlet.code.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test_statuses")
public class TaskStatusController {

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private UserUtils userUtils;

    private User currentUser = userUtils.getCurrentUser();

    @GetMapping(path = "")
    public ResponseEntity<List<TaskStatusDTO>> index() {
        var tasks = taskStatusService.getAllTasks();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Total-Count", String.valueOf(tasks.size()))
                .body(tasks);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskStatusDTO> show(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskStatusService.getTaskById(id));
    }

    @PostMapping(path = "")
    public ResponseEntity<TaskStatusDTO> create(@RequestBody TaskStatusCreateDTO dto) {
        if (currentUser != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskStatusDTO> update(@RequestBody TaskStatusUpdateDTO dto, @PathVariable Long id) {
        if (currentUser != null) {
            taskStatusService.update(dto, id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TaskStatusDTO> delete(@PathVariable Long id) {
        if (currentUser != null) {
            taskStatusService.deleteTask(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }
    }
}
