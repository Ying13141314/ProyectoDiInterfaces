package Controllers.Venta;

import Controllers.AbstractControladorVenta;
import DAO.VehiculoDAO;
import Models.Vehiculo;
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

public class ControladorBusquedaListadoVehiculo extends AbstractControladorVenta {
    //Estado
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAceptar;

    @FXML
    private TableView<Vehiculo> vehiculoTableView;
    @FXML
    private TableColumn<Vehiculo, String> campoMarca;
    @FXML
    private TableColumn<Vehiculo, String> campoModelo;
    @FXML
    private TableColumn<Vehiculo, String> campoTipoVehiculo;
    @FXML
    private TableColumn<Vehiculo, Integer> campoConcesionario;
    @FXML
    private TableColumn<Vehiculo, Float> campoPrecio;
    @FXML
    private TableColumn<Vehiculo, String> campoFechaEntrada;
    @FXML
    private TextField tfMarca, tfModelo, tfTipo,tfConcesionario;
    Vehiculo miVehiculo;

    /**
     * Controlador Vacio
     */
    public ControladorBusquedaListadoVehiculo() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize() throws SQLException {
        VehiculoDAO miVehiculoDao = new VehiculoDAO();
        ObservableList<Vehiculo> miListaVehiculo = miVehiculoDao.anadirVehiculoLista();
        vehiculoTableView.setItems(miListaVehiculo);

        campoMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        campoModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        campoTipoVehiculo.setCellValueFactory(cellData -> cellData.getValue().tipoVehiculoProperty());
        campoConcesionario.setCellValueFactory(cellData -> cellData.getValue().idConsecionarioProperty().asObject());
        campoPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        campoFechaEntrada.setCellValueFactory(cellData -> cellData.getValue().fechaEntradaProperty());

        buscarApellido(miVehiculoDao);
        cambiarFichaVehiculo();
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
    public void cambiarFichaVehiculo(){
        vehiculoTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //si detecta más de un click me lleva a otra ventana.
                if (event.getClickCount() > 1) {
                    FXMLLoader pane = new FXMLLoader(getClass().getResource("/View/Venta/FichaVehiculo.fxml"));
                    try {
                        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));
                        ControladorFichaVehiculo co = pane.getController();
                        co.setMiApp(miApp);
                        Vehiculo miVehiculo = vehiculoTableView.getSelectionModel().getSelectedItem();
                        co.mostrarDatosVehiculo(miVehiculo);
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
     * @param miVehiculoDao
     * @throws SQLException
     */
    private void buscarApellido(VehiculoDAO miVehiculoDao) throws SQLException {
        FilteredList<Vehiculo> filteredData = new FilteredList<Vehiculo>(miVehiculoDao.anadirVehiculoLista(), p -> true);
        vehiculoTableView.setItems(filteredData);

        tfTipo.setPromptText("Buscar...");
        tfTipo.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(vehiculo -> {
                if(text == null || text.isEmpty()) return true;

                String name = vehiculo.getTipoVehiculo().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
        tfModelo.setPromptText("Buscar...");
        tfModelo.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(vehiculo -> {
                if(text == null || text.isEmpty()) return true;

                String name = miVehiculo.getModelo().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
        tfMarca.setPromptText("Buscar...");
        tfMarca.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(vehiculo -> {
                if(text == null || text.isEmpty()) return true;

                String name = miVehiculo.getMarca().toLowerCase();
                return name.contains(text.toLowerCase());
            });
        });
    }
}

