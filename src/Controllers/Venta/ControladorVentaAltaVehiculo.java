package Controllers.Venta;


import Controllers.AbstractControladorVenta;
import DAO.VehiculoDAO;

import Models.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la Controladora de la Vista VentaVehiculo, permite dar de alta a un nuevo vehiculo.
 */

public class ControladorVentaAltaVehiculo extends AbstractControladorVenta {

    @FXML
    private TextField tfNumeroBastidor,tfMarca,tfModelo,tfPrecio,tfNumeroPuertas,tfNumeroRuedas,tfConcesionario,tfAno,tfKilometro;
    @FXML
    public Text lbConcesionarios;

    @FXML
    private DatePicker dpFechaEntrada,dpFechaSalida;
    @FXML
    private ChoiceBox cbTipoVehiculo, cbEstado,cbCombustible;
    @FXML
    Button buttonCancelar;
    @FXML
    Button buttonAceptar;

    @FXML
    public void initialize() {
        tfConcesionario.setVisible(false);
        lbConcesionarios.setVisible(false);
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
            vehiculo.put("IdConcesionario","8");
            vehiculo.put("FechaEntrada", dpFechaEntrada.getValue().toString());
            vehiculo.put("Estado", cbEstado.getSelectionModel().getSelectedItem().toString());
            vehiculo.put("FechaSalida", dpFechaSalida.getValue().toString());
            vehiculo.put("ano",tfAno.getText());
            vehiculo.put("combustible",cbCombustible.getSelectionModel().getSelectedItem().toString());
            vehiculo.put("kilometros",tfKilometro.getText());


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

        ArrayList<String> combustible = new ArrayList<>();
        combustible.add("diesel");
        combustible.add("gasolina");
        combustible.add("hibrido");
        combustible.add("electrico");
        ObservableList<String> list3 = FXCollections.observableArrayList(combustible);
        cbCombustible.setItems(list3);

        /*VehiculoDAO miVehiculo = new VehiculoDAO();
        ObservableList<Vehiculo> datosVehiculo = miVehiculo.anadirVehiculoLista();
        cbConcesionario.setItems(datosVehiculo);*/
    }
}