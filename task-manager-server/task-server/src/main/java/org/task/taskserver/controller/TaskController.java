package org.task.taskserver.controller;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.request.TaskRequest;
import org.task.taskserver.response.TaskResponse;
import org.task.taskserver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/get-tasks-list")
    public ResponseEntity<Page<TaskDto>> getTaskList(
            Pageable pageable,
            @RequestParam Optional<String> name,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> purchaseDateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> purchaseDateTo,
            @RequestParam Optional<Collection<String>> taskSeriesCodesList

    ) {
        Page<TaskDto> retVal = taskService.getTasksList(pageable, name.orElse(null), purchaseDateFrom.orElse(null), purchaseDateTo.orElse(null), taskSeriesCodesList.orElse(null));
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-tasks")
    public ResponseEntity<List<TaskDto>> fetchtasks() {
        List<TaskDto> retVal = taskService.fetchTasks();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-tasks-series-codes")
    public ResponseEntity<List<TaskDto>> fetchTasksSeriesCodes() {
        List<TaskDto> retVal = taskService.fetchTasksSeriesCodes();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/get-task-by-id")
    public ResponseEntity<TaskResponse> getById(@RequestParam Long id) {
        TaskDto taskDto = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponse(taskDto));
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public ResponseEntity<Long> createTask(@RequestBody TaskRequest request) {
        TaskDto taskDto = new TaskDto(request, null, false);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.createTask(taskDto));
    }

    @PutMapping("/update-task")
    public ResponseEntity<Boolean> update(@RequestParam long id, @RequestBody TaskRequest request) {
        TaskDto taskDto = new TaskDto(request, id, true);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskDto));
    }

    @RequestMapping("/delete-task")
    public ResponseEntity<Boolean> deleteTask(@RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTask(id));
    }

}
