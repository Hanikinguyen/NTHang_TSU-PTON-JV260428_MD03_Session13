package Ex07;

import database_library.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    // =====================================================
    // 1. THÊM SÁCH
    // =====================================================

    public void addBook(Book book) {

        // Kiểm tra sách đã tồn tại chưa
        String checkSql =
                "SELECT id FROM Book " +
                        "WHERE title = ? AND author = ?";

        String insertSql =
                "INSERT INTO Book " +
                        "(title, author, published_year, price) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    book.getTitle()
            );

            checkStatement.setString(
                    2,
                    book.getAuthor()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Sách đã tồn tại!"
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
                        book.getTitle()
                );

                insertStatement.setString(
                        2,
                        book.getAuthor()
                );

                insertStatement.setInt(
                        3,
                        book.getPublishedYear()
                );

                insertStatement.setDouble(
                        4,
                        book.getPrice()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm sách thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm sách thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm sách!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 2. CẬP NHẬT SÁCH
    // =====================================================

    public void updateBook(
            int id,
            Book book) {

        String sql =
                "UPDATE Book SET " +
                        "title = ?, " +
                        "author = ?, " +
                        "published_year = ?, " +
                        "price = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    book.getTitle()
            );

            statement.setString(
                    2,
                    book.getAuthor()
            );

            statement.setInt(
                    3,
                    book.getPublishedYear()
            );

            statement.setDouble(
                    4,
                    book.getPrice()
            );

            statement.setInt(
                    5,
                    id
            );

            int result =
                    statement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Cập nhật sách thành công!"
                );

            } else {

                System.out.println(
                        "Không tìm thấy sách với ID: "
                                + id
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi cập nhật sách!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 3. XÓA SÁCH
    // =====================================================

    public void deleteBook(int id) {

        String sql =
                "DELETE FROM Book WHERE id = ?";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int result =
                    statement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Xóa sách thành công!"
                );

            } else {

                System.out.println(
                        "Không tìm thấy sách với ID: "
                                + id
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi xóa sách!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 4. TÌM SÁCH THEO TÁC GIẢ
    // =====================================================

    public void findBooksByAuthor(
            String author) {

        String sql =
                "SELECT * FROM Book " +
                        "WHERE author LIKE ?";

        List<Book> books =
                new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "%" + author + "%"
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Book book = new Book();

                book.setId(
                        resultSet.getInt("id")
                );

                book.setTitle(
                        resultSet.getString("title")
                );

                book.setAuthor(
                        resultSet.getString("author")
                );

                book.setPublishedYear(
                        resultSet.getInt(
                                "published_year"
                        )
                );

                book.setPrice(
                        resultSet.getDouble("price")
                );

                books.add(book);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi tìm kiếm sách!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            return;
        }

        if (books.isEmpty()) {

            System.out.println(
                    "Không tìm thấy sách của tác giả: "
                            + author
            );

        } else {

            System.out.println();

            System.out.println(
                    "================ KẾT QUẢ TÌM KIẾM ================"
            );

            for (Book book : books) {
                book.display();
            }

            System.out.println(
                    "==================================================="
            );
        }
    }


    // =====================================================
    // 5. HIỂN THỊ TẤT CẢ SÁCH
    // =====================================================

    public void listAllBooks() {

        String sql =
                "SELECT * FROM Book";

        List<Book> books =
                new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Book book = new Book();

                book.setId(
                        resultSet.getInt("id")
                );

                book.setTitle(
                        resultSet.getString("title")
                );

                book.setAuthor(
                        resultSet.getString("author")
                );

                book.setPublishedYear(
                        resultSet.getInt(
                                "published_year"
                        )
                );

                book.setPrice(
                        resultSet.getDouble("price")
                );

                books.add(book);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi lấy danh sách sách!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            return;
        }

        if (books.isEmpty()) {

            System.out.println(
                    "Thư viện chưa có sách!"
            );

        } else {

            System.out.println();

            System.out.println(
                    "================ DANH SÁCH SÁCH ================"
            );

            for (Book book : books) {
                book.display();
            }

            System.out.println(
                    "================================================="
            );
        }
    }
}
