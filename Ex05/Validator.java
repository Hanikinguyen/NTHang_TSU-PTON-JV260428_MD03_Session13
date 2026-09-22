package Ex05;

import java.util.Scanner;

public class Validator {

    // Nhập chuỗi không được để trống
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


    // Nhập số nguyên
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
}