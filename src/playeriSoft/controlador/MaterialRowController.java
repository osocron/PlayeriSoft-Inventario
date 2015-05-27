package playeriSoft.controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import playeriSoft.modelo.Material;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocr_000 on 02/05/2015.
 * Clase que se encarga de cargar cada cedlda de la lista de materiales con un checkbox, un label y un
 * textfield. También se encarga de responder a los eventos generados por el usuario.
 */
public class MaterialRowController implements Initializable{

    Material curMaterial;

    @FXML
    private Label materialLabel;

    @FXML
    private CheckBox materialCheckBox;

    @FXML
    private TextField materialCantidadTextField;

    @FXML
    private HBox hBox;


    public  MaterialRowController(){
    }

    /**
     *Método que se encarga de cargar la celda con un material de la base de datos y de presentar la
     * descripción del material en el label, la cantidad en el textfield y si fue seleccionado en el
     * checkbox.
     */
    public void setInfo(Material material, boolean isConsulta)
    {
        curMaterial = material;
        materialLabel.setText(curMaterial.getDescripcionMaterial() + " " + String.valueOf(curMaterial.getCantidadDeProducto()) + " " + curMaterial.getUnidadMedida());
        if(curMaterial.isSelected()){
            materialCheckBox.setSelected(true);
            materialCantidadTextField.setText(String.valueOf(curMaterial.getCantidadSeleccionada()));
        }
        materialCheckBox.setDisable(isConsulta);
        materialCantidadTextField.setEditable(!isConsulta);
    }

    //Método que regersa el nodo principal de la vista para ser usado como el root de la celda
    public HBox getBox(){
        return hBox;
    }

    /**
     *Self explanatory
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListenerToTextField();
    }

    /**
     *Método que se encarga de responder al evento de un click al checkbox de la celda
     */
    @FXML
    public void materialCheckBoxClicked(){
        materialCantidadTextField.setEditable(materialCheckBox.isSelected());
        curMaterial.setSelected(materialCheckBox.isSelected());
        if(!materialCheckBox.isSelected()){
            materialCantidadTextField.setText("");
        }
    }

    /**
     * Método que se encarga de agregar un observador de eventos al TextField de la celda, de tal
     * manera que cuando el usuario escriba una cantidad automáticamente se actualize la información
     * del material de la celda.
     * */
    private void addListenerToTextField(){
        materialCantidadTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty() && !newValue.equals(".")) {
                curMaterial.setCantidadSeleccionada(Double.valueOf(newValue));
            }else{
                curMaterial.setCantidadSeleccionada(0.0);
            }
        });
    }

}
