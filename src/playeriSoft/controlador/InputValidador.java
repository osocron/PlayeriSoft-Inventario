package playeriSoft.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Various input validators used for input control, perfectly tailored for the lazy programmer ;)
 * Clase que contiene validadorse de datos de ingreso para asegurarse que los usuarios ingresen
 * datos incorrectos. Las validaciones se realizan con el uso de expresiones regulares y aceptan
 * texto o controladores de JavaFX.
 * El proósito de esta clase es que se pueda reutilizar en diferentes proyectos y que sea una ayuda
 * para los que quieran utilizarla (Obvio, tienen que pagar la licencia)
 */
public class InputValidador {

    public static boolean txtFieldIsLatinTextButNoPunctuation(String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean txtFieldIsNumericOnly (String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean txtFieldIsDecimalOnly (String text, String precisionPostDecimal){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }
}
