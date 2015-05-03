package playeriSoft.controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.MaterialesHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocr_000 on 02/05/2015.
 */
public class MaterialesController implements Initializable {

    private ObservableList<Material> items = FXCollections.observableArrayList();

    @FXML
    private TextField busqMaterialesTextField;

    @FXML
    private ListView<Material> materialesListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
        busqMaterialesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchMateriales(oldValue, newValue);
            }
        });
    }

    private void prepareListView(){
        MaterialesHandler myHandler = new MaterialesHandler();
        //Set items on ListView
        items = myHandler.getAllMateriales(items);
        materialesListView.setItems(items);
        //Tweak ListView to display custom cell with checkbox, label and textfield
        materialesListView.setCellFactory(new Callback<ListView<Material>, ListCell<Material>>() {
            @Override
            public ListCell<Material> call(ListView<Material> param) {
                ListCell<Material> cell = new ListCell<Material>() {
                    @Override
                    protected void updateItem(Material material, boolean bool) {
                        super.updateItem(material, bool);
                        if(bool){
                            setText(null);
                            setGraphic(null);
                        }else {
                            if (material != null) {
                                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("playeriSoft/vista/MaterialRow.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                MaterialRowController controller = loader.<MaterialRowController>getController();
                                controller.setInfo(material);
                                setGraphic(controller.getBox());
                            }
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    public void guardarMateriales(){

    }

    public void searchMateriales(String oldVal, String newVal){
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            materialesListView.setItems(items);
        }
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<Material> subentries = FXCollections.observableArrayList();
        for (Material entry : materialesListView.getItems()) {
            boolean match = true;
            String entryText = entry.getDescripcionMaterial();
            for ( String part: parts ) {
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
            if ( match ) {
                subentries.add(entry);
            }
        }
        materialesListView.setItems(subentries);
    }

}
