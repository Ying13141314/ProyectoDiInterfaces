package Controllers.Mecanico;

import Indice.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class ControladorMecanico extends AbstractControladorMecanico {

    @FXML
    private Text textUsuario;
    @FXML
    private Button buttonTrabajos,buttonCrearCliente;

    /**
     * Controlador Vacio
     */
    public ControladorMecanico() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){
        textUsuario.setText(Main.miUsuario.getNombre());
    }

    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException {
        String ruta = "";
        if (e.getSource().equals(buttonTrabajos)) {
            ruta = "/View/Mecanico/TrabajoDelDia.fxml";
        } else if (e.getSource().equals(buttonCrearCliente)){
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";
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
}
