package de.ckollmeier.kalender.View;

import java.util.Calendar;
import java.util.Locale;

public class CalendarView {
    public static String renderYear(int year) {
        StringBuilder rendered = new StringBuilder(year + "\n");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int startsAtWeekday = 8 - calendar.getMinimalDaysInFirstWeek();

        for (int month = 1; month <= 12; month++) {
            calendar.set(Calendar.MONTH, month - 1);
            rendered.append(renderMonth(calendar, startsAtWeekday));
            rendered.append("\n");
            startsAtWeekday = (startsAtWeekday + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1) % 7 + 1;
        }
        return rendered.toString();
    }

    private static String renderMonth(Calendar calendar, int startsAtWeekday) {
        StringBuilder rendered = new StringBuilder(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.GERMANY));

        rendered.append("\n");

        rendered.append(renderMonthDays(calendar, startsAtWeekday));
        return rendered.toString();
    }

    private static String renderMonthDays(Calendar calendar, int startsAtWeekday) {
        StringBuilder rendered = new StringBuilder();
        for (int weekday = 1; weekday <= 7; weekday++) {
            switch (weekday) {
                case 1:
                    rendered.append("Mo");
                    break;
                case 2:
                    rendered.append("Di");
                    break;
                case 3:
                    rendered.append("Mi");
                    break;
                case 4:
                    rendered.append("Do");
                    break;
                case 5:
                    rendered.append("Fr");
                    break;
                case 6:
                    rendered.append("Sa");
                    break;
                case 7:
                    rendered.append("So");
                    break;
                default:
                    break;
            }
            rendered.append(" ");
        }
        rendered.append("\n");

        int dayOfMonth = 1;

        for (int weekOfMonth = 1; weekOfMonth <= 6; weekOfMonth++) {
            for (int weekday = 1; weekday <= 7; weekday++) {
                if (weekOfMonth == 1 && weekday < startsAtWeekday) {
                    rendered.append("   ");
                    continue;
                }
                if (dayOfMonth < 10) {
                    rendered.append(" ");
                }
                rendered.append(dayOfMonth).append(" ");
                dayOfMonth++;
                if (dayOfMonth > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    break;
                }
            }
            rendered.append("\n");
            if (dayOfMonth > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                break;
            }
        }
        return rendered.toString();
    }
}
