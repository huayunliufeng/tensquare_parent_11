import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
       SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String format = sdf.format((new Date()));
        System.out.println(format);

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        System.out.println();
        System.out.println();
    }
}
