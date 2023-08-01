package FinalTask;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Note implements Serializable {
    private String date;
    private String time;
    private String content;

    public Note(String date, String time, String content) {
        this.date = date;
        this.time = time;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getWeekDay() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date dateObj = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObj);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            String[] weekDays = {"", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
            return weekDays[dayOfWeek];
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String toString() {
        return date + " " + time + " (" + getWeekDay() + ") - " + content;
    }
}