package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private ChoiceBox<String> tipoChoiceBox;

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
        ObservableList<String> items = FXCollections.observableArrayList("Normal","Polo");
        tipoChoiceBox.setItems(items);
    }


    public void setResourceObject(Producto resourceObject){
        this.curProduct = resourceObject;
        modificarButton.setText("Modificar");
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
        String tipo = myPlayera.getTipo().toString();
        if(tipo.equals("NORM")) {
            tipoChoiceBox.getSelectionModel().selectFirst();
        }else {
            tipoChoiceBox.getSelectionModel().selectLast();
        }
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
        tipoChoiceBox.setDisable(true);
        bordadoCheckBox.setDisable(true);
        serigrafiaCheckBox.setDisable(true);
        largoTextField.setDisable(true);
        anchoTextField.setDisable(true);
        agregarMaterialesButton.setDisable(true);
        eliminarButton.setDisable(true);
    }

    private void enableFields(){
        if(playeraCheckBox.isSelected()){
            playeraCheckBox.setDisable(false);
            playeraCheckBoxClicked();
        }
        else if(sudaderaCheckBox.isSelected()){
            sudaderaCheckBox.setDisable(false);
            sudaderaCheckBoxClicked();
        }
        else if(gorraCheckBox.isSelected()){
            gorraCheckBox.setDisable(false);
            gorraCheckBoxClicked();
        }else{
            parcheCheckBox.setDisable(false);
            parcheCheckBoxClicked();
        }
        descripTextField.setDisable(false);
        existenciasTextField.setDisable(false);
        descuentoTextField.setDisable(false);
        precioMayTextField.setDisable(false);
        precioMenTextField.setDisable(false);
        colorTextField.setDisable(false);
        tallaTextField.setDisable(false);
        tipoChoiceBox.setDisable(false);
        if(bordadoCheckBox.isSelected()){
            bordadoCheckBox.setDisable(false);
            bordadoCheckBoxClicked();
        }else{
            serigrafiaCheckBox.setDisable(false);
            serigrafiaCheckBoxClicked();
        }
        largoTextField.setDisable(false);
        anchoTextField.setDisable(false);
        agregarMaterialesButton.setDisable(false);
        eliminarButton.setDisable(false);
    }

    @FXML
    public void playeraCheckBoxClicked(){
        if(playeraCheckBox.isSelected()) {
            gorraCheckBox.setDisable(true);
            sudaderaCheckBox.setDisable(true);
            parcheCheckBox.setDisable(true);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }else{
            gorraCheckBox.setDisable(false);
            sudaderaCheckBox.setDisable(false);
            parcheCheckBox.setDisable(false);
            largoTextField.setDisable(false);
            anchoTextField.setDisable(false);
        }
    }

    @FXML
    public void sudaderaCheckBoxClicked(){
        if(sudaderaCheckBox.isSelected()) {
            gorraCheckBox.setDisable(true);
            playeraCheckBox.setDisable(true);
            parcheCheckBox.setDisable(true);
            tipoChoiceBox.setDisable(true);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }else{
            gorraCheckBox.setDisable(false);
            playeraCheckBox.setDisable(false);
            parcheCheckBox.setDisable(false);
            tipoChoiceBox.setDisable(false);
            largoTextField.setDisable(false);
            anchoTextField.setDisable(false);
        }
    }

    @FXML
    public void gorraCheckBoxClicked(){
        if(gorraCheckBox.isSelected()) {
            playeraCheckBox.setDisable(true);
            sudaderaCheckBox.setDisable(true);
            parcheCheckBox.setDisable(true);
            tipoChoiceBox.setDisable(true);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }else{
            playeraCheckBox.setDisable(false);
            sudaderaCheckBox.setDisable(false);
            parcheCheckBox.setDisable(false);
            tipoChoiceBox.setDisable(false);
            largoTextField.setDisable(false);
            anchoTextField.setDisable(false);
        }
    }

    @FXML
    public void parcheCheckBoxClicked(){
        if(parcheCheckBox.isSelected()) {
            gorraCheckBox.setDisable(true);
            sudaderaCheckBox.setDisable(true);
            playeraCheckBox.setDisable(true);
            tallaTextField.setDisable(true);
            colorTextField.setDisable(true);
            tipoChoiceBox.setDisable(true);
        }else{
            gorraCheckBox.setDisable(false);
            sudaderaCheckBox.setDisable(false);
            playeraCheckBox.setDisable(false);
            tallaTextField.setDisable(false);
            colorTextField.setDisable(false);
            tipoChoiceBox.setDisable(false);
        }
    }

    @FXML
    public void serigrafiaCheckBoxClicked(){
        if(serigrafiaCheckBox.isSelected()){
            bordadoCheckBox.setDisable(true);
        }else{
            bordadoCheckBox.setDisable(false);
        }
    }

    @FXML
    public void bordadoCheckBoxClicked(){
        if(bordadoCheckBox.isSelected()){
            serigrafiaCheckBox.setDisable(true);
        }else{
            serigrafiaCheckBox.setDisable(false);
        }
    }


    @FXML
    public void modificarButtonClicked(){
        if (modificarButton.getText().equals("Guardar")){
            guardarProducto();
        }else{
            enableFields();
            modificarButton.setText("Guardar");
        }
    }

    public void guardarProducto(){
        ProductViewHandler myHandler = new ProductViewHandler();
        if(playeraCheckBox.isSelected()){
            myHandler.guardarPlayera(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                    Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                    Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                    colorTextField.getText(),tipoChoiceBox.getValue(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
        }
        else if(sudaderaCheckBox.isSelected()){
            myHandler.guardarSudadera(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                    Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                    Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                    colorTextField.getText(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
        }
        else if(gorraCheckBox.isSelected()){
            myHandler.guardarGorra(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                    Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                    Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                    colorTextField.getText(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
        }else{
            myHandler.guardarParche(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                    Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                    Double.valueOf(precioMenTextField.getText()),Double.valueOf(largoTextField.getText()),
                    Double.valueOf(anchoTextField.getText()),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
        }
    }

}
