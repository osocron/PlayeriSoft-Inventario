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

    private MysqlConnector myConnector = new MysqlConnector();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductViewHandler(){}

    public Producto buildPlayera(Producto producto, String idPlayera){
        Playera playera = null;
        try {
            resultSet = getResultSetForBuildingProduct("playera", idPlayera);
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
            resultSet = getResultSetForBuildingProduct("sudadera", idSudadera);
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
            resultSet = getResultSetForBuildingProduct("gorra", idGorra);
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
            resultSet = getResultSetForBuildingProduct("parche", idParche);
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

    private ResultSet getResultSetForBuildingProduct(String table, String idProd){
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

    private ResultSet getResultSetToGetID(String table, String bordadoSerigrafia){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            preparedStatement = connection.prepareStatement("SELECT IdProducto FROM " + table + " WHERE IdProducto LIKE '%"+bordadoSerigrafia+"%' ORDER BY IdProducto DESC LIMIT 1");
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
        String productID = getNextID("Playera",isBordado);


    }

    public void guardarSudadera(double descuento, String descripcion, int existencias,
                                double precioMayoreo, double precioMenudeo,double talla, String color,
                                boolean isBordado, boolean isSerigrafia){
        String productID = getNextID("Sudadera",isBordado);
    }

    public void guardarGorra(double descuento, String descripcion, int existencias,
                             double precioMayoreo, double precioMenudeo, double talla,
                             String color, boolean isBordado, boolean isSerigrafia){
        String productID = getNextID("Gorra",isBordado);
    }

    public void guardarParche(double descuento, String descripcion, int existencias,
                              double precioMayoreo, double precioMenudeo, double largo,
                              double ancho, boolean isBordado){
        String productID = getNextID("Parche",isBordado);
        guardarProducto(productID,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Parche VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,productID);
            preparedStatement.setDouble(2, largo);
            preparedStatement.setDouble(3, ancho);
            if(isBordado) {
                preparedStatement.setString(4, "true");
                preparedStatement.setString(5, "false");
            }else{
                preparedStatement.setString(4, "false");
                preparedStatement.setString(5, "true");
            }
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void guardarProducto(String idProducto, double descuento, String descripcion, int existencias,
                                 double precioMayoreo, double precioMenudeo){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Producto VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,idProducto);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setInt(3, existencias);
            preparedStatement.setDouble(4, descuento);
            preparedStatement.setDouble(5,precioMayoreo);
            preparedStatement.setDouble(6,precioMenudeo);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*Metodo que se encarga de calcular el siguiente ID para el producto, tomando en cuenta el tipo de
    *producto y los valores existentes en la base de datos*/
    public String getNextID(String table, Boolean isBordado){
        String nextID = null;
        String bordadoSerigrefia  = isBordado ? "BORD" : "SERI";
        try {
            resultSet = getResultSetToGetID(table,bordadoSerigrefia);
            while (resultSet.next()) {
                nextID = resultSet.getString("IdProducto");
            }
            connection.close();
        }catch (Exception e){
            return nextID;
        }
        if(nextID != null && !nextID.isEmpty()) {
            nextID = getNextNumber(nextID);
        }else{
            nextID = table.substring(0,4).toUpperCase() + bordadoSerigrefia + "0001";
        }
        return nextID;
    }

    /*Metodo que se encarga de recibir un Id de Producto, calcular el siguiente numero consetcutivo y agregarlo
    * remplazarlo por el original, asi consiguiendo el nuevo numero consecutivo para el ID del Producto*/
    private String getNextNumber(String nextID){
        String nextNumber = String.format("%04d",(Integer.valueOf(nextID.substring(8,nextID.length())) + 1));
        return nextID.substring(0,8)+nextNumber;
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
