package Ex05;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        MovieManagement movieManagement =
                new MovieManagement();

        while (true) {

            System.out.println();

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "       MOVIE MANAGEMENT"
            );

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "1. Thêm phim"
            );

            System.out.println(
                    "2. Liệt kê phim"
            );

            System.out.println(
                    "3. Sửa phim"
            );

            System.out.println(
                    "4. Xóa phim"
            );

            System.out.println(
                    "5. Thoát"
            );

            System.out.println(
                    "================================"
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
                    // 1. THÊM PHIM
                    // =================================
                    case 1:

                        String title =
                                Validator.getString(
                                        scanner,
                                        "Nhập tên phim: "
                                );

                        String director =
                                Validator.getString(
                                        scanner,
                                        "Nhập đạo diễn: "
                                );

                        int year =
                                Validator.getInt(
                                        scanner,
                                        "Nhập năm phát hành: "
                                );

                        movieManagement.addMovie(
                                title,
                                director,
                                year
                        );

                        break;


                    // =================================
                    // 2. LIỆT KÊ PHIM
                    // =================================
                    case 2:

                        movieManagement.listMovies();

                        break;


                    // =================================
                    // 3. SỬA PHIM
                    // =================================
                    case 3:

                        int updateId =
                                Validator.getInt(
                                        scanner,
                                        "Nhập ID phim cần sửa: "
                                );

                        String newTitle =
                                Validator.getString(
                                        scanner,
                                        "Nhập tên phim mới: "
                                );

                        String newDirector =
                                Validator.getString(
                                        scanner,
                                        "Nhập đạo diễn mới: "
                                );

                        int newYear =
                                Validator.getInt(
                                        scanner,
                                        "Nhập năm phát hành mới: "
                                );

                        movieManagement.updateMovie(
                                updateId,
                                newTitle,
                                newDirector,
                                newYear
                        );

                        break;


                    // =================================
                    // 4. XÓA PHIM
                    // =================================
                    case 4:

                        int deleteId =
                                Validator.getInt(
                                        scanner,
                                        "Nhập ID phim cần xóa: "
                                );

                        movieManagement.deleteMovie(
                                deleteId
                        );

                        break;


                    // =================================
                    // 5. THOÁT
                    // =================================
                    case 5:

                        System.out.println(
                                "Good bye!"
                        );

                        scanner.close();

                        return;


                    default:

                        System.out.println(
                                "Vui lòng chọn từ 1 đến 5!"
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số từ 1 đến 5!"
                );
            }
        }
    }
}
