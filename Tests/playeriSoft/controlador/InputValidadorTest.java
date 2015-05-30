package playeriSoft.controlador;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by osocron on 25/05/15.
 */
public class InputValidadorTest extends TestCase {


    @Test
    public void testTxtFieldIsLatinTextButNoPunctuation() throws Exception {
        String text = "Hola";
        Assert.assertEquals(true,InputValidador.textIsLatinButNoPunctuation(text));
    }

    @Test
    public void testTxtFieldIsLatinTextWithNumbers(){
        String text = "13243";
        Assert.assertEquals(false,InputValidador.textIsLatinButNoPunctuation(text));
    }

    @Test
    public void testTxtFieldIsLatinTextWithInvalidSigns(){
        String text = "#$%/()";
        Assert.assertEquals(false,InputValidador.textIsLatinButNoPunctuation(text));
    }

    @Test
    public void testTxtFieldIsLatinTextWithEmptyString(){
        String text = "";
        Assert.assertEquals(false,InputValidador.textIsLatinButNoPunctuation(text));
    }

    @Test
    public void testTxtFieldIsNumericOnly() throws Exception {
        String text = "8892";
        Assert.assertEquals(true,InputValidador.textIsNumericOnly(text));
    }

    @Test
    public void testTxtFieldIsNumericOnlyWithLettersAndSigns() throws Exception {
        String text = "abdn#$%&()";
        Assert.assertEquals(false,InputValidador.textIsNumericOnly(text));
    }

    @Test
    public void testTxtFieldIsNumericOnlyWithEmptyText() throws Exception {
        String text = "abdn#$%&()";
        Assert.assertEquals(false,InputValidador.textIsNumericOnly(text));
    }

    @Test
    public void testTxtFieldIsDecimalOnlyWithPrecisionOfTwo() throws Exception {
        String text = "12.04";
        Assert.assertEquals(true,InputValidador.textIsDecimalOnly(text, "2"));
    }

    @Test
    public void testTxtFieldIsDecimalOnlyWithPrecisionOfThree() throws Exception {
        String text = "12.045";
        Assert.assertEquals(false,InputValidador.textIsDecimalOnly(text,"2"));
    }

    @Test
    public void testTxtFieldIsDecimalOnlyWithLetters() throws Exception {
        String text = "ajjfh";
        Assert.assertEquals(false,InputValidador.textIsDecimalOnly(text,"2"));
    }

    @Test
    public void testTxtFieldIsDecimalOnlyWithEmptyText() throws Exception {
        String text = "";
        Assert.assertEquals(true,InputValidador.textIsDecimalOnly(text,"2"));
    }

    @Test
    public void testValidateIncomingTextForCantidadMaterialesWithValidParameters(){
        Assert.assertEquals(true,InputValidador.validateIncomingTextForCantidadMateriales("2",4,true,20));
    }

    @Test
    public void testValidateIncomingTextForCantidadMaterialesWithTextTooLong(){
        Assert.assertEquals(false,InputValidador.validateIncomingTextForCantidadMateriales("2",14,true,20));
    }


}