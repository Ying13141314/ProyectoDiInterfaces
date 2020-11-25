package Controllers.Venta;

import DAO.VentaPropuestaDAO;
import Models.PropuestaVenta;
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
    TextField tfNombre;
    @FXML
    TextField tfPrimerApellido;
    @FXML
    TextField tfSegundoApellido;
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
    Button buttonCancelar;
    @FXML
    Button buttonAceptar;

    /**
     * Controlador Vacio
     */
    public ControladorVentaPropuesta() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){cargarDesplegables();}

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
            HashMap<String,Object> propuesta = new HashMap<>();
            propuesta.put("nombreCliente", tfNombre.getText());
            propuesta.put("primerApellido", tfPrimerApellido.getText());
            propuesta.put("segundoApellido", tfSegundoApellido.getText());
            propuesta.put("dni", tfDNI.getText());
            propuesta.put("estado", cbEstado.getSelectionModel().getSelectedItem().toString());
            propuesta.put("fechaNac", dpFechaNacimiento.getValue().toString());
            propuesta.put("FechaLimite", dpFechaLimite.getValue().toString());
            propuesta.put("direccion", tfDireccion.getText());
            propuesta.put("propuesta", taPropuesta.getText());
            propuesta.put("fechaLimiteAcep", dpFechaLimite.getValue().toString());

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
    public void cargarDesplegables(){
        ArrayList<String> estado = new ArrayList<>();
        estado.add("aceptada");
        estado.add("rechazada");
        estado.add("pendiente");
        ObservableList<String> list = FXCollections.observableArrayList(estado);
        cbEstado.setItems(list);
    }
}