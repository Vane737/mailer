package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import funciones.cadenas;
import datos.conexion;

public class Gestionar_Direccion {

    int id;
    String ubicacion;
    String lugar;
    String oficina;
    String descripcion;
    String latitud;
    String longitud;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Direccion() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdDireccion(int id) {
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
        this.ubicacion = vector[1].trim();
        this.lugar = vector[2].trim();
        this.oficina = vector[3].trim();
        this.latitud = vector[4].trim();
        this.longitud = vector[5].trim();

    }

    public int InsertarDireccion() {

        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        String sql = "INSERT INTO direcciones(\n"
                + "	id, ubicacion, lugar, oficina, descripcion, latitud, longitud)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.id);
            ps.setString(2, this.ubicacion);
            ps.setString(3, this.lugar);
            ps.setString(4, this.oficina);
            ps.setString(5, "UAGRM");
            ps.setString(6, this.latitud);
            ps.setString(7, this.longitud);

            int rows = ps.executeUpdate();

            this.m_Conexion.cerrarConexion();

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

    public String ListarDireccion() {
        String res = "";

        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        String sql = "SELECT id, ubicacion, lugar, oficina, latitud, longitud\n"
                + "	FROM direcciones;";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            res = "<table border=\"6\"><caption><b>Lista De Direcciones</b></caption>\n"
                    + "<tr><th style=background:orange;>ID</th><th style=background:orange;>Ubicacion</th><th style=background:orange;>Lugar</th><th style=background:orange;>Edificio</th><th style=background:orange;>Latitud</th><th style=background:orange;>Longitud</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("ubicacion") + "</td><td>" + rs.getString("lugar") + "</td><td>" + rs.getString("oficina") + "</td><td>" + rs.getString("latitud") + "</td><td>" + rs.getString("longitud") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int ModifDireccion() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE direcciones SET \n"
                + "ubicacion = ?,\n"
                + "lugar = ?,\n"
                + "oficina = ?,\n"
                + "descripcion = ?,\n"
                + "latitud = ?,\n"
                + "longitud = ? \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.ubicacion);
            ps.setString(2, this.lugar);
            ps.setString(3, this.oficina);
            ps.setString(4, "UAGRM");
            ps.setString(5, this.latitud);
            ps.setString(6, this.longitud);
            ps.setInt(7, this.id);
            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
        return c;

    }

    public int EliminarDireccion() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from  direcciones \n"
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

    public void AsigParametrosMod(String patron) {
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.ubicacion = vector[1].trim();
        this.lugar = vector[2].trim();
        this.oficina = vector[3].trim();
        this.latitud = vector[4].trim();
        this.longitud = vector[5].trim();
    }

}
