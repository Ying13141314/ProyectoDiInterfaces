package Controllers.Venta;

import Controllers.AbstractControladorVenta;
import DAO.ClientesDAO;
import DAO.VehiculoDAO;
import DAO.VentaPropuestaDAO;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la Controladora de la Vista AltaCliente, permite dar de alta a un nuevo cliente si no existe ya uno
 * con el mismo DNI.
 */

public class ControladorVentaPropuesta extends AbstractControladorVenta {

    @FXML
    Pane panel;
    @FXML
    Text textError;
    @FXML
    TextField tfNombrePropuesta;
    @FXML
    TextField tfPrimerApellido;
    @FXML
    TextField tfDNI;
    @FXML
    TextField tfDireccion;
    @FXML
    TextArea taPropuesta;
    @FXML
    DatePicker dpFechaNacimiento;
    @FXML
    DatePicker dpFechaLimite;
    @FXML
    ChoiceBox cbEstado;
    @FXML
    ChoiceBox <Vehiculo> cbVehiculo;
    @FXML
    Button buttonCancelar;
    @FXML
    Button buttonAceptar;

    private Cliente miCliente;

    private Vehiculo miVehiculo;

    private Venta miVenta;

    /**
     * Controlador Vacio
     */
    public ControladorVentaPropuesta() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize() throws SQLException {
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
            HashMap<String,String> propuesta = new HashMap<>();
            propuesta.put("nombreCliente", tfNombrePropuesta.getText());
            propuesta.put("primerApellido", tfPrimerApellido.getText());
            propuesta.put("dni", tfDNI.getText());
            propuesta.put("estado", cbEstado.getSelectionModel().getSelectedItem().toString());
            propuesta.put("fechaNac", dpFechaNacimiento.getValue().toString());
            propuesta.put("FechaLimite", dpFechaLimite.getValue().toString());
            propuesta.put("direccion", tfDireccion.getText());
            propuesta.put("propuesta", taPropuesta.getText());
            propuesta.put("idCliente",miCliente.getIdCliente().toString());
            propuesta.put("idVehiculo",cbVehiculo.getValue().getIdVehiculo().toString());
            propuesta.put("idVendedor",miApp.getMiUsuario().getId());

            PropuestaVenta p = new PropuestaVenta(propuesta);
            VentaPropuestaDAO vpdao = new VentaPropuestaDAO();
            vpdao.darAltaPropuesta(p,textError);

            ruta = "/View/Venta/Venta.fxml";
        }
        if(!textError.getText().equals("El DNI que has introducido ya existe.") || e.getSource().equals(buttonCancelar)) {
            FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
            miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

            AbstractControladorVenta co = pane.getController();
            co.setMiApp(miApp);
        }
    }

    public void mostrarDatosCliente(){
        tfNombrePropuesta.setText(miCliente.getNombre());
        tfPrimerApellido.setText(miCliente.getApellido());
        tfDNI.setText(miCliente.getDni());
        dpFechaNacimiento.setValue(LocalDate.parse(miCliente.getFechaNac()));
        tfDireccion.setText(miCliente.getDireccion());
    }

    public void cargarDesplegables() throws SQLException {
        ArrayList<String> estado = new ArrayList<>();
        estado.add("aceptada");
        estado.add("rechazada");
        estado.add("pendiente");
        ObservableList<String> list = FXCollections.observableArrayList(estado);
        cbEstado.setItems(list);

        VehiculoDAO miVehiculo = new VehiculoDAO();
        ObservableList<Vehiculo> datosVehiculo = miVehiculo.anadirVehiculoLista();
        cbVehiculo.setItems(datosVehiculo);
        cbVehiculo.toString();
    }


    public void setCliente(Cliente miCliente){
        this.miCliente = miCliente;
        mostrarDatosCliente();

    }
    public void setVehiculo(Vehiculo miVehiculo){
        this.miVehiculo = miVehiculo;
    }

    public void setVenta(Venta miVenta){
        this.miVenta = miVenta;
    }
}