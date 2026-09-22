package Ex07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        BookManager bookManager =
                new BookManager();

        while (true) {

            System.out.println();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "       QUẢN LÝ SÁCH TRONG THƯ VIỆN"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "1. Thêm sách"
            );

            System.out.println(
                    "2. Cập nhật thông tin sách"
            );

            System.out.println(
                    "3. Xóa sách"
            );

            System.out.println(
                    "4. Tìm kiếm sách theo tác giả"
            );

            System.out.println(
                    "5. Hiển thị tất cả sách"
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

                    // =================================
                    // 1. THÊM SÁCH
                    // =================================

                    case 1:

                        System.out.print(
                                "Nhập tên sách: "
                        );

                        String title =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập tác giả: "
                        );

                        String author =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập năm xuất bản: "
                        );

                        int year =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập giá sách: "
                        );

                        double price =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (
                                title.isEmpty()
                                        ||
                                        author.isEmpty()
                        ) {

                            System.out.println(
                                    "Tên sách và tác giả không được để trống!"
                            );

                            break;
                        }

                        if (year <= 0) {

                            System.out.println(
                                    "Năm xuất bản phải lớn hơn 0!"
                            );

                            break;
                        }

                        if (price < 0) {

                            System.out.println(
                                    "Giá sách không được âm!"
                            );

                            break;
                        }

                        Book newBook =
                                new Book(
                                        title,
                                        author,
                                        year,
                                        price
                                );

                        bookManager.addBook(
                                newBook
                        );

                        break;


                    // =================================
                    // 2. CẬP NHẬT
                    // =================================

                    case 2:

                        System.out.print(
                                "Nhập ID sách cần cập nhật: "
                        );

                        int updateId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập tên sách mới: "
                        );

                        String newTitle =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập tác giả mới: "
                        );

                        String newAuthor =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập năm xuất bản mới: "
                        );

                        int newYear =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập giá mới: "
                        );

                        double newPrice =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (
                                newTitle.isEmpty()
                                        ||
                                        newAuthor.isEmpty()
                        ) {

                            System.out.println(
                                    "Tên sách và tác giả không được để trống!"
                            );

                            break;
                        }

                        if (newYear <= 0) {

                            System.out.println(
                                    "Năm xuất bản phải lớn hơn 0!"
                            );

                            break;
                        }

                        if (newPrice < 0) {

                            System.out.println(
                                    "Giá sách không được âm!"
                            );

                            break;
                        }

                        Book updateBook =
                                new Book(
                                        newTitle,
                                        newAuthor,
                                        newYear,
                                        newPrice
                                );

                        bookManager.updateBook(
                                updateId,
                                updateBook
                        );

                        break;


                    // =================================
                    // 3. XÓA
                    // =================================

                    case 3:

                        System.out.print(
                                "Nhập ID sách cần xóa: "
                        );

                        int deleteId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        bookManager.deleteBook(
                                deleteId
                        );

                        break;


                    // =================================
                    // 4. TÌM KIẾM
                    // =================================

                    case 4:

                        System.out.print(
                                "Nhập tên tác giả cần tìm: "
                        );

                        String searchAuthor =
                                scanner.nextLine()
                                        .trim();

                        if (
                                searchAuthor.isEmpty()
                        ) {

                            System.out.println(
                                    "Tên tác giả không được để trống!"
                            );

                            break;
                        }

                        bookManager
                                .findBooksByAuthor(
                                        searchAuthor
                                );

                        break;


                    // =================================
                    // 5. HIỂN THỊ
                    // =================================

                    case 5:

                        bookManager.listAllBooks();

                        break;


                    // =================================
                    // 6. THOÁT
                    // =================================

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

                System.out.println(
                        "Vui lòng nhập đúng định dạng."
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
