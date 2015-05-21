package playeriSoft.controlador;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

/**
 * Created by osocron on 16/03/15.
 * Clase que se encarga de Proveer una conexión a la base de datos por medio de una intefaz simple y hacer las conusltas
 * y actualizaciones a la base de datos.
 * TODO: Proveer conexiones a diferentes manejadores de bases de datos, mandar email de error y crear pruebas
 */
public class DBConnector {

    private Connection connect;

    public DBConnector(){}

    public Connection connectToMysqlDB(String dataBase, String user, String password, String host) throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dataBase+"?"
                    + "user="+user+"&password="+password);
        }catch (ClassNotFoundException e) {
            /*MailSender mailSender = new MailSender();
            mailSender.sendMail("Error al conectarse a la base de datos",e.getMessage());*/
        }catch (Exception e){
            /*MailSender mailSender = new MailSender();
            mailSender.sendMail("Error al conectarse a la base de datos",e.getMessage());*/
        }
        return connect;
    }

    public ResultSet getResultSet(Connection connection, String sqlQuery){
        PreparedStatement preparedStatement = getPreparedStatement(connection, sqlQuery);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            /*MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(sw);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al ObtenerResultset",stringWriter.toString);*/
        }
        return resultSet;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sqlQuery){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
        } catch (Exception e) {
             /*MailSender mailSender = new MailSender();
            mailSender.sendMail("Error al obtener PreparedStatement",e.getMessage());*/
        }
        return  preparedStatement;
    }

}
