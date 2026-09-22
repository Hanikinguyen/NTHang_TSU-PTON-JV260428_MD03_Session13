package Ex04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {

    // Nhập chuỗi không được để trống
    public static String getString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

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


    // Nhập ngày tháng
    public static LocalDate getLocalDate(
            Scanner scanner,
            String message) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {

            String input =
                    getString(scanner, message);

            try {

                return LocalDate.parse(
                        input,
                        formatter
                );

            } catch (Exception e) {

                System.out.println(
                        "Sai định dạng ngày! "
                                + "Vui lòng nhập dd/MM/yyyy."
                );
            }
        }
    }
}
