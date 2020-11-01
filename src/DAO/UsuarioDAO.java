package DAO;

import Models.AbstractUsuario;
import Models.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends AbstractDAO {


    /**
     * Método para loguearse.
     * @param pass
     * @param user
     * @return
     * @throws SQLException
     */
    public AbstractUsuario loguearse(String pass,String user) throws SQLException {

        super.conectar();

        AbstractUsuario miUsuario = null;
        String sql = "SELECT * from usuario where contrasena = ? and nombreUsuario=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1,pass);
        ps.setString(2,user);
        ResultSet rs = ps.executeQuery();


        if (rs.isBeforeFirst()){
            rs.next();
            miUsuario = AbstractUsuario.tipo(rs);
        }

        super.cerrarBasesDatos();
        return miUsuario;
    }

}
