package org.task.taskserver.repository;

import org.task.taskserver.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskRepository {

    List<TaskDto> fetchTasks();

    List<TaskDto> fetchTasksSeriesCodes();

    Page<TaskDto> getTasksList(Pageable pageable, TaskDto dto);

    long createTask(TaskDto taskDto);

    boolean updateTask(TaskDto taskDto);

    TaskDto getTaskById(long id);

    boolean deleteTask(Long id);
}
