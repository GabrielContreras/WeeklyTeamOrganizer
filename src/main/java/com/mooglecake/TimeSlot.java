package com.mooglecake;

import java.time.DayOfWeek;
import java.util.Objects;

public class TimeSlot {

    final private String weekday;
    final private String hourOfDay;

    public TimeSlot(String weekday, String hourOfDay) {
        this.weekday = weekday;
        this.hourOfDay = hourOfDay;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getHourOfDay() {
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
        return Objects.hash(weekday, hourOfDay);
    }
}
