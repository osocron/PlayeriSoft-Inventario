package playeriSoft.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setResourceObject(Producto resourceObject){
        this.curProduct = resourceObject;
        setProductLabelTitle();
    }

    public void setProductLabelTitle(){
        productoLabel.setText(curProduct.getDescripcion().toString());
    }

}
