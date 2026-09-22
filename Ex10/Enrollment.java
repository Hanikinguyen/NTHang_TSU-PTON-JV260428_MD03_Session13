package Ex10;

public class Enrollment {

    private int studentId;
    private int courseId;
    private Double grade;

    public Enrollment() {
    }

    public Enrollment(
            int studentId,
            int courseId,
            Double grade) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}