package playeriSoft.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import playeriSoft.modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static org.junit.Assert.*;

/**
 * Created by osocron on 20/05/15.
 */
public class InventarioHandlerTest extends TestCase {

    InventarioHandler inventarioHandler = new InventarioHandler();

    @Test
    public void testGetAllProductsGetObject() throws Exception {
        ObservableList<Producto> observableList = FXCollections.observableArrayList();
        Assert.assertEquals(observableList,inventarioHandler.getAllProducts(observableList));
    }

}