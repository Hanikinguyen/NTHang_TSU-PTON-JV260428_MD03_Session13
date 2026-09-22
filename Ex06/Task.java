package Ex06;

public class Task {

    private int id;
    private String taskName;
    private String status;

    public Task() {
    }

    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void display() {

        String statusText;

        if (status.equalsIgnoreCase("COMPLETED")) {
            statusText = "Đã hoàn thành";
        } else {
            statusText = "Chưa hoàn thành";
        }

        System.out.printf(
                "| ID: %3d | Task: %-35s | Status: %-20s |%n",
                id,
                taskName,
                statusText
        );
    }
}
