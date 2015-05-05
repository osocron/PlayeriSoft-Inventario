package playeriSoft.controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import playeriSoft.modelo.Material;

import java.io.IOException;
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
        materialLabel.setText(curMaterial.getDescripcionMaterial() + " " + String.valueOf(curMaterial.getCantidadDeProducto()) + curMaterial.getUnidadMedida());
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
                    if (newValue.equals(".")) {
                        materialCantidadTextField.setText("");
                    }else{
                        curMaterial.setCantidadSeleccionada(Double.valueOf(newValue));
                    }

                }
            }
        });
    }

    @FXML
    public void materialCheckBoxClicked(){
        materialCantidadTextField.setEditable(materialCheckBox.isSelected() ? true : false);
        curMaterial.setSelected( materialCheckBox.isSelected() ? true : false);
    }


}
