package Controllers.Indice;

import DAO.UsuarioDAO;

import Indice.Main;
import Models.AbstractUsuario;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collections;

/**
 * Esta clase es la Controladora de la Vista Login, comprueba que el login se realice correctamente.
 */

public class ControladorLogin {

    @FXML
    private Parent panel;
    @FXML
    private PasswordField tfPassw;
    @FXML
    private TextField tfUser;
    @FXML
    private Label lbErrorPasswUser;

    private Main miApp;

    UsuarioDAO miUsuario = new UsuarioDAO();

    /**
     * Controlador Vacio
     */
    public ControladorLogin() {
    }

    /**
     * Método Initialize que se ejecuta después de cargar la vista.
     */
    @FXML
    public void initialize(){
    }

    /**
     * Método Setter del Main.
     * @param miApp
     */
    public void setMiApp(Main miApp) {
        this.miApp = miApp;
    }

    /**
     * Método que comprueba el logueo.
     * @param e
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    public void comprobarAcceder(ActionEvent e) throws SQLException, IOException, NoSuchAlgorithmException {
        comprobarAcceder();
    }

    public void comprobarAcceder() throws SQLException, IOException, NoSuchAlgorithmException {
        lbErrorPasswUser.setWrapText(true);
        if (tfUser.getText().equals("") || tfPassw.getText().equals("")) {
            lbErrorPasswUser.setText("Los campos no pueden estar vacios.");

        } else {
            String hashpass = toHexString(getSHA(tfPassw.getText()));

            AbstractUsuario au = miUsuario.loguearse(hashpass, tfUser.getText());
            if (au == null) {
                //Cambiamos el borde a rojo si hemos introducido mal el usuario.
                setRed(tfUser);
                setRed(tfPassw);
                lbErrorPasswUser.setText("Información incorrecta. Tu contraseña y/o información de registro están equivocadas.");
            } else {
                miApp.setMiUsuario(au);
                //Quitamos el borde rojo si hemos introducido correctamente.
                removeRed(tfUser);
                removeRed(tfPassw);
                miApp.cambiarScene();
            }
        }
    }

    /**
     * Método que cambia el borde del textField a rojo.
     * @param tf
     */
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("tferror")) {
            styleClass.add("tferror");
        }
    }

    /**
     * Método que quita el color rojo al borde del textField.
     * @param tf
     */
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("tferror"));
    }

    /**
     * Método que permite que la ventana puede ser arrastrada.
     * @param mouseEvent
     */
    @FXML
    private void dragPanel(MouseEvent mouseEvent) {
        panel.setOnMouseDragged(dragEvent -> {
            miApp.getPrimaryStage().setX(dragEvent.getScreenX() - mouseEvent.getSceneX());
            miApp.getPrimaryStage().setY(dragEvent.getScreenY() - mouseEvent.getSceneY());
        });
    }

    /**
     * Método que permite minimizar una ventana.
     * @param e
     */
    @FXML
    private void minimizePanel(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            miApp.getPrimaryStage().setIconified(true);
        }
    }

    /**
     * Método que oscurece un panel para dar sensacion de estar seleccionado.
     * @param e
     */
    @FXML
    private void shadowPane(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Pane panel = (Pane) e.getSource();
            panel.setBackground(new Background(new BackgroundFill(Color.web("#dcdcdc"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Método que devuelve el color normal al panel.
     * @param e
     */
    @FXML
    private void normalPane(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Pane panel = (Pane) e.getSource();
            panel.setBackground(Background.EMPTY);
        }
    }

    /**
     * Metodo que sirve para cerrar la aplicación.
     * @param e
     */
    @FXML
    private void closePanel(MouseEvent e) {
        if (e.getSource() instanceof Pane) {
            Platform.exit();
        }
    }

    @FXML
    private void enterAcceder(KeyEvent e) throws NoSuchAlgorithmException, SQLException, IOException {
        if(e.getCode().equals(KeyCode.ENTER)){
            comprobarAcceder();
        }
    }

    /**
     * Método que redirige a las redes sociales.
     * @param e
     */
    @FXML
    private void socialNetworks(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            Button b = (Button) e.getSource();
            if (b.getText().equals("FACEBOOK")) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/"));
                } catch (IOException | URISyntaxException a) {
                    a.printStackTrace();
                }
            } else if (b.getText().equals("GOOGLE")) {
                try {
                    Desktop.getDesktop().browse(new URI("https://google.com/"));
                } catch (IOException | URISyntaxException a) {
                    a.printStackTrace();
                }
            } else if (b.getText().equals("TWITTER")) {
                try {
                    Desktop.getDesktop().browse(new URI("https://twitter.com/"));
                } catch (IOException | URISyntaxException a) {
                    a.printStackTrace();
                }
            }
        }
    }

    /**
     * Función getSHA, nos devuelve la contraseña con el SHA implementado.
     * @param pass
     * @return MessageDigest
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String pass) throws NoSuchAlgorithmException {
        //Creamos el objeto que nos deja instanciar el SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Devuelve un Array Byte asimilado para calcular el mensaje  de la contraseña.
        return md.digest(pass.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Función toHexString nos permite convertir de una contraseña a una Hasheada.
     * @param hash
     * @return
     */
    public static String toHexString(byte[] hash) {
        // Convierte un Array de Byte en un BigInteger
        BigInteger number = new BigInteger(1, hash);

        // Convierte el mensaje asimilado en un valor hexadecimal
        StringBuilder hexString = new StringBuilder(number.toString(16));

        //Si la longitud de la cadena es menor a 32 rellenamos con ceros a la derecha hasta llegar a 32
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}
