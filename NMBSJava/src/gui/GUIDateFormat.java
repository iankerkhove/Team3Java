package gui;

import java.text.*;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

@SuppressWarnings("serial")
public class GUIDateFormat extends AbstractFormatter {

    private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
    
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
