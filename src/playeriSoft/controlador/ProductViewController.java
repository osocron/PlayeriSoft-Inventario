package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import playeriSoft.modelo.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Noe on 24/04/15.
 *Clase que funciona como controlador a la vista producto-view.fxml
 */
public class ProductViewController implements Initializable{

    private boolean isModificar = false;

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

    /*/Este metodo prepara lo necesario para que la informacion del producto seleccionado desde
    la primera pantalla primcipal
    se pueda visualizar en la pantalla de producto detallado, asimismo, junta la informacion de los materiales que el
    usuario ha definido como materia prima del producto seleccionado y los guarda en una lista de materiales para uso
    futuro por el metodo materialesButtonOnClicked*/
    public void setResourceObject(Producto resourceObject, InventarioProductosController inventarioProductosController){
        this.curProduct = resourceObject;
        modificarButton.setText("Modificar");
        agregarMaterialesButton.setText("Consultar Materiales");
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
    * en el metodo setRsourceObject, una vez encontrado el tipo de producto se acomoda el Layout de
    * la pantalla respectivamente*/
    private int sortClassName(Producto resourceObject){
        String idProd = resourceObject.getIdProducto();
        String prodType = idProd.substring(0, Math.min(idProd.length(), 4));
        switch (prodType) {
            case "PLAY":
                curProduct = myHandler.buildPlayera(resourceObject, idProd);
                setPlayeraLayout();
                return 1;
            case "SUDA":
                curProduct = myHandler.buildSudadera(resourceObject, idProd);
                setSudaderaLayout();
                return 2;
            case "GORR":
                curProduct = myHandler.buildGorra(resourceObject, idProd);
                setGorroLayout();
                return 3;
            default:
                curProduct = myHandler.buildParche(resourceObject, idProd);
                setParcheLayout();
                return 4;
        }
    }

    /**
     * Método que se encarga de cargar los elementos de la vista con los valores de la playera
     * y deshabiliatar campos que no necesitan ser habilitados por medio del método disableEditableFields().
     * */
    private void setPlayeraLayout(){
        Playera myPlayera = (Playera) curProduct;
        playeraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myPlayera.getColor()));
        tallaTextField.setText(String.valueOf(myPlayera.getTalla()));
        String tipo = myPlayera.getTipo();
        if(tipo.equals("NORM")) tipoChoiceBox.getSelectionModel().selectFirst();
        else tipoChoiceBox.getSelectionModel().selectLast();
        if(myPlayera.isBordado()) bordadoCheckBox.setSelected(true);
        else serigrafiaCheckBox.setSelected(true);
        setGeneralAttributes();
        disableCheckBoxes();
        disableEditableFields();
    }

    /**
     *Método que se encarga de cargar los elementos de la vista con los valores de la sudadera
     * y deshabiliatar campos que no necesitan ser habilitados por medio del método disableEditableFields().
     * */
    private void setSudaderaLayout(){
        Sudadera mySudadera = (Sudadera) curProduct;
        sudaderaCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(mySudadera.getColor()));
        tallaTextField.setText(String.valueOf(mySudadera.getTalla()));
        if(mySudadera.isBordado()) bordadoCheckBox.setSelected(true);
        else serigrafiaCheckBox.setSelected(true);
        setGeneralAttributes();
        disableCheckBoxes();
        disableEditableFields();
    }

    /**
     *Método que se encarga de cargar los elementos de la vista con los valores del gorro
     * y deshabiliatar campos que no necesitan ser habilitados por medio del método disableEditableFields().
     * */
    private void setGorroLayout(){
        Gorro myGorro = (Gorro) curProduct;
        gorraCheckBox.setSelected(true);
        colorTextField.setText(String.valueOf(myGorro.getColor()));
        tallaTextField.setText(String.valueOf(myGorro.getTalla()));
        if(myGorro.isBordado()) bordadoCheckBox.setSelected(true);
        else serigrafiaCheckBox.setSelected(true);
        setGeneralAttributes();
        disableCheckBoxes();
        disableEditableFields();
    }

    /**
     *Método que se encarga de cargar los elementos de la vista con los valores del parche
     * y deshabiliatar campos que no necesitan ser habilitados por medio del método disableEditableFields().
     * */
    private void setParcheLayout(){
        Parche myParche = (Parche) curProduct;
        parcheCheckBox.setSelected(true);
        largoTextField.setText(String.valueOf(myParche.getLargo()));
        anchoTextField.setText(String.valueOf(myParche.getAncho()));
        bordadoCheckBox.setSelected(true);
        setGeneralAttributes();
        disableCheckBoxes();
        disableEditableFields();
    }

    /**
     *Método refactorizado que permite agregar los datos independientes al tipo de producto a la vista.
     * */
    private void setGeneralAttributes(){
        productoLabel.setText(curProduct.getDescripcion());
        descripTextField.setText(curProduct.getDescripcion());
        existenciasTextField.setText(String.valueOf(curProduct.getExistencias()));
        descuentoTextField.setText(String.valueOf(curProduct.getDescuento()));
        precioMayTextField.setText(String.valueOf(curProduct.getPrecioMayoreo()));
        precioMenTextField.setText(String.valueOf(curProduct.getPrecioMenudeo()));
    }

    /**
     *Método que se encarga de deshabilitar la propiedad setEditable de los nodos de la vista
     * y deshabiliatr otros nodos necesarios.
     * */
    private void disableEditableFields(){
        descripTextField.setEditable(false);
        existenciasTextField.setEditable(false);
        descuentoTextField.setEditable(false);
        precioMayTextField.setEditable(false);
        precioMenTextField.setEditable(false);
        colorTextField.setEditable(false);
        tallaTextField.setEditable(false);
        largoTextField.setEditable(false);
        anchoTextField.setEditable(false);
        eliminarButton.setDisable(true);
        tipoChoiceBox.setDisable(true);
        bordadoCheckBox.setDisable(true);
        serigrafiaCheckBox.setDisable(true);
    }

    /**
     * Deshabilita los checkboxes
     * */
    private void disableCheckBoxes(){
        playeraCheckBox.setDisable(true);
        gorraCheckBox.setDisable(true);
        sudaderaCheckBox.setDisable(true);
        parcheCheckBox.setDisable(true);
    }

    /**
     *Método que se encarga de verificar si los checkboxes han sido seleccionados
     * */
    private boolean verifyACheckBoxIsSelected(){
        if(!playeraCheckBox.isSelected() && !sudaderaCheckBox.isSelected()
                && !gorraCheckBox.isSelected() && !parcheCheckBox.isSelected()){
            disableAllFields(true);
            return false;
        }else{
            return true;
        }
    }

    /**
     * Deshabilita todos los nodos de la vista
     * */
    private void disableAllFields(Boolean bool){
        descripTextField.setDisable(bool);
        existenciasTextField.setDisable(bool);
        descuentoTextField.setDisable(bool);
        precioMayTextField.setDisable(bool);
        precioMenTextField.setDisable(bool);
        tallaTextField.setDisable(bool);
        colorTextField.setDisable(bool);
        tipoChoiceBox.setDisable(bool);
        serigrafiaCheckBox.setDisable(bool);
        bordadoCheckBox.setDisable(bool);
        largoTextField.setDisable(bool);
        anchoTextField.setDisable(bool);
        agregarMaterialesButton.setDisable(bool);
        modificarButton.setDisable(bool);
        eliminarButton.setDisable(bool);
    }

    /**
     * Método que se encarga de llamar a los métodos que habilitan los campos necesarios para la modificación de un
     * producto dependiendo de cuál checkbox está seleccionado.
     * */
    private int enableFieldsParaModificar(){
        int returnNumber;
        if(playeraCheckBox.isSelected()){
            enableGeneralAttributesForPLayeraSudaderaGorra(true);
            tipoChoiceBox.setDisable(false);
            returnNumber = 1;
        }
        else if(sudaderaCheckBox.isSelected()){
            enableGeneralAttributesForPLayeraSudaderaGorra(true);
            returnNumber = 2;
        }
        else if(gorraCheckBox.isSelected()){
            enableGeneralAttributesForPLayeraSudaderaGorra(true);
            returnNumber = 3;
        }else{
            enableParcheAttributesForModificar();
            returnNumber = 4;
        }
        enableGeneralAttributesForModificar(true);
        return returnNumber;
    }

    /**
     * Método que habilita los campos para edición necesarios tanto para la playera como la sudadera y la gorra
     * */
    private void enableGeneralAttributesForPLayeraSudaderaGorra(Boolean bool){
        colorTextField.setEditable(bool);
        colorTextField.setDisable(!bool);
        tallaTextField.setEditable(bool);
        tallaTextField.setDisable(!bool);
        largoTextField.setDisable(bool);
        anchoTextField.setDisable(bool);
    }

    /**
     * Método que habilita los campos necesarios para la modificación de un producto independientemente del tipo de
     * producto que sea.
     * */
    private void enableGeneralAttributesForModificar(Boolean bool){
        descripTextField.setEditable(bool);
        descripTextField.setDisable(!bool);
        existenciasTextField.setEditable(bool);
        existenciasTextField.setDisable(!bool);
        descuentoTextField.setEditable(bool);
        descuentoTextField.setDisable(!bool);
        precioMayTextField.setEditable(bool);
        precioMayTextField.setDisable(!bool);
        precioMenTextField.setEditable(bool);
        precioMenTextField.setDisable(!bool);
        agregarMaterialesButton.setDisable(!bool);
        eliminarButton.setDisable(!bool);
        serigrafiaCheckBox.setDisable(isModificar);
        bordadoCheckBox.setDisable(isModificar);
        modificarButton.setDisable(false);
        eliminarButton.setDisable(!isModificar);
    }

    /**
     * Método que habilita los campos necesarios para la modificaión de un parche*/
    private void enableParcheAttributesForModificar(){
        colorTextField.setDisable(true);
        tallaTextField.setDisable(true);
        largoTextField.setEditable(true);
        largoTextField.setDisable(false);
        anchoTextField.setEditable(true);
        anchoTextField.setDisable(false);
    }

    /**
     * Borra los datos escritos por el usuario, incluyendo los materiales seleccionados.*/
    private void clearInput(){
        descripTextField.clear();
        existenciasTextField.clear();
        descripTextField.clear();
        descuentoTextField.clear();
        precioMayTextField.clear();
        precioMenTextField.clear();
        tallaTextField.clear();
        colorTextField.clear();
        largoTextField.clear();
        anchoTextField.clear();
        serigrafiaCheckBox.setSelected(false);
        bordadoCheckBox.setSelected(false);
        clearMaterialesSelection();
    }

    /**
     * Borra los materiales seleccionados*/
    private void clearMaterialesSelection(){
        listaMateriales.forEach(material ->{
            material.setSelected(false);
            material.setCantidadSeleccionada(0);
        });
    }

    /**
     * Métdo que responde al evento de un click del usuario y manda a llamar al metodo checkBoxClickHandler() y
     * habilita el checkbox de la playera.
     * */
    @FXML
    public void playeraCheckBoxClicked() {
        checkBoxClickHandler(playeraCheckBox.isSelected());
        playeraCheckBox.setDisable(false);
    }

    /**
     * Métdo que responde al evento de un click del usuarioy manda a llamar al metodo checkBoxClickHandler() y
     * habilita el checkbox de la sudadera.
     * */
    @FXML
    public void sudaderaCheckBoxClicked() {
        checkBoxClickHandler(sudaderaCheckBox.isSelected());
        sudaderaCheckBox.setDisable(false);
    }

    /**
     * Métdo que responde al evento de un click del usuarioy manda a llamar al metodo checkBoxClickHandler() y
     * habilita el checkbox de la gorra.
     * */
    @FXML
    public void gorraCheckBoxClicked(){
        checkBoxClickHandler(gorraCheckBox.isSelected());
        gorraCheckBox.setDisable(false);
    }

    /**
     * Métdo que responde al evento de un click del usuarioy manda a llamar al metodo checkBoxClickHandler() y
     * habilita el checkbox de el parche.
     * */
    @FXML
    public void parcheCheckBoxClicked(){
        checkBoxClickHandler(parcheCheckBox.isSelected());
        parcheCheckBox.setDisable(false);
    }

    /**
     * Método que verifica si almenos un checkbox ha sido seleccionado para habilitar los campos para la edición del
     * producto. También deshabilita los checkbox para evitar que el usuario seleccione dos o más checkbox al mismo
     * tiempo y así truene el programa como ejote.
     * */
    private void checkBoxClickHandler(Boolean bool){
        if(verifyACheckBoxIsSelected()) {
            enableFieldsParaModificar();
        }
        playeraCheckBox.setDisable(bool);
        sudaderaCheckBox.setDisable(bool);
        gorraCheckBox.setDisable(bool);
        parcheCheckBox.setDisable(bool);
        clearInput();
    }

    /**
     * Métdo que responde al evento de un click del usuario y deshabilita el checkbox de bordado dependiendo en si
     * el checkbox de serigrafía está seleccionado o no.* */
    @FXML
    public void serigrafiaCheckBoxClicked(){
        bordadoCheckBox.setDisable(serigrafiaCheckBox.isSelected());
    }

    /**
     * Métdo que responde al evento de un click del usuario y deshabilita el checkbox de serigrafia dependiendo en si
     * el checkbox de bordado está seleccionado o no.* */
    @FXML
    public void bordadoCheckBoxClicked(){
        serigrafiaCheckBox.setDisable(bordadoCheckBox.isSelected());
    }

    /**
     * Método que responde al evento de un click del usuario y que dependiendo el texto del botón permite guardar el
     * producto o habilitar los campos para modificar un un producto.* */
    @FXML
    public boolean modificarButtonClicked(){
        if (modificarButton.getText().equals("Guardar")){
            guardarProducto(isModificar);
            inventarioProductosController.refreshListView();
            isModificar = false;
        }else{
            isModificar = true;
            enableFieldsParaModificar();
            agregarMaterialesButton.setText("Agregar Materiales");
            modificarButton.setText("Guardar");
        }
        return isModificar;
    }

    /**
     * Cierra la ventana actual
     * */
    private void cerrarVentanActual(){
        Stage stage = (Stage) modificarButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Método que valida si todos los campos necesarios para guardar un producto han sido llenados por el usuario. De
     * ser así regresa un booleano verdadero, de lo contrario un booleano falso.* */
    private boolean validarEntradasGeneralesParaGuardarProducto(){
        return (descripTextField.getText().length() > 0) && (existenciasTextField.getText().length() > 0)
                && (descuentoTextField.getText().length() > 0) && (precioMayTextField.getText().length() > 0)
                && (precioMenTextField.getText().length() > 0) && (serigrafiaCheckBox.isSelected()
                || bordadoCheckBox.isSelected()) && (!listaMateriales.isEmpty() || (listaMateriales.size() > 0));
    }

    /**
     * Método que valida si todos los campos necesarios para guardar una playera han sido llenados por el usuario. De
     * ser así regresa un booleano verdadero, de lo contrario un booleano falso.* */
    private boolean validarGuardarPlayera(){
        return validarEntradasGeneralesParaGuardarProducto() && (tallaTextField.getText().length() > 0)
                && (colorTextField.getText().length() > 0)
                && (tipoChoiceBox.getSelectionModel().isSelected(0) || tipoChoiceBox.getSelectionModel().isSelected(1));
    }

    /**
     * Método que valida si todos los campos necesarios para guardar una sudadera o una gorra han sido llenados por el
     * usuario. De ser así regresa un booleano verdadero, de lo contrario un booleano falso.* */
    private boolean validarGuardarSudaderaGorra(){
        return validarEntradasGeneralesParaGuardarProducto() && (tallaTextField.getText().length() > 0)
                && (colorTextField.getText().length() > 0);
    }

    /**
     * Método que valida si todos los campos necesarios para guardar un parche han sido llenados por el usuario. De
     * ser así regresa un booleano verdadero, de lo contrario un booleano falso.* */
    private boolean validarGuardarParche(){
        return validarEntradasGeneralesParaGuardarProducto() && (largoTextField.getText().length() > 0)
                && (anchoTextField.getText().length() > 0);
    }

    /**
     * Método que se encarga de construir una alerta para el usuario en el caso de que la validación de los datos al
     * momento de guardar o modificar un producto haya fallado.* */
    private void mostrarMensajeDatosFaltantes(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText("No se han ingresado todos los datos necesarios para guardar el producto.");
        alert.setContentText("Favor de verificar que cada campo tenga informacion, " +
                "que se haya seleccionado \nsi es un producto serigrafiado o bordado e inclusive, " +
                "verificar que se hayan seleccionado\nmateriales para el producto.");
        alert.showAndWait();
    }

    /**
     * Método que se encarga de guardar o actualizar un producto dependiendo de la variable global isModificar y del
     * checkbox que esté seleccionado, esto puede ser, el checkbox de playera, de sudadera, de gorra o de parche.* */
    public void guardarProducto(boolean isForModificar) {
        if (playeraCheckBox.isSelected() && validarGuardarPlayera()) {
            if(isForModificar){
                myHandler.actualizarPlayera(curProduct.getIdProducto(),Double.valueOf(descuentoTextField.getText()),
                        descripTextField.getText(), Integer.valueOf(existenciasTextField.getText()),
                        Double.valueOf(precioMayTextField.getText()), Double.valueOf(precioMenTextField.getText()),
                        Double.valueOf(tallaTextField.getText()), colorTextField.getText(),tipoChoiceBox.getValue(),
                        bordadoCheckBox.isSelected(), listaMateriales);
            }else {
                myHandler.guardarPlayera(Double.valueOf(descuentoTextField.getText()), descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()), Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()), Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(), tipoChoiceBox.getValue(), bordadoCheckBox.isSelected(),
                        listaMateriales);
            }
            cerrarVentanActual();
        }
        else if(sudaderaCheckBox.isSelected() && validarGuardarSudaderaGorra()){
            if (isForModificar) {
                myHandler.actualizarSudadera(curProduct.getIdProducto(), Double.valueOf(descuentoTextField.getText()),
                        descripTextField.getText(), Integer.valueOf(existenciasTextField.getText()),
                        Double.valueOf(precioMayTextField.getText()), Double.valueOf(precioMenTextField.getText()),
                        Double.valueOf(tallaTextField.getText()), colorTextField.getText(),
                        bordadoCheckBox.isSelected(), listaMateriales);
            }else{
                myHandler.guardarSudadera(Double.valueOf(descuentoTextField.getText()), descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()), Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()), Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(), bordadoCheckBox.isSelected(), listaMateriales);
            }
            cerrarVentanActual();
        }
        else if(gorraCheckBox.isSelected() && validarGuardarSudaderaGorra()){
            if (isForModificar) {
                myHandler.actualizarGorra(curProduct.getIdProducto(), Double.valueOf(descuentoTextField.getText()),
                        descripTextField.getText(), Integer.valueOf(existenciasTextField.getText()),
                        Double.valueOf(precioMayTextField.getText()), Double.valueOf(precioMenTextField.getText()),
                        Double.valueOf(tallaTextField.getText()), colorTextField.getText(),
                        bordadoCheckBox.isSelected(), listaMateriales);
            }else{
                myHandler.guardarGorra(Double.valueOf(descuentoTextField.getText()), descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()), Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()), Double.valueOf(tallaTextField.getText()),
                        colorTextField.getText(), bordadoCheckBox.isSelected(), listaMateriales);
            }
            cerrarVentanActual();
        }else if(parcheCheckBox.isSelected() && validarGuardarParche()){
            if (isForModificar) {
                myHandler.actualizarParche(curProduct.getIdProducto(), Double.valueOf(descuentoTextField.getText()),
                        descripTextField.getText(), Integer.valueOf(existenciasTextField.getText()),
                        Double.valueOf(precioMayTextField.getText()), Double.valueOf(precioMenTextField.getText()),
                        Double.valueOf(largoTextField.getText()), Double.valueOf(anchoTextField.getText()),
                        bordadoCheckBox.isSelected(), listaMateriales);
            }else{
                myHandler.guardarParche(Double.valueOf(descuentoTextField.getText()), descripTextField.getText(),
                        Integer.valueOf(existenciasTextField.getText()), Double.valueOf(precioMayTextField.getText()),
                        Double.valueOf(precioMenTextField.getText()), Double.valueOf(largoTextField.getText()),
                        Double.valueOf(anchoTextField.getText()), bordadoCheckBox.isSelected(), listaMateriales);
            }
            cerrarVentanActual();
        }else{
            mostrarMensajeDatosFaltantes();
        }
    }

    /**
     * Metodo que se encarga de abrir la ventana de seleccion de materiales con materiales seleccionados previamente
     * por el usuario en caso de existan o sin materiales seleccionados previamente. Tambien pasa como argumento a la
     * clase que se encarga de abrir la ventana si se estan consultando los materiales de un producto existente o si el
     * usuario desea agregar o modificar materiales ya existentes en el producto.
     * */
    @FXML
    public void materialesButtonOnClicked(){
        ViewOpener viewOpener = new ViewOpener();
        boolean isConsulta;
        isConsulta = agregarMaterialesButton.getText().equals("Consultar Materiales");
        if(listaMateriales == null) {
            viewOpener.openMaterialesPicker("playeriSoft/vista/MaterialPicker.fxml",
                    "Seleccione Materiales", this, isConsulta);
        }else{
            viewOpener.openMaterialesPickerWithSelectedMaterials("playeriSoft/vista/MaterialPicker.fxml",
                    "Seleccione Materiales", this,listaMateriales, isConsulta);
        }
    }

    /**
     * Método setter para la lista de materiales
     * */
    public void setListaMateriales(List<Material> listaMateriales){
        this.listaMateriales = listaMateriales;
    }

    /**
     * Método que responde al evento de un clock del usuario y que manda  llamar al método encargado de eliminar el
     * producto, el método que actualiza la lista de productos de la ventana de selección de productos y al método
     * que cierra la ventana dependiendo de la decisión del usuario.
     * */
    @FXML
    public void eliminarButtonOnClicked(){
        if(showConfirmationDialog("¡Atención!", "¿Está seguro que desea eliminar el producto?",
                "Esta operación no podrá revertirse aunque le ore \na los dioses del Olimpo.")) {
            myHandler.eliminarProducto(curProduct.getIdProducto());
            inventarioProductosController.refreshListView();
            cerrarVentanActual();
        }
    }

    /**
     * Método que crea un mensaje de confirmación con dos opciónes. Regresa un booleano verdadero si el usuario da click
     * al botón de OK y un booleano falso de lo contrario.
     * */
    private boolean showConfirmationDialog(String title, String headerText, String contextText){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
