package playeriSoft.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import playeriSoft.modelo.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewController implements Initializable{

    private Producto curProduct;

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
    private TextField anchoTextField;

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
        sortClassName(resourceObject);
    }

    private void sortClassName(Producto resourceObject){
        ProductViewHandler myHandler = new ProductViewHandler();
        String idProd = resourceObject.getIdProducto();
        String prodType = idProd.substring(0, Math.min(idProd.length(), 4));
        if(prodType.equals("PLAY")){
            curProduct = myHandler.buildPlayera(resourceObject,idProd);
            setPlayeraLayout();
        }else if(prodType.equals("SUDA")){
            curProduct = myHandler.buildSudadera(resourceObject, idProd);
            setSudaderaLayout();
        }else if(prodType.equals("GORR")){
            curProduct = myHandler.buildGorra(resourceObject, idProd);
            setGorroLayout();
        }else{
            curProduct = myHandler.buildParche(resourceObject,idProd);
            setParcheLayout();
        }
    }


    private void setPlayeraLayout(){
        Playera myPlayera = (Playera) curProduct;
        productoLabel.setText(myPlayera.getDescripcion().toString());
        playeraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myPlayera.getColor().toString()));
        tallaTextField.setText(String.valueOf(myPlayera.getTalla()));
        tipoTextField.setText(myPlayera.getTipo().toString());
        if(myPlayera.isBordado()) {
            bordadoCheckBox.setSelected(true);
        }else{
            serigrafiaCheckBox.setSelected(true);
        }
        setGeneralAttributes();
        disableFields();
    }

    private void setSudaderaLayout(){
        Sudadera mySudadera = (Sudadera) curProduct;
        productoLabel.setText(mySudadera.getDescripcion().toString());
        sudaderaCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(mySudadera.getColor().toString()));
        tallaTextField.setText(String.valueOf(mySudadera.getTalla()));
        if(mySudadera.isBordado()) {
            bordadoCheckBox.setSelected(true);
        }else{
            serigrafiaCheckBox.setSelected(true);
        }
        setGeneralAttributes();
        disableFields();
    }

    private void setGorroLayout(){
        Gorro myGorro = (Gorro) curProduct;
        productoLabel.setText(myGorro.getDescripcion().toString());
        gorraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myGorro.getColor().toString()));
        tallaTextField.setText(String.valueOf(myGorro.getTalla()));
        if(myGorro.isBordado()) {
            bordadoCheckBox.setSelected(true);
        }else{
            serigrafiaCheckBox.setSelected(true);
        }
        setGeneralAttributes();
        disableFields();
    }

    private void setParcheLayout(){
        Parche myParche = (Parche) curProduct;
        productoLabel.setText(myParche.getDescripcion().toString());
        parcheCheckBox.setSelected(true);
        largoTextField.setText(String.valueOf(myParche.getLargo()));
        anchoTextField.setText(String.valueOf(myParche.getAncho()));
        bordadoCheckBox.setSelected(true);
        setGeneralAttributes();
        disableFields();
    }

    private void setGeneralAttributes(){
        descripTextField.setText(curProduct.getDescripcion().toString());
        existenciasTextField.setText(String.valueOf(curProduct.getExistencias()));
        descuentoTextField.setText(String.valueOf(curProduct.getDescuento()));
        precioMayTextField.setText(String.valueOf(curProduct.getPrecioMayoreo()));
        precioMenTextField.setText(String.valueOf(curProduct.getPrecioMenudeo()));
    }

    private void disableFields(){
        playeraCheckBox.setDisable(true);
        gorraCheckBox.setDisable(true);
        sudaderaCheckBox.setDisable(true);
        parcheCheckBox.setDisable(true);
        descripTextField.setDisable(true);
        existenciasTextField.setDisable(true);
        descuentoTextField.setDisable(true);
        precioMayTextField.setDisable(true);
        precioMenTextField.setDisable(true);
        colorTextField.setDisable(true);
        tallaTextField.setDisable(true);
        tipoTextField.setDisable(true);
        bordadoCheckBox.setDisable(true);
        serigrafiaCheckBox.setDisable(true);
        largoTextField.setDisable(true);
        anchoTextField.setDisable(true);
        agregarMaterialesButton.setDisable(true);
        eliminarButton.setDisable(true);
    }

}
