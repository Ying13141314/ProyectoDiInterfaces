package Controllers.Venta;

import Controllers.AbstractControlador;
import Models.AbstractUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Esta clase es la Controladora de la Vista FichaVehiculo, muestra la información de un vehiculo seleccionado a traves
 * de la Vista ListadoVehiculo (Se realiza en el Sprint 3).
 */

public class ControladorFichaVehiculo extends AbstractControlador {

    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAceptar;

    /**
     * Controlador Vacio
     */
    public ControladorFichaVehiculo() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){
    }

    /**
     * Método que sirve para cambiar de una venta a otra dependiendo que boton se ha elegido.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void changeScene(ActionEvent e) throws IOException {
        String ruta = "";
        if(e.getSource().equals(buttonCancelar)) {
            ruta = "/View/Venta/Venta.fxml";

        } else if (e.getSource().equals(buttonAceptar)){
            ruta = "/View/Venta/Venta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        ControladorVenta co = pane.getController();
        co.setMiApp(miApp);
    }
}
