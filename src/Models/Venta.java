
package Models;


import javafx.beans.property.IntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Venta extends AbstractUsuario{

    private IntegerProperty idVenta;

    public Venta(ResultSet rs) throws SQLException {
        super(rs);

    }





}
