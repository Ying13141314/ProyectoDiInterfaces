package Controllers.Mecanico;

import DAO.TareaDao;
import Models.Tarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ControladorBuscarListadoTrabajoDelDia extends AbstractControladorMecanico{

    //Estado
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAceptar;

    @FXML
    private TableView<Tarea> clienteTableView;
    @FXML
    private TableColumn<Tarea, String> campoTrabajo;
    @FXML
    private TableColumn<Tarea, String> campoEstado;
    @FXML
    private TextField tfTrabajo;
    Tarea miTarea;

    /**
     * Controlador Vacio
     */
    public ControladorBuscarListadoTrabajoDelDia() {
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
     * @param miTareaDao
     * @throws SQLException
     */
    private void buscarApellido(TareaDao miTareaDao) throws SQLException {

    }

}
