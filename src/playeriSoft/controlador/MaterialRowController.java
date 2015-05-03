package playeriSoft.controlador;

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

    @FXML
    private Button guardarButton;

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

    }

    @FXML
    public void materialCheckBoxClicked(){
        materialCantidadTextField.setEditable(materialCheckBox.isSelected() ? true : false);
    }


}
