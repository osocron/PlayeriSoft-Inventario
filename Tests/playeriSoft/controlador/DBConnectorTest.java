package playeriSoft.controlador;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by osocron on 21/05/15.
 */
public class DBConnectorTest extends TestCase {

    DBConnector dbConnector = new DBConnector();

    @Test(expected = Exception.class)
    public void testConnectToDBWithEmptyArguments() throws Exception {
        dbConnector.connectToMysqlDB("","","","");
    }

    @Test
    public void testConnectToDBWithCorrectArguments() throws Exception {
        Connection connection = null;
        Assert.assertEquals(connection,dbConnector.connectToMysqlDB("playeriSfot","osocron","patumecha1","localhost"));
    }
}