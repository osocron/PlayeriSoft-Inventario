package playeriSoft.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocron on 22/05/15.
 * Clase que funciona como controlador para la ventana principal de PlayeriSoft inc.
 */
public class PrincipalViewController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void pedidosButtonOnClickActionEvent(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("playeriSoft/vista/inventario-productos-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Inventario de Productos");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
