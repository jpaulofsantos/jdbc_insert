import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainProgram {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            connection = DB.getConnection();

            /*preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller"
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Carl Hanks"); //1 é o primeiro ? da query
            preparedStatement.setString(2, "carl@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/1985").getTime()));
            preparedStatement.setDouble(4, 3000.00);
            preparedStatement.setInt(5, 4);*/

            preparedStatement = connection.prepareStatement("insert into department (Name) values ('Dep1'), ('Dep2')", Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("Id: " + id);
                }
            } else {
                System.out.println("No rows affected");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
