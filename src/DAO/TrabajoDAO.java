package DAO;


import Models.PropuestaVenta;
import Models.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrabajoDAO extends AbstractDAO{

    /**
     * Método para insertar propuesta de venta al bbdd.
     * @param trabajo
     * @param textError
     */
    public void darAltaReparacion(Trabajo trabajo, Text textError){

        String SSQL = "INSERT INTO reparaciones (idCliente,idMecanico,componente,fechaIni,fechaFin,presupuesto)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
                textError.setText("");
                PreparedStatement pt = conexion.prepareStatement(SSQL);
                pt.setInt(1, trabajo.getCliente());
                pt.setInt(2, trabajo.getIdMecanico());
                pt.setString(3,trabajo.getPiezas());
                pt.setString(4, trabajo.getFechaEntrada());
                pt.setString(5, trabajo.getFechaSalida());
                pt.setFloat(6,trabajo.getPresupuesto());

                pt.executeUpdate();
                System.out.println("Terminado");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.cerrarBasesDatos();
    }
}
