package playeriSoft.controlador;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by osocron on 25/05/15.
 */
public class InputValidadorTest extends TestCase {

    @Test
    public void testTxtFieldIsLatinTextButNoPunctuation() throws Exception {
        InputValidador inputValidador = new InputValidador();
        String text = "Hola";
        Assert.assertEquals(true,inputValidador.txtFieldIsLatinTextButNoPunctuation(text));
    }

    @Test
    public void probarTextLatinoErroneo(){
        InputValidador inputValidador = new InputValidador();
        String text = "#$#$";
        Assert.assertEquals(false,inputValidador.txtFieldIsLatinTextButNoPunctuation(text));
    }

    @Test
    public void testTxtFieldIsNumericOnly() throws Exception {

    }

    @Test
    public void testTxtFieldIsDecimalOnly() throws Exception {

    }
}