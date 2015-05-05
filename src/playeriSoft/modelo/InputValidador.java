package playeriSoft.modelo;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import javax.swing.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by osocron on 12/03/15.
 */
public class InputValidador {

//Various input validators used for input controll, perfectly tailored for the lazy programer ;)

    public static boolean txtFieldIsInRange(int startRage, int endRange, TextField txtField){
        boolean isInRange;
        String text = txtField.getText();
        Pattern pattern = Pattern.compile(".{"+startRage+","+endRange+"}");
        Matcher matcher = pattern.matcher(text);
        isInRange = matcher.matches();
        return isInRange;
    }

    public static boolean txtFieldIsInRange(int startRage, int endRange, String text){
        boolean isInRange;
        Pattern pattern = Pattern.compile(".{"+startRage+","+endRange+"}");
        Matcher matcher = pattern.matcher(text);
        isInRange = matcher.matches();
        return isInRange;
    }


    public static  boolean txtFieldHasSpecificNumberOfCharacters(int numCharacters, TextField txtField){
        boolean hasNumberOfCharacters;
        String text = txtField.getText();
        Pattern pattern = Pattern.compile(".{"+numCharacters+"}");
        Matcher matcher = pattern.matcher(text);
        hasNumberOfCharacters = matcher.matches();
        return hasNumberOfCharacters;
    }

    public static  boolean txtFieldHasSpecificNumberOfCharacters(int numCharacters, String text){
        boolean hasNumberOfCharacters;
        Pattern pattern = Pattern.compile(".{"+numCharacters+"}");
        Matcher matcher = pattern.matcher(text);
        hasNumberOfCharacters = matcher.matches();
        return hasNumberOfCharacters;
    }

    public static boolean txtFieldIsLatinTextOnly (TextField txtField){
        boolean isTextOnly;
        String text = txtField.getText();
        Pattern pattern = Pattern.compile("^[\\p{L} .,'-]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean txtFieldIsLatinTextOnly (String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} .,'-]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean txtFieldIsLatinTextButNoPunctuation(String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean txtFieldIsNumericOnly (TextField txtField){
        boolean isNumericOnly;
        String text = txtField.getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean txtFieldIsNumericOnly (String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean txtFieldIsDecimalOnly (TextField txtField , String precisionPostDecimal){
        boolean isNumericOnly;
        String text = txtField.getText();
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
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

    public static boolean textValidation (int rangeStart, int rangeEnd, TextField textField){
        boolean isInRange = InputValidador.txtFieldIsInRange(rangeStart,rangeEnd,textField);
        boolean isTextOnly = InputValidador.txtFieldIsLatinTextOnly(textField);
        boolean result = isInRange&&isTextOnly;
        if(!isInRange){
            JOptionPane.showMessageDialog(null, "Text is not in Range!");
            textField.requestFocus();
        }
        else if(!isTextOnly){
            JOptionPane.showMessageDialog(null,"Please enter character values only!");
            textField.requestFocus();
        }
        return result;
    }

    public static boolean numericValidation (int rangeStart, int rangeEnd, TextField textField){
        boolean isInRange = InputValidador.txtFieldIsInRange(rangeStart,rangeEnd,textField);
        boolean isNumericOnly = InputValidador.txtFieldIsNumericOnly(textField);
        boolean result = isInRange&&isNumericOnly;
        if(!isInRange){
            JOptionPane.showMessageDialog(null,"Value is not in Range!");
            textField.requestFocus();
        }
        else if(!isNumericOnly){
            JOptionPane.showMessageDialog(null,"Please enter numeric values only!");
            textField.requestFocus();
        }
        return result;
    }

    public static boolean specificNumberOfNumbersValidation(int numberOfNumbers, TextField textField){
        boolean hasNumberOfNumbers = InputValidador.txtFieldHasSpecificNumberOfCharacters(numberOfNumbers,textField);
        boolean isNumericOnly = InputValidador.txtFieldIsNumericOnly(textField);
        boolean result = hasNumberOfNumbers&&isNumericOnly;
        if(!isNumericOnly){
            JOptionPane.showMessageDialog(null,"Please enter numeric values only!");
            textField.requestFocus();
        }
        else if(!hasNumberOfNumbers){
            JOptionPane.showMessageDialog(null,"Please input "+numberOfNumbers+" numbers only!");
            textField.requestFocus();
        }
        return result;
    }

    public static void validateDatePicker( DatePicker datePickerObject){
        datePickerObject.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isAfter(
                                        datePickerObject.getValue())
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }else if(item.isBefore(
                                        datePickerObject.getValue().minusYears(230))
                                        ){
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        datePickerObject.setDayCellFactory(dayCellFactory);
    }

}
