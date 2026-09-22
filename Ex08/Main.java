package Ex08;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        OrderManager orderManager =
                new OrderManager();

        while (true) {

            System.out.println();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "       QUẢN LÝ ĐƠN HÀNG CỬA HÀNG"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "1. Thêm sản phẩm"
            );

            System.out.println(
                    "2. Cập nhật khách hàng"
            );

            System.out.println(
                    "3. Tạo đơn hàng mới"
            );

            System.out.println(
                    "4. Hiển thị tất cả đơn hàng"
            );

            System.out.println(
                    "5. Tìm đơn hàng theo khách hàng"
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
                    // 1. THÊM SẢN PHẨM
                    // =====================================

                    case 1:

                        System.out.print(
                                "Nhập tên sản phẩm: "
                        );

                        String productName =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập giá sản phẩm: "
                        );

                        double productPrice =
                                Double.parseDouble(
                                        scanner.nextLine()
                                );

                        if (productName.isEmpty()) {

                            System.out.println(
                                    "Tên sản phẩm không được để trống!"
                            );

                            break;
                        }

                        if (productPrice < 0) {

                            System.out.println(
                                    "Giá sản phẩm không được âm!"
                            );

                            break;
                        }

                        Product product =
                                new Product(
                                        productName,
                                        productPrice
                                );

                        orderManager.addProduct(
                                product
                        );

                        break;


                    // =====================================
                    // 2. CẬP NHẬT KHÁCH HÀNG
                    // =====================================

                    case 2:

                        System.out.print(
                                "Nhập ID khách hàng: "
                        );

                        int customerId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập tên khách hàng mới: "
                        );

                        String customerName =
                                scanner.nextLine()
                                        .trim();

                        System.out.print(
                                "Nhập email mới: "
                        );

                        String customerEmail =
                                scanner.nextLine()
                                        .trim();

                        if (customerName.isEmpty()) {

                            System.out.println(
                                    "Tên khách hàng không được để trống!"
                            );

                            break;
                        }

                        Customer customer =
                                new Customer(
                                        customerName,
                                        customerEmail
                                );

                        orderManager.updateCustomer(
                                customerId,
                                customer
                        );

                        break;


                    // =====================================
                    // 3. TẠO ĐƠN HÀNG
                    // =====================================

                    case 3:

                        System.out.print(
                                "Nhập ID khách hàng: "
                        );

                        int orderCustomerId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập ngày đặt hàng "
                                        + "(yyyy-MM-dd): "
                        );

                        Date orderDate =
                                Date.valueOf(
                                        scanner.nextLine()
                                );

                        System.out.print(
                                "Nhập số loại sản phẩm: "
                        );

                        int numberOfProducts =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        if (numberOfProducts <= 0) {

                            System.out.println(
                                    "Số loại sản phẩm phải lớn hơn 0!"
                            );

                            break;
                        }

                        List<OrderDetail> details =
                                new ArrayList<>();

                        for (
                                int i = 0;
                                i < numberOfProducts;
                                i++
                        ) {

                            System.out.println();

                            System.out.println(
                                    "----- Sản phẩm thứ "
                                            + (i + 1)
                                            + " -----"
                            );

                            System.out.print(
                                    "Nhập ID sản phẩm: "
                            );

                            int productId =
                                    Integer.parseInt(
                                            scanner.nextLine()
                                    );

                            System.out.print(
                                    "Nhập số lượng: "
                            );

                            int quantity =
                                    Integer.parseInt(
                                            scanner.nextLine()
                                    );

                            if (quantity <= 0) {

                                System.out.println(
                                        "Số lượng phải lớn hơn 0!"
                                );

                                i--;

                                continue;
                            }

                            OrderDetail detail =
                                    new OrderDetail(
                                            productId,
                                            quantity,
                                            0
                                    );

                            details.add(detail);
                        }

                        Order order =
                                new Order(
                                        orderCustomerId,
                                        orderDate,
                                        details
                                );

                        orderManager.createOrder(
                                order
                        );

                        break;


                    // =====================================
                    // 4. HIỂN THỊ ĐƠN HÀNG
                    // =====================================

                    case 4:

                        orderManager.listAllOrders();

                        break;


                    // =====================================
                    // 5. TÌM ĐƠN HÀNG
                    // =====================================

                    case 5:

                        System.out.print(
                                "Nhập ID khách hàng cần tìm: "
                        );

                        int searchCustomerId =
                                Integer.parseInt(
                                        scanner.nextLine()
                                );

                        orderManager
                                .getOrdersByCustomer(
                                        searchCustomerId
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

            } catch (
                    IllegalArgumentException e) {

                System.out.println(
                        "Ngày không hợp lệ!"
                );

                System.out.println(
                        "Hãy nhập theo dạng yyyy-MM-dd."
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