package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.Objects;

public class DateSelectorHelper {
	/**
	 * Creates and returns a JComboBox containing the integers from 1 to 31.
	 *
	 * <p>
     * This method initializes a JComboBox with a predefined set of integer values,
     * representing the possible base dates (days) in a month. The JComboBox can be
     * used in a graphical user interface to allow users to select a day.
     * </p>
     *
     * @return a JComboBox<Integer> populated with integers from 1 to 31.
     */
    public static JComboBox<Integer> comboBoxGetBaseDates() {
        return new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
    }

	/**
	 * Creates and returns a JComboBox containing the names of the months.
	 *
	 * <p>
	 * This method initializes a JComboBox with a predefined set of string values,
	 * representing the twelve months of the year. The JComboBox can be used in a
	 * graphical user interface to allow users to select a month.
     * </p>
     *
     * @return a JComboBox<String> populated with the names of the months from
     *         January to December.
     */
    public static JComboBox<String> comboBoxGetBaseMonths() {
        return new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
    }

	/**
	 * Creates a JComboBox containing a list of years starting from the current year
	 * down to a specified year.
	 *
     * <p>
     * This method generates a JComboBox populated with integer values representing
     * years, starting from the current year and decrementing until it reaches the
     * specified year.
     * </p>
     *
     * @param generateUntilThisYear the year until which the JComboBox should be
     *                              populated. Must be less than or equal to the
     *                              current year.
     * @return a JComboBox<Integer> containing years from the current year down to
     *         the specified year.
     */
    public static JComboBox<Integer> comboBoxGetBaseYears(int generateUntilThisYear) {
        JComboBox<Integer> comboBox = new JComboBox<>();
        for (int i = Year.now().getValue(); i > generateUntilThisYear; --i) {
            comboBox.addItem(i);
        }
        return comboBox;
    }

	/**
	 * Creates a JComboBox containing a list of years starting from the current year
	 * up to a specified year.
	 *
	 * <p>
	 * This method generates a JComboBox populated with integer values representing
	 * years, starting from the current year and incrementing until it reaches the
	 * specified year.
	 * </p>
	 *
	 * @param generateUntilThisYear the year until which the JComboBox should be
	 *                              populated. Must be greater than or equal to the
	 *                              current year.
     * @return a JComboBox<Integer> containing years from the current year up to the
     *         specified year.
     */
    public static JComboBox<Integer> comboBoxGetBaseYearsReverse(int generateUntilThisYear) {
        JComboBox<Integer> comboBox = new JComboBox<>();
        for (int i = Year.now().getValue(); i < generateUntilThisYear; ++i) {
            comboBox.addItem(i);
        }
        return comboBox;
    }


    /**
     * Creates an {@link ActionListener} that updates the day JComboBox based on the selected month
     * and year from the provided JComboBoxes.
     *
     * <p>This method listens for changes in the month and year selection, and updates the day
     * JComboBox accordingly. It handles the different number of days in each month, including
     * leap years for February.</p>
     *
     * @param dayComboBox   the JComboBox for selecting the day. Must not be null.
     * @param monthComboBox the JComboBox for selecting the month. Must not be null.
     * @param yearComboBox  the JComboBox for selecting the year. Must not be null.
     * @return an ActionListener that updates the day JComboBox based on the selected month and year.
     */
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

    /**
     * Creates and returns a JComboBox containing the hours of a 24-hour format.
     *
     * <p>This method initializes a JComboBox with integer values ranging from 0 to 23,
     * representing the hours in a day. The JComboBox can be used in a graphical user interface
     * to allow users to select a base hour.</p>
     *
     * @return a JComboBox<Integer> populated with integers from 0 to 23.
     */
    public static JComboBox<Integer> comboBoxGetBaseHour() {
        return new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23});
    }

    /**
     * Creates and returns a JComboBox containing 15-minute intervals.
     *
     * <p>This method initializes a JComboBox with integer values representing
     * the minutes in a 60-minute hour, specifically the values 0, 15, 30, and 45.
     * This JComboBox can be used in a graphical user interface to allow users
     * to select a minute interval for scheduling or time selection purposes.</p>
     *
     * @return a JComboBox<Integer> populated with integers representing 15-minute intervals:
     *         0, 15, 30, and 45.
     */
    public static JComboBox<Integer> comboBoxGet15MinuteInterval() {
        return new JComboBox<>(new Integer[]{0, 15, 30, 45});
    }
}
