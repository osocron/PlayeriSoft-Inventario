package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Noe on 27/04/15.
 * Esta clase es el controlador para inventario-productos-view.fxml
 */
public class InventarioProductosController implements Initializable{

    private InventarioHandler myHandler;

    private InventarioProductosController inventarioProductosController = this;

    private ObservableList<Producto> items = FXCollections.observableArrayList();

    @FXML
    private ListView<Producto> prodListView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button nuevoButton;

    /*
    *Metodo que al inicializar la vista manda a llamar al método que permiten desplegar los
    * productos al usuario, agrega un listener a la barra de búsqueda y manda a llamar al métdo
    * que agrega tooltips a los distintos nodos de la vista.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
        addListenerToSearchTextField();
        addToolTipsToNodes();
    }

    /*
    *Metodo que se manda a llamar cuando se le da click al botón de Nuevo producto.
     * Este método crea una instancia de la clase ViewOpener para luego abrir la vista de
      * productos detallados especificando que se abra con los campos habillitados y sin datos de
      * algún producto.
    */
    @FXML
    public void abrirProductView(){
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openEditableProductView("playeriSoft/vista/producto-view.fxml", "Nuevo Producto", this);
    }


    private void addListenerToSearchTextField(){
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(oldValue, newValue);
        });
    }

    private void addToolTipsToNodes(){
        searchTextField.setTooltip(new Tooltip("Escribe para filtrar la lista"));
        nuevoButton.setTooltip(new Tooltip("Haz clic para crear un producto nuevo"));
    }

    public void prepareListView(){
        myHandler = new InventarioHandler();
        //Set items on ListView
        items = myHandler.getAllProducts(items);
        prodListView.setItems(items);
        //Agregar un Tooltip al Listview
        Tooltip tooltip = new Tooltip("Haz clic sobre un producto para consultarlo");
        Tooltip.install(prodListView,tooltip);
        //Tweak ListView to display only productos.descripcion
        prodListView.setCellFactory(new Callback<ListView<Producto>, ListCell<Producto>>() {
            @Override
            public ListCell<Producto> call(ListView<Producto> param) {
                ListCell<Producto> cell = new ListCell<Producto>(){
                    @Override
                    protected void updateItem(Producto prod, boolean bool){
                        super.updateItem(prod,bool);
                        if(bool){
                            setText(null);
                            setGraphic(null);
                        }else if(prod != null){
                            setText(prod.getDescripcion());
                        }
                    }
                };
                //Handle mouse clicks
                cell.setOnMouseClicked(event -> {
                    Producto curProd = prodListView.getSelectionModel().getSelectedItem();
                    if(curProd != null) {
                        ViewOpener myViewOpener = new ViewOpener();
                        myViewOpener.openProductViewWithResourceObject("playeriSoft/vista/producto-view.fxml",
                                "Consulta de Producto", curProd, inventarioProductosController);
                    }
                });
                return cell;
            }
        });

    }

    public void refreshListView(){
        prodListView.setItems(null);
        items.clear();
        myHandler = new InventarioHandler();
        items = myHandler.getAllProducts(items);
        prodListView.setItems(items);
    }

    public void searchProducts(String oldVal, String newVal){
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            prodListView.setItems(items);
        }
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<Producto> subentries = FXCollections.observableArrayList();
        for (Producto entry : prodListView.getItems()) {
            boolean match = true;
            String entryText = entry.getDescripcion();
            for ( String part: parts ) {
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
            if ( match ) {
                subentries.add(entry);
            }
        }
        prodListView.setItems(subentries);
    }

}
