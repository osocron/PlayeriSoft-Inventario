package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import playeriSoft.modelo.Playera;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    private Producto myProducto;
    private InventarioHandler myHandler;

    @FXML
    private ListView<String> prodListView;

    @FXML
    private Button nuevoButton;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myHandler = new InventarioHandler();
        ObservableList<String> items = FXCollections.observableArrayList();
        items = myHandler.getAllProducts(items);
        prodListView.setItems(items);
    }

    public void abrirProductView(ActionEvent event){
        //To eras
        myProducto = new Playera("PLAY12",0.13,"Playera Pateumecha",2,500,400,15,"Azul","Polo",true,false);
        //Open Productos Window
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openProductView("playeriSoft/vista/producto-view.fxml","Productos",myProducto);

    }

}
