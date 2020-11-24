package Controllers.Venta;

import Controllers.AbstractControladorVenta;
import DAO.VentaPropuestaDAO;
import Models.PropuestaVenta;
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

public class ControladorFichaPropuestaVenta extends AbstractControladorVenta {

    @FXML
    private Button buttonCancelar,buttonActualizar,buttonBorrar;
    @FXML
    private ChoiceBox<String> cbEstado;
    @FXML
    private TextField tfNombre,tfPrimerApellido,tfSegundoApellido,tfDNI,tfDireccion;
    @FXML
    private TextArea taPropuesta;
    @FXML
    private DatePicker dpFechaNacimiento,dpFechaLimite;
    private PropuestaVenta miPropuesta;
    private HashMap<String,Object> propuestaVenta;


    /**
     * Controlador vacio
     */
    public ControladorFichaPropuestaVenta() {
    }

    /**
     * Para inicial cuando el xml ya esta ejecutando.
     */
    @FXML
    public void initialize(){
        //inicialzo estos dos ChoiceBox para poder mostrar esos datos.
        ArrayList<String> estado = new ArrayList<>();
        estado.add("aceptada");
        estado.add("rechazada");
        estado.add("pendiente");
        ObservableList<String> list = FXCollections.observableArrayList(estado);
        cbEstado.setItems(list);

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
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";

        }else if (e.getSource().equals(buttonBorrar)){
            borrarDatos();
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        AbstractControladorVenta co = pane.getController();
        co.setMiApp(miApp);

    }

    /**
     * Método para obtener los datos que tenemos.
     */
    public void obtenerDatosPropuestaVenta(){
        propuestaVenta = new HashMap<>();
        propuestaVenta.put("Nombre", tfNombre.getText());
        propuestaVenta.put("primerApellidos", tfPrimerApellido.getText());
        propuestaVenta.put("segundoApellidos", tfSegundoApellido.getText());
        propuestaVenta.put("DNI", tfDNI.getText());
        propuestaVenta.put("Fecha", dpFechaNacimiento.getValue().toString());
        propuestaVenta.put("Direccion", tfDireccion.getText());
        propuestaVenta.put("estado", cbEstado.getSelectionModel().getSelectedItem().toString());
        propuestaVenta.put("propuesta", taPropuesta.getText());
        propuestaVenta.put("FechaLimi",dpFechaLimite.getValue().toString());

    }

    /**
     * Método que actualiza los datos del cliente si modificamos los campos.
     * @throws SQLException
     */
    private void actualizarDatos() throws SQLException {
        obtenerDatosPropuestaVenta();
        PropuestaVenta miPropuesta = new PropuestaVenta(propuestaVenta);
        VentaPropuestaDAO miVentasPropuestaDao = new VentaPropuestaDAO();
        miVentasPropuestaDao.actualizarDatos(miPropuesta);
    }

    /**
     * Método para borrar los propuesta.
     * @throws SQLException
     */
    private void borrarDatos() throws SQLException {
        obtenerDatosPropuestaVenta();
        PropuestaVenta miPropuesta = new PropuestaVenta(propuestaVenta);
        VentaPropuestaDAO miVentasPropuestaDao = new VentaPropuestaDAO();
        miVentasPropuestaDao.actualizarDatos(miPropuesta);

    }

    /**
     * Método que me muestra por pantalla los datos del propuesta.
     * @param miPropuesta
     */
    public void mostrarDatosPropuesta(PropuestaVenta miPropuesta){
        tfNombre.setText(miPropuesta.getNombreCliente());
        tfPrimerApellido.setText(miPropuesta.getPrimerApellido());
        tfSegundoApellido.setText(miPropuesta.getSegundoApellido());
        tfDNI.setText(miPropuesta.getDni());
        dpFechaNacimiento.setValue(LocalDate.parse(miPropuesta.getFechaNac()));
        dpFechaLimite.setValue(LocalDate.parse(miPropuesta.getFechaLimiteAcep()));
        tfDireccion.setText(miPropuesta.getDireccion());
        cbEstado.setValue(miPropuesta.getEstado());
        taPropuesta.setText(miPropuesta.getPropuesta());
    }

    /**
     * Setter del Propuesta de cliente.
     * @param miPropuesta
     */
    public void setPropuesta(PropuestaVenta miPropuesta){
        this.miPropuesta = miPropuesta;
    }
}
