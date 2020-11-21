package Controllers.Venta;

import Controllers.AbstractControlador;
import DAO.ClientesDAO;
import Models.Cliente;
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

public class ControladorVentaPropuesta extends AbstractControlador {

    @FXML
    Pane panel;
    @FXML
    Text textError;
    @FXML
    TextField tfNombre;
    @FXML
    TextField tfApellidos;
    @FXML
    TextField tfDNI;
    @FXML
    TextField tfDireccion;
    @FXML
    TextField tfEmail;
    @FXML
    DatePicker dpFechaNacimiento;
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
    public void initialize(){}

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
            HashMap<String,String> cliente = new HashMap<>();
            cliente.put("Nombre", tfNombre.getText());
            cliente.put("Apellidos", tfApellidos.getText());
            cliente.put("DNI", tfDNI.getText());
            cliente.put("Fecha", dpFechaNacimiento.getValue().toString());
            cliente.put("Direccion", tfDireccion.getText());
            cliente.put("Email", tfEmail.getText());

            ruta = "/View/Venta/BusquedaListadoClientes.fxml";
        }
        if(!textError.getText().equals("El DNI que has introducido ya existe.") || e.getSource().equals(buttonCancelar)) {
            FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
            miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

            AbstractControlador co = pane.getController();
            co.setMiApp(miApp);
        }
    }
}