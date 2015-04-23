package playeriSoft.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by osocr_000 on 23/04/2015.
 */
public class ViewOpener {

    public ViewOpener(){}

    //Method to controll opening new windows
    public int openView(ActionEvent event, String pathToFXML, String stageTitle, int sceneWidth, int sceneHeight){
        try {
            //Set root and Stage
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathToFXML));
            Stage stage = new Stage();
            stage.setTitle(stageTitle);
            stage.setScene(new Scene(root, sceneWidth, sceneHeight));
            stage.show();

            //Hide Current Window
            ((Node)(event.getSource())).getScene().getWindow().hide();
            return 0;

        } catch (IOException e) {
            return 1;
        }
    }

}
