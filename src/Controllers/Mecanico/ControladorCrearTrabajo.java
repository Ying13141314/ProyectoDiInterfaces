package Controllers.Mecanico;

import Indice.Main;
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

public class ControladorCrearTrabajo extends AbstractControladorMecanico {

    @FXML
    private ChoiceBox cbMecanico;
    @FXML
    private TextField tfCliente, tfEmail, tfTelefono, tfMarca, tfModelo, tfPresupuesto, tfPiezas;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Text textUsuario;
    @FXML
    private Button buttonRegistrar, buttonCancelar;

    /**
     * Controlador Vacio
     */
    public ControladorCrearTrabajo() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){ }

    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException {
        String ruta = "";
        if (e.getSource().equals(buttonRegistrar)) {
            ruta = "/View/Mecanico/Mecanico.fxml";
        } else if(e.getSource().equals(buttonCancelar)) {
            ruta = "/View/Mecanico/Mecanico.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        if (e.getSource().equals(buttonRegistrar)) {
            AbstractControladorMecanico co = pane.getController();
            co.setMiApp(miApp);

        } else if(e.getSource().equals(buttonCancelar)){
            AbstractControladorMecanico co = pane.getController();
            co.setMiApp(miApp);

        }
    }
}
