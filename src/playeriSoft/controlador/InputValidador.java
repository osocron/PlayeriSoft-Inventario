package playeriSoft.controlador;

import playeriSoft.modelo.Material;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contiene validadorse de datos de ingreso para asegurarse que los usuarios no ingresen
 * datos incorrectos. Las validaciones se realizan con el uso de expresiones regulares y aceptan
 * texto o controladores de JavaFX.
 * El proósito de esta clase es que se pueda reutilizar en diferentes proyectos y que sea una ayuda
 * para los que quieran utilizarla (Obvio, tienen que pagar la licencia)
 */
public class InputValidador {

    public static boolean textIsLatinButNoPunctuation(String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean textIsNumericOnly(String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean textIsDecimalOnly(String text, String precisionPostDecimal){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean validateIncomingTextForCantidadMateriales(String text, int textLength, boolean isDecimal,
                                                                    double curDouble){
        return curDouble < 10000 && isDecimal && (textLength < 8) || text.isEmpty();
    }

    public static boolean validateIncomingTextForColorTextField(String text, int textLength, boolean isTextOnly){
        return isTextOnly && (textLength < 30)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForDescripcionTextField(String text, int textLength, boolean isTextOnly){
        return isTextOnly && (textLength < 60)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForDescuentoMateriales(String text, int textLength, boolean isDecimal,
                                                                    double curDouble){
        return curDouble <= 1 && isDecimal && (textLength < 5) || text.isEmpty();
    }

    public static boolean validateIncomingTextForExistenciasTextField(String text, int textLength,
                                                                      boolean textIsNumericOnly){
        return textIsNumericOnly && (textLength < 7)|| text.isEmpty();
    }

    public static boolean validateIncomingTextForLargoAnchoTextField(String text, int textLength, boolean isDecimal,
                                                                     double curDouble){
        return curDouble < 1000 && isDecimal && (textLength < 6) || text.isEmpty();
    }

    public static boolean validateIncomingTextForMayoreoMenudeoTextField(String text, int textLength, boolean isDecimal,
                                                                     double curDouble){
        return curDouble < 100000 && isDecimal && (textLength < 9) || text.isEmpty();
    }

    public static boolean validateIncomingTextForTallaTextField(String text, int textLength, boolean isDecimal,
                                                                         double curDouble){
        return curDouble < 100 && isDecimal && (textLength < 5) || text.isEmpty();
    }

    public static boolean validateIfUserPressedBackSpaceForSearchProducts(String oldVal, String newVal){
        return oldVal != null && (newVal.length() < oldVal.length());
    }

    public static boolean validateIfEntryTextContainsUserInput(String entryText, String part){
        return entryText.toUpperCase().contains(part);
    }

    public static boolean validateIfMaterialIsSelectedAndHasCantidadSeleccionada(Material curMaterial){
        return curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() > 0.0;
    }

    public static boolean validateIfMaterialIsSelectedButHasNoCantidadSeleccionada(Material curMaterial){
        return curMaterial.isSelected() && curMaterial.getCantidadSeleccionada() == 0.0;
    }

    public static boolean validateCantidadDeMaterialIsNotEmptyAndIsNotADot(String newValue){
        return !newValue.isEmpty() && !newValue.equals(".");
    }

}
