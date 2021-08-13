package br.ufes;

import java.time.LocalDate;

/**
 *
 * @author ph
 */
public class Task {
    private String task;
    private String status;
    private LocalDate data;

    public Task(String task, String status, LocalDate data) {
        this.task = task;
        this.status = status;
        this.data = data;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    
}
