package playeriSoft.controlador;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import playeriSoft.modelo.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osocron on 25/05/15.
 */
public class InputValidadorTest {


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
    public void testTextIsDecimalOnlyWithEmptyText() throws Exception {
        String text = "";
        Assert.assertEquals(true,InputValidador.textIsDecimalOnly(text,"2"));
    }

    @Test
    public void testCantidadDeMaterialesConArguementoValido(){
        String text = "12";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testCantidadDeMaterialesConArgumentoDecimalValido(){
        String text = "24.12";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testCantidadDeMaterialesConArgumentoVacio(){
        String text = "";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testCantidadDeMaterialesConArgumentoInvalido(){
        String text = "ABD!#";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testCantidadDeMaterialesConArgumentoFueraDeRango(){
        String text = "188923.24";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testColorDeProductoConArgumentoValido(){
        String text = "Café";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForColorTextField(text, text.length(),
                isTextOnly));
    }

    @Test
    public void testColorDeProductoConArgumentoFueraDeRango(){
        String text = "RositaRositaRositaRositaRositaRositaRosita";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForColorTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testColorDeProductoConArgumentoInvalido(){
        String text = "314#/";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForColorTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testColoDeProductoConArgumentoVacio(){
        String text = "";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForColorTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testDescripcionDeProductoConArgumentoValido(){
        String text = "Playera NIKE";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescripcionTextField(text, text.length(),
                isTextOnly));
    }

    @Test
    public void testDescripcionDeProductoConArgumentoFueraDeRango(){
        String text = "Playera NIKEPlayera NIKEPlayera NIKEPlayera NIKEPlayera NIKEPlayera NIKE";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForDescripcionTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testDescripcionDeProductoConArgumentoInvalido(){
        String text = "#$%&()#2444";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForDescripcionTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testDescripcionDeProductoConArgumentoVacio(){
        String text = "";
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescripcionTextField(text,text.length(),
                isTextOnly));
    }

    @Test
    public void testDescuentoDeProductoConArgumentoDecimalValido(){
        String text = "0.02";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescuentoMateriales(text, text.length(),
                isDecimal, curDouble));
    }

    @Test
    public void testDescuentoDeProductoConArgumentoEnteroValido(){
        String text = "1";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescuentoMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testDescuentoDeProductoConArgumentoVacio(){
        String text = "";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescuentoMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testDescuentoDeProductoConArgumentoInvalido(){
        String text = "GHJ$#";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForDescuentoMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testDescuentoDeProductoConArgumentoFueraDeRango(){
        String text = "132.34";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForDescuentoMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testExistenciasDeProductoConArgumentoValido(){
        String text = "22";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForExistenciasTextField(text, text.length(),
                isNumericOnly));
    }

    @Test
    public void testExistenciasDeProductoConArgumentDecimal(){
        String text = "14.5";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForExistenciasTextField(text,text.length(),
                isNumericOnly));
    }

    @Test
    public void testExistenciasDeProductoConArgumentoVacio(){
        String text = "";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForExistenciasTextField(text,text.length(),
                isNumericOnly));
    }

    @Test
    public void testExistenciasDeProductoConArgumentoInvalido(){
        String text = "GHJ$#";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForExistenciasTextField(text,text.length(),
                isNumericOnly));
    }

    @Test
    public void testExistenciasDeProductoConArgumentoFueraDeRango(){
        String text = "99999999";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(false,InputValidador.validateIncomingTextForExistenciasTextField(text,text.length(),
                isNumericOnly));
    }

    @Test
    public void testLargoAnchoDeParcheConArgumentoDecimalValido(){
        String text = "99.99";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForLargoAnchoTextField(text, text.length(),
                isDecimal, curDouble));
    }

    @Test
    public void testLargoAnchoDeParcheConArgumentoEnteroValido(){
        String text = "1";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForLargoAnchoTextField(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testLargoAnchoDeParcheConArgumentoVacio(){
        String text = "";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForLargoAnchoTextField(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testLargoAnchoDeParcheConArgumentoInvalido(){
        String text = "XVZ-%";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForLargoAnchoTextField(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testLargoAnchoDeParcheConArgumentoFueraDeRango(){
        String text = "999.999";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(false,InputValidador.validateIncomingTextForLargoAnchoTextField(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testMaterialHaSidoSeleccionadoYTieneCantidadDeProductoConArgumentosValidos(){
        Material curMaterial = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        curMaterial.setSelected(true);
        curMaterial.setCantidadSeleccionada(1);
        Assert.assertEquals(true,InputValidador.validateIfMaterialIsSelectedAndHasCantidadSeleccionada
                (curMaterial));
    }

    @Test
    public void testMaterialHaSidoSeleccionadoYTieneCantidadDeProductoSinSerSeleccionado(){
        Material curMaterial = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        curMaterial.setSelected(false);
        curMaterial.setCantidadSeleccionada(12);
        Assert.assertEquals(false,InputValidador.validateIfMaterialIsSelectedAndHasCantidadSeleccionada
                (curMaterial));
    }

    @Test
    public void testMaterialHaSidoSeleccionadoYTieneCantidadDeProductoSeleccionadoSinCantidad(){
        Material curMaterial = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        curMaterial.setSelected(true);
        curMaterial.setCantidadSeleccionada(0.0);
        Assert.assertEquals(false,InputValidador.validateIfMaterialIsSelectedAndHasCantidadSeleccionada
                (curMaterial));
    }

    @Test
    public void testMaterialHaSidoSeleccionadoYTieneCantidadDeProductoSinSerSeleccionadoYSinCantidad(){
        Material curMaterial = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        curMaterial.setSelected(false);
        curMaterial.setCantidadSeleccionada(0.0);
        Assert.assertEquals(false,InputValidador.validateIfMaterialIsSelectedAndHasCantidadSeleccionada
                (curMaterial));
    }

    @Test
    public void testCantidadSeleccionadaNoEstaVaciaYNoEsUnPuntoConArgumentoVacio(){
        String text = "";
        Assert.assertEquals(false,InputValidador.validateCantidadDeMaterialIsNotEmptyAndIsNotADot(text));
    }

    @Test
    public void testCantidadSeleccionadaNoEstaVaciaYNoEsUnPuntoConPunto(){
        String text = ".";
        Assert.assertEquals(false,InputValidador.validateCantidadDeMaterialIsNotEmptyAndIsNotADot(text));
    }

    @Test
    public void testCantidadSeleccionadaNoEstaVaciaYNoEsUnPunto(){
        String text = "12.15";
        Assert.assertEquals(true,InputValidador.validateCantidadDeMaterialIsNotEmptyAndIsNotADot(text));
    }

    @Test
    public void testGuardarProductoConTodosLosArgumentosValidos(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(true,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConPrimerArgumentoVacio(){
        String descripcion = "";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConSegundoArgumentoVacio(){
        String descripcion = "Sudadera Adidas";
        String existencias = "";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConTercerArgumentoVacio(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConCuartoArgumentoVacio(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConQuintoArgumentoVacio(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoSinSerigrafiaOBordadoSeleccionado(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = false;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConListaMaterialesVacia(){
        String descripcion = "Sudadera Adidas";
        String existencias = "12";
        String descuento = "0.01";
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarProductoConTodosLosArgumentosVacios(){
        String descripcion = "";
        String existencias = "";
        String descuento = "";
        String precioMayoreo = "";
        String precioMenudeo = "";
        boolean serigrafiaIsSelected = false;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Assert.assertEquals(false,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarPlayeraConTodosLosArgumentosValidos(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = "Amarillo Patito";
        boolean tipoFirstChoiceSelected = true;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(true,InputValidador.validateGuardarPlayera(guardarProductoOK, talla, color,
                tipoFirstChoiceSelected, tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarPlayeraConPrimerArgumentoInvalido(){
        boolean guardarProductoOK = false;
        String talla = "12";
        String color = "Amarillo Patito";
        boolean tipoFirstChoiceSelected = true;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(false,InputValidador.validateGuardarPlayera(guardarProductoOK,talla,color,
                tipoFirstChoiceSelected,tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarPlayeraConSegundoArgumentoInvalido(){
        boolean guardarProductoOK = true;
        String talla = "";
        String color = "Amarillo Patito";
        boolean tipoFirstChoiceSelected = true;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(false,InputValidador.validateGuardarPlayera(guardarProductoOK,talla,color,
                tipoFirstChoiceSelected,tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarPlayeraConTercerArgumentoInvalido(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = "";
        boolean tipoFirstChoiceSelected = true;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(false,InputValidador.validateGuardarPlayera(guardarProductoOK,talla,color,
                tipoFirstChoiceSelected,tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarPlayeraConCuartoYQuintoArgumentoInvalido(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = "Amarillo Patito";
        boolean tipoFirstChoiceSelected = false;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(false,InputValidador.validateGuardarPlayera(guardarProductoOK,talla,color,
                tipoFirstChoiceSelected,tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarPlayeraConTodosLosArgumentosInvalidos(){
        boolean guardarProductoOK = false;
        String talla = "";
        String color = "";
        boolean tipoFirstChoiceSelected = false;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(false,InputValidador.validateGuardarPlayera(guardarProductoOK,talla,color,
                tipoFirstChoiceSelected,tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarSudaderaGorraConElPrimerArgumentoVerdaderoYLosDemasLlenos(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = "Amarillo Patito";
        Assert.assertEquals(true,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK, talla, color));
    }

    @Test
    public void testGuardarSudaderaGorraConPrimerArgumentoFalso(){
        boolean guardarProductoOK = false;
        String talla = "12";
        String color = "Amarillo Patito";
        Assert.assertEquals(false,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK, talla, color));
    }

    @Test
    public void testGuardarSudaderaGorraConSegundoArgumentoVacio(){
        boolean guardarProductoOK = true;
        String talla = "";
        String color = "Amarillo Patito";
        Assert.assertEquals(false,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK,talla,color));
    }

    @Test
    public void testGuardarSudaderaGorraConTercerArgumentoVacio(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = "";
        Assert.assertEquals(false,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK,talla,color));
    }

    @Test
    public void testGuardarSudaderaGorraConElPrimerArgumentoFalsoYLosDemasVacios(){
        boolean guardarProductoOK = false;
        String talla = "";
        String color = "";
        Assert.assertEquals(false,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK,talla,color));
    }

    @Test
    public void testGuardarParcheConTodosLosArgumentosValidos(){
        boolean guardarProductoOK = true;
        String largo = "34.5";
        String ancho = "22.14";
        Assert.assertEquals(true,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

    @Test
    public void testGuardarParcheConPrimerArgumentoFalso(){
        boolean guardarProductoOK = false;
        String largo = "34.5";
        String ancho = "22.14";
        Assert.assertEquals(false,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

    @Test
    public void testGuardarParcheConSegundoArgumentoVacio(){
        boolean guardarProductoOK = true;
        String largo = "";
        String ancho = "22.14";
        Assert.assertEquals(false,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

    @Test
    public void testGuardarParcheConTercerArgumentoVacio(){
        boolean guardarProductoOK = true;
        String largo = "34.5";
        String ancho = "";
        Assert.assertEquals(false,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

    @Test
    public void testGuardarParcheConElPrimerArgumentoFalsoYLosDemasVacios(){
        boolean guardarProductoOK = false;
        String largo = "";
        String ancho = "";
        Assert.assertEquals(false,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

    @Test
    public void testCantidadDeMaterialesParaQueFalle(){
        String text = null;
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForCantidadMateriales(text,text.length(),
                isDecimal,curDouble));
    }

    @Test
    public void testColorDeProductoParaQueFalle(){
        String text = null;
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForColorTextField(text, text.length(),
                isTextOnly));
    }

    @Test
    public void testDescripcionDeProductoParaQueFalle(){
        String text = null;
        boolean isTextOnly = InputValidador.textIsLatinButNoPunctuation(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescripcionTextField(text, text.length(),
                isTextOnly));
    }

    @Test
    public void testDescuentoDeProductoParaQueFalle(){
        String text = "12.3";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"-2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForDescuentoMateriales(text, text.length(),
                isDecimal, curDouble));
    }

    @Test
    public void testExistenciasDeProductoParaQueFalle(){
        String text = "22";
        boolean isNumericOnly = InputValidador.textIsNumericOnly(text);
        Assert.assertEquals(true,InputValidador.validateIncomingTextForExistenciasTextField(text, Integer.valueOf("Falla!"),
                isNumericOnly));
    }

    @Test
    public void testLargoAnchoDeParcheParaQueFalle(){
        String text = "99.99";
        boolean isDecimal = InputValidador.textIsDecimalOnly(text,"2");
        double curDouble;
        try {
            curDouble = Double.valueOf(text);
        }catch (Exception e){
            curDouble = 0.0;
        }
        Assert.assertEquals(true,InputValidador.validateIncomingTextForLargoAnchoTextField(text, text.length(),
                isDecimal, Double.valueOf((String.valueOf(curDouble))+"Falla")));
    }

    @Test
    public void testMaterialHaSidoSeleccionadoYTieneCantidadDeProductoParaQueFalle(){
        Material curMaterial = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        curMaterial.setSelected(true);
        curMaterial.setCantidadSeleccionada(Integer.valueOf("Ejote"));
        Assert.assertEquals(true,InputValidador.validateIfMaterialIsSelectedAndHasCantidadSeleccionada
                (curMaterial));
    }

    @Test
    public void testCantidadSeleccionadaNoEstaVaciaYNoEsUnPuntoParaQueFalle(){
        String text = null;
        Assert.assertEquals(false,InputValidador.validateCantidadDeMaterialIsNotEmptyAndIsNotADot(text));
    }

    @Test
    public void testGuardarProductoParaQueFalle(){
        String descripcion = null;
        String existencias = null;
        String descuento = null;
        String precioMayoreo = "120";
        String precioMenudeo = "135";
        boolean serigrafiaIsSelected = true;
        boolean bordadoIsSelected = false;
        List<Material> listaMateriales = new ArrayList<>();
        Material material = new Material("TINT0012","Tinta China Roja",55.12,"ml",520);
        material.setSelected(true);
        material.setCantidadSeleccionada(2.5);
        listaMateriales.add(material);
        Assert.assertEquals(true,InputValidador.validateGuardarProducto(descripcion,existencias,descuento,
                precioMayoreo,precioMenudeo,serigrafiaIsSelected,bordadoIsSelected,listaMateriales));
    }

    @Test
    public void testGuardarPlayeraParaQueFalle(){
        boolean guardarProductoOK = true;
        String talla = "12";
        String color = null;
        boolean tipoFirstChoiceSelected = true;
        boolean tipoSecondChoiceSelected = false;
        Assert.assertEquals(true,InputValidador.validateGuardarPlayera(guardarProductoOK, talla, color,
                tipoFirstChoiceSelected, tipoSecondChoiceSelected));
    }

    @Test
    public void testGuardarSudaderaGorraConParaQueFalle(){
        boolean guardarProductoOK = true;
        String talla = null;
        String color = "";
        Assert.assertEquals(false,InputValidador.validateGuardarSudaderaGorra(guardarProductoOK,talla,color));
    }

    @Test
    public void testGuardarParcheParaQueFalle(){
        boolean guardarProductoOK = true;
        String largo = "34.5";
        String ancho = null;
        Assert.assertEquals(true,InputValidador.validateGuardarParche(guardarProductoOK, largo, ancho));
    }

}