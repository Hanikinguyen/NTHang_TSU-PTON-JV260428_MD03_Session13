package Ex02;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentManagement studentManagement =
                new StudentManagement();

        while (true) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("     STUDENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Thoát");
            System.out.println("==============================");

            System.out.print("Mời nhập lựa chọn: ");

            String input = scanner.nextLine();

            try {

                int choice = Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        studentManagement.displayListStudent();
                        break;

                    case 2:
                        studentManagement.addStudent(scanner);
                        break;

                    case 3:
                        System.out.println(
                                "Chức năng sửa chưa thực hiện."
                        );
                        break;

                    case 4:
                        System.out.println(
                                "Chức năng xóa chưa thực hiện."
                        );
                        break;

                    case 5:
                        System.out.println(
                                "Chức năng tìm kiếm chưa thực hiện."
                        );
                        break;

                    case 6:
                        System.out.println("Good bye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Vui lòng chọn từ 1 đến 6!"
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số từ 1 đến 6!"
                );
            }
        }
    }
}
