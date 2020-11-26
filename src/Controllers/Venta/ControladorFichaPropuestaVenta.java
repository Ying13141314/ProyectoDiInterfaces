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
    private TextField tfNombre,tfPrimerApellido,tfDNI,tfDireccion;
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
    public void obtenerDatosPropuestaVenta() throws SQLException {
        propuestaVenta = new HashMap<>();
        propuestaVenta.put("nombreCliente", tfNombre.getText());
        propuestaVenta.put("primerApellido", tfPrimerApellido.getText());
        propuestaVenta.put("dni", tfDNI.getText());
        propuestaVenta.put("fechaNac", dpFechaNacimiento.getValue().toString());
        propuestaVenta.put("direccion", tfDireccion.getText());
        propuestaVenta.put("estado", cbEstado.getSelectionModel().getSelectedItem());
        propuestaVenta.put("propuesta", taPropuesta.getText());
        propuestaVenta.put("FechaLimi",dpFechaLimite.getValue().toString());
        //miPropuesta = new PropuestaVenta(propuestaVenta);
    }

    private void actualizar()  {
        miPropuesta.setNombreCliente(tfNombre.getText());
        miPropuesta.setPrimerApellido(tfPrimerApellido.getText());
        miPropuesta.setDni(tfDNI.getText());
        miPropuesta.setFechaNac( dpFechaNacimiento.getValue().toString());
        miPropuesta.setDireccion(tfDireccion.getText());
        miPropuesta.setEstado(cbEstado.getSelectionModel().getSelectedItem());
        miPropuesta.setPropuesta(taPropuesta.getText());
        miPropuesta.setFechaLimiteAcep(dpFechaLimite.getValue().toString());
    }

    /**
     * Método que actualiza los datos del cliente si modificamos los campos.
     */
    private void actualizarDatos()  {
        actualizar();
        VentaPropuestaDAO miVentasPropuestaDao = new VentaPropuestaDAO();
        miVentasPropuestaDao.actualizarDatos(miPropuesta);
    }

    /**
     * Método para borrar los propuesta.
     * @throws SQLException
     */
    private void borrarDatos() throws SQLException {
        VentaPropuestaDAO miVentasPropuestaDao = new VentaPropuestaDAO();
        miVentasPropuestaDao.borrarPropuesta(miPropuesta);

    }

    /**
     * Método que me muestra por pantalla los datos del propuesta.
     * @param
     */
    public void mostrarDatosPropuesta(){
        tfNombre.setText(miPropuesta.getNombreCliente());
        tfPrimerApellido.setText(miPropuesta.getPrimerApellido());
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
        mostrarDatosPropuesta();

    }
}
