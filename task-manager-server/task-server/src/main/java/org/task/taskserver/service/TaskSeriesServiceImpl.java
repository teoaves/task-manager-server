package org.task.taskserver.service;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.dto.TaskSeriesDetails;
import org.task.taskserver.dto.TaskSeriesDto;
import org.task.taskserver.repository.TaskSeriesRepository;
import org.task.taskserver.response.TaskSeriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class TaskSeriesServiceImpl implements TaskSeriesService {


    @Autowired
    private TaskSeriesRepository taskSeriesRepository;

    @Override
    public List<TaskDto> fetchTasksSeriesCodes() {

        return taskSeriesRepository.fetchTasksSeriesCodes();
    }

    @Override
    public List<TaskDto> fetchAvailableTasks() {
        return taskSeriesRepository.fetchAvailableTasks();
    }

    @Override
    public long createTaskSeries(TaskSeriesDto dto) {
        return taskSeriesRepository.createTaskSeries(dto);
    }

    @Override
    public boolean updateTaskSeries(TaskSeriesDto dto) {
        return taskSeriesRepository.updateTaskSeries(dto);
    }

    @Override
    public List<TaskSeriesDto> fetchTasksByTaskSeriesCode(String qrCode) {
        return taskSeriesRepository.fetchTasksByTaskSeriesCode(qrCode);
    }

    @Override
    public List<TaskSeriesResponse> getTaskSeriesList() {
        List<TaskSeriesResponse> taskSeriesResponseList = new ArrayList<>();
        List<TaskDto> taskDtos = this.fetchTasksSeriesCodes();
        List<TaskSeriesDto> taskSeriesDtos = taskSeriesRepository.getTaskSeriesList();

        // loop list with taskCodes
        for (TaskDto tempTaskDto : taskDtos) {
             Long id = tempTaskDto.getId();
             String taskSeriesCode = tempTaskDto.getTaskSeriesCode(); // get task code
             List<TaskSeriesDetails> taskSeriesDetailsList = new ArrayList<>(); // create task details list
            for (TaskSeriesDto tempTaskSeriesDto : taskSeriesDtos) {
                if (!tempTaskSeriesDto.getTaskSeriesCode().equals(taskSeriesCode)) {
                    continue;
                } else {
                    TaskSeriesDetails taskSeriesDetails = new TaskSeriesDetails();
                    taskSeriesDetails.setTaskName(tempTaskSeriesDto.getName());
                    taskSeriesDetails.setTaskDescription(tempTaskSeriesDto.getDescription());
                    taskSeriesDetails.setTaskLot(tempTaskSeriesDto.getTaskLot());
                    taskSeriesDetails.setTasksCount(tempTaskSeriesDto.getTasksCount());
                    taskSeriesDetailsList.add(taskSeriesDetails);
                }
            }
            TaskSeriesResponse taskSeriesResponse = new TaskSeriesResponse(id,taskSeriesCode, taskSeriesDetailsList);
            taskSeriesResponseList.add(taskSeriesResponse);
        }
        return taskSeriesResponseList;
    }

    @Override
    public List<TaskDto> getTaskSeriesById(long id) {
        return taskSeriesRepository.getTaskSeriesById(id);
    }

    @Override
    public boolean deleteTaskSeries(Long id) {
        return taskSeriesRepository.deleteTaskSeries(id);
    }
}
