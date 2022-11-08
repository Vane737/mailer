package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private Connection connection = null;
    final String host;
    final String user;
    final String password;
    private static conexion m_Conexion = null;

    private conexion() {
        this.host = db.DB_HOST;
        this.user = db.DB_USER;
        this.password = db.DB_PASSWORD;

    }

    public static conexion getInstancia() {
        if (m_Conexion == null) {
            m_Conexion = new conexion();
        }
        return m_Conexion;
    }

    public Connection getConexion() {
        return this.connection;
    }

    public void abrirConexion() {
        String dbname = db.DB_NAME;
        String url_db = "jdbc:postgresql://" + this.host + ":5432/" + dbname;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url_db, this.user, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
