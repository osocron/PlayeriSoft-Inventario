package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioProductosController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void abrirProductView(ActionEvent event){
        ViewOpener myViewOpener = new ViewOpener();
        myViewOpener.openView(event,"playeriSoft/vista/producto-view.fxml","Productos",600,400);
    }

}
