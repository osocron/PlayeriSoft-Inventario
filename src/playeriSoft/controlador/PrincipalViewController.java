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
 */
public class PrincipalViewController implements Initializable {

    @FXML
    private Button productosButton;

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
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            //Cerrar ventana
            Stage stage1 = (Stage) productosButton.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
