package Controllers.Venta;

import DAO.VentaPropuestaDAO;
import Models.PropuestaVenta;
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

    public class ControladorBusquedaListadoPropuestaVenta extends AbstractControladorVenta {

        //Estado
        @FXML
        private Button buttonCancelar;

        @FXML
        private TableView<PropuestaVenta> propuestaTableView;
        @FXML
        private TableColumn<PropuestaVenta, String> campoNombreCliente;
        @FXML
        private TableColumn<PropuestaVenta, String> campoPrimerApellido;
        @FXML
        private TableColumn<PropuestaVenta, String> campoDNI;
        @FXML
        private TableColumn<PropuestaVenta, String> campoDireccion;
        @FXML
        private TableColumn<PropuestaVenta, String> campoEstado;
        @FXML
        private TableColumn<PropuestaVenta, String> campoFechaLimite;
        @FXML
        private TextField tfNombre,tfDNI,tfApellido;
        PropuestaVenta miPropuesta;

        /**
         * Controlador Vacio
         */
        public ControladorBusquedaListadoPropuestaVenta() {
        }

        /**
         * Método Initialize que se ejecuta después de cargar la vista.
         */
        @FXML
        public void initialize() throws SQLException {
            VentaPropuestaDAO miVentaDao = new VentaPropuestaDAO();
            ObservableList<PropuestaVenta> miListaVenta = miVentaDao.anadirVentasLista();
            propuestaTableView.setItems(miListaVenta);

            campoNombreCliente.setCellValueFactory(cellData -> cellData.getValue().nombreClienteProperty());
            campoPrimerApellido.setCellValueFactory(cellData -> cellData.getValue().primerApellidoProperty());
            campoDNI.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
            campoDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
            campoEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
            campoFechaLimite.setCellValueFactory(cellData -> cellData.getValue().fechaLimiteAcepProperty());

            buscarApellido(miVentaDao);
            cambiarFichaPropuesta();
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
            }

            FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
            miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

            ControladorVenta co = pane.getController();
            co.setMiApp(miApp);
        }

        /**
         * Método que cuando se selecciona una columna de la tabla, muestra la ficha cliente con todas las información.
         */
        public void cambiarFichaPropuesta(){
            propuestaTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //si detecta más de un click me lleva a otra ventana.
                    if (event.getClickCount() > 1) {
                        FXMLLoader pane = new FXMLLoader(getClass().getResource("/View/Venta/FichaPropuestaVenta.fxml"));
                        try {
                            miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));
                            ControladorFichaPropuestaVenta co = pane.getController();
                            co.setMiApp(miApp);
                            PropuestaVenta miPropuesta = propuestaTableView.getSelectionModel().getSelectedItem();
                            co.mostrarDatosPropuesta(miPropuesta);
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
         * @param miPropuesta
         * @throws SQLException
         */
        private void buscarApellido(VentaPropuestaDAO miPropuesta) throws SQLException {
            FilteredList<PropuestaVenta> filteredData = new FilteredList<PropuestaVenta>(miPropuesta.anadirVentasLista(), p -> true);
            propuestaTableView.setItems(filteredData);

            tfApellido.setPromptText("Buscar...");
            tfApellido.textProperty().addListener((prop, old, text) -> {
                filteredData.setPredicate(venta -> {
                    if(text == null || text.isEmpty()) return true;

                    String name = venta.getPrimerApellido().toLowerCase();
                    return name.contains(text.toLowerCase());
                });
            });
            tfDNI.setPromptText("Buscar...");
            tfDNI.textProperty().addListener((prop, old, text) -> {
                filteredData.setPredicate(venta -> {
                    if(text == null || text.isEmpty()) return true;

                    String name = venta.getDni().toLowerCase();
                    return name.contains(text.toLowerCase());
                });
            });
            tfNombre.setPromptText("Buscar...");
            tfNombre.textProperty().addListener((prop, old, text) -> {
                filteredData.setPredicate(venta -> {
                    if(text == null || text.isEmpty()) return true;

                    String name = venta.getNombreCliente().toLowerCase();
                    return name.contains(text.toLowerCase());
                });
            });
        }
}
