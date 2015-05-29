package playeriSoft.controlador;

import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import playeriSoft.modelo.Producto;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        * obtuvo como parámetro. Al finalizar, se regresa la lista.
    */
    public ObservableList<Producto> getAllProducts(ObservableList<Producto> items){
        DBConnector myConnector = new DBConnector();
        try {
            Connection connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            ResultSet resultSet = myConnector.getResultSet(connection, "SELECT * FROM Producto");
            Producto curProd;
            while(resultSet.next()){
                curProd = new Producto(resultSet.getString("IdProducto"), resultSet.getDouble("Descuento"),
                        resultSet.getString("DescripProd"), resultSet.getInt("Existencias"),
                        resultSet.getDouble("PrecioMayoreo"), resultSet.getDouble("PrecioMenudeo"));
                items.add(curProd);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al recuperar los productos", stringWriter.toString());
            return null;
        }
    }

}
