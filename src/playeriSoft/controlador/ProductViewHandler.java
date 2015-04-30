package playeriSoft.controlador;

import playeriSoft.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewHandler {


    public ProductViewHandler(){}

    public Producto buildPlayera(Producto producto, String idPlayera){
        Playera playera = null;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM playera WHERE IdProducto = '"+idPlayera+"'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isBordadoString = resultSet.getString("Bordado");
                String isSerigrafiaString = resultSet.getString("Serigrafia");
                Boolean isBordado;
                Boolean isSerigrafia;
                if(isBordadoString.equals("true")){
                    isBordado = true;
                }else{isBordado = false;}
                if(isSerigrafiaString.equals("true")){
                    isSerigrafia = true;
                }else{isSerigrafia = false;}
                playera = new Playera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),resultSet.getString("Estilo"),isBordado,isSerigrafia);
            }
            connection.close();
            return playera;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildSudadera(Producto producto, String idSudadera){
        Sudadera sudadera = null;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM sudadera WHERE IdProducto = '"+idSudadera+"'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isBordadoString = resultSet.getString("Bordado");
                String isSerigrafiaString = resultSet.getString("Serigrafia");
                Boolean isBordado;
                Boolean isSerigrafia;
                if(isBordadoString.equals("true")){
                    isBordado = true;
                }else{isBordado = false;}
                if(isSerigrafiaString.equals("true")){
                    isSerigrafia = true;
                }else{isSerigrafia = false;}
                sudadera = new Sudadera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),isBordado,isSerigrafia);
            }
            connection.close();
            return sudadera;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildGorra(Producto producto, String idGorra){
        Gorro gorra = null;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM gorra WHERE IdProducto = '"+idGorra+"'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isBordadoString = resultSet.getString("Bordado");
                String isSerigrafiaString = resultSet.getString("Serigrafia");
                Boolean isBordado;
                Boolean isSerigrafia;
                if(isBordadoString.equals("true")){
                    isBordado = true;
                }else{isBordado = false;}
                if(isSerigrafiaString.equals("true")){
                    isSerigrafia = true;
                }else{isSerigrafia = false;}
                gorra = new Gorro(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),isBordado,isSerigrafia);
            }
            connection.close();
            return gorra;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Producto buildParche(Producto producto, String idParche){
        Parche parche = null;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = MysqlConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM parche WHERE IdProducto = '"+idParche+"'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String isBordadoString = resultSet.getString("Bordado");
                String isSerigrafiaString = resultSet.getString("Serigrafia");
                Boolean isBordado;
                Boolean isSerigrafia;
                if(isBordadoString.equals("true")){
                    isBordado = true;
                }else{isBordado = false;}
                if(isSerigrafiaString.equals("true")){
                    isSerigrafia = true;
                }else{isSerigrafia = false;}
                parche = new Parche(producto,resultSet.getDouble("Largo"),resultSet.getDouble("Ancho"),isBordado,isSerigrafia);
            }
            connection.close();
            return parche;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
