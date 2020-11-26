package DAO;

import Models.AbstractUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO extends AbstractDAO {
    /**
     * Método para que devuelve un usuario para poder loguarse.
     * @param pass
     * @param user
     * @return
     * @throws SQLException
     */
    public AbstractUsuario loguearse(String pass,String user) throws SQLException {

        AbstractUsuario miUsuario = null;

        //binary sirve para diferenciar entre mayuscula y minuscula. Prohibe loguearse aunque tiene el mismo caracter.
        String sql = "SELECT * from usuario where binary contrasena = ? and binary nombreUsuario=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1,pass);
        ps.setString(2,user);
        ResultSet rs = ps.executeQuery();


        if (rs.isBeforeFirst()){
            rs.next();


            miUsuario = AbstractUsuario.tipo(rs);
        }

        return miUsuario;
    }

    public ArrayList<String> obtenerMecanicos() throws SQLException {
        ArrayList<String> nombreMecanicos = new ArrayList<>();
        //binary sirve para diferenciar entre mayuscula y minuscula. Prohibe loguearse aunque tiene el mismo caracter.
        String sql = "SELECT nombre from usuario";

        Statement stm = conexion.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()){
            nombreMecanicos.add(rs.getString("nombre"));
        }
        return nombreMecanicos;
    }

    public Integer obtenerIdMecanico(String nombreMecanico) throws SQLException {
        String sql = "SELECT idUsuario from usuario where nombre = "+nombreMecanico;

        Statement stm = conexion.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        return rs.getInt("idUsuario");
    }
}
