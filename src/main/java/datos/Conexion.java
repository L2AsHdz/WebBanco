package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @date 11/11/2020
 * @time 09:52:02
 * @author asael
 */
public class Conexion {

    private static Connection conexion = null;
    private final String url = "jdbc:mariadb://localhost:3306/WebBanco";
    private final String usuario = "userDB";
    private final String password = "123";

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
}
