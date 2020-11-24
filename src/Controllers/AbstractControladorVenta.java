package Controllers;

import Indice.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

/**
 * Controlador padre de Venta.
 */
public abstract class AbstractControladorVenta {
    protected Main miApp;
    @FXML
    protected Pane panel;
    @FXML
    private Pane panelBuscarCliente;
    @FXML
    private Pane panelPropuestaVenta;
    @FXML
    private Pane panelVerVehiculo;
    @FXML
    private Pane panelVehiculoVenta,panelAltaCliente;


    public void setMiApp(Main miapp){
        this.miApp = miapp;
    }

    /**
     * Metódo que hace que el panel tenga otro tipo de color.
     * @param e
     */
    @FXML
    protected void shadowPane(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Pane panel = (Pane) e.getSource();
            panel.setBackground(new Background(new BackgroundFill(Color.web("#ac914f"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Método que devuelve el color del panal al que tenia.
     * @param e
     */
    @FXML
    protected void normalPane(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Pane panel = (Pane) e.getSource();
            panel.setBackground(new Background(new BackgroundFill(Color.web("#e9c46a"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Método que cierra la aplicación.
     * @param e
     */
    @FXML
    private void closePane(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Platform.exit();
        }
    }

    /**
     * Método que permite arrastrar paneles.
     * @param mouseEvent
     */
    @FXML
    protected void dragPanel(MouseEvent mouseEvent) {
        panel.setOnMouseDragged(dragEvent -> {
            miApp.getPrimaryStage().setX(dragEvent.getScreenX() - mouseEvent.getSceneX());
            miApp.getPrimaryStage().setY(dragEvent.getScreenY() - mouseEvent.getSceneY());
        });
    }

    /**
     * Método que permite usar panel superior para el menu.
     * @param e
     * @throws IOException
     */
    @FXML
    protected void panelSuperior(MouseEvent e) throws IOException {
        String ruta = "";
        if (e.getSource().equals(panelVehiculoVenta)) {
            ruta = "/View/Venta/VentaAltaVehiculo.fxml";

        } else if (e.getSource().equals(panelAltaCliente)){
            ruta = "/View/Venta/VentaAltaCliente.fxml";

        } else if (e.getSource().equals(panelVerVehiculo)){
            ruta = "/View/Venta/FichaVehiculo.fxml";
        } else if (e.getSource().equals(panelBuscarCliente)){
            ruta = "/View/Venta/BusquedaListadoClientes.fxml";
        }else if (e.getSource().equals(panelPropuestaVenta)){
            ruta = "/View/Venta/BusquedaListadoPropuestaVenta.fxml";
        }
        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        miApp.getPrimaryStage().setScene(new Scene(pane.load(), 1280, 720));

        AbstractControladorVenta co = pane.getController();
        co.setMiApp(miApp);
    }

}
