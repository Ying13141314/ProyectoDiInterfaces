package Controllers.Venta;

import Controllers.AbstractControlador;

import DAO.VehiculoDAO;

import Models.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la Controladora de la Vista VentaVehiculo, permite dar de alta a un nuevo vehiculo.
 */

public class ControladorVentaAltaVehiculo extends AbstractControlador {

    @FXML
    private TextField tfNumeroBastidor,tfMarca,tfModelo,tfPrecio,tfNumeroPuertas,tfNumeroRuedas, tfConcesionario;
    @FXML
    private DatePicker dpFechaEntrada,dpFechaSalida;
    @FXML
    private ChoiceBox cbTipoVehiculo, cbEstado;
    @FXML
    Button buttonCancelar;
    @FXML
    Button buttonAceptar;

    @FXML
    public void initialize(){
        cargarDesplegables();
    }

    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException, SQLException {
        String ruta = "";
        if(e.getSource().equals(buttonCancelar)) {
            ruta = "/View/Venta/Venta.fxml";

        } else if (e.getSource().equals(buttonAceptar)){
            HashMap<String,Object> vehiculo = new HashMap<>();
            vehiculo.put("Marca", tfMarca.getText());
            vehiculo.put("Modelo", tfModelo.getText());
            vehiculo.put("Precio", tfPrecio.getText());
            vehiculo.put("NumeroPuertas", tfNumeroPuertas.getText());
            vehiculo.put("NumeroRuedas",tfNumeroRuedas.getText());
            vehiculo.put("NumeroBastidor",tfNumeroBastidor.getText());
            vehiculo.put("TipoVehiculo", cbTipoVehiculo.getSelectionModel().getSelectedItem().toString());
            vehiculo.put("IdConcesionario",tfConcesionario.getText());
            vehiculo.put("FechaEntrada", dpFechaEntrada.getValue().toString());
            vehiculo.put("Estado", cbEstado.getSelectionModel().getSelectedItem().toString());
            vehiculo.put("FechaSalida", dpFechaSalida.getValue().toString());


            Vehiculo v = new Vehiculo(vehiculo);
            VehiculoDAO vdao = new VehiculoDAO();
            vdao.crearVehiculo(v);

            ruta = "/View/Venta/Venta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        ControladorVenta co = pane.getController();
        co.setMiApp(miApp);
    }

    /**
     * Método que rellena los desplegables ChoiceBox.
     */
    public void cargarDesplegables(){
        ArrayList<String> tipoVehiculos = new ArrayList<>();
        tipoVehiculos.add("coches");
        tipoVehiculos.add("motocicleta");
        tipoVehiculos.add("ciclomotor");
        ObservableList<String> list = FXCollections.observableArrayList(tipoVehiculos);
        cbTipoVehiculo.setItems(list);

        ArrayList<String> tipoEstado = new ArrayList<>();
        tipoEstado.add("Vendido");
        tipoEstado.add("En venta");
        ObservableList<String> list2 = FXCollections.observableArrayList(tipoEstado);
        cbEstado.setItems(list2);
    }
}