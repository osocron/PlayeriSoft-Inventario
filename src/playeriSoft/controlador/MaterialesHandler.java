package playeriSoft.controlador;

import javafx.collections.ObservableList;
import playeriSoft.modelo.Material;
import playeriSoft.modelo.Producto;

import java.io.PrintWriter;
import java.io.StringWriter;
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

    public MaterialesHandler(){
    }

    public Collection<Material> getAllMateriales(Collection<Material> items){
        DBConnector myConnector = new DBConnector();
        try {
            Connection connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            ResultSet resultSet = myConnector.getResultSet(connection, "SELECT * FROM Material");
            Material material;
            while(resultSet.next()){
                material = new Material(resultSet.getString("IdMaterial"), resultSet.getString("DescripMaterial"),
                        resultSet.getDouble("PrecioMaterial"), resultSet.getString("UMedida"),
                        resultSet.getDouble("CantidadMaterial"));
                items.add(material);
            }
            connection.close();
            return items;
        } catch (Exception e) {
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al recuperar los materiales", stringWriter.toString());
            return null;
        }

    }

}
