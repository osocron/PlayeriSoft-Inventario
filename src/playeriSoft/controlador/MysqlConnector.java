package playeriSoft.controlador;

import java.sql.*;

/**
 * Created by osocron on 16/03/15.
 */
public class MysqlConnector {

    static Connection connect;

    static Connection connectToMysqlDB(String dataBase, String user, String password, String host) throws Exception{

        //Conexión a Mysql
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dataBase+"?"
                    + "user="+user+"&password="+password);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connect;

    }

}
