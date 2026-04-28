package model;

import java.time.LocalDateTime;



public class Task {
    private int id;
    private String name;
    private String type;
    private String importance;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String description;
    private TaskStatus status;

    public Task(int id, String name, String type, String importance,
                LocalDateTime startTime, LocalDateTime finishTime, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.importance = importance;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
