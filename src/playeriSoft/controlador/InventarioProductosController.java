package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import playeriSoft.modelo.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    private Producto myProducto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void abrirProductView(ActionEvent event){
        //To eras
        myProducto = new Producto("PLAY12",0.13,"Playera Pateumecha",2,500,400);
        //Open Productos Window
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openProductView("playeriSoft/vista/producto-view.fxml","Productos",myProducto);

    }

}
