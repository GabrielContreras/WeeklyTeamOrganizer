package com.mooglecake;

import java.time.DayOfWeek;

public class TimeSlot {

    final private DayOfWeek weekday;
    final private int hourOfDay;

    public TimeSlot(DayOfWeek weekday, int hourOfDay) {
        this.weekday = weekday;
        this.hourOfDay = hourOfDay;
    }

    public DayOfWeek getWeekday() {
        return weekday;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (hourOfDay != timeSlot.hourOfDay) return false;
        return weekday == timeSlot.weekday;
    }

    @Override
    public int hashCode() {
        int result = weekday != null ? weekday.hashCode() : 0;
        result = 31 * result + hourOfDay;
        return result;
    }
}
