package playeriSoft.controlador;
import playeriSoft.modelo.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**Created by Noe on 24/04/15.
 *Clase que se encarga de realizar la comunicación entre la clase ProductViewCotroller y la base de datos, realizando
 * las operaciones necesarias para guardar, actualizar y eliminar productos.*/
public class ProductViewHandler {

    private DBConnector myConnector = new DBConnector();
    private Connection connection;
    private ResultSet resultSet;
    private MailSender mailSender;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    public ProductViewHandler(){
        mailSender = new MailSender();
        stringWriter = new StringWriter();
    }
    /**
     *Método que crea un objeto de tipo Playera en base a la información recuperada de la base de datos.* */
    public Producto buildPlayera(Producto producto, String idPlayera){
        Playera playera = null;
        try {
            resultSet = getResultSetForBuildingProduct("Playera", idPlayera);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
                playera = new Playera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),
                        resultSet.getString("Estilo"),isBordado,isSerigrafia);
            }
            connection.close();
            return playera;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear el objeto Playera", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que crea un objeto de tipo Sudadera en base a la información recuperada de la base de datos.* */
    public Producto buildSudadera(Producto producto, String idSudadera){
        Sudadera sudadera = null;
        try {
            resultSet = getResultSetForBuildingProduct("Sudadera", idSudadera);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
                sudadera = new Sudadera(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),
                        isBordado,isSerigrafia);
            }
            connection.close();
            return sudadera;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear el objeto Sudadera", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que crea un objeto de tipo Gorra en base a la información recuperada de la base de datos.* */
    public Producto buildGorra(Producto producto, String idGorra){
        Gorro gorra = null;
        try {
            resultSet = getResultSetForBuildingProduct("Gorra", idGorra);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
                gorra = new Gorro(producto,resultSet.getDouble("Talla"),resultSet.getString("Color"),
                        isBordado,isSerigrafia);
            }
            connection.close();
            return gorra;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear el objeto Gorra", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que crea un objeto de tipo Parche en base a la información recuperada de la base de datos.* */
    public Producto buildParche(Producto producto, String idParche){
        Parche parche = null;
        try {
            resultSet = getResultSetForBuildingProduct("Parche", idParche);
            while (resultSet.next()) {
                Boolean isBordado = getValueOfBordado(resultSet);
                Boolean isSerigrafia = getValueOfSerigrafia(resultSet);
                parche = new Parche(producto,resultSet.getDouble("Largo"),resultSet.getDouble("Ancho"),
                        isBordado,isSerigrafia);
            }
            connection.close();
            return parche;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear el objeto Parche", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que regresa el resultset necesario para crear un objeto de tipo Producto.
     * */
    private ResultSet getResultSetForBuildingProduct(String table, String idProd){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT * FROM "+ table +
                    " WHERE IdProducto = '" + idProd + "'");
            return resultSet;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al obtener el resultset para crear un Producto", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que regresa el resultset necesario para obtener el ID del producto que se agregará a la base de datos.* */
    private ResultSet getResultSetToGetID(String table, String bordadoSerigrafia){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT IdProducto FROM " + table +
                    " WHERE IdProducto LIKE '%"+bordadoSerigrafia+"%' ORDER BY IdProducto DESC LIMIT 1");
            return resultSet;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al obtener el resultset para obtener el ID del producto",
                    stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que convierte la cadena de caracteres de la columna Bordado en la base de datos a un booleano.
     * */
    public Boolean getValueOfBordado(ResultSet resultSet){
        try {
            Boolean isBordado;
            String isBordadoString = resultSet.getString("Bordado");
            if(isBordadoString.equals("true")){
                isBordado = true;
            }else{isBordado = false;}
            return isBordado;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al obtener el String Bordado del resultset", stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que convierte la cadena de caracteres de la columna Serigrafia en la base de datos a un booleano.* */
    public Boolean getValueOfSerigrafia(ResultSet resultSet){
        try {
            Boolean isSerigrafia;
            String isSerigrafiaString = resultSet.getString("Serigrafia");
            if(isSerigrafiaString.equals("true")){
                isSerigrafia = true;
            }else{isSerigrafia = false;}
            return isSerigrafia;
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear al obtener el String Serigrafia del resultSet",
                    stringWriter.toString());
            return null;
        }
    }

    /**
     * Método que se encarga de guardar una playera a la base de datos.
     * */
    public int guardarPlayera(double descuento, String descripcion, int existencias,
                               double precioMayoreo, double precioMenudeo,double talla, String color,
                               String tipo, boolean isBordado, List<Material> listaMateriales){
        String productID = getNextID("Playera",isBordado);
        guardarProducto(productID,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Playera VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,productID);
            preparedStatement.setDouble(2, talla);
            preparedStatement.setString(3, color);
            preparedStatement.setString(4, tipo.substring(0,4).toUpperCase());
            if(isBordado) {
                preparedStatement.setString(5, "true");
                preparedStatement.setString(6, "false");
            }else{
                preparedStatement.setString(5, "false");
                preparedStatement.setString(6, "true");
            }
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al guardar la Playera a la base de datos", stringWriter.toString());
            return 0;
        }
        guardarMateriales(listaMateriales, productID);
        return 1;
    }

    /**
     * Método que se encarga de guardar una sudadera en la base de datos.
     * */
    public int guardarSudadera(double descuento, String descripcion, int existencias,
                                double precioMayoreo, double precioMenudeo,double talla, String color,
                                boolean isBordado, List<Material> listaMateriales){
        String productID = getNextID("Sudadera",isBordado);
        guardarProducto(productID,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Sudadera VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, productID);
            preparedStatement.setDouble(2, talla);
            preparedStatement.setString(3, color);
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
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al guardar la Sudadera a la base de datos", stringWriter.toString());
            return 0;
        }
        guardarMateriales(listaMateriales, productID);
        return 1;
    }

    /**
     * Método que se encarga de guardar una Gorra en la base de datos.
     * */
    public int guardarGorra(double descuento, String descripcion, int existencias,
                             double precioMayoreo, double precioMenudeo, double talla,
                             String color, boolean isBordado, List<Material> listaMateriales){
        String productID = getNextID("Gorra",isBordado);
        guardarProducto(productID,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Gorra VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, productID);
            preparedStatement.setDouble(2, talla);
            preparedStatement.setString(3, color);
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
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al guardar la gorra a la base de datos", stringWriter.toString());
            return 0;
        }
        guardarMateriales(listaMateriales, productID);
        return 1;
    }

    /**
     * Méotdo que se encarga de guardar un Parche en la base de datos.
     * */
    public int guardarParche(double descuento, String descripcion, int existencias,
                              double precioMayoreo, double precioMenudeo, double largo,
                              double ancho, boolean isBordado, List<Material> listaMateriales){
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
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al guardar el parche a la base de datos", stringWriter.toString());
            return 0;
        }
        guardarMateriales(listaMateriales, productID);
        return 1;
    }

    /**
     * Método que se encarga de guardar los materiales seleccionados en la relación de producto con materiales
     * */
    private void guardarMateriales(List<Material> listaMateriales, String productoID){
        for(Material curMaterial : listaMateriales) {
            try {
                connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
                String statement = "INSERT INTO RMaterialProducto VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setString(1, productoID);
                preparedStatement.setString(2, curMaterial.getIdMaterial());
                preparedStatement.setDouble(3, curMaterial.getCantidadSeleccionada());
                preparedStatement.execute();
                connection.close();
            } catch (Exception e) {
                printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                mailSender.sendMail("Error al guardar los materiales en la base de datos", stringWriter.toString());
            }
        }
    }

    /**
     * Método que se encarga de guardar un Producto en la base de datos.
     * */
    private int guardarProducto(String idProducto, double descuento, String descripcion, int existencias,
                                 double precioMayoreo, double precioMenudeo){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "INSERT INTO Producto VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,idProducto);
            preparedStatement.setDouble(2, descuento);
            preparedStatement.setString(3, descripcion);
            preparedStatement.setInt(4, existencias);
            preparedStatement.setDouble(5,precioMayoreo);
            preparedStatement.setDouble(6,precioMenudeo);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al guardar el producto en la base de datos", stringWriter.toString());
            return 0;
        }
        return 1;
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
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear el obtener el ID del producto", stringWriter.toString());
            return nextID;
        }
        if(nextID != null && !nextID.isEmpty())
            nextID = getNextNumber(nextID);
        else nextID = table.substring(0, 4).toUpperCase() + bordadoSerigrefia + "0001";
        return nextID;
    }

    /*Metodo que se encarga de recibir un Id de Producto, calcular el siguiente numero consetcutivo y agregarlo
    * remplazarlo por el original, asi consiguiendo el nuevo numero consecutivo para el ID del Producto*/
    private String getNextNumber(String nextID){
        String nextNumber = String.format("%04d",(Integer.valueOf(nextID.substring(8,nextID.length())) + 1));
        return nextID.substring(0,8)+nextNumber;
    }


    /**
     * Método que regresa las coincidencias de materiales con el producto dado.
     * */
    public List<Material> getSelectedMateriales(List<Material> materialesSeleccionados, Producto curProduct){
        MaterialesHandler handler = new MaterialesHandler();
        String idProducto = curProduct.getIdProducto();
        List<Material> todosMateriales = new ArrayList<Material>();
        todosMateriales = (List<Material>) handler.getAllMateriales(todosMateriales);
        List<String[]> listaIdMateriales = new ArrayList<String[]>();
        DBConnector myConnector = new DBConnector();
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            resultSet = myConnector.getResultSet(connection,"SELECT * FROM RMaterialProducto WHERE IdProducto = '"
                    + idProducto + "'");
            while(resultSet.next()){
                String[] idCantidad = new String[2];
                idCantidad[0] = resultSet.getString("IdMaterial");
                idCantidad[1] = String.valueOf(resultSet.getDouble("Cantidad"));
                listaIdMateriales.add(idCantidad);
            }
            connection.close();
        } catch (Exception e) {
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al obetner la información de la relación de materiales con productos" +
                    "para obtener los materiales seleccionados", stringWriter.toString());
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

    /**
     * Método que se encarga de actualizar un producto de la base de datos.
     * */
    private void actualizarProducto(String idProducto, double descuento, String descripcion, int existencias,
                                    double precioMayoreo, double precioMenudeo){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "UPDATE Producto SET Descuento = ?, DescripProd = ?, Existencias = ?, PrecioMayoreo = ?, PrecioMenudeo = ? WHERE IdProducto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDouble(1, descuento);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setInt(3, existencias);
            preparedStatement.setDouble(4,precioMayoreo);
            preparedStatement.setDouble(5,precioMenudeo);
            preparedStatement.setString(6,idProducto);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método que se encarga de actualizar una playera.
     * */
    public void actualizarPlayera(String idProducto, double descuento, String descripcion, int existencias,
                                   double precioMayoreo, double precioMenudeo,double talla, String color,
                                   String tipo, boolean isBordado, List<Material> listaMateriales ){
        actualizarProducto(idProducto, descuento, descripcion, existencias, precioMayoreo, precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "UPDATE Playera SET Talla = ?, Color = ?, Estilo = ?, Bordado = ?, Serigrafia = ? WHERE IdProducto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDouble(1, talla);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, tipo.substring(0,4).toUpperCase());
            if(isBordado) {
                preparedStatement.setString(4, "true");
                preparedStatement.setString(5, "false");
            }else{
                preparedStatement.setString(4, "false");
                preparedStatement.setString(5, "true");
            }
            preparedStatement.setString(6, idProducto);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        eliminarMaterial(idProducto);
        guardarMateriales(listaMateriales, idProducto);
    }

    /**
     * Método que se encarga de actualizar
     * */
    public void actualizarSudadera(String idProducto, double descuento, String descripcion, int existencias,
                                double precioMayoreo, double precioMenudeo,double talla, String color,
                                boolean isBordado, List<Material> listaMateriales){
        actualizarProducto(idProducto,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "UPDATE Sudadera SET Talla = ?, Color = ?, Bordado = ?, Serigrafia = ? WHERE IdProducto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDouble(1, talla);
            preparedStatement.setString(2, color);
            if(isBordado) {
                preparedStatement.setString(3, "true");
                preparedStatement.setString(4, "false");
            }else{
                preparedStatement.setString(3, "false");
                preparedStatement.setString(4, "true");
            }
            preparedStatement.setString(5, idProducto);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        eliminarMaterial(idProducto);
        guardarMateriales(listaMateriales, idProducto);
    }

    /**
     * Método que se encarga de actualizar una gorra
     * */
    public void actualizarGorra(String idProducto, double descuento, String descripcion, int existencias,
                             double precioMayoreo, double precioMenudeo, double talla,
                             String color, boolean isBordado, List<Material> listaMateriales){
        actualizarProducto(idProducto,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "UPDATE Gorra SET Talla = ?, Color = ?, Bordado = ?, Serigrafia = ? WHERE IdProducto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDouble(1, talla);
            preparedStatement.setString(2, color);
            if(isBordado) {
                preparedStatement.setString(3, "true");
                preparedStatement.setString(4, "false");
            }else{
                preparedStatement.setString(3, "false");
                preparedStatement.setString(4, "true");
            }
            preparedStatement.setString(5, idProducto);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        eliminarMaterial(idProducto);
        guardarMateriales(listaMateriales, idProducto);
    }

    /**
     * Método que se encarga de actualizar un parche
     * */
    public void actualizarParche(String idProducto, double descuento, String descripcion, int existencias,
                              double precioMayoreo, double precioMenudeo, double largo,
                              double ancho, boolean isBordado, List<Material> listaMateriales){
        actualizarProducto(idProducto,descuento,descripcion,existencias,precioMayoreo,precioMenudeo);
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "UPDATE Parche SET Largo = ?, Ancho = ?, Bordado = ?, Serigrafia = ? WHERE IdProducto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setDouble(1, largo);
            preparedStatement.setDouble(2, ancho);
            if(isBordado) {
                preparedStatement.setString(3, "true");
                preparedStatement.setString(4, "false");
            }else{
                preparedStatement.setString(3, "false");
                preparedStatement.setString(4, "true");
            }
            preparedStatement.setString(5, idProducto);
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        eliminarMaterial(idProducto);
        guardarMateriales(listaMateriales, idProducto);
    }

    /**
     * Método que se encarga de eliminar un producto de la base de datos
     * */
    public void eliminarProducto(String idProducto){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "DELETE FROM Producto WHERE IdProducto=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, idProducto);
            preparedStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que se encarga de eliminar la relación entre materiales y el producto dado
     * */
    private void eliminarMaterial(String idProducto){
        try {
            connection = myConnector.connectToMysqlDB("playeriSoft", "osocron", "patumecha1", "localhost");
            String statement = "DELETE FROM RMaterialProducto WHERE IdProducto=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, idProducto);
            preparedStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
