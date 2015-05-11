package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.controlador.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 */
public class ColorTextField extends TextField {

    public ColorTextField(){super();}

    @Override
    public void replaceText(int start, int end, String text){
        boolean isTextOnly = InputValidador.txtFieldIsLatinTextButNoPunctuation(text);
        int textLength = this.getText().length();
        if(isTextOnly && (textLength < 30)|| text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}