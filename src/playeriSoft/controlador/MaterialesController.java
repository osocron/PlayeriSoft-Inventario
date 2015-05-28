package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import playeriSoft.modelo.Material;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocr_000 on 02/05/2015.
 * Clase que funciona como controlador de la pantalle de materiales.
 */
public class MaterialesController implements Initializable {

    private ObservableList<Material> items = FXCollections.observableArrayList();
    private ProductViewController productViewController;
    private boolean consulta;

    @FXML
    private ListView<Material> materialesListView;

    @FXML
    private Button guardarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
    }

    /*
    *Método que prepara el contenedor de elementos mandando a llamar métodos que agregan los Materiales
     * al contenedor y crean celdas modificadas para la modificación de la información de los materiales
    */
    private void prepareListView(){
        setMaterialesForListView();
        createCustomCells();
    }

    /*
    * Método que se encarga de obtener los materiales de la base de datos a llamar el método getAllMateriales()
    * y luego agrega los materiales obtenidos a la lista de materiales,
    */
    private void setMaterialesForListView(){
        MaterialesHandler myHandler = new MaterialesHandler();
        items = (ObservableList<Material>) myHandler.getAllMateriales(items);
        materialesListView.setItems(items);
    }

    /*
    *Método que se llamar a una cell factory para crear celdas modificadas llamando el método
     * getCustomCellWithUpdateItemOverriden()
    */
    private void createCustomCells(){
        materialesListView.setCellFactory(param -> {
            ListCell<Material> cell = getCustomCellWithUpdateItemOverriden();
            return cell;
        });
    }

    /*
    *Método que regresa una celda modificada que contiene un checkbox, un label y un textfield.
     * La celda contiene la información del material, esto es, su descripción, si ha sido seleccionada y la cantidad
      * de materiales que se agregó a la base de datos. Para lograrlo esta clase hace uso
      * del método setInfo() del objeto MaterialRowController.
    */
    private ListCell<Material> getCustomCellWithUpdateItemOverriden(){
        return new ListCell<Material>(){
            @Override
            protected void updateItem(Material material, boolean bool) {
                super.updateItem(material, bool);
                if (bool) {
                    setText(null);
                    setGraphic(null);
                }else if(material != null){
                    MaterialRowController controller = getMaterialRowController();
                    controller.setInfo(material,consulta);
                    setGraphic(controller.getBox());
                }
            }
        };
    }

    /*
    *Método que regresa el controlador de las filas de materiales que se agregan a las celdas modificadas de la
     * lista de materiales. Se logra cargando el archivo fxml y llamando el método getController()
    */
    private MaterialRowController getMaterialRowController(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("playeriSoft/vista/MaterialRow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MaterialRowController controller = loader.<MaterialRowController>getController();
        return  controller;
    }

    /*
    *Método que se llama cuando el usuario de click al botón de guardar materiales. Manda a llamar el método
    * validarMateriales() el cual se encarga de validar la entrada de datos.
    */
    @FXML
    public void guardarMaterialesOnClickEvent(){
        validarMateriales();
    }

    /*
    *Método que manda a llamar validateMaterialItems() y que en base al resultado muestra mensajes al usuario y si los
     * datos han sido validados correctamente, devuelve la lista de materiales seleccionados al controlador de la vista
      * que mando a llamar a esta ventana por medio del método setListaMateriales() y cierra la ventana actual
    */
    private void validarMateriales(){
        List<Material> myListaMateriales = new ArrayList<>();
        final boolean[] isValidated = {true};
        final boolean[] atLeastOneSelected = {false};
        final boolean[] allWithCantidades = {true};
        validateMaterialItems(myListaMateriales, isValidated, atLeastOneSelected, allWithCantidades);
        if(!allWithCantidades[0]){
            Alert alert = getWarningAlert("Error!","Algunos materiales seleccionados no tienen cantidad seleccionada."
                    ,"Favor de ingresar cantidades o no seleccionar el material.");
            alert.showAndWait();
        }else if(!atLeastOneSelected[0]){
            Alert alert = getWarningAlert("Error!","No se han seleccionado materiales.",
                    "Favor de seleccionar al menos un material.");
            alert.showAndWait();
            isValidated[0] = false;
            //Se han validado correctamente los datos
        }else if(isValidated[0]) {
            sendMaterialListToParentController(myListaMateriales);
            cerrarVentanActual();
        }
    }

    private void sendMaterialListToParentController(List<Material> listaMateriales){
        productViewController.setListaMateriales(listaMateriales);
    }

    /*
    *Método que cicla por toda la lista de materiales y valida si los datos ingresados son correctos, si lo son los
     * agrega a la lista que se mando como parámetro para luego ser manipulada por el método que mandó a llamar a este
      * método. Como aclaracion, las variables usadas en Lambdas deben ser declaradas final.
    */
    private void validateMaterialItems(List<Material> myListaMateriales, boolean[] isValidated, boolean[] atLeastOneSelected, boolean[] allWithCantidades) {
        items.forEach((Material curMaterial) -> {
            if (curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() > 0.0){
                myListaMateriales.add(curMaterial);
                atLeastOneSelected[0] = true;
            }else if (curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() == 0.0){
                allWithCantidades[0] = false;
                isValidated[0] = false;
            }
        });
    }

    /*
    *Método que crea Alertas. Simple y sencillo.
    */
    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    /*
    *Método que cierra la ventana actual.
    */
    private void cerrarVentanActual(){
        Stage stage = (Stage) guardarButton.getScene().getWindow();
        stage.close();
    }

    /*
    *Método que recibe como parámetro el controlador de la vista padre. Este controlador se utiliza para comunicarse
     * con la vista padre. Un ejemplo del uso de este controlador se encuentra en el método
     * sendMaterialListToParentController()
    */
    public void setParent(ProductViewController controller){
        this.productViewController = controller;
        disableFields(consulta);
    }

    /*
    *Método llamado por la vista padre que se encarga de encontrar los materiales que han sido seleccionados por medio
    * de un filtro para cargar la lista de materiales con los datos correspondientes.
    */
    public void setSelectedMaterials(List<Material> selectedMateriales){
        for(Material material : items)
            selectedMateriales.stream().filter(curMaterial ->
                    material.getIdMaterial().equals(curMaterial.getIdMaterial())).forEach(curMaterial -> {
                material.setSelected(curMaterial.isSelected());
                material.setCantidadSeleccionada(curMaterial.getCantidadSeleccionada());
            });
        materialesListView.setItems(items);
    }

    //Método seter del boolean consulta
    public void isConsulta(boolean consulta){
        this.consulta = consulta;
    }

    //Método que dehabilita el botón de guardar cuando sólo se están consultando los materiales.
    private void disableFields(boolean consulta){
            guardarButton.setDisable(consulta);
    }

}
