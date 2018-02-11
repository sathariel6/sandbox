import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class LocalDateTimeTest {

    @Test
    void testVariousLocalDateTimeTools() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("The current date/time: " + currentDateTime);
        System.out.println("The current time in NY: " + currentDateTime.atZone(ZoneId.of("-05:00")));
        System.out.println("Format current date in ISO: " + currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("Format current date in ISO_LOCAL: " + currentDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("Format current date in custom format: " + currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
        System.out.println("Pass in some arbitrary date: " + LocalDateTime.of(2000, 06,10,14,12));
        System.out.println("Extract current date: " + currentDateTime.toLocalDate());
        System.out.println("Extract current time: " + currentDateTime.toLocalTime());
        System.out.println("Travel back in time!: " + currentDateTime.of(currentDateTime.toLocalDate().minusDays(1), currentDateTime.toLocalTime().minusHours(1)));
    }
}