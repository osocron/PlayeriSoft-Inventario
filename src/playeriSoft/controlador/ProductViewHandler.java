package playeriSoft.controlador;

import playeriSoft.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewHandler {

    private MysqlConnector myMysqlConnector;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductViewHandler(){}

    public Producto buildPlayera(Producto producto, String idPlayera){
        Playera playera;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM playera WHERE IdProducto = '"+idPlayera+"'");
            resultSet = preparedStatement.executeQuery();
            Boolean isBordado;
            Boolean isSerigrafia;
            Integer tinyBordado = resultSet.getInt("Bordado");
            if(tinyBordado == 1){
                isBordado = true;
            }else{isBordado = false;}
            if((resultSet.getInt("Serigrafia"))==1){
                isSerigrafia = true;
            }else{isSerigrafia = false;}
            playera = new Playera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),resultSet.getString("Estilo"),isBordado,isSerigrafia);
            connection.close();
            return playera;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildSudadera(Producto producto, String idSudadera){
        Sudadera sudadera;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM sudadera WHERE IdProducto = '"+idSudadera+"'");
            resultSet = preparedStatement.executeQuery();
            Boolean isBordado;
            Boolean isSerigrafia;
            if((resultSet.getInt("Bordado"))==1){
                isBordado = true;
            }else{isBordado = false;}
            if((resultSet.getInt("Serigrafia"))==1){
                isSerigrafia = true;
            }else{isSerigrafia = false;}
            sudadera = new Sudadera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),isBordado,isSerigrafia);
            connection.close();
            return sudadera;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildGorra(Producto producto, String idGorra){
        Gorro gorra;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM gorra WHERE IdProducto = '"+idGorra+"'");
            resultSet = preparedStatement.executeQuery();
            Boolean isBordado;
            Boolean isSerigrafia;
            if((resultSet.getInt("Bordado"))==1){
                isBordado = true;
            }else{isBordado = false;}
            if((resultSet.getInt("Serigrafia"))==1){
                isSerigrafia = true;
            }else{isSerigrafia = false;}
            gorra = new Gorro(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),isBordado,isSerigrafia);
            connection.close();
            return gorra;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildParche(Producto producto, String idParche){
        Parche parche;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM parche WHERE IdProducto = '"+idParche+"'");
            resultSet = preparedStatement.executeQuery();
            parche = new Parche(producto,resultSet.getDouble("Largo"),resultSet.getDouble("Ancho"));
            connection.close();
            return parche;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
