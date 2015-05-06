package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import playeriSoft.modelo.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewController implements Initializable{

    private Producto curProduct;

    private List<Material> listaMateriales;

    private ProductViewHandler myHandler = new ProductViewHandler();

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

    /*/Este metodo prepara lo necesario para que la informacion del producto seleccionado desde la primera pantalla primcipal
    se pueda visualizar en la pantalla de producto detallado, asimismo, junta la informacion de los materiales que el
    usuario ha definido como materia prima del producto seleccionado y los guarda en una lista de materiales para uso
    futuro por el metodo materialesButtonOnClicked*/
    public void setResourceObject(Producto resourceObject){
        this.curProduct = resourceObject;
        modificarButton.setText("Modificar");
        sortClassName(resourceObject);
        listaMateriales = new ArrayList<Material>();
        listaMateriales = myHandler.getSelectedMateriales(listaMateriales, curProduct);
    }

    /*Este metodo se encarga de acomodar el Layout cuando el usuario desea crear un nuevo usuario, por default
    * deja el Layout como si se fuera a ingresar una playera*/
    public void setNewProductLayout(){
        eliminarButton.setDisable(true);
        playeraCheckBox.setSelected(true);
        playeraCheckBoxClicked();

    }

    /*Cuando se recibe un objeto producto desde la pantalla ane¡terior, es necesario definir a que subclase pertenece,
    * para esto, este metodo se encarga de verificar de que tipo de producto es el objeto que se pasa como parametro
    * en el metodo setRsourceObject, una vez encontrado el tipo de producto se acomoda el Layout de la pantalla respectivamente*/
    private void sortClassName(Producto resourceObject){
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
        descripTextField.setEditable(false);
        existenciasTextField.setEditable(false);
        descuentoTextField.setEditable(false);
        precioMayTextField.setEditable(false);
        precioMenTextField.setEditable(false);
        colorTextField.setEditable(false);
        tallaTextField.setEditable(false);
        tipoChoiceBox.setDisable(true);
        bordadoCheckBox.setDisable(true);
        serigrafiaCheckBox.setDisable(true);
        largoTextField.setEditable(false);
        anchoTextField.setEditable(false);
        agregarMaterialesButton.setDisable(true);
        eliminarButton.setDisable(true);
    }

    private void enableFieldsParaModificar(){
        if(playeraCheckBox.isSelected()){
            playeraCheckBoxClicked();
            colorTextField.setEditable(true);
            tallaTextField.setEditable(true);
            tipoChoiceBox.setDisable(false);
        }
        else if(sudaderaCheckBox.isSelected()){
            sudaderaCheckBoxClicked();
            colorTextField.setEditable(true);
            tallaTextField.setEditable(true);
        }
        else if(gorraCheckBox.isSelected()){
            gorraCheckBoxClicked();
            colorTextField.setEditable(true);
            tallaTextField.setEditable(true);
        }else{
            parcheCheckBoxClicked();
        }
        descripTextField.setEditable(true);
        existenciasTextField.setEditable(true);
        descuentoTextField.setEditable(true);
        precioMayTextField.setEditable(true);
        precioMenTextField.setEditable(true);
        agregarMaterialesButton.setDisable(false);
        eliminarButton.setDisable(false);
    }

    @FXML
    public void playeraCheckBoxClicked(){
        gorraCheckBox.setDisable(playeraCheckBox.isSelected() ? true : false);
        sudaderaCheckBox.setDisable(playeraCheckBox.isSelected() ? true : false);
        parcheCheckBox.setDisable(playeraCheckBox.isSelected() ? true : false);
        largoTextField.setEditable(playeraCheckBox.isSelected() ? false : true);
        anchoTextField.setEditable(playeraCheckBox.isSelected() ? false : true);
    }

    @FXML
    public void sudaderaCheckBoxClicked(){
        gorraCheckBox.setDisable(sudaderaCheckBox.isSelected() ? true : false);
        playeraCheckBox.setDisable(sudaderaCheckBox.isSelected() ? true : false);
        parcheCheckBox.setDisable(sudaderaCheckBox.isSelected() ? true : false);
        tipoChoiceBox.setDisable(sudaderaCheckBox.isSelected() ? true : false);
        largoTextField.setEditable(sudaderaCheckBox.isSelected() ? false : true);
        anchoTextField.setEditable(sudaderaCheckBox.isSelected() ? false : true);
    }

    @FXML
    public void gorraCheckBoxClicked(){
        playeraCheckBox.setDisable(gorraCheckBox.isSelected() ? true : false);
        sudaderaCheckBox.setDisable(gorraCheckBox.isSelected() ? true : false);
        parcheCheckBox.setDisable(gorraCheckBox.isSelected() ? true : false);
        tipoChoiceBox.setDisable(gorraCheckBox.isSelected() ? true : false);
        largoTextField.setEditable(gorraCheckBox.isSelected() ? false : true);
        anchoTextField.setEditable(gorraCheckBox.isSelected() ? false : true);
    }

    @FXML
    public void parcheCheckBoxClicked(){
        gorraCheckBox.setDisable(parcheCheckBox.isSelected() ? true : false);
        sudaderaCheckBox.setDisable(parcheCheckBox.isSelected() ? true : false);
        playeraCheckBox.setDisable(parcheCheckBox.isSelected() ? true : false);
        tallaTextField.setEditable(parcheCheckBox.isSelected() ? false : true);
        colorTextField.setEditable(parcheCheckBox.isSelected() ? false : true);
        tipoChoiceBox.setDisable(parcheCheckBox.isSelected() ? true : false);
    }

    @FXML
    public void serigrafiaCheckBoxClicked(){
        bordadoCheckBox.setDisable(serigrafiaCheckBox.isSelected() ? true : false);
    }

    @FXML
    public void bordadoCheckBoxClicked(){
        serigrafiaCheckBox.setDisable(bordadoCheckBox.isSelected() ? true : false);
    }


    @FXML
    public void modificarButtonClicked(){
        if (modificarButton.getText().equals("Guardar")){
            guardarProducto();
        }else{
            enableFieldsParaModificar();
            modificarButton.setText("Guardar");
        }
    }

    public void guardarProducto(){
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
        }else if(parcheCheckBox.isSelected()){
            myHandler.guardarParche(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                    Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                    Double.valueOf(precioMenTextField.getText()),Double.valueOf(largoTextField.getText()),
                    Double.valueOf(anchoTextField.getText()),bordadoCheckBox.isSelected(), listaMateriales);
        }else{

        }
    }

    @FXML
    public void materialesButtonOnClicked(){
        ViewOpener viewOpener = new ViewOpener();
        if(listaMateriales == null) {
            viewOpener.openMaterialesPicker("playeriSoft/vista/MaterialPicker.fxml", "Seleccione Materiales", this);
        }else{
            viewOpener.openMaterialesPickerWithSelectedMaterials("playeriSoft/vista/MaterialPicker.fxml", "Seleccione Materiales", this,listaMateriales);
        }
    }

    public void setListaMateriales(List<Material> listaMateriales){
        this.listaMateriales = listaMateriales;
    }

}
