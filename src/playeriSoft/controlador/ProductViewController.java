package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
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

    private List<Material> listaMateriales = new ArrayList<>();

    private ProductViewHandler myHandler = new ProductViewHandler();

    private InventarioProductosController inventarioProductosController;

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
    public void setResourceObject(Producto resourceObject, InventarioProductosController inventarioProductosController){
        this.curProduct = resourceObject;
        modificarButton.setText("Modificar");
        sortClassName(resourceObject);
        listaMateriales = new ArrayList<>();
        listaMateriales = myHandler.getSelectedMateriales(listaMateriales, curProduct);
        this.inventarioProductosController = inventarioProductosController;
    }

    /*Este metodo se encarga de acomodar el Layout cuando el usuario desea crear un nuevo usuario, por default
    * deja el Layout como si se fuera a ingresar una playera. Tambien obtiene una referencia al controlador padre
    * que llamo a esta clase*/
    public void setNewProductLayout(InventarioProductosController inventarioProductosController){
        eliminarButton.setDisable(true);
        playeraCheckBox.setSelected(true);
        playeraCheckBoxClicked();
        this.inventarioProductosController = inventarioProductosController;
    }

    /*Cuando se recibe un objeto producto desde la pantalla ane¡terior, es necesario definir a que subclase pertenece,
    * para esto, este metodo se encarga de verificar de que tipo de producto es el objeto que se pasa como parametro
    * en el metodo setRsourceObject, una vez encontrado el tipo de producto se acomoda el Layout de la pantalla respectivamente*/
    private void sortClassName(Producto resourceObject){
        String idProd = resourceObject.getIdProducto();
        String prodType = idProd.substring(0, Math.min(idProd.length(), 4));
        switch (prodType) {
            case "PLAY":
                curProduct = myHandler.buildPlayera(resourceObject, idProd);
                setPlayeraLayout();
                break;
            case "SUDA":
                curProduct = myHandler.buildSudadera(resourceObject, idProd);
                setSudaderaLayout();
                break;
            case "GORR":
                curProduct = myHandler.buildGorra(resourceObject, idProd);
                setGorroLayout();
                break;
            default:
                curProduct = myHandler.buildParche(resourceObject, idProd);
                setParcheLayout();
                break;
        }
    }


    private void setPlayeraLayout(){
        Playera myPlayera = (Playera) curProduct;
        productoLabel.setText(myPlayera.getDescripcion());
        playeraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myPlayera.getColor()));
        tallaTextField.setText(String.valueOf(myPlayera.getTalla()));
        String tipo = myPlayera.getTipo();
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
        productoLabel.setText(mySudadera.getDescripcion());
        sudaderaCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(mySudadera.getColor()));
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
        productoLabel.setText(myGorro.getDescripcion());
        gorraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myGorro.getColor()));
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
        productoLabel.setText(myParche.getDescripcion());
        parcheCheckBox.setSelected(true);
        largoTextField.setText(String.valueOf(myParche.getLargo()));
        anchoTextField.setText(String.valueOf(myParche.getAncho()));
        bordadoCheckBox.setSelected(true);
        setGeneralAttributes();
        disableFields();
    }

    private void setGeneralAttributes(){
        descripTextField.setText(curProduct.getDescripcion());
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

    private boolean verifyACheckBoxIsSelected(){
        if(!playeraCheckBox.isSelected() && !sudaderaCheckBox.isSelected() && !gorraCheckBox.isSelected() && !parcheCheckBox.isSelected()){
            descripTextField.setDisable(true);
            existenciasTextField.setDisable(true);
            descuentoTextField.setDisable(true);
            precioMayTextField.setDisable(true);
            precioMenTextField.setDisable(true);
            tallaTextField.setDisable(true);
            colorTextField.setDisable(true);
            tipoChoiceBox.setDisable(true);
            serigrafiaCheckBox.setDisable(true);
            bordadoCheckBox.setDisable(true);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
            agregarMaterialesButton.setDisable(true);
            modificarButton.setDisable(true);
            eliminarButton.setDisable(true);
            return false;
        }else{
            return true;
        }
    }

    private void enableFieldsParaModificar(){
        if(playeraCheckBox.isSelected()){
            colorTextField.setEditable(true);
            colorTextField.setDisable(false);
            tallaTextField.setEditable(true);
            tallaTextField.setDisable(false);
            tipoChoiceBox.setDisable(false);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }
        else if(sudaderaCheckBox.isSelected()){
            colorTextField.setEditable(true);
            colorTextField.setDisable(false);
            tallaTextField.setEditable(true);
            tallaTextField.setDisable(false);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }
        else if(gorraCheckBox.isSelected()){
            colorTextField.setEditable(true);
            colorTextField.setDisable(false);
            tallaTextField.setEditable(true);
            tallaTextField.setDisable(false);
            largoTextField.setDisable(true);
            anchoTextField.setDisable(true);
        }else{
            colorTextField.setDisable(true);
            tallaTextField.setDisable(true);
            largoTextField.setEditable(true);
            largoTextField.setDisable(false);
            anchoTextField.setEditable(true);
            anchoTextField.setDisable(false);
        }
        descripTextField.setEditable(true);
        descripTextField.setDisable(false);
        existenciasTextField.setEditable(true);
        existenciasTextField.setDisable(false);
        descuentoTextField.setEditable(true);
        descuentoTextField.setDisable(false);
        precioMayTextField.setEditable(true);
        precioMayTextField.setDisable(false);
        precioMenTextField.setEditable(true);
        precioMenTextField.setDisable(false);
        agregarMaterialesButton.setDisable(false);
        eliminarButton.setDisable(false);
    }

    private void enableFieldsForNewProducto(){
        serigrafiaCheckBox.setDisable(false);
        bordadoCheckBox.setDisable(false);
        modificarButton.setDisable(false);
        eliminarButton.setDisable(true);
    }

    @FXML
    public void playeraCheckBoxClicked() {
        if(verifyACheckBoxIsSelected()){
            enableFieldsParaModificar();
            enableFieldsForNewProducto();
        }
        gorraCheckBox.setDisable(playeraCheckBox.isSelected());
        sudaderaCheckBox.setDisable(playeraCheckBox.isSelected());
        parcheCheckBox.setDisable(playeraCheckBox.isSelected());
    }

    @FXML
    public void sudaderaCheckBoxClicked() {
        if(verifyACheckBoxIsSelected()) {
            enableFieldsParaModificar();
            enableFieldsForNewProducto();
        }
        gorraCheckBox.setDisable(sudaderaCheckBox.isSelected());
        playeraCheckBox.setDisable(sudaderaCheckBox.isSelected());
        parcheCheckBox.setDisable(sudaderaCheckBox.isSelected());
    }

    @FXML
    public void gorraCheckBoxClicked(){
        if(verifyACheckBoxIsSelected()) {
            enableFieldsParaModificar();
            enableFieldsForNewProducto();
        }
        playeraCheckBox.setDisable(gorraCheckBox.isSelected());
        sudaderaCheckBox.setDisable(gorraCheckBox.isSelected());
        parcheCheckBox.setDisable(gorraCheckBox.isSelected());
    }

    @FXML
    public void parcheCheckBoxClicked(){
        if(verifyACheckBoxIsSelected()) {
            enableFieldsParaModificar();
            enableFieldsForNewProducto();
        }
        gorraCheckBox.setDisable(parcheCheckBox.isSelected());
        sudaderaCheckBox.setDisable(parcheCheckBox.isSelected());
        playeraCheckBox.setDisable(parcheCheckBox.isSelected());
    }

    @FXML
    public void serigrafiaCheckBoxClicked(){
        bordadoCheckBox.setDisable(serigrafiaCheckBox.isSelected());
    }

    @FXML
    public void bordadoCheckBoxClicked(){
        serigrafiaCheckBox.setDisable(bordadoCheckBox.isSelected());
    }


    @FXML
    public void modificarButtonClicked(){
        if (modificarButton.getText().equals("Guardar")){
            guardarProducto();
            inventarioProductosController.refreshListView();
        }else{
            enableFieldsParaModificar();
            modificarButton.setText("Guardar");
        }
    }

    private void cerrarVentanActual(){
        Stage stage = (Stage) modificarButton.getScene().getWindow();
        stage.close();
    }

    private boolean validarEntradasGeneralesParaGuardarProducto(){
        if((descripTextField.getText().length() > 0) && (existenciasTextField.getText().length() > 0)
                && (descuentoTextField.getText().length() > 0) && (precioMayTextField.getText().length() > 0)
                && (precioMenTextField.getText().length() > 0) && (serigrafiaCheckBox.isSelected() || bordadoCheckBox.isSelected())
                && (!listaMateriales.isEmpty())){
            return true;
        }else{
            return false;
        }
    }

    private boolean validarGuardarPlayera(){
        if(validarEntradasGeneralesParaGuardarProducto() && (tallaTextField.getText().length() > 0)
                && (colorTextField.getText().length() > 0)
                && (tipoChoiceBox.getSelectionModel().isSelected(0) || tipoChoiceBox.getSelectionModel().isSelected(1))){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validarGuardarSudaderaGorra(){
        if(validarEntradasGeneralesParaGuardarProducto() && (tallaTextField.getText().length() > 0)
                && (colorTextField.getText().length() > 0) && (!listaMateriales.isEmpty() || (listaMateriales.size() > 0))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validarGuardarParche(){
        if(validarEntradasGeneralesParaGuardarProducto() && (largoTextField.getText().length() > 0)
                && (anchoTextField.getText().length() > 0)
                && (!listaMateriales.isEmpty() || (listaMateriales.size() > 0))) {
            return true;
        } else {
            return false;
        }
    }

    private void mostrarMensajeDatosFaltantes(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText("No se han ingresado todos los datos necesarios para guardar el producto.");
        alert.setContentText("Favor de verificar que cada campo tenga informacion, que se haya seleccionado si es un " +
                "producto serigrafiado o bordado e inclusive, verificar que se hayan seleccionado materiales para el producto.");
        alert.showAndWait();
    }

    public void guardarProducto() {
        if (playeraCheckBox.isSelected() && validarGuardarPlayera()) {
            myHandler.guardarPlayera(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(),tipoChoiceBox.getValue(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
            cerrarVentanActual();
        }
        else if(sudaderaCheckBox.isSelected() && validarGuardarSudaderaGorra()){
            myHandler.guardarSudadera(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
            cerrarVentanActual();
        }
        else if(gorraCheckBox.isSelected() && validarGuardarSudaderaGorra()){
            myHandler.guardarGorra(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()),Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(),bordadoCheckBox.isSelected(),serigrafiaCheckBox.isSelected());
            cerrarVentanActual();
        }else if(parcheCheckBox.isSelected() && validarGuardarParche()){
            myHandler.guardarParche(Double.valueOf(descuentoTextField.getText()),descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()),Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()),Double.valueOf(largoTextField.getText()),
                        Double.valueOf(anchoTextField.getText()),bordadoCheckBox.isSelected(), listaMateriales);
            cerrarVentanActual();
        }else{
            mostrarMensajeDatosFaltantes();
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
