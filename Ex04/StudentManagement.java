package Ex04;

import Database.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    // ==================================================
    // 1. HIỂN THỊ DANH SÁCH SINH VIÊN
    // ==================================================
    public void displayListStudent() {

        List<Student> students = new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call get_all_students()}"
                        );

                ResultSet resultSet =
                        callableStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student =
                        new Student();

                student.setStudentId(
                        resultSet.getInt(
                                "student_id"
                        )
                );

                student.setFullName(
                        resultSet.getString(
                                "full_name"
                        )
                );

                student.setDateOfBirth(
                        resultSet
                                .getDate("date_of_birth")
                                .toLocalDate()
                );

                student.setEmail(
                        resultSet.getString(
                                "email"
                        )
                );

                students.add(student);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi lấy danh sách sinh viên!"
            );

            System.out.println(
                    "Chi tiết: "
                            + e.getMessage()
            );

            return;
        }

        if (students.isEmpty()) {

            System.out.println(
                    "Danh sách sinh viên đang trống!"
            );

        } else {

            System.out.println();
            System.out.println(
                    "========== DANH SÁCH SINH VIÊN =========="
            );

            for (Student student : students) {

                student.display();
            }

            System.out.println(
                    "=========================================="
            );
        }
    }


    // ==================================================
    // 2. THÊM MỚI SINH VIÊN
    // ==================================================
    public void addStudent(
            Scanner scanner) {

        Student student =
                new Student();

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


        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call add_student(?,?,?)}"
                        )
        ) {

            callableStatement.setString(
                    1,
                    student.getFullName()
            );

            callableStatement.setDate(
                    2,
                    Date.valueOf(
                            student.getDateOfBirth()
                    )
            );

            callableStatement.setString(
                    3,
                    student.getEmail()
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Thêm sinh viên thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể thêm sinh viên!"
            );

            System.out.println(
                    "Chi tiết: "
                            + e.getMessage()
            );
        }
    }


    // ==================================================
    // 3. SỬA SINH VIÊN
    // ==================================================
    public void updateStudent(
            Scanner scanner) {

        int studentId =
                Validator.getInt(
                        scanner,
                        "Nhập ID sinh viên cần sửa: "
                );

        String fullName =
                Validator.getString(
                        scanner,
                        "Nhập họ tên mới: "
                );

        java.time.LocalDate dateOfBirth =
                Validator.getLocalDate(
                        scanner,
                        "Nhập ngày sinh mới (dd/MM/yyyy): "
                );

        String email =
                Validator.getString(
                        scanner,
                        "Nhập email mới: "
                );


        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call update_student(?,?,?,?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    studentId
            );

            callableStatement.setString(
                    2,
                    fullName
            );

            callableStatement.setDate(
                    3,
                    Date.valueOf(dateOfBirth)
            );

            callableStatement.setString(
                    4,
                    email
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Cập nhật sinh viên thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể cập nhật sinh viên!"
            );

            System.out.println(
                    "Chi tiết: "
                            + e.getMessage()
            );
        }
    }


    // ==================================================
    // 4. XÓA SINH VIÊN
    // ==================================================
    public void deleteStudent(
            Scanner scanner) {

        int studentId =
                Validator.getInt(
                        scanner,
                        "Nhập ID sinh viên cần xóa: "
                );


        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call delete_student(?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    studentId
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Xóa sinh viên thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể xóa sinh viên!"
            );

            System.out.println(
                    "Chi tiết: "
                            + e.getMessage()
            );
        }
    }


    // ==================================================
    // 5. TÌM KIẾM SINH VIÊN
    // ==================================================
    public void searchStudentByName(
            Scanner scanner) {

        String name =
                Validator.getString(
                        scanner,
                        "Nhập tên sinh viên cần tìm: "
                );

        List<Student> students =
                new ArrayList<>();


        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call search_student(?)}"
                        )
        ) {

            // Truyền tên cần tìm vào Stored Procedure
            callableStatement.setString(
                    1,
                    name
            );

            ResultSet resultSet =
                    callableStatement.executeQuery();


            while (resultSet.next()) {

                Student student =
                        new Student();

                student.setStudentId(
                        resultSet.getInt(
                                "student_id"
                        )
                );

                student.setFullName(
                        resultSet.getString(
                                "full_name"
                        )
                );

                student.setDateOfBirth(
                        resultSet
                                .getDate(
                                        "date_of_birth"
                                )
                                .toLocalDate()
                );

                student.setEmail(
                        resultSet.getString(
                                "email"
                        )
                );

                students.add(student);
            }


        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi tìm kiếm sinh viên!"
            );

            System.out.println(
                    "Chi tiết: "
                            + e.getMessage()
            );

            return;
        }


        if (students.isEmpty()) {

            System.out.println(
                    "Không tìm thấy sinh viên phù hợp."
            );

        } else {

            System.out.println();
            System.out.println(
                    "========== KẾT QUẢ TÌM KIẾM =========="
            );

            for (Student student : students) {

                student.display();
            }

            System.out.println(
                    "======================================="
            );
        }
    }
}
