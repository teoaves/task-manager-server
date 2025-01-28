package org.task.taskserver.repository;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.dto.TaskSeriesDto;

import java.util.List;

public interface TaskSeriesRepository {

    List<TaskSeriesDto> getTaskSeriesList();

    List<TaskDto> fetchTasksSeriesCodes();

    List<TaskDto> fetchAvailableTasks();

    List<TaskSeriesDto> fetchTasksByTaskSeriesCode(String qrCode);

    long createTaskSeries(TaskSeriesDto TaskSeriesDto);

    boolean updateTaskSeries(TaskSeriesDto TaskSeriesDto);

    List<TaskDto> getTaskSeriesById(long id);

    boolean deleteTaskSeries(Long id);
}
