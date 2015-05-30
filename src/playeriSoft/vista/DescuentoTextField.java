package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.controlador.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 * Esta clase permite crear un Text Field que impida que el usuario ingrese datos que no sean
 * numeros decimales o que sean de un valor mayor a 1 o que el decimal rebase una precision
 * de dos digitos.
 */
public class DescuentoTextField extends TextField{

    public DescuentoTextField(){
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        String newText = getText().substring(0, start)+text+getText().substring(end);
        boolean isDecimal = InputValidador.textIsDecimalOnly(newText, "2");
        int textLength = this.getText().length();
        Double curDouble;
        try{
            curDouble  = Double.valueOf(newText);
        }catch (Exception e){
            curDouble = 0.0;
        }
        if (InputValidador.validateIncomingTextForDescuentoMateriales(text,textLength,isDecimal,curDouble)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
