package hicheel2024.buteelt.NetWorkDays;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class DateUtils {

    public static long networkdays(LocalDate startDate, LocalDate endDate, Set<LocalDate> holidays) {
        long workingDays = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            if (!isWeekend(date) && !holidays.contains(date)) {
                workingDays++;
            }
            date = date.plusDays(1);
        }

        return workingDays;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 4);

        Set<LocalDate> holidays = new HashSet<>();
        // Example holiday

        long workingDays = networkdays(startDate, endDate, holidays);
        System.out.println("Number of working days: " + workingDays);
    }
}
