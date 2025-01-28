package org.task.taskserver.dto;

import org.task.taskserver.request.GenericRequest;
import org.task.taskserver.request.TaskSeriesRequest;

import java.util.Collection;

public class TaskSeriesDto extends GenericRequest {

    private String TaskSeriesCode;

    private String TaskLot;

    private Long TasksCount;

    private Collection<String> connectedTasksIds;

    private Collection<String> unconnectedTasksIds;

    public TaskSeriesDto() {
    }

    public String getTaskSeriesCode() {
        return TaskSeriesCode;
    }

    public void setTaskSeriesCode(String taskSeriesCode) {
        TaskSeriesCode = taskSeriesCode;
    }

    public String getTaskLot() {
        return TaskLot;
    }

    public void setTaskLot(String taskLot) {
        TaskLot = taskLot;
    }

    public Long getTasksCount() {
        return TasksCount;
    }

    public void setTasksCount(Long tasksCount) {
        TasksCount = tasksCount;
    }

    public Collection<String> getConnectedTasksIds() {
        return connectedTasksIds;
    }

    public void setConnectedTasksIds(Collection<String> connectedTasksIds) {
        this.connectedTasksIds = connectedTasksIds;
    }

    public Collection<String> getUnconnectedTasksIds() {
        return unconnectedTasksIds;
    }

    public void setUnconnectedTasksIds(Collection<String> unconnectedTasksIds) {
        this.unconnectedTasksIds = unconnectedTasksIds;
    }

    public TaskSeriesDto(TaskSeriesRequest request, Long id, boolean isUpdate) {
        this.setTaskSeriesCode(request.getTaskSeriesCode());
        this.setConnectedTasksIds(request.getConnectedTasksIds());
        if (isUpdate) {
            this.setId(id);
            this.setUnconnectedTasksIds(request.getUnconnectedTasksIds());
        }
    }
}
