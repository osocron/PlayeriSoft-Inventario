package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import playeriSoft.modelo.Material;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocr_000 on 02/05/2015.
 */
public class MaterialesController implements Initializable {

    private ObservableList<Material> items = FXCollections.observableArrayList();
    private ProductViewController productViewController;
    private boolean consulta;

    @FXML
    private ListView<Material> materialesListView;

    @FXML
    private Button guardarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
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
                return new ListCell<Material>() {
                    @Override
                    protected void updateItem(Material material, boolean bool) {
                        super.updateItem(material, bool);
                        if (bool) {
                            setText(null);
                            setGraphic(null);
                        }else if(material != null){
                            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("playeriSoft/vista/MaterialRow.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            MaterialRowController controller = loader.<MaterialRowController>getController();
                            controller.setInfo(material,consulta);
                            setGraphic(controller.getBox());
                        }
                    }
                };
            }
        });
    }

    @FXML
    public void guardarMateriales(){
        List<Material> myListaMateriales = new ArrayList<>();
        final boolean[] isValidated = {true};
        final boolean[] atLeastOneSelected = {false};
        final boolean[] allWithCantidades = {true};
        items.forEach((Material curMaterial) -> {
            if (curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() > 0.0){
                myListaMateriales.add(curMaterial);
                atLeastOneSelected[0] = true;
            }else if (curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() == 0.0){
                allWithCantidades[0] = false;
                isValidated[0] = false;
            }
        });

        if(!allWithCantidades[0]){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("Algunos materiales seleccionados no tienen cantidad seleccionada.");
            alert.setContentText("Favor de ingresar cantidades o no seleccionar el material.");
            alert.showAndWait();
        }else if(!atLeastOneSelected[0]){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("No se han seleccionado materiales.");
            alert.setContentText("Favor de seleccionar al menos un material.");
            alert.showAndWait();
            isValidated[0] = false;
        }else if(isValidated[0]) {
            productViewController.setListaMateriales(myListaMateriales);
            cerrarVentanActual();
        }
    }

    private void cerrarVentanActual(){
        Stage stage = (Stage) guardarButton.getScene().getWindow();
        stage.close();
    }

    public void setParent(ProductViewController controller){
        this.productViewController = controller;
        disableFields(consulta);
    }

    public void setSelectedMaterials(List<Material> selectedMateriales){
        for(Material material : items)
            selectedMateriales.stream().filter(curMaterial -> material.getIdMaterial().equals(curMaterial.getIdMaterial())).forEach(curMaterial -> {
                material.setSelected(true);
                material.setCantidadSeleccionada(curMaterial.getCantidadSeleccionada());
            });
        materialesListView.setItems(items);
    }

    public void isConsulta(boolean consulta){
        this.consulta = consulta;
    }

    private void disableFields(boolean consulta){
        if(consulta){
            guardarButton.setDisable(true);
        }
    }

}
