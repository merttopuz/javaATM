import java.text.SimpleDateFormat;
import java.util.Calendar;

public class mCal {
    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat hour = new SimpleDateFormat("HH");
    private int day = cal.get(Calendar.DAY_OF_MONTH);
    private int month = cal.get(Calendar.MONTH)+1;
    private int year = cal.get(Calendar.YEAR);

    public String currentTime(){
        return time.format(cal.getTime());
    }
    public String currentDate(){
        String t_day = "";
        String t_month = "";
        if (day < 10) {t_day = "0"+day;}
        if (month < 10) {t_month = "0"+month;}
        return t_day+"."+t_month+"."+year;
    }
    public String date(){
        return day+"."+month+"."+year;
    }
    public String message(){
        int nowHour = Integer.parseInt(hour.format(cal.getTime()));
        if(nowHour > 21 || nowHour < 5) {
            return "Good Night";
        } else if (nowHour > 18) {
            return "Good  Evening";
        } else if (nowHour > 12) {
            return "Good Afternoon";
        } else if(nowHour > 5) {
            return "Good Morning";
        } else {
            return "Hi";
        }
    }
}
