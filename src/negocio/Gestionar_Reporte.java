package negocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import funciones.cadenas;
import datos.conexion;

public class Gestionar_Reporte {
    
    int año;
    String grupo;
    String codigo,detalle,fecha;
    int id_responsable,id_estado,id_grupo,id_direccion,id_adquisicion;
    
    conexion m_Conexion;
    cadenas cade = new cadenas();
    
    public Gestionar_Reporte() {
        this.m_Conexion = conexion.getInstancia();
    }
    
    public void setAnioRevaluo(int año) {
        this.año = año;
    }
    
    public void setGrupInmueble(String grupo) {
        this.grupo = grupo;
    }
    
                              //["Edificio || Terreno || Construcciones"]
    
    public String listRepRevaluo() {
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
    
    public String listRepGrupo() {
        String res = "";
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        String sql = "SELECT im.id, im.codigo, im.detalle, ad.\"fechaAdquisicion\", gr.nombre as grupo, di.latitud, di.longitud,res.detalle as responsable\n"+
                     "FROM inmuebles as im, adquisiciones as ad, direcciones as di, grupos as gr, estados as es, responsables as res \n" +
                     "WHERE im.\"idAdquisicion\"=ad.id and im.\"idDireccion\"=di.id and im.\"idGrupo\"=gr.id and im.\"idEstado\"=es.id and im.\"idResponsable\"=res.id and gr.nombre ilike \'" + this.grupo + "%\'";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            
            res = "<table border=\"6\"><caption><b>Reporte Grupo</b></caption>\n"
                    + "<tr><th style=background:#8fe5f6;>Codigo</th><th style=background:#8fe5f6;>Descripcion</th><th style=background:#8fe5f6;>Fecha Adquisicion</th><th style=background:#8fe5f6;>Grupo</th><th style=background:#8fe5f6;>Latitud</th><th style=background:#8fe5f6;>Longitud</th><th style=background:#8fe5f6;>Responsable</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getString("codigo") + "</td><td>" + rs.getString("detalle") + "</td><td>" + rs.getString("fechaAdquisicion") + "</td><td>" + rs.getString("grupo") + "</td><td>" + rs.getString("latitud")+ "</td><td>" + rs.getString("longitud")+ "</td><td>" + rs.getString("responsable")+ "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
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
    
    
    
}
