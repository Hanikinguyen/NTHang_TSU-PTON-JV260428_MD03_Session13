package Ex06;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        TaskManagement taskManagement =
                new TaskManagement();

        while (true) {

            System.out.println();

            System.out.println(
                    "===================================="
            );

            System.out.println(
                    "        TO-DO LIST MANAGEMENT"
            );

            System.out.println(
                    "===================================="
            );

            System.out.println(
                    "1. Thêm công việc"
            );

            System.out.println(
                    "2. Liệt kê công việc"
            );

            System.out.println(
                    "3. Cập nhật trạng thái công việc"
            );

            System.out.println(
                    "4. Xóa công việc"
            );

            System.out.println(
                    "5. Tìm kiếm công việc"
            );

            System.out.println(
                    "6. Thống kê công việc"
            );

            System.out.println(
                    "7. Thoát"
            );

            System.out.println(
                    "===================================="
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

                    // =================================
                    // 1. THÊM CÔNG VIỆC
                    // =================================
                    case 1:

                        String taskName =
                                Validator.getString(
                                        scanner,
                                        "Nhập tên công việc: "
                                );

                        String status =
                                Validator.getStatus(
                                        scanner,
                                        "Nhập trạng thái "
                                                + "(PENDING/COMPLETED): "
                                );

                        taskManagement.addTask(
                                taskName,
                                status
                        );

                        break;


                    // =================================
                    // 2. LIỆT KÊ
                    // =================================
                    case 2:

                        taskManagement.listTasks();

                        break;


                    // =================================
                    // 3. CẬP NHẬT TRẠNG THÁI
                    // =================================
                    case 3:

                        int taskId =
                                Validator.getInt(
                                        scanner,
                                        "Nhập ID công việc: "
                                );

                        String newStatus =
                                Validator.getStatus(
                                        scanner,
                                        "Nhập trạng thái mới "
                                                + "(PENDING/COMPLETED): "
                                );

                        taskManagement
                                .updateTaskStatus(
                                        taskId,
                                        newStatus
                                );

                        break;


                    // =================================
                    // 4. XÓA
                    // =================================
                    case 4:

                        int deleteId =
                                Validator.getInt(
                                        scanner,
                                        "Nhập ID công việc cần xóa: "
                                );

                        taskManagement.deleteTask(
                                deleteId
                        );

                        break;


                    // =================================
                    // 5. TÌM KIẾM
                    // =================================
                    case 5:

                        String searchName =
                                Validator.getString(
                                        scanner,
                                        "Nhập tên công việc cần tìm: "
                                );

                        taskManagement
                                .searchTaskByName(
                                        searchName
                                );

                        break;


                    // =================================
                    // 6. THỐNG KÊ
                    // =================================
                    case 6:

                        taskManagement.taskStatistics();

                        break;


                    // =================================
                    // 7. THOÁT
                    // =================================
                    case 7:

                        System.out.println(
                                "Good bye!"
                        );

                        scanner.close();

                        return;


                    default:

                        System.out.println(
                                "Vui lòng chọn từ 1 đến 7!"
                        );
                }

            } catch (
                    NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số từ 1 đến 7!"
                );
            }
        }
    }
}