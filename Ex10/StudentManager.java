package Ex10;

import database_school.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentManager {

    // =====================================================
    // 1. THÊM SINH VIÊN
    // =====================================================

    public void addStudent(Student student) {

        String checkSql =
                "SELECT id FROM Student WHERE email = ?";

        String insertSql =
                "INSERT INTO Student(name, email) " +
                        "VALUES (?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    student.getEmail()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Email sinh viên đã tồn tại!"
                );

                return;
            }

            try (
                    PreparedStatement insertStatement =
                            connection.prepareStatement(
                                    insertSql
                            )
            ) {

                insertStatement.setString(
                        1,
                        student.getName()
                );

                insertStatement.setString(
                        2,
                        student.getEmail()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm sinh viên thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm sinh viên thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm sinh viên!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 2. THÊM KHÓA HỌC
    // =====================================================

    public void addCourse(Course course) {

        String checkSql =
                "SELECT id FROM Course WHERE title = ?";

        String insertSql =
                "INSERT INTO Course(title, credits) " +
                        "VALUES (?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    course.getTitle()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Khóa học đã tồn tại!"
                );

                return;
            }

            try (
                    PreparedStatement insertStatement =
                            connection.prepareStatement(
                                    insertSql
                            )
            ) {

                insertStatement.setString(
                        1,
                        course.getTitle()
                );

                insertStatement.setInt(
                        2,
                        course.getCredits()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm khóa học thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm khóa học thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm khóa học!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 3. GHI DANH SINH VIÊN
    // =====================================================

    public void enrollStudent(
            int studentId,
            int courseId) {

        String studentSql =
                "SELECT id FROM Student WHERE id = ?";

        String courseSql =
                "SELECT id FROM Course WHERE id = ?";

        String enrollmentCheckSql =
                "SELECT student_id " +
                        "FROM Enrollment " +
                        "WHERE student_id = ? " +
                        "AND course_id = ?";

        String insertSql =
                "INSERT INTO Enrollment " +
                        "(student_id, course_id, grade) " +
                        "VALUES (?, ?, NULL)";

        try (
                Connection connection =
                        Database.getConnection()
        ) {

            // =============================================
            // KIỂM TRA SINH VIÊN
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    studentSql
                            )
            ) {

                statement.setInt(
                        1,
                        studentId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Không tìm thấy sinh viên ID: "
                                    + studentId
                    );

                    return;
                }
            }


            // =============================================
            // KIỂM TRA KHÓA HỌC
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    courseSql
                            )
            ) {

                statement.setInt(
                        1,
                        courseId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Không tìm thấy khóa học ID: "
                                    + courseId
                    );

                    return;
                }
            }


            // =============================================
            // KIỂM TRA ĐÃ GHI DANH CHƯA
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    enrollmentCheckSql
                            )
            ) {

                statement.setInt(
                        1,
                        studentId
                );

                statement.setInt(
                        2,
                        courseId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (resultSet.next()) {

                    System.out.println(
                            "Sinh viên đã được ghi danh vào khóa học này!"
                    );

                    return;
                }
            }


            // =============================================
            // INSERT ENROLLMENT
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    insertSql
                            )
            ) {

                statement.setInt(
                        1,
                        studentId
                );

                statement.setInt(
                        2,
                        courseId
                );

                int result =
                        statement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Ghi danh sinh viên thành công!"
                    );

                } else {

                    System.out.println(
                            "Ghi danh thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi ghi danh!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 4. HIỂN THỊ SINH VIÊN VÀ ĐIỂM
    // =====================================================

    public void listStudentsAndGrades() {

        String sql =
                "SELECT " +
                        "s.id AS student_id, " +
                        "s.name AS student_name, " +
                        "s.email, " +
                        "c.id AS course_id, " +
                        "c.title AS course_title, " +
                        "c.credits, " +
                        "e.grade " +
                        "FROM Student s " +
                        "LEFT JOIN Enrollment e " +
                        "ON s.id = e.student_id " +
                        "LEFT JOIN Course c " +
                        "ON e.course_id = c.id " +
                        "ORDER BY s.id";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            boolean found = false;

            System.out.println();

            System.out.println(
                    "================ SINH VIÊN VÀ ĐIỂM ================"
            );

            while (resultSet.next()) {

                found = true;

                int studentId =
                        resultSet.getInt(
                                "student_id"
                        );

                String studentName =
                        resultSet.getString(
                                "student_name"
                        );

                String email =
                        resultSet.getString(
                                "email"
                        );

                String courseTitle =
                        resultSet.getString(
                                "course_title"
                        );

                Object grade =
                        resultSet.getObject(
                                "grade"
                        );

                System.out.println();

                System.out.println(
                        "Sinh viên ID: "
                                + studentId
                );

                System.out.println(
                        "Tên: "
                                + studentName
                );

                System.out.println(
                        "Email: "
                                + email
                );

                if (courseTitle == null) {

                    System.out.println(
                            "Khóa học: Chưa ghi danh"
                    );

                } else {

                    System.out.println(
                            "Khóa học: "
                                    + courseTitle
                    );

                    if (grade == null) {

                        System.out.println(
                                "Điểm: Chưa có điểm"
                        );

                    } else {

                        System.out.printf(
                                "Điểm: %.2f%n",
                                ((Number) grade)
                                        .doubleValue()
                        );
                    }
                }

                System.out.println(
                        "--------------------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "Chưa có sinh viên nào!"
                );
            }

            System.out.println(
                    "==================================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi hiển thị sinh viên và điểm!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 5. CẬP NHẬT ĐIỂM
    // =====================================================

    public void updateStudentGrade(
            int studentId,
            int courseId,
            double grade) {

        String sql =
                "UPDATE Enrollment " +
                        "SET grade = ? " +
                        "WHERE student_id = ? " +
                        "AND course_id = ?";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(
                    1,
                    grade
            );

            statement.setInt(
                    2,
                    studentId
            );

            statement.setInt(
                    3,
                    courseId
            );

            int result =
                    statement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Cập nhật điểm thành công!"
                );

            } else {

                System.out.println(
                        "Không tìm thấy sinh viên trong khóa học này!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi cập nhật điểm!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }
}
