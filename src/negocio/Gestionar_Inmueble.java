package negocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import funciones.cadenas;
import datos.conexion;

public class Gestionar_Inmueble {
    
    int Id;
    String codigo,detalle,fecha;
    int id_responsable,id_estado,id_grupo,id_direccion,id_adquisicion;
    
    conexion m_Conexion;
    cadenas cade = new cadenas();
    
    public Gestionar_Inmueble() {
        this.m_Conexion = conexion.getInstancia();
    }
    
    public void setIdInmueble(int id) {
        this.Id = id;
    }
    
    public void AsigParametros(String patron) {
        
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron=patron.trim();
        vector=patron.split(",");
        this.Id = Integer.parseInt(vector[0].trim());
        this.codigo = vector[1].trim();
        this.detalle = vector[2].trim();
        this.fecha= vector[3].trim();
        this.id_responsable = Integer.parseInt(vector[4].trim());
        this.id_estado = Integer.parseInt(vector[5].trim());
        this.id_grupo = Integer.parseInt(vector[6].trim());
        this.id_direccion = Integer.parseInt(vector[7].trim());
        this.id_adquisicion = Integer.parseInt(vector[8].trim());
    }
    
    public int InsertarInmueble() {
        
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;
        
        String sql = "INSERT INTO inmuebles(\n" +
"	id, codigo, detalle, \"fechaRegistro\", \"idResponsable\", \"idEstado\", \"idGrupo\", \"idDireccion\", \"idAdquisicion\")\n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.Id);
            ps.setString(2, this.codigo);
            ps.setString(3, this.detalle);
            ps.setString(4, this.fecha);
            ps.setInt(5, this.id_responsable);
            ps.setInt(6, this.id_estado);
            ps.setInt(7, this.id_grupo);
            ps.setInt(8, this.id_direccion);
            ps.setInt(9, this.id_adquisicion);
            
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
    
    public String ListarInmueble() {
        String res = "";
        
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        String sql = "SELECT im.id, im.codigo, im.detalle,ad.\"fechaAdquisicion\", gr.nombre as grupo, di.ubicacion, es.nombre as estado, res.detalle as responsable, im.\"fechaRegistro\"\n" +
                     "FROM inmuebles as im, adquisiciones as ad, direcciones as di, grupos as gr, estados as es, responsables as res \n" +
                     "WHERE im.\"idAdquisicion\"=ad.id and im.\"idDireccion\"=di.id and im.\"idGrupo\"=gr.id and im.\"idEstado\"=es.id and im.\"idResponsable\"=res.id ";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            
            res = "<table border=\"6\"><caption><b>Listado de Inmuebles</b></caption>\n"
                    + "<tr><th style=background:#8fe5f6;>ID Inmueble</th><th style=background:#8fe5f6;>Codigo</th><th style=background:#8fe5f6;>Detalle</th><th style=background:#8fe5f6;>Fecha Registro</th><th style=background:#8fe5f6;>Grupo</th><th style=background:#8fe5f6;>Ubicacion</th><th style=background:#8fe5f6;>Estado</th><th style=background:#8fe5f6;>Responsable</th><th style=background:#8fe5f6;>Fecha Adquisicion</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("codigo") + "</td><td>" + rs.getString("detalle") + "</td><td>" + rs.getString("fechaRegistro") + "</td><td>" + rs.getString("grupo") + "</td><td>" + rs.getString("ubicacion")+ "</td><td>" + rs.getString("estado")+ "</td><td>" + rs.getString("responsable")+ "</td><td>" + rs.getString("fechaAdquisicion")+ "</tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
    public int ModifInmueble() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE inmuebles SET \n"
                + "codigo = ?,\n"
                + "detalle = ?,\n"
                + "\"fechaRegistro\" = ?,\n"
                + "\"idResponsable\" = ?,\n"
                + "\"idEstado\" = ?,\n"
                + "\"idGrupo\" = ?,\n"
                + "\"idDireccion\" = ?,\n"
                + "\"idAdquisicion\" = ? \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.codigo);
            ps.setString(2, this.detalle);
            ps.setString(3, this.fecha);
            ps.setInt(4, this.id_responsable);
            ps.setInt(5, this.id_estado);
            ps.setInt(6, this.id_grupo);
            ps.setInt(7, this.id_direccion);
            ps.setInt(8, this.id_adquisicion);
            ps.setInt(9, this.Id);
            int rows = ps.executeUpdate();
            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c = -1;
        }
        return c;
        
    }
    
    public int EliminarInmueble() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from  inmuebles \n"
                + "WHERE id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.Id);
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
        patron=patron.trim();
        vector=patron.split(",");
//        MODINMU["ID","Codigo","Detalle","Fecha","IdResponsable","IdEstado","IdGrupo","IdDireccion","IdAdquisicion"]
        this.Id = Integer.parseInt(vector[0].trim());
        this.codigo = vector[1].trim();
        this.detalle = vector[2].trim();
        this.fecha= vector[3].trim();
        this.id_responsable = Integer.parseInt(vector[4].trim());
        this.id_estado = Integer.parseInt(vector[5].trim());
        this.id_grupo = Integer.parseInt(vector[6].trim());
        this.id_direccion = Integer.parseInt(vector[7].trim());
        this.id_adquisicion = Integer.parseInt(vector[8].trim());
    }
    
}
