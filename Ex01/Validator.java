package Ex01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {

    public static String getString(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Không được để trống!");
        }
    }

    public static int getInt(Scanner scanner, String message) {

        while (true) {

            String input = getString(scanner, message);

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên!");
            }
        }
    }

    public static LocalDate getLocalDate(
            Scanner scanner,
            String message) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {

            String input = getString(scanner, message);

            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
                System.out.println(
                        "Ngày không hợp lệ! Hãy nhập theo dạng dd/MM/yyyy."
                );
            }
        }
    }
}
