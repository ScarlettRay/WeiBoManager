import java.time.LocalTime;
import java.util.Date;

/**
 * @author winray
 * @since v1.0.1
 */
public class Test {

    public static void main(String[] args) {
        Date date = new Date("Fri Jun 05 20:00:04 +0800 2020");
        LocalTime localTime = LocalTime.parse("Fri Jun 05 20:00:04 +0800 2020");
        System.out.println(localTime);
    }
}
