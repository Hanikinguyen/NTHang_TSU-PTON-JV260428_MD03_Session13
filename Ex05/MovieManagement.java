package Ex05;

import database_movie.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {

    // ==========================================
    // 1. THÊM PHIM
    // ==========================================
    public void addMovie(
            String title,
            String director,
            int year) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call add_movie(?,?,?)}"
                        )
        ) {

            callableStatement.setString(
                    1,
                    title
            );

            callableStatement.setString(
                    2,
                    director
            );

            callableStatement.setInt(
                    3,
                    year
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Thêm phim thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể thêm phim!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // ==========================================
    // 2. LIỆT KÊ PHIM
    // ==========================================
    public void listMovies() {

        List<Movie> movies =
                new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call list_movies()}"
                        );

                ResultSet resultSet =
                        callableStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                Movie movie =
                        new Movie();

                movie.setId(
                        resultSet.getInt("id")
                );

                movie.setTitle(
                        resultSet.getString("title")
                );

                movie.setDirector(
                        resultSet.getString("director")
                );

                movie.setYear(
                        resultSet.getInt("year")
                );

                movies.add(movie);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi lấy danh sách phim!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            return;
        }


        if (movies.isEmpty()) {

            System.out.println(
                    "Danh sách phim đang trống!"
            );

        } else {

            System.out.println();

            System.out.println(
                    "=============== DANH SÁCH PHIM ==============="
            );

            for (Movie movie : movies) {

                movie.display();
            }

            System.out.println(
                    "==============================================="
            );
        }
    }


    // ==========================================
    // 3. SỬA PHIM
    // ==========================================
    public void updateMovie(
            int id,
            String title,
            String director,
            int year) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call update_movie(?,?,?,?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    id
            );

            callableStatement.setString(
                    2,
                    title
            );

            callableStatement.setString(
                    3,
                    director
            );

            callableStatement.setInt(
                    4,
                    year
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Cập nhật phim thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể cập nhật phim!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // ==========================================
    // 4. XÓA PHIM
    // ==========================================
    public void deleteMovie(int id) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call delete_movie(?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    id
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Xóa phim thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể xóa phim!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }
}
