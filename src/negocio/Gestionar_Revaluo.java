package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Revaluo {

    int id;
    String descripcion,fecha;
    float costo,costoActualizado,depreciacionAcu,ValorNeto;
    int id_inmueble;

    conexion m_Conexion;
    cadenas cade = new cadenas();

    public Gestionar_Revaluo() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdRevaluo(int id) {
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
        this.fecha = vector[1].trim();
        this.costo = Float.parseFloat(vector[2].trim());
        this.costoActualizado = Float.parseFloat(vector[3].trim());
        this.depreciacionAcu = Float.parseFloat(vector[4].trim());
        this.ValorNeto = Float.parseFloat(vector[5].trim());
        this.id_inmueble = Integer.parseInt(vector[6].trim());

    }

    public int InsertarRevaluo() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO revaluos(\n"
                + "	id, descripcion, \"fechaRevaluo\", costo, \"costoActualizado\", \"depreciacionAcumulada\", \"valorNeto\", \"idInmueble\"\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setString(2, "a");
            ps.setString(3, this.fecha);
            ps.setFloat(4, this.costo);
            ps.setFloat(5, this.costoActualizado);
            ps.setFloat(6, this.depreciacionAcu);
            ps.setFloat(7, this.ValorNeto);
            ps.setInt(8, this.id_inmueble);

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

    public String ListarRevaluo() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "	FROM revaluos ";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Revaluos</b></caption>\n"
                    + "<tr><th style=background:#8fe5f6;>ID</th><th style=background:#8fe5f6;>Fecha Revaluo</th><th style=background:#8fe5f6;>Costo</th><th style=background:#8fe5f6;>Costo Actualizado</th><th style=background:#8fe5f6;>Depreciacion Acumulada</th><th style=background:#8fe5f6;>Valor Neto</th><th style=background:#8fe5f6;>ID Inmueble</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("fechaRevaluo") + "</td><td>" + rs.getFloat("costo") + "</td><td>" + rs.getFloat("costoActualizado") + "</td><td>" + rs.getFloat("depreciacionAcumulada") + "</td><td>" + rs.getString("valorNeto") + "</td><td>" + rs.getString("idInmueble") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int EliminarRevaluo() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from revaluos \n"
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

    public int ModifRevaluo() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE revaluos SET \n"
                + "\"fechaRevaluo\" = ?, \n"
                + "costo = ?, \n"
                + "\"costoActualizado\" = ?, \n"
                + "\"depreciacionAcumulada\" = ?, \n"
                + "\"valorNeto\" = ?, \n"
                + "\"idInmueble\" = ?\n"
                + "WHERE id = ?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.fecha);
            ps.setFloat(2, this.costo);
            ps.setFloat(3, this.costoActualizado);
            ps.setFloat(4, this.depreciacionAcu);
            ps.setFloat(5, this.ValorNeto);
            ps.setInt(6, this.id_inmueble);
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

    public void AsigParametrosMod(String patron) {

        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.fecha = vector[1].trim();
        this.costo = Float.parseFloat(vector[2].trim());
        this.costoActualizado = Float.parseFloat(vector[3].trim());
        this.depreciacionAcu = Float.parseFloat(vector[4].trim());
        this.ValorNeto = Float.parseFloat(vector[5].trim());
        this.id_inmueble = Integer.parseInt(vector[6].trim());
    }

}
