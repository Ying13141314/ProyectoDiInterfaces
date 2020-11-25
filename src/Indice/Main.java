package Indice;

import Controllers.Venta.AbstractControladorVenta;
import Controllers.Jefe.ControladorJefe;
import Controllers.Indice.ControladorLogin;
import Controllers.Mecanico.AbstractControladorMecanico;
import Models.AbstractUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
            public static AbstractUsuario miUsuario;

            @Override
            public void start(Stage primaryStage) {
                this.primaryStage=primaryStage;
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setResizable(false);
                iniciarLogin();
            }

            private void iniciarLogin(){
                try {
                    //Cargar toda la FXML y más.
                    FXMLLoader logueader = new FXMLLoader(getClass().getResource("/View/Indice/Login.fxml"));
                    primaryStage.setTitle("Yicar");
                    primaryStage.setScene(new Scene(logueader.load(), 1280, 720));
                    primaryStage.getScene().getStylesheets().add(getClass().getResource("/View/Resources/styleMain.css").toExternalForm());
                    //Cargar el controlador
            ControladorLogin co = logueader.getController();
            //Para dar parametro al constructor.
            co.setMiApp(this);

            //Para mostrar la ventana por eso se pone al final.
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cambiarScene() throws IOException {
        //comprobar el tipo de usuario , segun cual es carga uno u otra.
        String ruta="";

        if(miUsuario.getTipo().equals(AbstractUsuario.VENTA)){
            ruta = "/View/Venta/Venta.fxml";
        }else if (miUsuario.getTipo().equals(AbstractUsuario.MECANICO)){

            ruta = "/View/Mecanico/Mecanico.fxml";
        }else{
            ruta ="/View/Jefe.fxml";
        }

        FXMLLoader pane = new FXMLLoader(getClass().getResource(ruta));
        primaryStage.setScene(new Scene(pane.load(), 1280, 720));

        if(ruta.equals("/View/Venta/Venta.fxml")) {
            AbstractControladorVenta co = pane.getController();
            co.setMiApp(this);
        } else if(ruta.equals("/View/Mecanico/Mecanico.fxml")){
            AbstractControladorMecanico co = pane.getController();
            co.setMiApp(this);
        } else{
            ControladorJefe co = pane.getController();
            //co.setMiApp(this);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setMiUsuario(AbstractUsuario miUsuario) {
        this.miUsuario = miUsuario;
    }

    public AbstractUsuario getMiUsuario() { return miUsuario; }

    public static void main(String[] args) {
        launch(args);
    }

}