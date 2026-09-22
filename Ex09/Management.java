package Ex09;

import database_company.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Management {

    // =====================================================
    // 1. THÊM NHÂN VIÊN
    // =====================================================

    public void addEmployee(Employee employee) {

        String checkSql =
                "SELECT id FROM Employee WHERE name = ?";

        String insertSql =
                "INSERT INTO Employee " +
                        "(name, department, salary) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    employee.getName()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Nhân viên đã tồn tại!"
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
                        employee.getName()
                );

                insertStatement.setString(
                        2,
                        employee.getDepartment()
                );

                insertStatement.setDouble(
                        3,
                        employee.getSalary()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm nhân viên thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm nhân viên thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm nhân viên!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 2. THÊM DỰ ÁN
    // =====================================================

    public void addProject(Project project) {

        String checkSql =
                "SELECT id FROM Project WHERE name = ?";

        String insertSql =
                "INSERT INTO Project(name, budget) " +
                        "VALUES (?, ?)";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement checkStatement =
                        connection.prepareStatement(checkSql)
        ) {

            checkStatement.setString(
                    1,
                    project.getName()
            );

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Dự án đã tồn tại!"
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
                        project.getName()
                );

                insertStatement.setDouble(
                        2,
                        project.getBudget()
                );

                int result =
                        insertStatement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Thêm dự án thành công!"
                    );

                } else {

                    System.out.println(
                            "Thêm dự án thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi thêm dự án!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 3. GÁN NHÂN VIÊN VÀO DỰ ÁN
    // =====================================================

    public void assignEmployeeToProject(
            int employeeId,
            int projectId,
            String role) {

        String employeeSql =
                "SELECT id FROM Employee WHERE id = ?";

        String projectSql =
                "SELECT id FROM Project WHERE id = ?";

        String assignmentCheckSql =
                "SELECT employee_id " +
                        "FROM Assignments " +
                        "WHERE employee_id = ? " +
                        "AND project_id = ?";

        String insertSql =
                "INSERT INTO Assignments " +
                        "(employee_id, project_id, role) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection connection =
                        Database.getConnection()
        ) {

            // =============================================
            // KIỂM TRA EMPLOYEE
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    employeeSql
                            )
            ) {

                statement.setInt(
                        1,
                        employeeId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Không tìm thấy nhân viên ID: "
                                    + employeeId
                    );

                    return;
                }
            }


            // =============================================
            // KIỂM TRA PROJECT
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    projectSql
                            )
            ) {

                statement.setInt(
                        1,
                        projectId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Không tìm thấy dự án ID: "
                                    + projectId
                    );

                    return;
                }
            }


            // =============================================
            // KIỂM TRA ĐÃ ĐƯỢC GÁN CHƯA
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    assignmentCheckSql
                            )
            ) {

                statement.setInt(
                        1,
                        employeeId
                );

                statement.setInt(
                        2,
                        projectId
                );

                ResultSet resultSet =
                        statement.executeQuery();

                if (resultSet.next()) {

                    System.out.println(
                            "Nhân viên đã được gán vào dự án này!"
                    );

                    return;
                }
            }


            // =============================================
            // INSERT ASSIGNMENT
            // =============================================

            try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    insertSql
                            )
            ) {

                statement.setInt(
                        1,
                        employeeId
                );

                statement.setInt(
                        2,
                        projectId
                );

                statement.setString(
                        3,
                        role
                );

                int result =
                        statement.executeUpdate();

                if (result > 0) {

                    System.out.println(
                            "Gán nhân viên vào dự án thành công!"
                    );

                } else {

                    System.out.println(
                            "Gán nhân viên thất bại!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi gán nhân viên vào dự án!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 4. HIỂN THỊ NHÂN VIÊN VÀ DỰ ÁN
    // =====================================================

    public void listEmployeesAndProjects() {

        String sql =
                "SELECT " +
                        "e.id AS employee_id, " +
                        "e.name AS employee_name, " +
                        "e.department, " +
                        "e.salary, " +
                        "p.id AS project_id, " +
                        "p.name AS project_name, " +
                        "p.budget, " +
                        "a.role " +
                        "FROM Employee e " +
                        "LEFT JOIN Assignments a " +
                        "ON e.id = a.employee_id " +
                        "LEFT JOIN Project p " +
                        "ON a.project_id = p.id " +
                        "ORDER BY e.id";

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
                    "================ NHÂN VIÊN VÀ DỰ ÁN ================"
            );

            while (resultSet.next()) {

                found = true;

                int employeeId =
                        resultSet.getInt(
                                "employee_id"
                        );

                String employeeName =
                        resultSet.getString(
                                "employee_name"
                        );

                String department =
                        resultSet.getString(
                                "department"
                        );

                double salary =
                        resultSet.getDouble(
                                "salary"
                        );

                String projectName =
                        resultSet.getString(
                                "project_name"
                        );

                String role =
                        resultSet.getString(
                                "role"
                        );

                System.out.println();

                System.out.println(
                        "Nhân viên ID: "
                                + employeeId
                );

                System.out.println(
                        "Tên: "
                                + employeeName
                );

                System.out.println(
                        "Phòng ban: "
                                + department
                );

                System.out.printf(
                        "Lương: %.2f%n",
                        salary
                );

                if (projectName == null) {

                    System.out.println(
                            "Dự án: Chưa được phân công"
                    );

                } else {

                    System.out.println(
                            "Dự án: "
                                    + projectName
                    );

                    System.out.println(
                            "Vai trò: "
                                    + role
                    );
                }

                System.out.println(
                        "--------------------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "Chưa có nhân viên nào!"
                );
            }

            System.out.println(
                    "==================================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi hiển thị dữ liệu!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // 5. CẬP NHẬT LƯƠNG
    // =====================================================

    public void updateEmployeeSalary(
            int employeeId,
            double newSalary) {

        String sql =
                "UPDATE Employee " +
                        "SET salary = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        Database.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(
                    1,
                    newSalary
            );

            statement.setInt(
                    2,
                    employeeId
            );

            int result =
                    statement.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "Cập nhật lương thành công!"
                );

            } else {

                System.out.println(
                        "Không tìm thấy nhân viên ID: "
                                + employeeId
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Lỗi khi cập nhật lương!"
            );

            System.out.println(
                    "Chi tiết: " + e.getMessage()
            );
        }
    }
}
