import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class consultaJoin {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "";
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // Primer query: JOIN para todos los empleados
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(
                             "SELECT e.Nombre, e.Apellido, d.Nombre AS Departamento " +
                                     "FROM empleados e INNER JOIN departamento d ON e.DepartamentoID = d.id")) {
                    System.out.println("Empleados con su respectivo departamento");
                    System.out.println("Nombre | Apellido | Departamento");


                    while (resultSet.next()) {
                        System.out.printf("%s | %s | %s%n",
                                resultSet.getString("Nombre"),
                                resultSet.getString("Apellido"),
                                resultSet.getString("Departamento"));
                        System.out.println("------------------------");
                    }
                }
                // Segundo query: JOIN para empleados en Cafetería
                try (Statement statement_2 = connection.createStatement();
                     ResultSet resultSetCafeteria = statement_2.executeQuery(
                             "SELECT e.Nombre, e.Apellido, d.Nombre AS Departamento " +
                                     "FROM empleados e INNER JOIN departamento d ON e.DepartamentoID = d.id" +
                             " WHERE d.Nombre = 'Cafetería'")) {

                    System.out.println("Nombre | Apellido | Departamento");
                    System.out.println("Empleados exlusivamente del departamento CAFETERIA");
                    while (resultSetCafeteria.next()) {

                        System.out.printf("%s | %s | %s%n",
                                resultSetCafeteria.getString("Nombre"),
                                resultSetCafeteria.getString("Apellido"),
                                resultSetCafeteria.getString("Departamento"));


                    }

                } // Cierre del try interno
            } // Cierre del try de Connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Cierre del método main
} // Cierre de la clase