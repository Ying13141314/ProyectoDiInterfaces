package Controllers.Mecanico;

import DAO.ClientesDAO;
import DAO.TrabajoDAO;
import DAO.UsuarioDAO;
import Indice.Main;
import Models.Cliente;
import Models.Trabajo;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorCrearTrabajo extends AbstractControladorMecanico {

    @FXML
    Text textError;

    @FXML
    ChoiceBox cbMecanico;

    @FXML
    DatePicker dpFecha;

    @FXML
    TextField tfCliente, tfEmail, tfTelefono, tfMarca,  tfModelo, tfPresupuesto, tfPiezas;

    @FXML
    private Button buttonTrabajos,buttonCrearCliente;

    /**
     * Controlador Vacio
     */
    public ControladorCrearTrabajo() {
    }

    @FXML
    public void initialize(){
        try {
            cargarMecanicos();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException, SQLException {
        String ruta = "";
        if (e.getSource().equals(buttonTrabajos)) {
            Integer idMecanico = obtenerIdMecanico(cbMecanico.getSelectionModel().getSelectedItem().toString());
            HashMap<String,Object> trabajo = new HashMap<>();
            trabajo.put("IdMecanico", idMecanico);
            trabajo.put("Mecanico", tfCliente.getText());
            trabajo.put("FechaEntrada", dpFecha.getValue().toString());
            trabajo.put("Cliente", tfCliente.getText());
            trabajo.put("Marca", tfMarca.getText());
            trabajo.put("Modelo", tfModelo.getText());
            trabajo.put("Presupuesto", tfPresupuesto.getText());
            trabajo.put("Email", tfEmail.getText());
            trabajo.put("Telefono", tfTelefono.getText());
            trabajo.put("Piezas", tfPiezas.getText());

            try {
                Trabajo t = new Trabajo(trabajo);
                TrabajoDAO tdao = new TrabajoDAO();
                tdao.darAltaReparacion(t, textError);
                ruta = "/View/Mecanico/TrabajoDelDia.fxml";
            } catch (SQLException a) {
                a.printStackTrace();
            }

        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        if (e.getSource().equals(buttonTrabajos)) {
            AbstractControladorMecanico co = pane.getController();
            co.setMiApp(miApp);

        } else if (e.getSource().equals(buttonCrearCliente)){
            AbstractControladorMecanico co = pane.getController();
            co.setMiApp(miApp);
        }
    }
    private void cargarMecanicos() throws SQLException {
        UsuarioDAO udao = new UsuarioDAO();
        ArrayList<String> listaMecanico = udao.obtenerMecanicos();
        ObservableList<String> listaMecanicos = FXCollections.observableArrayList(listaMecanico);
        cbMecanico.setItems(listaMecanicos);
    }


    public Integer obtenerIdMecanico(String nombreMecanico) throws SQLException {
        UsuarioDAO udao = new UsuarioDAO();
        return udao.obtenerIdMecanico(nombreMecanico);
    }
}
