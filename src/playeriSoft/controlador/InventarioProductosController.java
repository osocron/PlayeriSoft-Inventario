package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    //Local variables
    private InventarioHandler myHandler;

    private InventarioProductosController inventarioProductosController = this;

    private ObservableList<Producto> items = FXCollections.observableArrayList();

    @FXML
    private ListView<Producto> prodListView;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(oldValue, newValue);
        });
    }

    @FXML
    public void abrirProductView(){
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openEditableProductView("playeriSoft/vista/producto-view.fxml", "Nuevo Producto", this);
    }

    public void prepareListView(){
        myHandler = new InventarioHandler();
        //Set items on ListView
        items = myHandler.getAllProducts(items);
        prodListView.setItems(items);
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
                    ViewOpener myViewOpener = new ViewOpener();
                    Producto curProd = prodListView.getSelectionModel().getSelectedItem();
                    if(curProd != null) {
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
        items.removeAll(items);
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
