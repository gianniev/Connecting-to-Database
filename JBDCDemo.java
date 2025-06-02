import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JBDCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "";
        try {
            // Opcional en versiones recientes de JDBC
            // Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // Query 1: empleados
                try (Statement statement = connection.createStatement();
                     ResultSet resultSetEmpleados = statement.executeQuery("SELECT * FROM empleados")) {
                    System.out.println("Empleados:");
                    System.out.println("ID | Nombre | Apellido | Salario | DepartamentoID");
                    System.out.println("---------------------------------------------");
                    while (resultSetEmpleados.next()) {
                        System.out.printf("%d | %s | %s | %d | %d%n",
                                resultSetEmpleados.getInt(1),
                                resultSetEmpleados.getString(2),
                                resultSetEmpleados.getString(3),
                                resultSetEmpleados.getInt(4),
                                resultSetEmpleados.getInt(5));
                    }
                }

                // Query 2: departamento
                try (Statement statement = connection.createStatement();
                     ResultSet resultSetDepartamento = statement.executeQuery("SELECT * FROM departamento")) {
                    System.out.println("\nDepartamentos:");
                    System.out.println("ID | Nombre");
                    System.out.println("---------------");
                    while (resultSetDepartamento.next()) {
                        System.out.printf("%d | %s%n",
                                resultSetDepartamento.getInt(1),
                                resultSetDepartamento.getString(2));
                    }
                }
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("INSERT INTO departamento (id, Nombre) VALUES " +
                            "(1, 'Ventas'), " +
                            "(2, 'Marketing'), " +
                            "(3, 'Recursos Humanos'), " +
                            "(4, 'Desarrollo Web'), " +
                            "(5, 'Cafetería'), " +
                            "(6, 'Dirección')");
                }

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("INSERT INTO empleados (id, Nombre, Apellido, Salario, DepartamentoID) VALUES " +
                                                "(3, 'Sara', 'Gomez', '1600', 3), " +
                                                "(4, 'Pedro', 'Lopez', '1700', 2), " +
                                                "(5, 'Ramiro', 'Gonzalez', '1800', 1), " +
                                                "(6, 'Gonzalo', 'Monte', '1600', 3), " +
                                                "(7, 'Andrea', 'Mascherano', '2000', 4), " +
                                                "(8, 'Georgina', 'Pirlo', '1500', 5), " +
                                                "(9, 'Paula', 'Melende', '3500', 6), " +
                                                "(10, 'Lorena', 'Smith', '1500', 1), " +
                                                "(11, 'Florencia', 'Clavijo', '1500', 1), " +
                                                "(12, 'Miguel Angel', 'Ribeiro', '1600', 3), " +
                                                "(13, 'Tatiana', 'Ventura', '1500', 5), " +
                                                "(14, 'Sofia', 'Meana', '1500', 5)");

                }

                try (Statement statement = connection.createStatement()) {
                    int rowChange = statement.executeUpdate("UPDATE empleados SET Nombre = 'Noelia' WHERE id = 13 ");
                    System.out.println("Fila de empleados actualizadas: " + rowChange);

                    // Actualizar nombre de departamento
                    int rowDepartamento  = statement.executeUpdate("UPDATE departamento SET Nombre = 'Informática' WHERE id = 4");
                    System.out.println("Fila de departamentos actualizada: " + rowDepartamento);

                }

            } // Cierre del try de Connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Cierre del método main
} // Cierre de la clase