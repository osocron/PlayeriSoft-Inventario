package playeriSoft.controlador;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

/**
 * Created by osocron on 16/03/15.
 * Clase que se encarga de Proveer una conexión a la base de datos por medio de una intefaz simple y hacer las conusltas
 * y actualizaciones a la base de datos.
 * TODO: Proveer conexiones a diferentes manejadores de bases de datos, crear pruebas
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
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error, mysql Class not Found!",stringWriter.toString());
        }catch (Exception e){
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al Conectarse a la base de datos!",stringWriter.toString());
        }
        return connect;
    }

    public ResultSet getResultSet(Connection connection, String sqlQuery){
        PreparedStatement preparedStatement = getPreparedStatement(connection, sqlQuery);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al ObtenerResultset",stringWriter.toString());
        }
        return resultSet;
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sqlQuery){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
        } catch (Exception e) {
            MailSender mailSender = new MailSender();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            mailSender.sendMail("Error al crear Prepared Statement",stringWriter.toString());
        }
        return  preparedStatement;
    }

}
