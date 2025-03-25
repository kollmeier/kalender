package de.ckollmeier.kalender.Controller;

import de.ckollmeier.kalender.View.CalendarView;

public class CalendarController {
    public static void displayYear(int year) {
        System.out.println(CalendarView.renderYear(year));
    }
}
