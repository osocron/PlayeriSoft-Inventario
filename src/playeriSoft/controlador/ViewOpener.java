package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import playeriSoft.modelo.Producto;

import java.io.IOException;

/**
 * Created by osocr_000 on 23/04/2015.
 */
public class ViewOpener {

    public ViewOpener(){}

    public void openProductView(String pathToFXML, String title, Producto resourceObject) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            ProductViewController controller = loader.<ProductViewController>getController();
            controller.setResourceObject(resourceObject);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

