package br.com.generatebit.util;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateUtil {

    private static final String PATTERN = "dd/MM/yyyy";

    public static LocalDate stringToDate(String date){
        if (date.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(PATTERN, Locale.forLanguageTag("pt-BR"));
        try {
            return LocalDate.parse(date, dtf);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }

    }

    public static String dateToString(LocalDate localDate){

        return localDate.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    public static void formaterDatePicker(TextField textField) {

        textField.setOnKeyTyped((KeyEvent e) -> {
            if (!"0123456789".contains(e.getCharacter())) {
                e.consume();
            }
            if (e.getCharacter().trim().length() == 0) {

                if (textField.getText().length() == 3) {
                    textField.setText(textField.getText().substring(0, 2));
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 6) {
                    textField.setText(textField.getText().substring(0, 5));
                    textField.positionCaret(textField.getText().length());
                }
            } else {
                if (textField.getText().length() ==10){
                    e.consume();
                }
                if (textField.getText().length() == 2) {
                    textField.setText(textField.getText() + "/");
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 5) {
                    textField.setText(textField.getText() + "/");
                    textField.positionCaret(textField.getText().length());
                }
            }
        });
    }
}
