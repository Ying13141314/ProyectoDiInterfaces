package Models;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Mecanico extends AbstractUsuario {
    public Mecanico(ResultSet rs) throws SQLException {
        super(rs);

    }

}
