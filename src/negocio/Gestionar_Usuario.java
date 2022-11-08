package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import datos.conexion;
import funciones.cadenas;

public class Gestionar_Usuario {

    Integer id;
    String nombre,apellido,genero,nacionalidad;
    Integer ci,celular;
    String direccion,Email,Foto,Password,TipoUsuario,Letra,Color;
   
    conexion m_Conexion;

    cadenas cade = new cadenas();

    public Gestionar_Usuario() {
        this.m_Conexion = conexion.getInstancia();
    }

    public void setIdUsuario(Integer id) {
        this.id = id;
    }

    //insert
    public void AsigParametros(String patron) {
        String vector[];
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        vector = patron.split(",");
        this.id = Integer.parseInt(vector[0].trim());
        this.nombre = vector[1].trim();
        this.apellido = vector[2].trim();
        this.genero = vector[3].trim();
        this.nacionalidad = vector[4].trim();
        this.ci = Integer.parseInt(vector[5].trim());
        this.celular = Integer.parseInt(vector[6].trim());
        this.direccion = vector[7].trim();
        this.Email = vector[8].trim();
        this.Password = vector[9].trim();
        this.TipoUsuario = vector[10].trim();     
    }

    public int InsertarUsuario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        int i = 1;

        // Preparo la consulta
        String sql
                = "INSERT INTO usuarios(\n"
                + "	id, nombre, apellido, genero, nacionalidad, ci, celular, direccion, email, foto, password, tipo_usuario, letra, color)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias aut	oincrementables
            ps.setInt(1, this.id);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.genero);
            ps.setString(5, this.nacionalidad);
            ps.setInt(6, this.ci);
            ps.setInt(7, this.celular);
            ps.setString(8, this.direccion);
            ps.setString(9, this.Email);
            ps.setString(10, "Ninguna");
            ps.setString(11, this.Password);
            ps.setString(12, this.TipoUsuario);
            ps.setString(13, "Z");
            ps.setString(14, "Verde");

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

    public String ListarUsuarios() {
        String res = "";

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT * FROM usuarios";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
            res = "<table border=\"6\"><caption><b>Lista de Usuarios</b></caption>\n"
                    + "<tr><th style=background:orange;>ID</th><th style=background:orange;>Nombre</th><th style=background:orange;>Genero</th><th style=background:orange;>CI</th><th style=background:orange;>Email</th><th style=background:orange;>Tipo Usuario</th>";
            // Recorro el resultado
            while (rs.next()) {
                res = res + "<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("nombre")+rs.getString("apellido") + "</td><td>" + rs.getString("genero") + "</td><td>" + rs.getInt("ci") + "</td><td>" + rs.getString("email") + "</td><td>" + rs.getString("tipo_usuario") + "</td></tr>";
            }
            res = res + "</table>";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int EliminarUsuario() {
        int i = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from usuarios \n"
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

    public int ModifUsuario() {
        int c = 1;
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE usuarios SET \n"
                + "nombre = ?,\n"
                + "apellido = ?,\n"
                + "genero = ?,\n"
                + "nacionalidad = ?,\n"
                + "celular = ?, \n"
                + "direccion = ?, \n"
                + "email = ?, \n"
                + "password = ?,\n"
                + "tipo_usuario = ?\n"
                + "WHERE ci = ?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, this.nombre);
            ps.setString(2, this.apellido);
            ps.setString(3, this.genero);
            ps.setString(4, this.nacionalidad);
            ps.setInt(5, this.celular);
            ps.setString(6, this.direccion);
            ps.setString(7, this.Email);
            ps.setString(8, this.Password);
            ps.setString(9, this.TipoUsuario);
            ps.setInt(10, this.ci);
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
        this.ci = Integer.parseInt(vector[0].trim());
        this.nombre = vector[1].trim();
        this.apellido = vector[2].trim();
        this.genero = vector[3].trim();
        this.nacionalidad = vector[4].trim();
        this.celular = Integer.parseInt(vector[5].trim());
        this.direccion = vector[6].trim();
        this.Email = vector[7].trim();
        this.Password = vector[8].trim();
        this.TipoUsuario = vector[9].trim();

    }

}
