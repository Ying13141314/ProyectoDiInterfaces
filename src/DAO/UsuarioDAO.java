package DAO;

import Models.AbstractUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            if(rs.getString("tipo").equals("venta")){
                String sql2 = "SELECT * from usuario left join vendedor on usuario.idUsuario=vendedor.idUsuario";

                PreparedStatement ps2 = conexion.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                miUsuario = AbstractUsuario.tipo(rs2);

            }else{
                miUsuario = AbstractUsuario.tipo(rs);
            }



        }

        return miUsuario;
    }

}
