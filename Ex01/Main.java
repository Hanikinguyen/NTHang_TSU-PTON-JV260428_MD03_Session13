package Ex01;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentManagement studentManagement =
                new StudentManagement();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("      STUDENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Tìm sinh viên theo ID");
            System.out.println("5. Xóa sinh viên");
            System.out.println("6. Thoát");
            System.out.println("==============================");

            int choice = Validator.getInt(
                    scanner,
                    "Mời nhập lựa chọn: "
            );

            switch (choice) {

                case 1:
                    studentManagement.displayListStudent();
                    break;

                case 2:
                    studentManagement.addStudent(scanner);
                    break;

                case 3:
                    studentManagement.updateStudent(scanner);
                    break;

                case 4:
                    studentManagement.displayStudentById(scanner);
                    break;

                case 5:
                    studentManagement.deleteStudent(scanner);
                    break;

                case 6:
                    System.out.println("Good bye!");
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Lựa chọn không hợp lệ!"
                    );
            }
        }
    }
}
