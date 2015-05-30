package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.controlador.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 * Esta clase permite crear un Text Field que impida que el usuario ingrese datos que no sean
 * numeros enteros o que sean de un longitud mayor a 6
 */
public class ExistenciasTextField extends TextField {

    public ExistenciasTextField(){super();}

    @Override
    public void replaceText(int start, int end, String text){
        boolean textIsNumericOnly = InputValidador.textIsNumericOnly(text);
        int textLength = this.getText().length();
        if(InputValidador.validateIncomingTextForExistenciasTextField(text,textLength,textIsNumericOnly)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }

}
