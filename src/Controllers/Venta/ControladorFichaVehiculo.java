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


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la Controladora de la Vista FichaVehiculo, muestra la información de un vehiculo seleccionado a traves
 * de la Vista ListadoVehiculo (Se realiza en el Sprint 3).
 */

public class ControladorFichaVehiculo extends AbstractControladorVenta {

    @FXML
    private Button buttonCancelar,buttonBorrar,buttonActualizar;
    @FXML
    private TextField tfNumeroBastidor,tfMarca,tfModelo,tfPrecio,tfNumeroPuertas,tfNumeroRuedas,tfConcesionario;
    @FXML
    private DatePicker dpFechaEntrada,dpFechaSalida;
    @FXML
    private ChoiceBox<String> cbTipoVehiculo,cbEstado;
    private Vehiculo miVehiculo;
    private HashMap<String,Object> vehiculo;

    /**
     * Controlador Vacio
     */
    public ControladorFichaVehiculo() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){
        ArrayList<String> tipoVehiculos = new ArrayList<>();
        tipoVehiculos.add("coches");
        tipoVehiculos.add("motocicleta");
        tipoVehiculos.add("ciclomotor");
        ObservableList<String> list = FXCollections.observableArrayList(tipoVehiculos);
        cbTipoVehiculo.setItems(list);

        ArrayList<String> tipoEstado = new ArrayList<>();
        tipoEstado.add("vendido");
        tipoEstado.add("en venta");
        ObservableList<String> list2 = FXCollections.observableArrayList(tipoEstado);
        cbEstado.setItems(list2);
    }

    /**
     * Métdodo que depentiendo del botón realiza una función u otro.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException, SQLException {
        String ruta = "";
        if(e.getSource().equals(buttonCancelar)) {
            ruta = "/View/Venta/Venta.fxml";
        } else if (e.getSource().equals(buttonActualizar)){
            actualizarDatos();
            ruta = "/View/Venta/BusquedaListadoVehiculo.fxml";
        } else if (e.getSource().equals(buttonBorrar)){
            borrarDatos();
            ruta = "/View/Venta/BusquedaListadoVehiculo.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        AbstractControladorVenta co = pane.getController();
        co.setMiApp(miApp);
    }

    /**
     * Método para obtener los datos que tenemos.
     */
    public void obtenerDatosVehiculo(){
        vehiculo = new HashMap<>();
        vehiculo.put("Marca", tfMarca.getText());
        vehiculo.put("Modelo", tfModelo.getText());
        vehiculo.put("NumeroPuertas", tfNumeroPuertas.getText());
        vehiculo.put("FechaEntrada", dpFechaEntrada.getValue().toString());
        vehiculo.put("Precio", tfPrecio.getText());
        vehiculo.put("numeroRuedas", tfNumeroRuedas.toString());
        vehiculo.put("fechaSalida", dpFechaSalida.getValue().toString());
        vehiculo.put("numeroBastidor", tfNumeroBastidor.getText());
        vehiculo.put("Estado",cbEstado.getSelectionModel().getSelectedItem());
        vehiculo.put("TipoVehiculo",cbTipoVehiculo.getSelectionModel().getSelectedItem());
        vehiculo.put("idConcesionario",tfConcesionario.getText());

    }

    private void actualizar(){
        miVehiculo.setMarca(tfMarca.getText());
        miVehiculo.setModelo(tfModelo.getText());
        miVehiculo.setNumeroPuertas(Integer.parseInt(tfNumeroPuertas.getText()));
        miVehiculo.setFechaEntrada(dpFechaEntrada.getValue().toString());
        miVehiculo.setPrecio(Float.parseFloat(tfPrecio.getText()));
        miVehiculo.setNumeroRuedas(Integer.parseInt(tfNumeroRuedas.getText()));
        miVehiculo.setFechaSalida(dpFechaSalida.getValue().toString());
        miVehiculo.setNumeroBastidor(tfNumeroBastidor.getText());
        miVehiculo.setVendido(cbEstado.getSelectionModel().getSelectedItem());
        miVehiculo.setTipoVehiculo(cbTipoVehiculo.getSelectionModel().getSelectedItem());
        miVehiculo.setIdConsecionario(tfConcesionario.getLength());
    }

    /**
     * Método que actualiza los datos del cliente si modificamos los campos.
     * @throws SQLException
     */
    private void actualizarDatos() throws SQLException {
        actualizar();
        VehiculoDAO miVehiculoDao = new VehiculoDAO();
        miVehiculoDao.actualizarDatos(miVehiculo);
    }

    /**
     * Método para borrar los cliente.
     * @throws SQLException
     */
    private void borrarDatos() throws SQLException {
        VehiculoDAO miVehiculoDao = new VehiculoDAO();
        miVehiculoDao.borrarVehiculo(miVehiculo);

    }

    /**
     * Método que me muestra por pantalla los datos del vehiculo.
     * @param
     */
    public void mostrarDatosVehiculo(){

        tfMarca.setText(miVehiculo.getMarca());
        tfModelo.setText(miVehiculo.getModelo());
        if (miVehiculo.getNumeroBastidor()!=null){
            tfNumeroBastidor.setText(miVehiculo.getNumeroBastidor());
        }else{
            tfNumeroBastidor.setText("no existe");
        }
        tfNumeroRuedas.setText(miVehiculo.getNumeroRuedas().toString());
        tfPrecio.setText(miVehiculo.getPrecio().toString());
        tfNumeroPuertas.setText(miVehiculo.getNumeroPuertas().toString());
        cbTipoVehiculo.setValue(miVehiculo.getTipoVehiculo());
        if (miVehiculo.getFechaSalida()!=null){
            dpFechaSalida.setValue(LocalDate.parse(miVehiculo.getFechaSalida()));
        }

        dpFechaEntrada.setValue(LocalDate.parse(miVehiculo.getFechaEntrada()));
        cbTipoVehiculo.setValue(miVehiculo.getTipoVehiculo());
        tfConcesionario.setText(miVehiculo.getIdConsecionario().toString());
        cbEstado.setValue(miVehiculo.getVendido());
    }

    /**
     * Setter del vehiculo.
     * @param miVehiculo
     */
    public void setVehiculo(Vehiculo miVehiculo){
        this.miVehiculo = miVehiculo;
        mostrarDatosVehiculo();
    }
}
