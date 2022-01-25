import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a date.
 * A date includes year, month, day, hour, minute and second.
 * */
public class Date {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    private int second;

    // Constructors

    public Date(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Date(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = 0;
        this.second = 0;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    // Getters & Setters

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    // Overridden methods

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return getYear() == date.getYear() &&
                getMonth() == date.getMonth() &&
                getDay() == date.getDay() &&
                getHour() == date.getHour() &&
                getMinute() == date.getMinute() &&
                getSecond() == date.getSecond();
    }

    @Override
    public String toString() {
        String monthString = String.format("%02d", getMonth());
        String dayString = String.format("%02d", getDay());
        String hourString = String.format("%02d", getHour());
        String minuteString = String.format(("%02d"), getMinute());
        String secondString = String.format(("%02d"), getSecond());
        return getYear() + "-" + monthString + "-" + dayString + ", " + hourString + ":" + minuteString + ":" + secondString;
    }
}
