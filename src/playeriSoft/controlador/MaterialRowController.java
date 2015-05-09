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

    public void setInfo(Material material)
    {
        curMaterial = material;
        materialLabel.setText(curMaterial.getDescripcionMaterial() + " " + String.valueOf(curMaterial.getCantidadDeProducto()) + " " + curMaterial.getUnidadMedida());
        if(curMaterial.isSelected()){
            materialCheckBox.setSelected(true);
            materialCantidadTextField.setText(String.valueOf(curMaterial.getCantidadSeleccionada()));
        }
    }

    public HBox getBox(){
        return hBox;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        materialCantidadTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty()) {
                    curMaterial.setCantidadSeleccionada(Double.valueOf(newValue));
                }else{
                    curMaterial.setCantidadSeleccionada(0.0);
                }
            }
        });
    }

    @FXML
    public void materialCheckBoxClicked(){
        materialCantidadTextField.setEditable(materialCheckBox.isSelected());
        curMaterial.setSelected(materialCheckBox.isSelected());
        if(!materialCheckBox.isSelected()){
            materialCantidadTextField.setText("");
        }
    }


}
