package negocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Responsable {

    int id;
    int cod_asig;
    String detalle;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Responsable() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdResposable(int id) {
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
        this.cod_asig = Integer.parseInt(vector[1].trim());
        this.detalle = vector[2].trim();
    }

    public int InsertarResponsable() {

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO responsables(\n"
                + "	id, \"codigoAsignado\", detalle)\n"
                + "	VALUES (?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setInt(2, this.cod_asig);
            ps.setString(3, this.detalle);

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

    public String ListarResponsable() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "FROM responsables";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Listado de Responsables</b></caption>\n"
                    + "<tr><th style=background:orange;>ID</th><th style=background:orange;>Cod Asignacion</th><th style=background:orange;>Detalle</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("codigoAsignado") + "</td><td>" + rs.getString("detalle") + "</td></tr>";
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
        patron=patron.trim();
        vector=patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.cod_asig= Integer.parseInt(vector[1].trim());
        this.detalle = vector[2].trim();
    }

    public int ModifResponsable() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE responsables SET \n"
                + "\"codigoAsignado\" = ? ,\n"
                + "detalle = ? \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.cod_asig);
            ps.setString(2, this.detalle);
            ps.setInt(3, this.cod_asig);
            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
        return c;

    }

    public int EliminarResponsable() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from responsables \n"
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
