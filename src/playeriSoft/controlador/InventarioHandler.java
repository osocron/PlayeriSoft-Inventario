package playeriSoft.controlador;

import javafx.collections.ObservableList;
import playeriSoft.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Noe on 27/04/15.
 * Esta clase tiene como propósito el realizar consultas a la base de datos y obtener todos
 * los productos para que se puedan visualizar y manipular por otras clases.
 * TODO: Hacer prueba para la clase y separar getAllProducts en submétodos
 */
public class InventarioHandler {

    public InventarioHandler(){

    }
    /*
     *Este metodo recibe como parámetro una lista, se conecta a la Base de Datos, hace una consulta para
      * obtener todos los productos y del resultado que obtiene, hace un ciclo para construir objetos de
       * tipo Producto con los datos obtenidos de la base de datos y luego agregarlos a la lista que se
        * obtuvo como parámetro.
        * Al finalizar, se regresa la lista.
    */
    public ObservableList<Producto> getAllProducts(ObservableList<Producto> items){
        MysqlConnector myConnector = new MysqlConnector();
        try {
            Connection connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Producto");
            ResultSet resultSet = preparedStatement.executeQuery();
            int cont = 0;
            Producto curProd;
            while(resultSet.next()){
                curProd = new Producto(resultSet.getString("IdProducto"), resultSet.getDouble("Descuento"),
                        resultSet.getString("DescripProd"), resultSet.getInt("Existencias"),
                        resultSet.getDouble("PrecioMayoreo"), resultSet.getDouble("PrecioMenudeo"));
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
