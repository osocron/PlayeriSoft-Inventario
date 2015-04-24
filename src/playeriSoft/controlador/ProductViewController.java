package playeriSoft.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import playeriSoft.modelo.Playera;
import playeriSoft.modelo.Producto;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewController implements Initializable{

    private Producto curProduct;
    private ProductViewHandler myHandler;

    @FXML
    private Label productoLabel;

    @FXML
    private CheckBox playeraCheckBox;

    @FXML
    private CheckBox sudaderaCheckBox;

    @FXML
    private CheckBox gorraCheckBox;

    @FXML
    private CheckBox parcheCheckBox;

    @FXML
    private TextField descripTextField;

    @FXML
    private TextField existenciasTextField;

    @FXML
    private TextField descuentoTextField;

    @FXML
    private TextField precioMayTextField;

    @FXML
    private TextField precioMenTextField;

    @FXML
    private TextField tallaTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private CheckBox serigrafiaCheckBox;

    @FXML
    private CheckBox bordadoCheckBox;

    @FXML
    private TextField largoTextField;

    @FXML
    private TextField tipoTextField;

    @FXML
    private TextField anchoMayTextField;

    @FXML
    private Button agregarMaterialesButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button modificarButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setResourceObject(Producto resourceObject){
        this.curProduct = resourceObject;
        String name = curProduct.getClass().getSimpleName();
        if(name.equals("Playera")){
            Playera myPlayera = (Playera) curProduct;
            setProductLabelTitle(curProduct.getDescripcion().toString());
            playeraCheckBox.setSelected(true);
            playeraCheckBox.setDisable(true);
            gorraCheckBox.setDisable(true);
            sudaderaCheckBox.setDisable(true);
            parcheCheckBox.setDisable(true);
            descripTextField.setText(curProduct.getDescripcion().toString());
            existenciasTextField.setText(String.valueOf(myPlayera.getExistencias()));
            descuentoTextField.setText(String.valueOf(myPlayera.getDescuento()));
            precioMayTextField.setText(String.valueOf(myPlayera.getPrecioMayoreo()));
            precioMenTextField.setText(String.valueOf(myPlayera.getPrecioMenudeo()));
            precioMenTextField.setText(String.valueOf(myPlayera.getTalla()));
            colorTextField.setText(String.valueOf(myPlayera.getColor().toString()));
            tallaTextField.setText(String.valueOf(myPlayera.getTalla()));
            tipoTextField.setText(myPlayera.getTipo().toString());
            bordadoCheckBox.setSelected(true);
            serigrafiaCheckBox.setDisable(true);
            largoTextField.setDisable(true);
            anchoMayTextField.setDisable(true);

        }
    }

    public void setProductLabelTitle(String title){
        productoLabel.setText(title);
    }

}
