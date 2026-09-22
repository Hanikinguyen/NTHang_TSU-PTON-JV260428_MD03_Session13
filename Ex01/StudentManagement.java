package Ex01;

import database.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    // 1. Lấy tất cả sinh viên
    public void displayListStudent() {

        List<Student> students = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             CallableStatement callableStatement =
                     connection.prepareCall("{call get_all_students()}");
             ResultSet resultSet =
                     callableStatement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student();

                student.setStudentId(
                        resultSet.getInt("student_id")
                );

                student.setFullName(
                        resultSet.getString("full_name")
                );

                student.setDateOfBirth(
                        resultSet.getDate("date_of_birth").toLocalDate()
                );

                student.setEmail(
                        resultSet.getString("email")
                );

                students.add(student);
            }

        } catch (Exception e) {
            System.out.println("Lấy danh sách sinh viên thất bại!");
            System.out.println("Lỗi: " + e.getMessage());
        }

        if (students.isEmpty()) {

            System.out.println("Danh sách sinh viên đang trống!");

        } else {

            System.out.println("\n===== DANH SÁCH SINH VIÊN =====");

            for (Student student : students) {
                student.display();
            }
        }
    }


    // 2. Thêm sinh viên
    public void addStudent(Scanner scanner) {

        Student student = new Student();

        student.setFullName(
                Validator.getString(
                        scanner,
                        "Nhập họ tên: "
                )
        );

        student.setDateOfBirth(
                Validator.getLocalDate(
                        scanner,
                        "Nhập ngày sinh (dd/MM/yyyy): "
                )
        );

        student.setEmail(
                Validator.getString(
                        scanner,
                        "Nhập email: "
                )
        );

        try (Connection connection = Database.getConnection();
             CallableStatement callableStatement =
                     connection.prepareCall(
                             "{call add_student(?,?,?)}"
                     )) {

            callableStatement.setString(
                    1,
                    student.getFullName()
            );

            callableStatement.setDate(
                    2,
                    Date.valueOf(student.getDateOfBirth())
            );

            callableStatement.setString(
                    3,
                    student.getEmail()
            );

            int result =
                    callableStatement.executeUpdate();

            if (result > 0) {
                System.out.println(
                        "Thêm sinh viên thành công!"
                );
            } else {
                System.out.println(
                        "Thêm sinh viên thất bại!"
                );
            }

        } catch (Exception e) {
            System.out.println(
                    "Không thể thêm sinh viên!"
            );
            System.out.println(
                    "Lỗi: " + e.getMessage()
            );
        }
    }


    // 3. Cập nhật sinh viên
    public void updateStudent(Scanner scanner) {

        int id = Validator.getInt(
                scanner,
                "Nhập ID sinh viên cần sửa: "
        );

        Student oldStudent = findById(id);

        if (oldStudent == null) {

            System.out.println(
                    "Không tìm thấy sinh viên có ID = " + id
            );

            return;
        }

        System.out.println("\nThông tin hiện tại:");
        oldStudent.display();

        Student student = new Student();

        student.setStudentId(id);

        student.setFullName(
                Validator.getString(
                        scanner,
                        "Nhập họ tên mới: "
                )
        );

        student.setDateOfBirth(
                Validator.getLocalDate(
                        scanner,
                        "Nhập ngày sinh mới (dd/MM/yyyy): "
                )
        );

        student.setEmail(
                Validator.getString(
                        scanner,
                        "Nhập email mới: "
                )
        );

        try (Connection connection = Database.getConnection();
             CallableStatement callableStatement =
                     connection.prepareCall(
                             "{call update_student(?,?,?,?)}"
                     )) {

            callableStatement.setInt(
                    1,
                    student.getStudentId()
            );

            callableStatement.setString(
                    2,
                    student.getFullName()
            );

            callableStatement.setDate(
                    3,
                    Date.valueOf(student.getDateOfBirth())
            );

            callableStatement.setString(
                    4,
                    student.getEmail()
            );

            int result =
                    callableStatement.executeUpdate();

            if (result > 0) {
                System.out.println(
                        "Cập nhật sinh viên thành công!"
                );
            } else {
                System.out.println(
                        "Cập nhật sinh viên thất bại!"
                );
            }

        } catch (Exception e) {
            System.out.println(
                    "Không thể cập nhật sinh viên!"
            );
            System.out.println(
                    "Lỗi: " + e.getMessage()
            );
        }
    }


    // 4. Tìm sinh viên theo ID
    public Student findById(int studentId) {

        try (Connection connection = Database.getConnection();
             CallableStatement callableStatement =
                     connection.prepareCall(
                             "{call find_student_by_id(?)}"
                     )) {

            callableStatement.setInt(
                    1,
                    studentId
            );

            try (ResultSet resultSet =
                         callableStatement.executeQuery()) {

                if (resultSet.next()) {

                    Student student = new Student();

                    student.setStudentId(
                            resultSet.getInt("student_id")
                    );

                    student.setFullName(
                            resultSet.getString("full_name")
                    );

                    student.setDateOfBirth(
                            resultSet.getDate("date_of_birth")
                                    .toLocalDate()
                    );

                    student.setEmail(
                            resultSet.getString("email")
                    );

                    return student;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Tìm sinh viên thất bại!"
            );

            System.out.println(
                    "Lỗi: " + e.getMessage()
            );
        }

        return null;
    }


    // Hiển thị sinh viên theo ID
    public void displayStudentById(Scanner scanner) {

        int studentId = Validator.getInt(
                scanner,
                "Nhập ID sinh viên cần tìm: "
        );

        Student student = findById(studentId);

        if (student == null) {

            System.out.println(
                    "Không tìm thấy sinh viên có ID = "
                            + studentId
            );

        } else {

            System.out.println("\n===== THÔNG TIN SINH VIÊN =====");
            student.display();
        }
    }


    // 5. Xóa sinh viên
    public void deleteStudent(Scanner scanner) {

        int studentId = Validator.getInt(
                scanner,
                "Nhập ID sinh viên cần xóa: "
        );

        Student student = findById(studentId);

        if (student == null) {

            System.out.println(
                    "Không tìm thấy sinh viên có ID = "
                            + studentId
            );

            return;
        }

        System.out.println("\nSinh viên sẽ bị xóa:");
        student.display();

        try (Connection connection = Database.getConnection();
             CallableStatement callableStatement =
                     connection.prepareCall(
                             "{call delete_student(?)}"
                     )) {

            callableStatement.setInt(
                    1,
                    studentId
            );

            int result =
                    callableStatement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Xóa sinh viên thành công!"
                );

            } else {

                System.out.println(
                        "Xóa sinh viên thất bại!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Không thể xóa sinh viên!"
            );

            System.out.println(
                    "Lỗi: " + e.getMessage()
            );
        }
    }
}