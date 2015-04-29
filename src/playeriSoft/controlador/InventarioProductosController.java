package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import playeriSoft.modelo.Playera;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    //Local variables
    private Producto myProducto;
    private InventarioHandler myHandler;

    @FXML
    private ListView<Producto> prodListView;

    @FXML
    private Button nuevoButton;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareListView();
    }

    public void abrirProductView(ActionEvent event){
        //To eras
        myProducto = new Playera("PLAY12",0.13,"Playera Pateumecha",2,500,400,15,"Azul","Polo",true,false);
        //Open Productos Window
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openProductView("playeriSoft/vista/producto-view.fxml","Productos",myProducto);

    }

    public void prepareListView(){
        myHandler = new InventarioHandler();
        //Set items on ListView
        ObservableList<Producto> items = FXCollections.observableArrayList();
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
                        if(prod != null){
                            setText(prod.getDescripcion());
                        }
                    }
                };
                return cell;
            }
        });
        //Handle mouse clicks on items
        prodListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Producto curProd = prodListView.getSelectionModel().getSelectedItem();

            }
        });
    }

}
