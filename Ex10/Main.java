package Ex10;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        StudentManager studentManager =
                new StudentManager();

        while (true) {

            System.out.println();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "       QUẢN LÝ SINH VIÊN VÀ KHÓA HỌC"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "1. Thêm sinh viên"
            );

            System.out.println(
                    "2. Thêm khóa học"
            );

            System.out.println(
                    "3. Ghi danh sinh viên vào khóa học"
            );

            System.out.println(
                    "4. Hiển thị sinh viên và điểm"
            );

            System.out.println(
                    "5. Cập nhật điểm"
            );

            System.out.println(
                    "6. Thoát"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.print(
                    "Mời nhập lựa chọn: "
            );

            String input =
                    scanner.nextLine();

            try {

                int choice =
                        Integer.parseInt(input);

                switch (choice) {

                    // =====================================
                    // 1. THÊM SINH VIÊN
                    // =====================================

                    case 1:

                        System.out.print(
                                "Nhập tên sinh viên: "
                        );

                        String studentName =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập email: "
                        );

                        String email =
                                scanner.nextLine()
                                        .trim();

                        if (studentName.isEmpty()) {

                            System.out.println(
                                    "Tên sinh viên không được để trống!"
                            );

                            break;
                        }

                        if (email.isEmpty()) {

                            System.out.println(
                                    "Email không được để trống!"
                            );

                            break;
                        }

                        Student student =
                                new Student(
                                        studentName,
                                        email
                                );

                        studentManager.addStudent(
                                student
                        );

                        break;


                    // =====================================
                    // 2. THÊM KHÓA HỌC
                    // =====================================

                    case 2:

                        System.out.print(
                                "Nhập tên khóa học: "
                        );

                        String courseTitle =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập số tín chỉ: "
                        );

                        int credits =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        if (courseTitle.isEmpty()) {

                            System.out.println(
                                    "Tên khóa học không được để trống!"
                            );

                            break;
                        }

                        if (credits <= 0) {

                            System.out.println(
                                    "Số tín chỉ phải lớn hơn 0!"
                            );

                            break;
                        }

                        Course course =
                                new Course(
                                        courseTitle,
                                        credits
                                );

                        studentManager.addCourse(
                                course
                        );

                        break;


                    // =====================================
                    // 3. GHI DANH
                    // =====================================

                    case 3:

                        System.out.print(
                                "Nhập ID sinh viên: "
                        );

                        int studentId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập ID khóa học: "
                        );

                        int courseId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        studentManager.enrollStudent(
                                studentId,
                                courseId
                        );

                        break;


                    // =====================================
                    // 4. HIỂN THỊ
                    // =====================================

                    case 4:

                        studentManager
                                .listStudentsAndGrades();

                        break;


                    // =====================================
                    // 5. CẬP NHẬT ĐIỂM
                    // =====================================

                    case 5:

                        System.out.print(
                                "Nhập ID sinh viên: "
                        );

                        int updateStudentId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập ID khóa học: "
                        );

                        int updateCourseId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập điểm: "
                        );

                        double grade =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (
                                grade < 0
                                        ||
                                        grade > 10
                        ) {

                            System.out.println(
                                    "Điểm phải từ 0 đến 10!"
                            );

                            break;
                        }

                        studentManager
                                .updateStudentGrade(
                                        updateStudentId,
                                        updateCourseId,
                                        grade
                                );

                        break;


                    // =====================================
                    // 6. THOÁT
                    // =====================================

                    case 6:

                        System.out.println(
                                "Good bye!"
                        );

                        scanner.close();

                        return;


                    default:

                        System.out.println(
                                "Vui lòng chọn từ 1 đến 6!"
                        );
                }

            } catch (
                    NumberFormatException e) {

                System.out.println(
                        "Dữ liệu số không hợp lệ!"
                );

            } catch (Exception e) {

                System.out.println(
                        "Đã xảy ra lỗi!"
                );

                System.out.println(
                        "Chi tiết: " + e.getMessage()
                );
            }
        }
    }
}
