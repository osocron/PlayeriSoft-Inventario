package playeriSoft.controlador;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Noe on 27/04/15.
 */
public class InventarioHandler {

    private MysqlConnector myConnector;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InventarioHandler(){

    }

    public ObservableList<String> getAllProducts(ObservableList<String> items){

        try {
            connection = myConnector.connectToMysqlDB("playeriSoft","playeriSoftAdmin","playerisoft1","localhost");
            preparedStatement = connection.prepareStatement("SELECT DescripProd FROM Producto");
            resultSet = preparedStatement.executeQuery();
            int cont = 0;
            while(resultSet.next()){
                items.add(cont,resultSet.getString("DescripProd"));
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
