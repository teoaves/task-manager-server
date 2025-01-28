package org.task.taskserver.request;

import java.util.Collection;

public class TaskSeriesRequest {

    private String taskSeriesCode;

    private Collection<String> connectedTasksIds;

    private Collection<String> unconnectedTasksIds;

    public String getTaskSeriesCode() {
        return taskSeriesCode;
    }

    public void setTaskSeriesCode(String taskSeriesCode) {
        this.taskSeriesCode = taskSeriesCode;
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

    public void setUnconnectedtasksIds(Collection<String> unconnectedTasksIds) {
        this.unconnectedTasksIds = unconnectedTasksIds;
    }
}
