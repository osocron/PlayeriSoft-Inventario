package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import playeriSoft.modelo.Playera;
import playeriSoft.modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    private Producto myProducto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void abrirProductView(ActionEvent event){
        //To eras
        myProducto = new Playera("PLAY12",0.13,"Playera Pateumecha",2,500,400,15,"Azul","Polo",true,false);
        //Open Productos Window
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openProductView("playeriSoft/vista/producto-view.fxml","Productos",myProducto);

    }

}
