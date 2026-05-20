import java.time.LocalTime;

public class Event {
    private String name;
    private String date; // YYYY-MM-DD
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String name, String date, String startTime, String endTime) {
        this.name = name;
        this.date = date;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}