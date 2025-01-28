package org.task.taskserver.response;

import org.task.taskserver.dto.TaskSeriesDetails;
import org.task.taskserver.dto.TaskSeriesDto;
import org.task.taskserver.request.GenericRequest;
import java.util.Collection;
import java.util.List;

public class TaskSeriesResponse extends GenericRequest {

    private Long taskSeriesId;

    private Long tasksCount;

    private String taskSeriesCode;

    private String taskLot;

    private List<TaskSeriesDetails> taskSeriesDetails;

    private Collection<String> connectedTasksIds;

    public TaskSeriesResponse() {
    }

    public TaskSeriesResponse(Long id, String taskSeriesCode, List<TaskSeriesDetails> taskSeriesDetails) {
        this.id = id;
        this.taskSeriesCode = taskSeriesCode;
        this.taskSeriesDetails = taskSeriesDetails;
    }

    public Long getTaskSeriesId() {
        return taskSeriesId;
    }

    public void setTaskSeriesId(Long taskSeriesId) {
        this.taskSeriesId = taskSeriesId;
    }

    public Long getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Long tasksCount) {
        this.tasksCount = tasksCount;
    }

    public String getTaskSeriesCode() {
        return taskSeriesCode;
    }

    public void setTaskSeriesCode(String taskSeriesCode) {
        this.taskSeriesCode = taskSeriesCode;
    }

    public String getTaskLot() {
        return taskLot;
    }

    public void setTaskLot(String taskLot) {
        this.taskLot = taskLot;
    }

    public List<TaskSeriesDetails> getTaskSeriesDetails() {
        return taskSeriesDetails;
    }

    public void setTaskSeriesDetails(List<TaskSeriesDetails> taskSeriesDetails) {
        this.taskSeriesDetails = taskSeriesDetails;
    }

    public Collection<String> getConnectedTasksIds() {
        return connectedTasksIds;
    }

    public void setConnectedTasksIds(Collection<String> connectedTasksIds) {
        this.connectedTasksIds = connectedTasksIds;
    }

    public TaskSeriesResponse(TaskSeriesDto taskSeriesDto) {
        this.setTaskSeriesCode(taskSeriesDto.getTaskSeriesCode());
        this.setConnectedTasksIds(taskSeriesDto.getConnectedTasksIds());
        this.setName(taskSeriesDto.getName());
        this.setDescription(taskSeriesDto.getDescription());
        this.setTaskLot(taskSeriesDto.getTaskLot());
        this.setTasksCount(taskSeriesDto.getTasksCount());
    }
}
