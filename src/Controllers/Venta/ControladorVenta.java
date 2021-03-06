package Controllers.Venta;

import Controllers.AbstractControladorVenta;
import Indice.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Esta clase es la Controladora de la Vista Venta, muestra la interfaz principal del usuario de tipo venta, en la cual
 * se puede navegar a las diferentes opciones de tipo venta.
 */

public class ControladorVenta extends AbstractControladorVenta {

    @FXML
    private Text textUsuario;
    @FXML
    private Button buttonFichaCliente,buttonVehiculoConcesionario,buttonRegistrarCliente,buttonPropuestaVenta;

    /**
     * Controlador Vacio
     */
    public ControladorVenta() {
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
        if (e.getSource().equals(buttonRegistrarCliente)) {
            ruta = "/View/Venta/VentaAltaCliente.fxml";
        } else if (e.getSource().equals(buttonFichaCliente)){
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";
        } else if (e.getSource().equals(buttonVehiculoConcesionario)){
            ruta = "/View/Venta/BusquedaListadoVehiculo.fxml";
        } else if (e.getSource().equals(buttonPropuestaVenta)){
            ruta = "/View/Venta/VentaAltaPropuesta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        if (e.getSource().equals(buttonRegistrarCliente)) {
            AbstractControladorVenta co = pane.getController();
            co.setMiApp(miApp);

        } else if (e.getSource().equals(buttonFichaCliente)){
            AbstractControladorVenta co = pane.getController();
            co.setMiApp(miApp);
        } else if (e.getSource().equals(buttonVehiculoConcesionario)){
            AbstractControladorVenta co = pane.getController();
            co.setMiApp(miApp);
        } else if (e.getSource().equals(buttonPropuestaVenta)){
            AbstractControladorVenta co = pane.getController();
            co.setMiApp(miApp);
        }
    }
}