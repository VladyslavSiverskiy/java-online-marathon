import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class MyUtils {
    public static String getDateAfterToday(int years, int months, int days) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(years).plusMonths(months).plusDays(days);;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return endDate.format(formatter);

    }
}
