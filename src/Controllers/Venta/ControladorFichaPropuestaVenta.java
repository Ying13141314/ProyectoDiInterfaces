package Controllers.Venta;

import Controllers.AbstractControlador;
import DAO.ClientesDAO;
import DAO.VentaPropuestaDAO;
import Models.Cliente;
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

public class ControladorFichaPropuestaVenta extends AbstractControlador {

    @FXML
    private Button buttonCancelar,buttonActualizar,buttonBorrar;
    @FXML
    private ChoiceBox cbEstado;
    @FXML
    private TextField tfNombre,tfApellidos,tfDNI,tfDireccion;
    @FXML
    private TextArea taPropuesta;
    @FXML
    private DatePicker dpFechaNacimiento;
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
        estado.add("hombre");
        estado.add("mujer");
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
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";

        } else if (e.getSource().equals(buttonActualizar)){
            actualizarDatos();
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";

        }else if (e.getSource().equals(buttonBorrar)){
            borrarDatos();
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        AbstractControlador co = pane.getController();
        co.setMiApp(miApp);

    }

    /**
     * Método para obtener los datos que tenemos.
     */
    public void obtenerDatosPropuestaVenta(){
        propuestaVenta = new HashMap<>();
        propuestaVenta.put("Nombre", tfNombre.getText());
        propuestaVenta.put("Apellidos", tfApellidos.getText());
        propuestaVenta.put("DNI", tfDNI.getText());
        propuestaVenta.put("Fecha", dpFechaNacimiento.getValue().toString());
        propuestaVenta.put("Direccion", tfDireccion.getText());
        propuestaVenta.put("Estados", cbEstado.getSelectionModel().getSelectedItem().toString());
        propuestaVenta.put("propuesta", taPropuesta.getText());

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
        tfApellidos.setText(miPropuesta.getPrimerApellido());
        tfDNI.setText(miPropuesta.getDni());
        dpFechaNacimiento.setValue(LocalDate.parse(miPropuesta.getFechaNac()));
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
