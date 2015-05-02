package playeriSoft.controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.Producto;

import java.io.IOException;
import java.util.List;

/**
 * Created by osocr_000 on 23/04/2015.
 */
public class ViewOpener {

    public ViewOpener(){}

    public int openProductViewWithResourceObject(String pathToFXML, String title, Producto resourceObject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            ProductViewController controller = loader.<ProductViewController>getController();
            controller.setResourceObject(resourceObject);
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public int openEditableProductView(String pathToFXML, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            ProductViewController controller = loader.<ProductViewController>getController();
            controller.setNewProductLayout();
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public void openMaterialesPicker(String pathToFXML, String title, List<Material> listaMateriales){

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathToFXML));
            Stage primaryStage = new Stage();
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}

