package playeriSoft.modelo;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by osocr_000 on 02/05/2015.
 */
public class MaterialesHandler {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MaterialesHandler(){

    }

    public ObservableList<Material> getAllMateriales(ObservableList<Material> items){
        MysqlConnector myConnector = new MysqlConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM material");
            resultSet = preparedStatement.executeQuery();
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
