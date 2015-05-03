package playeriSoft.controlador;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import playeriSoft.modelo.InventarioHandler;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    //Local variables
    private InventarioHandler myHandler;

    private ObservableList<Producto> items = FXCollections.observableArrayList();

    @FXML
    private ListView<Producto> prodListView;

    @FXML
    private Button nuevoButton;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchProducts(oldValue, newValue);
            }
        });
    }

    @FXML
    public void abrirProductView(ActionEvent event){
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openEditableProductView("playeriSoft/vista/producto-view.fxml", "Nuevo Producto");
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
                        }else {
                            if (prod != null) {
                                setText(prod.getDescripcion());
                            }
                        }
                    }
                };
                //Handle mouse clicks
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ViewOpener myViewOpener = new ViewOpener();
                        Producto curProd = prodListView.getSelectionModel().getSelectedItem();
                        if(curProd != null) {
                            myViewOpener.openProductViewWithResourceObject("playeriSoft/vista/producto-view.fxml", "Consulta de Producto", curProd);
                        }
                    }
                });
                return cell;
            }
        });

    }

    @FXML
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
