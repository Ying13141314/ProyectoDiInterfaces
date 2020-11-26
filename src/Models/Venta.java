
package Models;


import javafx.beans.property.IntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Venta extends AbstractUsuario{

    private Integer idVenta,idUsuario;

    public Venta(ResultSet rs) throws SQLException {
        super(rs);
        idVenta = rs.getInt("idVenta");
        idUsuario = rs.getInt("idUsuario");

    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
