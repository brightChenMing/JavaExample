package time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by chenming on 17/4/18.
 * JSR-310
 */
public class JavaTimeTest {

    @Test
    public void testDateTime(){
       LocalDateTime now= LocalDateTime.now();

        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    @Test
    public void from_time_millis_to_date_time(){
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()),
                ZoneId.systemDefault()).toString());
    }

    public void covert_date_to_LocalDateTime(){
        Date now=new Date();
        System.out.println(LocalDateTime.ofInstant(now.toInstant(),ZoneId.systemDefault()));


    }
}
