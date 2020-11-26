package DAO;


import Models.PropuestaVenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaPropuestaDAO extends AbstractDAO{

    /**
     * Método para insertar propuesta de venta al bbdd.
     * @param miVenta
     * @param textError
     */
    public void darAltaPropuesta(PropuestaVenta miVenta, Text textError){

        String SSQL = "INSERT INTO ventas (nombreCliente,primerApellido,dni,fechaNacimiento,direccion,propuesta,estado,fechaLimiteAceptación,idVehiculo,idCliente,idVendedor)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if(!comprobarDni(miVenta.getDni())){
                textError.setText("");
                PreparedStatement pt = conexion.prepareStatement(SSQL);
                pt.setString(1, miVenta.getNombreCliente());
                pt.setString(2, miVenta.getPrimerApellido());
                pt.setString(3, miVenta.getDni());
                pt.setString(4, miVenta.getFechaNac());
                pt.setString(5,miVenta.getDireccion());
                pt.setString(6,miVenta.getPropuesta());
                pt.setString(7,miVenta.getEstado());
                pt.setString(8,miVenta.getFechaLimiteAcep());
                pt.setInt(9,miVenta.getIdVehiculo());
                pt.setInt(10,miVenta.getIdCliente());
                pt.setInt(11,miVenta.getIdVendedor());


                pt.executeUpdate();
                System.out.println("Terminado");
            } else {
                textError.setText("El DNI que has introducido ya existe.");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Método donde actualiza los datos que me muestra por la pantalla.
     * @param miVenta
     */
    public void actualizarDatos(PropuestaVenta miVenta) {

        String SQL = "UPDATE ventas SET nombreCliente=?,primerApellido=?,fechaNacimiento=?,direccion=?,propuesta=?,estado=?,fechaLimiteAceptación=? where dni =?";


        try{
            PreparedStatement ps = conexion.prepareStatement(SQL);
            ps.setString(1, miVenta.getNombreCliente());
            ps.setString(2, miVenta.getPrimerApellido());
            ps.setString(3, miVenta.getFechaNac());
            ps.setString(4, miVenta.getDireccion());
            ps.setString(5, miVenta.getPropuesta());
            ps.setString(6, miVenta.getEstado());
            ps.setString(7, miVenta.getFechaLimiteAcep());
            ps.setString(8, miVenta.getDni());

            ps.executeUpdate();

            System.out.println("Actualizado");

        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //super.cerrarBasesDatos();
    }

    public void borrarPropuesta(PropuestaVenta miVenta) throws SQLException {
        String sql = "delete from ventas where dni = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, miVenta.getDni());
        ps.executeUpdate();
    }

    /**
     * Método que comprueba el DNI ante de insertar un cliente.
     * @param miDni
     * @return
     * @throws SQLException
     */
    private boolean comprobarDni(String miDni) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("select dni from ventas where dni=?");
        ps.setString(1, miDni);
        ResultSet rs = ps.executeQuery();
        return rs.isBeforeFirst();
    }

    public ObservableList<PropuestaVenta> anadirVentasLista() throws SQLException {
        super.conectar();
        ObservableList<PropuestaVenta> datosVentas = FXCollections.observableArrayList();
        PreparedStatement ps = conexion.prepareStatement("select * from ventas");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            datosVentas.add(new PropuestaVenta(rs));
        }
        return datosVentas;
    }
}
