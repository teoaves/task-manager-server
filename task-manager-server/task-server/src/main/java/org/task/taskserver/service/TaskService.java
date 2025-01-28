package org.task.taskserver.service;


import org.task.taskserver.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface TaskService {

    List<TaskDto> fetchTasks();

    List<TaskDto> fetchTasksSeriesCodes();

    Page<TaskDto> getTasksList(Pageable pageable, String name, Date purchaseDateFrom, Date purchaseDateTo, Collection<String> taskSeriesCodesList);

    long createTask(TaskDto dto);

    boolean updateTask(TaskDto dto);

    TaskDto getTaskById(long id);

    boolean deleteTask(Long id);
}



