package Ex09;

public class Assignment {

    private int employeeId;
    private int projectId;
    private String role;

    public Assignment() {
    }

    public Assignment(
            int employeeId,
            int projectId,
            String role) {

        this.employeeId = employeeId;
        this.projectId = projectId;
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
