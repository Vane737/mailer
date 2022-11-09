package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Adquisicion {

    int id;
    String tipo_ingreso;
    String modo_ingreso;
    String recurso;
    String fecha;
    float monto;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Adquisicion() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdAdquisicion(int id) {
        this.id = id;
    }

    public void AsigParametros(String patron) {
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.tipo_ingreso = vector[1].trim();
        this.modo_ingreso = vector[2].trim();
        this.recurso = vector[3].trim();
        this.fecha = vector[4].trim();
        this.monto = Float.parseFloat(vector[5].trim());
    }

    public int InsertarAdquisicion() {

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO adquisiciones(\n"
                + "	id, \"tipoIngreso\", \"ModoIngreso\", recursos, \"fechaAdquisicion\", monto)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?);";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            ps.setInt(1, this.id);
            ps.setString(2, this.tipo_ingreso);
            ps.setString(3, this.modo_ingreso);
            ps.setString(4, this.recurso);
            ps.setString(5, this.fecha);
            ps.setFloat(6, this.monto);
            

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

    public String ListarAdquisicion() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "from adquisiciones";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Aquisiciones</b></caption>\n"
                        + "<tr><th style=background:#8fe5f6;>ID</th><th style=background:#8fe5f6;>Tipo de Ingreso</th><th style=background:#8fe5f6;>Modo de Ingreso</th><th style=background:#8fe5f6;>Recursos</th><th style=background:#8fe5f6;>Fecha</th><th style=background:#8fe5f6;>Monto</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("tipoIngreso") + "</td><td>" + rs.getString("ModoIngreso") + "</td><td>" + rs.getString("recursos") + "</td><td>" + rs.getString("fechaAdquisicion") + "</td><td>" + rs.getString("monto") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int EliminarAdquisicion() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from  adquisiciones \n"
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

    public int ModifAquisicion() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE adquisiciones SET \n"
                + "tipoIngreso = ?,\n"
                + "ModoIngreso = ?,\n"
                + "recursos = ?,\n"
                + "fechaAdquisicion = ?, \n"
                + "monto = ? \n"
                + "WHERE id = ?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.tipo_ingreso);
            ps.setString(2, this.modo_ingreso);
            ps.setString(3, this.recurso);
            ps.setString(4, this.fecha);
            ps.setFloat(5, this.monto);
            ps.setInt(6, this.id);

            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
        return c;

    }

    public void AsigParametrosMod(String patron) {
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.tipo_ingreso = vector[1].trim();
        this.modo_ingreso = vector[2].trim();
        this.recurso = vector[3].trim();
        this.fecha = vector[4].trim();
        this.monto = Float.parseFloat(vector[5].trim());
    }

}
