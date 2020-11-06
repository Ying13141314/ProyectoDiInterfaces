package DAO;

import Models.AbstractUsuario;
import Models.Vehiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO extends AbstractDAO {

    public void crearVehiculo(Vehiculo vehiculo) throws SQLException {

        super.conectar();

        //
        String query = "INSERT INTO vehiculo (marca,modelo,numeroPuertas,precio,fechaEntrada,vendido) " + "VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setString(1, vehiculo.getMarca());
        ps.setString(2, vehiculo.getModelo());
        ps.setInt(3, vehiculo.getNumeroPuertas());
        ps.setFloat(4, vehiculo.getPrecio());
        ps.setString(5, vehiculo.getFechaEntrada());
        ps.setBoolean(6, vehiculo.isVendido());
        ps.executeUpdate();
    }
}