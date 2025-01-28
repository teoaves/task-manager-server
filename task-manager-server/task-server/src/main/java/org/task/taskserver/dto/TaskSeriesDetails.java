package org.task.taskserver.dto;

public class TaskSeriesDetails {

    String taskName;
    String taskDescription;
    String taskLot;
    Long tasksCount;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskLot() {
        return taskLot;
    }

    public void setTaskLot(String taskLot) {
        this.taskLot = taskLot;
    }

    public Long getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Long tasksCount) {
        this.tasksCount = tasksCount;
    }
}
