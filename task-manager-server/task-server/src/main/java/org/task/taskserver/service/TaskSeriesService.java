package org.task.taskserver.service;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.dto.TaskSeriesDto;
import org.task.taskserver.response.TaskSeriesResponse;

import java.util.List;

public interface TaskSeriesService {

    List<TaskSeriesResponse> getTaskSeriesList();

    List<TaskDto> fetchTasksSeriesCodes();

    List<TaskSeriesDto> fetchTasksByTaskSeriesCode(String qrCode);

    List<TaskDto> fetchAvailableTasks();

    long createTaskSeries(TaskSeriesDto dto);

    boolean updateTaskSeries(TaskSeriesDto dto);

    List<TaskDto> getTaskSeriesById(long id);

    boolean deleteTaskSeries(Long id);
}
