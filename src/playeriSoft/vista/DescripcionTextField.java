package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.controlador.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 * Esta clase permite crear un Text Field que impida que el usuario ingrese datos a no ser que sean
 * letras en el alfabeto latino incluyendo eñes, acentos y el signo '-'. También impide que la longitud del texto no
 * sobrepase una longitud de 60 caracteres.
 */
public class DescripcionTextField extends TextField {

    public DescripcionTextField(){super();}

    @Override
    public void replaceText(int start, int end, String text){
        boolean isTextOnly = InputValidador.txtFieldIsLatinTextButNoPunctuation(text);
        int textLength = this.getText().length();
        if(isTextOnly && (textLength < 60)|| text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
