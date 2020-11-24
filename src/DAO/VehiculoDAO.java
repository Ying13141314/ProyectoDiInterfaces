package DAO;

import Models.AbstractUsuario;
import Models.Cliente;
import Models.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO extends AbstractDAO {

    public void crearVehiculo(Vehiculo vehiculo) throws SQLException {

        super.conectar();

        //
        String query = "INSERT INTO vehiculo (marca,modelo,numeroPuertas,precio,fechaEntrada,fechaSalida,numeroRuedas,numeroBastidor,tipoVehiculo,vendido,idConcesionarios) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        if(!comprobarVehiculo(vehiculo.getNumeroBastidor())){
            PreparedStatement ps = conexion.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getNumeroPuertas());
            ps.setFloat(4, vehiculo.getPrecio());
            ps.setString(5, vehiculo.getFechaEntrada());
            ps.setString(6, vehiculo.getFechaSalida());
            ps.setInt(7, vehiculo.getNumeroRuedas());
            ps.setString(8, vehiculo.getNumeroBastidor());
            ps.setString(9, vehiculo.getTipoVehiculo());
            ps.setBoolean(10, vehiculo.isVendido());
            ps.setInt(11, vehiculo.getIdConsecionario());
            ps.executeUpdate();
        }

    }

    /**
     * Método donde actualiza los datos que me muestra por la pantalla.
     * @param miVehiculo
     */
    public void actualizarDatos(Vehiculo miVehiculo) {

        String SQL = "UPDATE vehiculo SET marca=?,modelo=?,numeroPuertas=?,fechaEntrada=?,fechaSalida=?,precio=?,numeroRuedas=?,tipoVehiculo=? where numeroBastidor =?";

        try{
            PreparedStatement ps = conexion.prepareStatement(SQL);
            ps.setString(1, miVehiculo.getMarca());
            ps.setString(2, miVehiculo.getModelo());
            ps.setInt(3, miVehiculo.getNumeroPuertas());
            ps.setString(4, miVehiculo.getFechaEntrada());
            ps.setString(5, miVehiculo.getFechaSalida());
            ps.setFloat(6, miVehiculo.getPrecio());
            ps.setInt(7, miVehiculo.getNumeroRuedas());
            ps.setString(9, miVehiculo.getTipoVehiculo());
            ps.executeUpdate();

            System.out.println("Actualizado");

        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //super.cerrarBasesDatos();
    }

    /**
     * Método que comprueba el número de bastidor ante de insertar un vehiculo.
     * @param miBastidor
     * @return
     * @throws SQLException
     */
    private boolean comprobarVehiculo(String miBastidor) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("select numeroBastidor from vehiculo where numeroBastidor=?");
        ps.setString(1, miBastidor);
        ResultSet rs = ps.executeQuery();
        return rs.isBeforeFirst();
    }

    public void borrarVehiculo(Vehiculo miVehiculo) throws SQLException {
        String sql = "delete from vehiculo where numeroBastidor = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, miVehiculo.getNumeroBastidor());
        ps.executeUpdate();
    }

    public ObservableList<Vehiculo> anadirVehiculoLista() throws SQLException {
        super.conectar();
        ObservableList<Vehiculo> datosVehiculo = FXCollections.observableArrayList();
        PreparedStatement ps = conexion.prepareStatement("select * from vehiculo");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            datosVehiculo.add(new Vehiculo(rs));
        }
        return datosVehiculo;
    }
}