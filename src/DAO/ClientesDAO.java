package DAO;
import Models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesDAO extends AbstractDAO {

    public void darAltaCliente(Cliente miCliente, Text textError){

        String SSQL = "INSERT INTO cliente (nombre,apellido,dni,fechaNac,direccion,sexo,correo,telefono,tipoComunicacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?)";
        try {
            if(!comprobarDni(miCliente.getDni())){
                textError.setText("");
                PreparedStatement pt = conexion.prepareStatement(SSQL);
                pt.setString(1, miCliente.getNombre());
                pt.setString(2, miCliente.getApellido());
                pt.setString(3, miCliente.getDni());
                pt.setString(4, miCliente.getFechaNac());
                pt.setString(5,miCliente.getDireccion());
                pt.setString(6,miCliente.getSexo());
                pt.setString(7,miCliente.getCorreo());
                pt.setString(8,miCliente.getTelefono());
                pt.setString(9,miCliente.getTipoComunicacion());

                pt.executeUpdate();
                System.out.println("Terminado");
            } else {
                textError.setText("El DNI que has introducido ya existe.");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.cerrarBasesDatos();
    }

    /**
     * Método donde actualiza los datos que me muestra por la pantalla.
     * @param miCliente
     */
    public void actualizarDatos(Cliente miCliente) {

        String SQL = "UPDATE cliente SET Nombre=?,apellido=?,fechaNac=?,direccion=?,sexo=?,correo=?,telefono=?,tipoComunicacion=? where dni =?";

        try{
            PreparedStatement ps = conexion.prepareStatement(SQL);
            ps.setString(1, miCliente.getNombre());
            ps.setString(2, miCliente.getApellido());
            ps.setString(3, (miCliente.getFechaNac()));
            ps.setString(4, miCliente.getDireccion());
            ps.setString(5, miCliente.getSexo());
            ps.setString(6, miCliente.getCorreo());
            ps.setString(7, miCliente.getTelefono());
            ps.setString(8, miCliente.getTipoComunicacion());
            ps.setString(9, miCliente.getDni());
            ps.executeUpdate();

            System.out.println("Actualizado");

        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //super.cerrarBasesDatos();
    }

    public void borrarCliente(Cliente miCliente) throws SQLException {
        String sql = "delete from cliente where dni = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, miCliente.getDni());
        ps.executeUpdate();
    }

    /**
     * Método que comprueba el DNI ante de insertar un cliente.
     * @param miDni
     * @return
     * @throws SQLException
     */
    private boolean comprobarDni(String miDni) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("select dni from cliente where dni=?");
        ps.setString(1, miDni);
        ResultSet rs = ps.executeQuery();
        return rs.isBeforeFirst();
    }

    public ObservableList<Cliente> anadirClienteLista() throws SQLException {
        super.conectar();
        ObservableList<Cliente> datosCliente = FXCollections.observableArrayList();
        PreparedStatement ps = conexion.prepareStatement("select * from cliente");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            datosCliente.add(new Cliente(rs));
        }
        return datosCliente;
    }

}
