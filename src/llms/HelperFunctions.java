package llms;

import javax.swing.JList;

/**
 * A class that encapsulate lines of code that are written more than once
 * throughout the project into functions in order to keep the code DRY.
 *
 * @author Sevag Saro Aredjian
 */
public class HelperFunctions {

    /**
     * To get the first value of a JList. This function is designed for getting
     * the serial number or student id of a book or student from the GUI from
     * JList.
     *
     * @param list The JList from which the first value will be extracted.
     * @return The serial number of the book if the JList is a book list or the
     * student id if the JList is a student list.
     */
    public static String getFirstValueJList(JList<String> list) {
        return list.getSelectedValue().split(",")[0].split(":")[1].trim();
    }

    /**
     * To try to convert a string to an Integer. If the conversion fails, i.e.
     * the string is not an Integer, return the specified value (catchVal).
     *
     * @param value The String that will be attempted to convert to an Integer.
     * @param catchVal The Integer value that will be returned if the conversion
     * fails.
     * @return If the conversion is successful, the input String value will be
     * returned as an Integer, else, the specified catchVal will be returned.
     */
    public static Integer tryParseInt(String value, int catchVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return catchVal;
        }
    }
}
