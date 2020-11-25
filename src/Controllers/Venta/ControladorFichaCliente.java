package Controllers.Venta;

import DAO.ClientesDAO;
import Models.Cliente;
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
 *  Esta clase es la Controladora del ficha Cliente, comprueba todos los procesos de ficha Cliente.
 */
public class ControladorFichaCliente extends AbstractControladorVenta {

    @FXML
    private Button buttonCancelar,buttonActualizar,buttonBorrar;
    @FXML
    private ChoiceBox cbSexo;
    @FXML
    private TextField tfNombre,tfApellido,tfCorreo,tfDireccion,tfTelefono;
    @FXML
    private Label lbDni;
    @FXML
    private ChoiceBox cbOpciones;
    @FXML
    private DatePicker dpFechaNacimiento;
    private Cliente miCliente;
    private HashMap <String,String> cliente;


    /**
     * Controlador vacio
     */
    public ControladorFichaCliente() {
    }

    /**
     * Para inicial cuando el xml ya esta ejecutando.
     */
    @FXML
    public void initialize(){
        //inicialzo estos dos ChoiceBox para poder mostrar esos datos.
        ArrayList<String> generos = new ArrayList<>();
        generos.add("hombre");
        generos.add("mujer");
        ObservableList<String> list = FXCollections.observableArrayList(generos);
        cbSexo.setItems(list);

        ArrayList<String> aviso = new ArrayList<>();
        aviso.add("correo");
        aviso.add("sms");
        ObservableList<String> list2 = FXCollections.observableArrayList(aviso);
        cbOpciones.setItems(list2);


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
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";

        } else if (e.getSource().equals(buttonActualizar)){
            actualizarDatos();
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";

        }else if (e.getSource().equals(buttonBorrar)){
            borrarDatos();
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        AbstractControladorVenta co = pane.getController();
        co.setMiApp(miApp);

    }

    /**
     * Método para obtener los datos que tenemos.
     */
    public void obtenerDatosCliente(){
        cliente = new HashMap<>();
        cliente.put("Nombre", tfNombre.getText());
        cliente.put("Apellidos", tfApellido.getText());
        cliente.put("DNI", lbDni.getText());
        cliente.put("Fecha", dpFechaNacimiento.getValue().toString());
        cliente.put("Direccion", tfDireccion.getText());
        cliente.put("Sexo", cbSexo.getSelectionModel().getSelectedItem().toString());
        cliente.put("Email", tfCorreo.getText());
        cliente.put("Telefono", tfTelefono.getText());
        cliente.put("Opciones", cbOpciones.getSelectionModel().getSelectedItem().toString());

    }

    /**
     * Método que actualiza los datos del cliente si modificamos los campos.
     * @throws SQLException
     */
    private void actualizarDatos() throws SQLException {
        obtenerDatosCliente();
        Cliente miClientes = new Cliente(cliente);
        ClientesDAO miClienteDao = new ClientesDAO();
        miClienteDao.actualizarDatos(miClientes);
    }

    /**
     * Método para borrar los cliente.
     * @throws SQLException
     */
    private void borrarDatos() throws SQLException {
        obtenerDatosCliente();
        Cliente miClientes = new Cliente(cliente);
        ClientesDAO miClienteDao = new ClientesDAO();
        miClienteDao.borrarCliente(miClientes);

    }

    /**
     * Método que me muestra por pantalla los datos del cliente.
     * @param miCliente
     */
    public void mostrarDatosCliente(Cliente miCliente){
        tfNombre.setText(miCliente.getNombre());
        tfApellido.setText(miCliente.getApellido());
        lbDni.setText(miCliente.getDni());
        dpFechaNacimiento.setValue(LocalDate.parse(miCliente.getFechaNac()));
        tfDireccion.setText(miCliente.getDireccion());
        cbSexo.setValue(miCliente.getSexo());
        tfCorreo.setText(miCliente.getCorreo());
        tfTelefono.setText(miCliente.getTelefono());
        cbOpciones.setValue(miCliente.getTipoComunicacion());
    }

    /**
     * Setter del cliente.
     * @param miCliente
     */
    public void setCliente(Cliente miCliente){
        this.miCliente = miCliente;
    }


}
