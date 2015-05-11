package playeriSoft.vista;

import javafx.scene.control.TextField;
import playeriSoft.controlador.InputValidador;

/**
 * Created by osocr_000 on 05/05/2015.
 */
public class PrecioMayoreoMenudeoTextField  extends TextField {

    public PrecioMayoreoMenudeoTextField(){
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        String newText = getText().substring(0, start)+text+getText().substring(end);
        boolean isDecimal = InputValidador.txtFieldIsDecimalOnly(newText, "2");
        int textLength = this.getText().length();
        Double curDouble;
        try{
            curDouble  = Double.valueOf(newText);
        }catch (Exception e){
            curDouble = 0.0;
        }
        if(curDouble < 100000){
            if (isDecimal && (textLength < 9) || text.isEmpty()) {
                super.replaceText(start, end, text);
            }
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }

}
