import java.util.*;

public class EventCalendar {
    private List<Event> events;

    public EventCalendar() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {

        // Validate time
        if (!event.getStartTime().isBefore(event.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        // Check overlap on same date
        for (Event e : getEventsOnDate(event.getDate())) {
            boolean overlap =
                    event.getStartTime().isBefore(e.getEndTime()) &&
                    event.getEndTime().isAfter(e.getStartTime());

            if (overlap) {
                throw new IllegalArgumentException(
                        "Event '" + event.getName() +
                        "' conflicts with existing event '" + e.getName() +
                        "' on " + event.getDate()
                );
            }
        }

        events.add(event);

        // Sort by start time
        events.sort(Comparator.comparing(Event::getStartTime));
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public List<Event> getEventsOnDate(String date) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.getDate().equals(date)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Event> getAllEvents() {
        return events;
    }
}