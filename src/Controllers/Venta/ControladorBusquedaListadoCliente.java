package Controllers.Venta;

import Controllers.AbstractControlador;

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

/**
 * Esta clase es la Controladora de la Vista ListadoClientes, lista los clientes que hay en la Base de Datos para
 * poder modificarlos.
 */

public class ControladorBusquedaListadoCliente extends AbstractControlador {

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
    public ControladorBusquedaListadoCliente() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize() throws SQLException {
        ClientesDAO miClienteDao = new ClientesDAO();
        ObservableList<Cliente> miListaCliente = miClienteDao.anadirClienteLista();
        clienteTableView.setItems(miListaCliente);

        campoNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        campoApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        campoDNI.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        campoTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        campoSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
        campoCorreo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());

        buscarApellido(miClienteDao);
        cambiarFichaCliente();
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
            ruta = "/View/Venta/Venta.fxml";
        //Si le damos al botón aceptar entramos al siguiente.
        } else if (e.getSource().equals(buttonAceptar)){
            ruta = "/View/Venta/Venta.fxml";
        }

        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        ControladorVenta co = pane.getController();
        co.setMiApp(miApp);
    }

    /**
     * Método que cuando se selecciona una columna de la tabla, muestra la ficha cliente con todas las información.
     */
    public void cambiarFichaCliente(){
        clienteTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //si detecta más de un click me lleva a otra ventana.
                if (event.getClickCount() > 1) {
                    FXMLLoader pane = new FXMLLoader(getClass().getResource("/View/Venta/FichaCliente.fxml"));
                    try {
                        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));
                        ControladorFichaCliente co = pane.getController();
                        co.setMiApp(miApp);
                        Cliente miClientes = clienteTableView.getSelectionModel().getSelectedItem();
                        co.mostrarDatosCliente(miClientes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("doble click");
                }
            }
        });
    }

    /**
     * Método que filtra el cliente por apellido, nombre o dni.
     * @param miCliente
     * @throws SQLException
     */
    private void buscarApellido(ClientesDAO miCliente) throws SQLException {
        FilteredList<Cliente> filteredData = new FilteredList<Cliente>(miCliente.anadirClienteLista(), p -> true);
        clienteTableView.setItems(filteredData);

        tfApellido.setPromptText("Buscar...");
        tfApellido.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(cliente -> {
                if(text == null || text.isEmpty()) return true;

                String name = cliente.getApellido().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
        tfDNI.setPromptText("Buscar...");
        tfDNI.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(cliente -> {
                if(text == null || text.isEmpty()) return true;

                String name = cliente.getDni().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
        tfNombre.setPromptText("Buscar...");
        tfNombre.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(cliente -> {
                if(text == null || text.isEmpty()) return true;

                String name = cliente.getNombre().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
    }
}
