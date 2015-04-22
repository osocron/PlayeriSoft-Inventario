package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TextField searchTextView;
    @FXML
    private ListView prodListView;
    @FXML
    private Button nuevoButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void abrirProductView(ActionEvent event){
        
    }

}
