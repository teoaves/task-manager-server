package org.task.taskserver.dto;


import org.task.taskserver.request.GenericRequest;
import org.task.taskserver.request.TaskRequest;

import java.util.Collection;
import java.util.Date;

public class TaskDto extends GenericRequest {

    private Long taskSeriesId;

    private String taskRef;

    private String taskLot;

    private String taskManufacturer;

    private Date taskPurchaseDate;

    private String taskNotes;

    private String taskSeriesCode;

    private Collection<String> taskSeriesCodesList;

    private Date purchaseDateFrom;

    private Date purchaseDateTo;

    private String taskSeriesQrCode;

    private Long tasksCount;


    public TaskDto() {
    }

    public TaskDto(String name, Date purchaseDateFrom, Date purchaseDateTo, Collection<String> taskSeriesCodesList) {
        this.name = name;
        this.purchaseDateFrom = purchaseDateFrom;
        this.purchaseDateTo = purchaseDateTo;
        this.taskSeriesCodesList = taskSeriesCodesList;
    }

    public TaskDto(TaskRequest request, Long id, boolean isUpdate) {
        this.setName(request.getName());
        this.setDescription(request.getDescription());
        this.setTaskRef(request.getTaskRef());
        this.setTaskLot(request.getTaskLot());
        this.setTaskManufacturer(request.getTaskManufacturer());
        this.setTaskPurchaseDate(request.getTaskPurchaseDate());
        this.setTaskNotes(request.getTaskNotes());
        if (isUpdate) {
            this.setId(id);
        }
    }

    public Long getTaskSeriesId() {
        return taskSeriesId;
    }

    public void setTaskSeriesId(Long taskSeriesId) {
        this.taskSeriesId = taskSeriesId;
    }

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

    public String getTaskSeriesCode() {
        return taskSeriesCode;
    }

    public void setTaskSeriesCode(String taskSeriesCode) {
        this.taskSeriesCode = taskSeriesCode;
    }

    public Collection<String> getTaskSeriesCodesList() {
        return taskSeriesCodesList;
    }

    public void setTaskSeriesCodesList(Collection<String> taskSeriesCodesList) {
        this.taskSeriesCodesList = taskSeriesCodesList;
    }

    public Date getPurchaseDateFrom() {
        return purchaseDateFrom;
    }

    public void setPurchaseDateFrom(Date purchaseDateFrom) {
        this.purchaseDateFrom = purchaseDateFrom;
    }

    public Date getPurchaseDateTo() {
        return purchaseDateTo;
    }

    public void setPurchaseDateTo(Date purchaseDateTo) {
        this.purchaseDateTo = purchaseDateTo;
    }

    public String getTaskSeriesQrCode() {
        return taskSeriesQrCode;
    }

    public void setTaskSeriesQrCode(String taskSeriesQrCode) {
        this.taskSeriesQrCode = taskSeriesQrCode;
    }

    public Long getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Long tasksCount) {
        this.tasksCount = tasksCount;
    }
}
