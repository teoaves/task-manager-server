package org.task.taskserver.response;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.request.GenericRequest;

import java.util.Date;

public class TaskResponse extends GenericRequest {

    private String taskRef;
    private String taskLot;
    private String taskManufacturer;
    private Date taskPurchaseDate;
    private String taskNotes;

    public String getTaskRef() {
        return taskRef;
    }

    public void setTaskRef(String taskRef) {
        this.taskRef = taskRef;
    }

    public String getTaskLot() {
        return taskLot;
    }

    public void setTaskLot(String taskLot) {
        this.taskLot = taskLot;
    }

    public String getTaskManufacturer() {
        return taskManufacturer;
    }

    public void setTaskManufacturer(String taskManufacturer) {
        this.taskManufacturer = taskManufacturer;
    }

    public Date getTaskPurchaseDate() {
        return taskPurchaseDate;
    }

    public void setTaskPurchaseDate(Date taskPurchaseDate) {
        this.taskPurchaseDate = taskPurchaseDate;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    public TaskResponse(TaskDto taskDto) {
        this.setId(taskDto.getId());
        this.setName(taskDto.getName());
        this.setDescription(taskDto.getDescription());
        this.setTaskRef(taskDto.getTaskRef());
        this.setTaskLot(taskDto.getTaskLot());
        this.setTaskManufacturer(taskDto.getTaskManufacturer());
        this.setTaskPurchaseDate(taskDto.getTaskPurchaseDate());
        this.setTaskNotes(taskDto.getTaskNotes());
    }
}
