package Ex06;

import database_task.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {


    // ==================================================
    // 1. THÊM CÔNG VIỆC
    // ==================================================
    public void addTask(
            String taskName,
            String status) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call add_task(?,?)}"
                        )
        ) {

            callableStatement.setString(
                    1,
                    taskName
            );

            callableStatement.setString(
                    2,
                    status
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Thêm công việc thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể thêm công việc!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // ==================================================
    // 2. LIỆT KÊ CÔNG VIỆC
    // ==================================================
    public void listTasks() {

        List<Task> tasks =
                new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call list_tasks()}"
                        );

                ResultSet resultSet =
                        callableStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                Task task =
                        new Task();

                task.setId(
                        resultSet.getInt("id")
                );

                task.setTaskName(
                        resultSet.getString("task_name")
                );

                task.setStatus(
                        resultSet.getString("status")
                );

                tasks.add(task);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi lấy danh sách công việc!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            return;
        }


        if (tasks.isEmpty()) {

            System.out.println(
                    "Danh sách công việc đang trống!"
            );

        } else {

            System.out.println();

            System.out.println(
                    "=============== DANH SÁCH CÔNG VIỆC ==============="
            );

            for (Task task : tasks) {

                task.display();
            }

            System.out.println(
                    "===================================================="
            );
        }
    }


    // ==================================================
    // 3. CẬP NHẬT TRẠNG THÁI
    // ==================================================
    public void updateTaskStatus(
            int taskId,
            String status) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call update_task_status(?,?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    taskId
            );

            callableStatement.setString(
                    2,
                    status
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Cập nhật trạng thái thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể cập nhật trạng thái!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // ==================================================
    // 4. XÓA CÔNG VIỆC
    // ==================================================
    public void deleteTask(
            int taskId) {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call delete_task(?)}"
                        )
        ) {

            callableStatement.setInt(
                    1,
                    taskId
            );

            callableStatement.executeUpdate();

            System.out.println(
                    "Xóa công việc thành công!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Không thể xóa công việc!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // ==================================================
    // 5. TÌM KIẾM CÔNG VIỆC
    // ==================================================
    public void searchTaskByName(
            String taskName) {

        List<Task> tasks =
                new ArrayList<>();

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call search_task_by_name(?)}"
                        )
        ) {

            callableStatement.setString(
                    1,
                    taskName
            );

            ResultSet resultSet =
                    callableStatement.executeQuery();


            while (resultSet.next()) {

                Task task =
                        new Task();

                task.setId(
                        resultSet.getInt("id")
                );

                task.setTaskName(
                        resultSet.getString("task_name")
                );

                task.setStatus(
                        resultSet.getString("status")
                );

                tasks.add(task);
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi tìm kiếm công việc!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );

            return;
        }


        if (tasks.isEmpty()) {

            System.out.println(
                    "Không tìm thấy công việc phù hợp."
            );

        } else {

            System.out.println();

            System.out.println(
                    "=============== KẾT QUẢ TÌM KIẾM ==============="
            );

            for (Task task : tasks) {

                task.display();
            }

            System.out.println(
                    "=================================================="
            );
        }
    }


    // ==================================================
    // 6. THỐNG KÊ CÔNG VIỆC
    // ==================================================
    public void taskStatistics() {

        try (
                Connection connection =
                        Database.getConnection();

                CallableStatement callableStatement =
                        connection.prepareCall(
                                "{call task_statistics()}"
                        );

                ResultSet resultSet =
                        callableStatement.executeQuery()
        ) {

            int completed = 0;
            int pending = 0;

            while (resultSet.next()) {

                String status =
                        resultSet.getString("status");

                int total =
                        resultSet.getInt("total");

                if (
                        status.equalsIgnoreCase(
                                "COMPLETED"
                        )
                ) {

                    completed = total;

                } else if (
                        status.equalsIgnoreCase(
                                "PENDING"
                        )
                ) {

                    pending = total;
                }
            }

            System.out.println();
            System.out.println(
                    "========== THỐNG KÊ CÔNG VIỆC =========="
            );

            System.out.println(
                    "Đã hoàn thành: " + completed
            );

            System.out.println(
                    "Chưa hoàn thành: " + pending
            );

            System.out.println(
                    "========================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thống kê công việc!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }
}
