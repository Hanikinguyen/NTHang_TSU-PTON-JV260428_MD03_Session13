package Ex08;

import database_store.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    // =====================================================
    // 1. THÊM PRODUCT
    // =====================================================

    public void addProduct(Product product) {

        String checkSql =
                "SELECT id FROM Product WHERE name = ?";

        String insertSql =
                "INSERT INTO Product(name, price) " +
                        "VALUES (?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    product.getName()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Sản phẩm đã tồn tại!"
                );

                return;
            }

            try (
                    PreparedStatement insertStatement =
                            connection.prepareStatement(
                                    insertSql
                            )
            ) {

                insertStatement.setString(
                        1,
                        product.getName()
                );

                insertStatement.setDouble(
                        2,
                        product.getPrice()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm sản phẩm thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm sản phẩm thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm sản phẩm!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 2. CẬP NHẬT CUSTOMER
    // =====================================================

    public void updateCustomer(
            int customerId,
            Customer customer) {

        String sql =
                "UPDATE Customer " +
                        "SET name = ?, email = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    customer.getName()
            );

            statement.setString(
                    2,
                    customer.getEmail()
            );

            statement.setInt(
                    3,
                    customerId
            );

            int result =
                    statement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Cập nhật khách hàng thành công!"
                );

            } else {

                System.out.println(
                        "Không tìm thấy khách hàng có ID: "
                                + customerId
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi cập nhật khách hàng!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 3. TẠO ORDER
    // =====================================================

    public void createOrder(Order order) {

        String productSql =
                "SELECT price FROM Product " +
                        "WHERE id = ?";

        String insertOrderSql =
                "INSERT INTO Orders " +
                        "(customer_id, order_date, total_amount) " +
                        "VALUES (?, ?, ?)";

        String insertDetailSql =
                "INSERT INTO OrderDetail " +
                        "(product_id, order_id, quantity, priceSell) " +
                        "VALUES (?, ?, ?, ?)";

        Connection connection = null;

        try {

            connection =
                    Database.getConnection();

            // Bắt đầu Transaction
            connection.setAutoCommit(false);

            double totalAmount = 0;

            // =============================================
            // TÍNH TỔNG TIỀN
            // =============================================

            for (
                    OrderDetail detail :
                    order.getOrderDetails()
            ) {

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        productSql
                                )
                ) {

                    statement.setInt(
                            1,
                            detail.getProductId()
                    );

                    ResultSet resultSet =
                            statement.executeQuery();

                    if (!resultSet.next()) {

                        System.out.println(
                                "Không tìm thấy sản phẩm ID: "
                                        + detail.getProductId()
                        );

                        connection.rollback();

                        return;
                    }

                    double productPrice =
                            resultSet.getDouble("price");

                    detail.setPriceSell(
                            productPrice
                    );

                    totalAmount +=
                            productPrice
                                    * detail.getQuantity();
                }
            }

            // =============================================
            // INSERT ORDER
            // =============================================

            int orderId;

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    insertOrderSql,
                                    Statement.RETURN_GENERATED_KEYS
                            )
            ) {

                statement.setInt(
                        1,
                        order.getCustomerId()
                );

                statement.setDate(
                        2,
                        order.getOrderDate()
                );

                statement.setDouble(
                        3,
                        totalAmount
                );

                statement.executeUpdate();

                ResultSet generatedKeys =
                        statement.getGeneratedKeys();

                if (!generatedKeys.next()) {

                    System.out.println(
                            "Không thể tạo đơn hàng!"
                    );

                    connection.rollback();

                    return;
                }

                orderId =
                        generatedKeys.getInt(1);
            }

            // =============================================
            // INSERT ORDER DETAIL
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    insertDetailSql
                            )
            ) {

                for (
                        OrderDetail detail :
                        order.getOrderDetails()
                ) {

                    statement.setInt(
                            1,
                            detail.getProductId()
                    );

                    statement.setInt(
                            2,
                            orderId
                    );

                    statement.setInt(
                            3,
                            detail.getQuantity()
                    );

                    statement.setDouble(
                            4,
                            detail.getPriceSell()
                    );

                    statement.addBatch();
                }

                statement.executeBatch();
            }

            // =============================================
            // COMMIT
            // =============================================

            connection.commit();

            order.setId(orderId);
            order.setTotalAmount(totalAmount);

            System.out.println(
                    "Tạo đơn hàng thành công!"
            );

            System.out.println(
                    "Mã đơn hàng: " + orderId
            );

            System.out.printf(
                    "Tổng tiền: %.2f%n",
                    totalAmount
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể tạo đơn hàng!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            try {

                if (connection != null) {
                    connection.rollback();
                }

            } catch (Exception rollbackException) {

                System.out.println(
                        "Lỗi khi rollback!"
                );
            }

        } finally {

            try {

                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }

            } catch (Exception e) {

                System.out.println(
                        "Lỗi khi đóng connection!"
                );
            }
        }
    }


    // =====================================================
    // 4. HIỂN THỊ TẤT CẢ ORDER
    // =====================================================

    public void listAllOrders() {

        String sql =
                "SELECT " +
                        "o.id, " +
                        "c.name AS customer_name, " +
                        "o.order_date, " +
                        "o.total_amount " +
                        "FROM Orders o " +
                        "JOIN Customer c " +
                        "ON o.customer_id = c.id " +
                        "ORDER BY o.id";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            boolean found = false;

            System.out.println();

            System.out.println(
                    "================ DANH SÁCH ĐƠN HÀNG ================"
            );

            while (resultSet.next()) {

                found = true;

                int id =
                        resultSet.getInt("id");

                String customerName =
                        resultSet.getString(
                                "customer_name"
                        );

                String orderDate =
                        resultSet.getDate(
                                "order_date"
                        ).toString();

                double totalAmount =
                        resultSet.getDouble(
                                "total_amount"
                        );

                System.out.printf(
                        "| ID: %3d | Khách hàng: %-20s | Ngày: %s | Tổng: %12.2f |%n",
                        id,
                        customerName,
                        orderDate,
                        totalAmount
                );
            }

            System.out.println(
                    "====================================================="
            );

            if (!found) {

                System.out.println(
                        "Chưa có đơn hàng nào!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi lấy danh sách đơn hàng!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 5. TÌM ORDER THEO CUSTOMER
    // =====================================================

    public void getOrdersByCustomer(
            int customerId) {

        String sql =
                "SELECT " +
                        "o.id, " +
                        "c.name AS customer_name, " +
                        "o.order_date, " +
                        "o.total_amount " +
                        "FROM Orders o " +
                        "JOIN Customer c " +
                        "ON o.customer_id = c.id " +
                        "WHERE o.customer_id = ? " +
                        "ORDER BY o.id";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    customerId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            boolean found = false;

            System.out.println();

            System.out.println(
                    "============= ĐƠN HÀNG CỦA KHÁCH HÀNG ============="
            );

            while (resultSet.next()) {

                found = true;

                int id =
                        resultSet.getInt("id");

                String customerName =
                        resultSet.getString(
                                "customer_name"
                        );

                String orderDate =
                        resultSet.getDate(
                                "order_date"
                        ).toString();

                double totalAmount =
                        resultSet.getDouble(
                                "total_amount"
                        );

                System.out.printf(
                        "| ID: %3d | Khách hàng: %-20s | Ngày: %s | Tổng: %12.2f |%n",
                        id,
                        customerName,
                        orderDate,
                        totalAmount
                );
            }

            System.out.println(
                    "===================================================="
            );

            if (!found) {

                System.out.println(
                        "Không tìm thấy đơn hàng của khách hàng ID: "
                                + customerId
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi tìm đơn hàng!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }
}
