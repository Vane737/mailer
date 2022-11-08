package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Fotografia {

    int id;
    String direccion;
    String nombre;
    String detalle;
    String fecha;
    int idInmueble;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Fotografia() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdFotografia(int id) {
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
        this.direccion = vector[1].trim();
        this.nombre  = vector[2].trim();
        this.fecha = vector[3].trim();
        this.idInmueble = Integer.parseInt(vector[5].trim());

    }

    public int InsertarFotografia() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO fotografias(\n"
                + "	id, direccion, nombre, detalle, fechaSubido, idInmueble)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setString(2, this.direccion);
            ps.setString(3, this.nombre);
            ps.setString(4, "Fotografia del inmueble");
            ps.setString(5, this.fecha);
            ps.setInt(6, this.idInmueble);

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

    public String ListarFotografia() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "	FROM fotografias";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Imagenes de Inmuebles Registrados</b></caption>\n"
                    + "<tr><th style=background:orange;>ID</th><th style=background:orange;>Ruta</th><th style=background:orange;>Nombre</th><th style=background:orange;>Detalle</th><th style=background:orange;>Fecha Subida</th><th style=background:orange;>ID Inmueble</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("direccion") + "</td><td>" + rs.getString("nombre") + "</td><td>" + rs.getString("detalle") + "</td><td>" + rs.getString("fechaSubido") + "</td><td>" + rs.getString("idInmueble") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int EliminarFotografia() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from fotografias \n"
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
        this.direccion = vector[1].trim();
        this.nombre = vector[2].trim();
        this.fecha = vector[4].trim();
        this.idInmueble = Integer.parseInt(vector[5].trim());
    }

    public int ModifFotografia() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE fotografias SET \n"
                + "direccion = ?,\n"
                + "nombre = ?,\n"
                + "detalle = ?,\n"
                + "fechaSubido = ?,\n"
                + "idInmueble = ?\n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.direccion);
            ps.setString(2, this.nombre);
            ps.setString(3, "Fotografia del inmueble");
            ps.setString(4, this.fecha);
            ps.setInt(5, this.idInmueble);
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
}
