package playeriSoft.controlador;

import javafx.collections.ObservableList;
import playeriSoft.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Noe on 27/04/15.
 */
public class InventarioHandler {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public InventarioHandler(){

    }

    public ObservableList<Producto> getAllProducts(ObservableList<Producto> items){
        MysqlConnector myConnector = new MysqlConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM Producto");
            resultSet = preparedStatement.executeQuery();
            int cont = 0;
            Producto curProd;
            while(resultSet.next()){
                curProd = new Producto(resultSet.getString("IdProducto"),resultSet.getDouble("Descuento"),
                        resultSet.getString("DescripProd"),resultSet.getInt("Existencias"),
                        resultSet.getDouble("PrecioMayoreo"),resultSet.getDouble("PrecioMenudeo"));
                items.add(cont,curProd);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
