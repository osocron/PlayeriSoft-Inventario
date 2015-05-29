package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import playeriSoft.modelo.Producto;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Noe on 27/04/15.
 * Esta clase es el controlador para inventario-productos-view.fxml y se encarga de mostrar los productos
 * al usuario, filtrarlos si el usuario desea, y proveer de eventos de click a las celdas de la lista de
 * productos y al botón de Nuevo.
 * TODO: Hacer pruebas!
 */
public class InventarioProductosController implements Initializable {

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
      * productos.
    */
    @FXML
    public void abrirProductView() {
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openEditableProductView("playeriSoft/vista/producto-view.fxml", "Nuevo Producto", this);
    }

    /*
    *Método que le asigna un Listener a la barra de búsqueda para que al ingresar caracteres
     * se pueda filtrar la lista. La forma en que se filtra la lista se especifica en el método
      * searchProducts()
    */
    private void addListenerToSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(oldValue, newValue);
        });
    }

    /*
    *Método que agrega Tooltips a la barra de búsqueda, al botón de Nuevo y a la lista de
    * productos.
    */
    private void addToolTipsToNodes() {
        searchTextField.setTooltip(new Tooltip("Escribe para filtrar la lista"));
        nuevoButton.setTooltip(new Tooltip("Haz clic para crear un producto nuevo"));
        Tooltip tooltip = new Tooltip("Haz clic sobre un producto para consultarlo");
        Tooltip.install(prodListView, tooltip);
    }

    /*
    *Método que prepara la lista de productos llamando a un método para obtener los productos
     * de la base de datos, otro para crear celdas modificadas que permitan desplegar la información
     * detallada del producto que contienen cuando el usuario le de clic a la celda.
    */
    private void prepareListView() {
        getProductsForListView();
        createCustomCells();
    }

    /*
    *Método que crea una instancia de InventarioHandler para obtener los productos de la base de datos.
    * Una vez obtenidos los productos, los agrega a la lista de productos con el método setItems();
    */
    private void getProductsForListView() {
        InventarioHandler myHandler = new InventarioHandler();
        items = myHandler.getAllProducts(items);
        prodListView.setItems(items);
    }

    /*
    * Método que crea Celdas modificadas para la lista de Productos. Estas celdas tienen la propiedad
    * de mostrar la descripción del Producto que contienen y de responder a eventos de ratón por parte
    * del usuario por medio del método setOnMouseClicked();
    */
    private void createCustomCells() {
        prodListView.setCellFactory(param -> {
            ListCell<Producto> cell = (ListCell<Producto>) getCellWithUpdateItemOverriden();
            cell.setOnMouseClicked(event -> handleMouseClickOnCell());
            return cell;
        });
    }

    /*
    *Método que devuelve una celda modificada para que despliege la descripción del producto que
     *contiene por medio del método setText(). También se asegura de que si la celda esta vacía
     * no despliege información alguna y que solo despliege la información del producto si el objeto
     * producto que contiene no es nulo.
    */
    private Cell<Producto> getCellWithUpdateItemOverriden() {
        return new ListCell<Producto>() {
            @Override
            protected void updateItem(Producto prod, boolean bool) {
                super.updateItem(prod, bool);
                if (bool) {
                    setText(null);
                    setGraphic(null);
                } else if (prod != null) {
                    setText(prod.getDescripcion());
                }
            }
        };
    }

    /*
    *Método que especifica la acción que se tomará cuando el usuario le de click a una celda.
     * Cuando el usuario da click a la celda se crea una instancia de ViewOpener para abrir la
      * pantalla de productos detallados y se le pasa como argumentos el nombre del archivo fxml,
       * el títlulo que se desea que aparezca en la pantalla, una instancia de esta clase y el objeto
       * de tipo Producto que pertenece a la celda a la que se le dió click.
    */
    private void handleMouseClickOnCell() {
        InventarioProductosController inventarioProductosController = this;
        Producto curProd = prodListView.getSelectionModel().getSelectedItem();
        if (curProd != null) {
            ViewOpener myViewOpener = new ViewOpener();
            myViewOpener.openProductViewWithResourceObject("playeriSoft/vista/producto-view.fxml",
                    "Consulta de Producto", curProd, inventarioProductosController);
        }
    }

    /*
    *Método que quita los productos de la lista de productos, vacía la lista y manda a llamar
     * el método para volver a cargar la lista de productos de elementos de la base de datos
    */
    public void refreshListView() {
        prodListView.setItems(null);
        items.clear();
        getProductsForListView();
    }

    /*
    *Método que filtra los productos de la lista de productos comparando el texto que el usuario
     * escribe contra la descripción de los productos de la lista de productos. Esto se logra haciendo
      * un ciclo sobre la lista de productos, obteniendo la descripción de cada producto y comparandola
       * con el texto ingresado por el usuario utilizando el método contains(). Si el texto que el usuario
       * escribio es parte de la descripción del producto ese producto se agrega a una lista de productos
       * provicional que finalmente se asigna al ListView.
    */
    private void searchProducts(String oldVal, String newVal) {
        if (oldVal != null && (newVal.length() < oldVal.length()))
            prodListView.setItems(items);
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<Producto> subentries = FXCollections.observableArrayList();
        for (Producto entry : prodListView.getItems()) {
            boolean match = true;
            String entryText = entry.getDescripcion();
            for (String part : parts) {
                if (!entryText.toUpperCase().contains(part)) {
                    match = false;
                    break;
                }
            }
            if (match)
                subentries.add(entry);
        }
        prodListView.setItems(subentries);
    }
}
