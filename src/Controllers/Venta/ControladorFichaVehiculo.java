package Controllers.Venta;


import Controllers.AbstractControladorVenta;
import DAO.VehiculoDAO;

import Models.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Esta clase es la Controladora de la Vista FichaVehiculo, muestra la información de un vehiculo seleccionado a traves
 * de la Vista ListadoVehiculo (Se realiza en el Sprint 3).
 */

public class ControladorFichaVehiculo extends AbstractControladorVenta {

    @FXML
    private Button buttonCancelar,buttonBorrar,buttonActualizar;
    @FXML
    private TextField tfBastidor,tfMarca,tfModelo,tfPrecio,tfNumeroPuertas,tfNumeroRuedas;
    @FXML
    private DatePicker dpFechaEntrada,dpFechaSalida;
    @FXML
    private ChoiceBox cbTipoVehiculo;
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
            ruta = "/View/Venta/Venta.fxml";
        } else if (e.getSource().equals(buttonBorrar)){
            borrarDatos();
            ruta = "/View/Venta/BusquedaListadoVehiculo.fxml.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        ControladorVenta co = pane.getController();
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
        vehiculo.put("numeroBastidor", tfBastidor.getText());


    }

    /**
     * Método que actualiza los datos del cliente si modificamos los campos.
     * @throws SQLException
     */
    private void actualizarDatos() throws SQLException {
        obtenerDatosVehiculo();
        Vehiculo miVehiculo = new Vehiculo(vehiculo);
        VehiculoDAO miVehiculoDao = new VehiculoDAO();
        miVehiculoDao.actualizarDatos(miVehiculo);
    }

    /**
     * Método para borrar los cliente.
     * @throws SQLException
     */
    private void borrarDatos() throws SQLException {
        obtenerDatosVehiculo();
        Vehiculo miVehiculo = new Vehiculo(vehiculo);
        VehiculoDAO miVehiculoDao = new VehiculoDAO();
        miVehiculoDao.borrarVehiculo(miVehiculo);

    }

    /**
     * Método que me muestra por pantalla los datos del cliente.
     * @param miVehiculo
     */
    public void mostrarDatosVehiculo(Vehiculo miVehiculo){

        tfMarca.setText(miVehiculo.getMarca());
        tfModelo.setText(miVehiculo.getModelo());
        if (miVehiculo.getNumeroBastidor()!=null){
            tfBastidor.setText(miVehiculo.getNumeroBastidor());
        }else{
            tfBastidor.setText("no existe");
        }
        tfNumeroRuedas.setText(miVehiculo.getNumeroRuedas().toString());
        tfPrecio.setText(miVehiculo.getPrecio().toString());
        tfNumeroPuertas.setText(miVehiculo.getNumeroPuertas().toString());
        cbTipoVehiculo.setValue(miVehiculo.getTipoVehiculo());
        dpFechaSalida.setValue(LocalDate.parse(miVehiculo.getFechaSalida()));
        dpFechaEntrada.setValue(LocalDate.parse(miVehiculo.getFechaEntrada()));

    }

    /**
     * Setter del vehiculo.
     * @param miVehiculo
     */
    public void setVehiculo(Vehiculo miVehiculo){
        this.miVehiculo = miVehiculo;
    }
}
