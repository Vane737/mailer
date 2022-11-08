package negocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Estado {

    int id;
    String nombre,estado,descripcion;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Estado() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdEstado(int id) {
        this.id = id;
    }

    public void AsigParametros(String patron) {        
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron=patron.trim();
        vector=patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.nombre = vector[1].trim();
    }

    public int InsertarEstado() {

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO estados(\n"
                + "	id, nombre, \"estadoHabilitado\", descripcion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setString(2, this.nombre);
            ps.setString(3, "1");
            ps.setString(4, "Descripcion Estado");

            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            i = -1;
        }
        return i;
    }

    public String ListarEstado() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "FROM estados";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Estados</b></caption>\n"
                    + "<tr><th style=background:orange;>ID</th><th style=background:orange;>Nombre</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("nombre") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public void AsigParametrosMod(String patron) {
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.nombre = vector[1].trim();

    }

    public int ModifEstado() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE estados SET \n"
                + "nombre = ?, \n"
                + "\"estadoHabilitado\" = ? ,\n"
                + "descripcion = ? \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, "1");
            ps.setString(3, "Estado modificado");
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
        return c;

    }

    public int EliminarEstado() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from estados \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            i = -1;
        }
        return i;
    }

}
