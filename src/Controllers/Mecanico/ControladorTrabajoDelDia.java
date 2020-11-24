package Controllers.Mecanico;

import Controllers.Venta.ControladorFichaCliente;
import Controllers.Venta.ControladorVenta;
import DAO.ClientesDAO;
import Models.Cliente;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class ControladorTrabajoDelDia extends AbstractControladorMecanico{

    //Estado
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAceptar;

    @FXML
    private TableView<Cliente> clienteTableView;
    @FXML
    private TableColumn<Cliente, String> campoNombre;
    @FXML
    private TableColumn<Cliente, String> campoApellido;
    @FXML
    private TableColumn<Cliente, String> campoDNI;
    @FXML
    private TableColumn<Cliente, String> campoTelefono;
    @FXML
    private TableColumn<Cliente, String> campoSexo;
    @FXML
    private TableColumn<Cliente, String> campoCorreo;
    @FXML
    private TextField tfNombre,tfDNI,tfApellido;
    Cliente miCliente;

    /**
     * Controlador Vacio
     */
    public ControladorTrabajoDelDia() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize() throws SQLException {

    }

    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     */
    public void changeScene(ActionEvent e) throws IOException {
        String ruta = "";
        //si le damos al botón Cancelar volvemos a la página anterior
        if(e.getSource().equals(buttonCancelar)) {
            ruta = "/View/Mecanico/Mecanico.fxml";
            //Si le damos al botón aceptar entramos al siguiente.
        }

        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        ControladorMecanico co = pane.getController();
        co.setMiApp(miApp);
    }

    /**
     * Método que cuando se selecciona una columna de la tabla, muestra la ficha cliente con todas las información.
     */
    public void cambiarFichaCliente(){

    }

    /**
     * Método que filtra el cliente por apellido, nombre o dni.
     * @param miCliente
     * @throws SQLException
     */
    private void buscarApellido(ClientesDAO miCliente) throws SQLException {

    }

}
