package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/practica_mvc"
            + "?useSSL=false&serverTimezone=UTC"
            + "&allowPublicKeyRetrieval=true";

    private static final String USUARIO = "gisela_andrade";
    private static final String PASSWORD = "AndradeM98+";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✓ Conexion MySQL exitosa");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Driver no encontrado: " + e.getMessage());
            System.err.println("Verifica que mysql-connector-j.jar este en lib/");
            return null;
        } catch (SQLException e) {
            System.err.println("✗ Error de conexion: " + e.getMessage());
            System.err.println("Codigo: " + e.getErrorCode());
            return null;
        }
    }
} 

