package Ex06;

import java.util.Scanner;

public class Validator {

    // ==========================================
    // Nhập chuỗi không được để trống
    // ==========================================
    public static String getString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    "Không được để trống! Vui lòng nhập lại."
            );
        }
    }


    // ==========================================
    // Nhập số nguyên
    // ==========================================
    public static int getInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số nguyên!"
                );
            }
        }
    }


    // ==========================================
    // Nhập trạng thái
    // ==========================================
    public static String getStatus(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine()
                            .trim()
                            .toUpperCase();

            if (
                    input.equals("PENDING")
                            ||
                            input.equals("COMPLETED")
            ) {

                return input;
            }

            System.out.println(
                    "Trạng thái chỉ được nhập "
                            + "PENDING hoặc COMPLETED!"
            );
        }
    }
}