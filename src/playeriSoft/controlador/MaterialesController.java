package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.MaterialesHandler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocr_000 on 02/05/2015.
 */
public class MaterialesController implements Initializable {

    @FXML
    private TextField busqMaterialesTextField;

    @FXML
    private ListView materialesListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
    }

    private void prepareListView(){
        MaterialesHandler myHandler = new MaterialesHandler();
        //Set items on ListView
        ObservableList<Material> items = FXCollections.observableArrayList();
        items = myHandler.getAllMateriales(items);
        materialesListView.setItems(items);
        //Tweak ListView to display only productos.descripcion
        materialesListView.setCellFactory(new Callback<ListView<Material>, ListCell<Material>>() {
            @Override
            public ListCell<Material> call(ListView<Material> param) {
                ListCell<Material> cell = new ListCell<Material>() {
                    @Override
                    protected void updateItem(Material material, boolean bool) {
                        super.updateItem(material, bool);
                        if (material != null) {
                            setText(material.getDescripcionMaterial());
                        }
                    }
                };
                return cell;
            }
        });
    }

}
