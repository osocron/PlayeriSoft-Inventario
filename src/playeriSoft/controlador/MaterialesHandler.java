package playeriSoft.controlador;

import javafx.collections.ObservableList;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by osocr_000 on 02/05/2015.
 */
public class MaterialesHandler {

    private Connection connection;
    private ResultSet resultSet;

    public MaterialesHandler(){

    }

    public ObservableList<Material> getAllMateriales(ObservableList<Material> items){
        DBConnector myConnector = new DBConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT * FROM Material");
            int cont = 0;
            Material material;
            while(resultSet.next()){
                material = new Material(resultSet.getString("IdMaterial"),resultSet.getString("DescripMaterial"),
                        resultSet.getDouble("PrecioMaterial"),resultSet.getString("UMedida"),
                        resultSet.getDouble("CantidadMaterial"));
                items.add(cont,material);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Material> getAllMateriales(List<Material> items){
        DBConnector myConnector = new DBConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT * FROM Material");
            int cont = 0;
            Material material;
            while(resultSet.next()){
                material = new Material(resultSet.getString("IdMaterial"),resultSet.getString("DescripMaterial"),
                        resultSet.getDouble("PrecioMaterial"),resultSet.getString("UMedida"),
                        resultSet.getDouble("CantidadMaterial"));
                items.add(cont,material);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
