package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.modelo.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 */
public class CantidadMaterialesTextField extends TextField {

    public CantidadMaterialesTextField(){
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        String newText = getText().substring(0, start)+text+getText().substring(end);
        boolean isDecimal = InputValidador.txtFieldIsDecimalOnly(newText,"4","3");
        int textLength = this.getText().length();
        Double curDouble;
        try{
            curDouble  = Double.valueOf(newText);
        }catch (Exception e){
            curDouble = 0.0;
        }
        if(curDouble < 10000){
            if (isDecimal && (textLength < 8) || text.isEmpty()) {
                super.replaceText(start, end, text);
            }
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }

}
