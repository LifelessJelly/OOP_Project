package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.Objects;

public class DateSelectorHelper {
    public static JComboBox<Integer> comboBoxGetBaseDates() {
        return new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
    }
    public static JComboBox<String> comboBoxGetBaseMonths(){
        return new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
    }
    public static JComboBox<Integer> comboBoxGetBaseYears(int generateUntilThisYear){
        JComboBox<Integer> comboBox = new JComboBox<>();
        for (int i = Year.now().getValue(); i > generateUntilThisYear; --i) {
            comboBox.addItem(i);
        }
        return comboBox;
    }

    public static ActionListener datesUpdater(JComboBox<Integer> dayComboBox, JComboBox<String> monthComboBox, JComboBox<Integer> yearComboBox) {
        return e -> {
            int selectedDate = dayComboBox.getSelectedIndex() + 1;
            switch ((String) (Objects.requireNonNull(monthComboBox.getSelectedItem()))) {
                case "January":
                case "March":
                case "May":
                case "July":
                case "August":
                case "October":
                case "December":
                    dayComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}));
                    dayComboBox.setSelectedIndex(selectedDate - 1);

                    break;
                case "April":
                case "June":
                case "September":
                case "November":
                    if (selectedDate > 30) {
                        selectedDate = 30;
                    }
                    dayComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}));
                    dayComboBox.setSelectedIndex(selectedDate - 1);
                    break;
                default: // February case
                    boolean isLeapYear = false;
                    int selectedYear = (Integer) (Objects.requireNonNull(yearComboBox.getSelectedItem()));
                    if (selectedYear % 4 == 0 && selectedYear % 100 != 0 || selectedYear % 400 == 0) {
                        isLeapYear = true;
                    }
                    if (selectedDate > 29 && isLeapYear) {
                        selectedDate = 29;
                    } else if (selectedDate > 28 && !isLeapYear) {
                        selectedDate = 28;

                    }
                    if (isLeapYear) {
                        dayComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29}));
                    } else {
                        dayComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28}));
                    }
                    dayComboBox.setSelectedIndex(selectedDate - 1);
            }
        };
    }
}
