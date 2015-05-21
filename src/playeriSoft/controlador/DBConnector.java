package playeriSoft.controlador;

import java.sql.*;

/**
 * Created by osocron on 16/03/15.
 * Clase que se encarga de Proveer una conexión a la base de datos por medio de una intefaz simple.
 * TODO: Proveer conexiones a diferentes manejadores de bases de datos
 */
public class DBConnector {

    private Connection connect;

    public DBConnector(){}

    public Connection connectToMysqlDB(String dataBase, String user, String password, String host) throws Exception{

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
