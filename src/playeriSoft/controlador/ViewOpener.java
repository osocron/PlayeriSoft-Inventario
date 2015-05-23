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

    public int openProductViewWithResourceObject(String pathToFXML, String title,
                                                 Producto resourceObject,
                                                 InventarioProductosController inventarioProductosController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            ProductViewController controller = loader.<ProductViewController>getController();
            controller.setResourceObject(resourceObject, inventarioProductosController);
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public int openEditableProductView(String pathToFXML, String title, InventarioProductosController inventarioProductosController){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            ProductViewController controller = loader.<ProductViewController>getController();
            controller.setNewProductLayout(inventarioProductosController);
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public int openMaterialesPicker(String pathToFXML, String title, ProductViewController productViewController, boolean consulta){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 500, 400));
            MaterialesController controller = loader.<MaterialesController>getController();
            controller.isConsulta(consulta);
            controller.setParent(productViewController);
            stage.show();
            return 0;
        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }

    }

    public int openMaterialesPickerWithSelectedMaterials(String pathToFXML, String title,
                                                          ProductViewController productViewController,
                                                          List<Material> listaMateriales, boolean consulta){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 500, 400));
            MaterialesController controller = loader.<MaterialesController>getController();
            controller.isConsulta(consulta);
            controller.setParent(productViewController);
            controller.setSelectedMaterials(listaMateriales);
            stage.show();
            return 0;
        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }

    }


}

