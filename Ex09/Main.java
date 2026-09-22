package Ex09;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        Management management =
                new Management();

        while (true) {

            System.out.println();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "      QUẢN LÝ NHÂN VIÊN VÀ DỰ ÁN"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "1. Thêm nhân viên"
            );

            System.out.println(
                    "2. Thêm dự án"
            );

            System.out.println(
                    "3. Gán nhân viên vào dự án"
            );

            System.out.println(
                    "4. Hiển thị nhân viên và dự án"
            );

            System.out.println(
                    "5. Cập nhật lương nhân viên"
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
                    // 1. THÊM NHÂN VIÊN
                    // =====================================

                    case 1:

                        System.out.print(
                                "Nhập tên nhân viên: "
                        );

                        String employeeName =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập phòng ban: "
                        );

                        String department =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập lương: "
                        );

                        double salary =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (employeeName.isEmpty()) {

                            System.out.println(
                                    "Tên nhân viên không được để trống!"
                            );

                            break;
                        }

                        if (department.isEmpty()) {

                            System.out.println(
                                    "Phòng ban không được để trống!"
                            );

                            break;
                        }

                        if (salary < 0) {

                            System.out.println(
                                    "Lương không được âm!"
                            );

                            break;
                        }

                        Employee employee =
                                new Employee(
                                        employeeName,
                                        department,
                                        salary
                                );

                        management.addEmployee(
                                employee
                        );

                        break;


                    // =====================================
                    // 2. THÊM DỰ ÁN
                    // =====================================

                    case 2:

                        System.out.print(
                                "Nhập tên dự án: "
                        );

                        String projectName =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập ngân sách: "
                        );

                        double budget =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (projectName.isEmpty()) {

                            System.out.println(
                                    "Tên dự án không được để trống!"
                            );

                            break;
                        }

                        if (budget < 0) {

                            System.out.println(
                                    "Ngân sách không được âm!"
                            );

                            break;
                        }

                        Project project =
                                new Project(
                                        projectName,
                                        budget
                                );

                        management.addProject(
                                project
                        );

                        break;


                    // =====================================
                    // 3. GÁN NHÂN VIÊN
                    // =====================================

                    case 3:

                        System.out.print(
                                "Nhập ID nhân viên: "
                        );

                        int employeeId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập ID dự án: "
                        );

                        int projectId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập vai trò: "
                        );

                        String role =
                                scanner.nextLine()
                                        .trim();

                        if (role.isEmpty()) {

                            System.out.println(
                                    "Vai trò không được để trống!"
                            );

                            break;
                        }

                        management
                                .assignEmployeeToProject(
                                        employeeId,
                                        projectId,
                                        role
                                );

                        break;


                    // =====================================
                    // 4. HIỂN THỊ
                    // =====================================

                    case 4:

                        management
                                .listEmployeesAndProjects();

                        break;


                    // =====================================
                    // 5. CẬP NHẬT LƯƠNG
                    // =====================================

                    case 5:

                        System.out.print(
                                "Nhập ID nhân viên: "
                        );

                        int updateEmployeeId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập mức lương mới: "
                        );

                        double newSalary =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (newSalary < 0) {

                            System.out.println(
                                    "Lương không được âm!"
                            );

                            break;
                        }

                        management
                                .updateEmployeeSalary(
                                        updateEmployeeId,
                                        newSalary
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
