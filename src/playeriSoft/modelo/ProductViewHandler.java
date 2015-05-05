package playeriSoft.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noe on 24/04/15.
 */
public class ProductViewHandler {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductViewHandler(){}

    public Producto buildPlayera(Producto producto, String idPlayera){
        Playera playera = null;
        try {
            resultSet = getResultSet("playera", idPlayera);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
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
        try {
            resultSet = getResultSet("sudadera",idSudadera);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
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
        try {
            resultSet = getResultSet("gorra",idGorra);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
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
        try {
            resultSet = getResultSet("parche",idParche);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
                parche = new Parche(producto,resultSet.getDouble("Largo"),resultSet.getDouble("Ancho"),isBordado,isSerigrafia);
            }
            connection.close();
            return parche;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private ResultSet getResultSet(String table, String idProd){
        MysqlConnector myConnector = new MysqlConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM "+ table + " WHERE IdProducto = '" + idProd + "'");
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean getValueOfBordado(ResultSet resultSet){
        try {
            Boolean isBordado;
            String isBordadoString = resultSet.getString("Bordado");
            if(isBordadoString.equals("true")){
                isBordado = true;
            }else{isBordado = false;}
            return isBordado;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean getValueOfSerigrafia(ResultSet resultSet){
        try {
            Boolean isSerigrafia;
            String isSerigrafiaString = resultSet.getString("Serigrafia");
            if(isSerigrafiaString.equals("true")){
                isSerigrafia = true;
            }else{isSerigrafia = false;}
            return isSerigrafia;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void guardarPlayera(double descuento, String descripcion, int existencias,
                               double precioMayoreo, double precioMenudeo,double talla, String color,
                               String tipo, boolean isBordado, boolean isSerigrafia){
        /*String idProducto = "";
        MysqlConnector myConnector = new MysqlConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("INSERT INTO producto VALUES('"+ idProducto + "','"+descripcion+"',"+existencias+","+descuento+","+precioMayoreo+","+precioMenudeo);
            resultSet = preparedStatement.executeQuery();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

    public void guardarSudadera(double descuento, String descripcion, int existencias,
                                double precioMayoreo, double precioMenudeo,double talla, String color,
                                boolean isBordado, boolean isSerigrafia){

    }

    public void guardarGorra(double descuento, String descripcion, int existencias,
                             double precioMayoreo, double precioMenudeo, double talla,
                             String color, boolean isBordado, boolean isSerigrafia){

    }

    public void guardarParche(double descuento, String descripcion, int existencias,
                              double precioMayoreo, double precioMenudeo, double largo,
                              double ancho, boolean isBordado, boolean isSerigrafia){

    }

    public String getPlayeraID(){
        return null;
    }

    public String getSudaderaID(){
        return null;
    }

    public String getGorraID(){
        return null;
    }

    public String getParcheID(){
        return null;
    }

    //Este metodo regresa las coincidencias del producto dado en la Tabla de RMaterialProducto
    public List<Material> getSelectedMateriales(List<Material> materialesSeleccionados, Producto curProduct){
        MaterialesHandler handler = new MaterialesHandler();
        String idProducto = curProduct.getIdProducto();
        List<Material> todosMateriales = new ArrayList<Material>();
        todosMateriales = handler.getAllMateriales(todosMateriales);
        List<String[]> listaIdMateriales = new ArrayList<String[]>();
        MysqlConnector myConnector = new MysqlConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT * FROM RMaterialProducto WHERE IdProducto = '"+idProducto+"'");
            resultSet = preparedStatement.executeQuery();
            int cont = 0;
            while(resultSet.next()){
                String[] idCantidad = new String[2];
                idCantidad[0] = resultSet.getString("IdMaterial");
                idCantidad[1] = String.valueOf(resultSet.getDouble("Cantidad"));
                listaIdMateriales.add(idCantidad);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for(Material material : todosMateriales){
            for(String[] idMaterial : listaIdMateriales){
                if (material.getIdMaterial().equals(idMaterial[0])){
                    material.setSelected(true);
                    material.setCantidadSeleccionada(Double.valueOf(idMaterial[1]));
                    materialesSeleccionados.add(material);
                }
            }
        }
        return materialesSeleccionados;
    }
}
