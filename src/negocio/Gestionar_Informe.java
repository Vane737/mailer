package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;
<<<<<<< Updated upstream
import java.sql.Timestamp;
=======
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
>>>>>>> Stashed changes
import java.util.Calendar;
import java.util.GregorianCalendar;
import presentacion.hilo;

public class Gestionar_Informe {
    conexion m_Conexion;
    cadenas cade = new cadenas();
    
    int id;
    String url;
    String descripcion;
    int id_revaluo;
    String fecha_hora_actual = cade.getFechaHora();
    
    
    public Gestionar_Informe() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdInforme(int id) {
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
        this.descripcion = vector[1].trim();
        this.url = vector[2].trim();
        this.id_revaluo = Integer.parseInt(vector[3].trim());
    }

    public int InsertarInforme() {

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql = "INSERT INTO informes(\n"
                + "	id, created_at, updated_at, url, descripcion, id_revaluo)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setTimestamp(2, Timestamp.valueOf(this.fecha_hora_actual));
            ps.setTimestamp(3, Timestamp.valueOf(this.fecha_hora_actual));
            ps.setString(4, this.url);
            ps.setString(5, this.descripcion);
            ps.setInt(6, this.id_revaluo);
            
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

    public String ListarInforme() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * \n"
                + "FROM informes";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Lista de Informes</b></caption>\n"
                    + "<tr><th style=background:#8fe5f6;>ID</th><th style=background:#8fe5f6;>Descripcion</th><th style=background:#8fe5f6;>Url</th><th style=background:#8fe5f6;>Fecha de creacion</th><th style=background:#8fe5f6;>Fecha de actualizacion</th><th style=background:#8fe5f6;>ID Revaluo</th></tr>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("descripcion") + "</td><td>" + rs.getString("url") + "</td><td>" + rs.getString("created_at") + "</td><td>" + rs.getString("updated_at") + "</td><td>" + rs.getInt("id_revaluo") + "</td></tr>";
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
        this.descripcion = vector[1].trim();
        this.url = vector[2].trim();
        this.id_revaluo = Integer.parseInt(vector[3].trim());
    }
    
    public int ModifInforme() {
        
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int c = 1;

        // Preparo la consulta
<<<<<<< Updated upstream
        String sql = "UPDATE informes SET \n"
                + "descripcion = ? \n"
                + "url = ? \n"
                + "updated_at = ? \n"
=======
        String sql;
        sql = "UPDATE informes SET \n"
                + "descripcion = ?,\n"
                + "url = ?,\n"
                + "updated_at = ?,\n"
>>>>>>> Stashed changes
                + "id_revaluo = ? \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, this.descripcion);
            ps.setString(2, this.url);
            ps.setTimestamp(3, Timestamp.valueOf(this.fecha_hora_actual));
<<<<<<< Updated upstream
            ps.setInt(4, this.id_revaluo);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }

/*        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.descripcion);
            ps.setString(2, this.url); 
            ps.setTimestamp(3, Timestamp.valueOf(this.fecha_hora_actual));
=======
>>>>>>> Stashed changes
            ps.setInt(4, this.id_revaluo);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();
            // Cierro Conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
*/
        return c;
        
    }
    
    public int EliminarInforme() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from informes \n"
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
