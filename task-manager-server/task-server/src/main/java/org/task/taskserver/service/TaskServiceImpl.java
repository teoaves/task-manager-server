package org.task.taskserver.service;


import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDto> fetchTasks() {
        return taskRepository.fetchTasks();
    }

    @Override
    public List<TaskDto> fetchTasksSeriesCodes() {
        return taskRepository.fetchTasksSeriesCodes();
    }

    @Override
    public Page<TaskDto> getTasksList(Pageable pageable, String name,
                                            Date purchaseDateFrom,
                                            Date purchaseDateTo, Collection<String> taskSeriesCodesList) {
        return taskRepository.getTasksList(pageable, new TaskDto(name, purchaseDateFrom, purchaseDateTo, taskSeriesCodesList));
    }

    @Override
    public long createTask(TaskDto dto) {

        return taskRepository.createTask(dto);
    }

    @Override
    public boolean updateTask(TaskDto dto) {

        return taskRepository.updateTask(dto);
    }

    @Override
    public TaskDto getTaskById(long id) {

        return taskRepository.getTaskById(id);
    }
    @Override
    public boolean deleteTask(Long id) {
        return taskRepository.deleteTask(id);
    }
}
