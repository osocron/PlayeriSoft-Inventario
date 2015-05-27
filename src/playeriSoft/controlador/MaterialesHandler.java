package playeriSoft.controlador;

import javafx.collections.ObservableList;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by osocr_000 on 02/05/2015.
 * Clase que se encarga de conectarse a ala base de datos para recuperar la información de los materiales y
 * regresarlos en una colección que puede ser utilizada para manipular los datos de los materiales.
 */
public class MaterialesHandler {

    private Connection connection;
    private ResultSet resultSet;

    public MaterialesHandler(){
    }

    public Collection<Material> getAllMateriales(Collection<Material> items){
        DBConnector myConnector = new DBConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT * FROM Material");
            Material material;
            while(resultSet.next()){
                material = new Material(resultSet.getString("IdMaterial"),resultSet.getString("DescripMaterial"),
                        resultSet.getDouble("PrecioMaterial"),resultSet.getString("UMedida"),
                        resultSet.getDouble("CantidadMaterial"));
                items.add(material);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
