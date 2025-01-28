package org.task.taskserver.controller;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.dto.TaskSeriesDto;
import org.task.taskserver.request.TaskSeriesRequest;
import org.task.taskserver.response.TaskSeriesResponse;
import org.task.taskserver.service.TaskSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks-series")
public class TaskSeriesController {

    @Autowired
    private TaskSeriesService taskSeriesService;

    @RequestMapping("/get-task-series-list")
    public ResponseEntity<List<TaskSeriesResponse>> gettaskSeriesList() {

        List<TaskSeriesResponse> dtos = taskSeriesService.getTaskSeriesList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/create-task-series")
    public ResponseEntity<Long> create(@RequestBody TaskSeriesRequest request) {
        TaskSeriesDto dto = new TaskSeriesDto(request, null, false);
        return ResponseEntity.status(HttpStatus.OK).body(taskSeriesService.createTaskSeries(dto));
    }

    @PutMapping("/update-task-series")
    public ResponseEntity<Boolean> update(@RequestParam Long id, @RequestBody TaskSeriesRequest request) {
        TaskSeriesDto taskSeriesDto = new TaskSeriesDto(request, id, true);
        return ResponseEntity.status(HttpStatus.OK).body(taskSeriesService.updateTaskSeries(taskSeriesDto));
    }

    @GetMapping("/fetch-available-tasks")
    public ResponseEntity<List<TaskDto>> fetchAvailabletasks() {
        List<TaskDto> retVal = taskSeriesService.fetchAvailableTasks();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-tasks-by-task-series-code")
    public ResponseEntity<List<TaskSeriesDto>> fetchtasksBytaskSeriesCode(
            @RequestParam String qrCode) {
        List<TaskSeriesDto> retVal = taskSeriesService.fetchTasksByTaskSeriesCode(qrCode);
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/get-task-series-by-id")
    public ResponseEntity<List<TaskDto>> getById(@RequestParam Long id) {
        List<TaskDto> retVal = taskSeriesService.getTaskSeriesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @RequestMapping("/delete-task-series")
    public ResponseEntity<Boolean> deletetaskSeries(@RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskSeriesService.deleteTaskSeries(id));
    }
}
