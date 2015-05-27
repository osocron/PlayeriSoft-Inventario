package playeriSoft.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocron on 22/05/15.
 * Clase chafita que funciona como controlador de la ventana de Login para el proyecto.
 */
public class LoginViewController implements Initializable{

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void entrarLoginButtonOnClickedActionEvent(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("playeriSoft/vista/Principal-View.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("PlayeriSoft inc.");
            stage.setScene(new Scene(root, 800, 550));
            stage.show();
            //Cerrar ventana
            Stage stage1 = (Stage) loginButton.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
