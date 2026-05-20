import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EventCalendarTest {

    private EventCalendar calendar;

    @BeforeEach
    void setUp() {
        calendar = new EventCalendar();
    }

    @Test
    void testAddEvent() {
        Event e = new Event("Meeting", "2024-06-01", "10:00", "11:00");
        calendar.addEvent(e);

        assertEquals(1, calendar.getAllEvents().size());
        assertEquals("Meeting", calendar.getAllEvents().get(0).getName());
    }

    @Test
    void testOverlappingEvent() {
        Event e1 = new Event("Meeting", "2024-06-01", "10:00", "11:00");
        Event e2 = new Event("Lunch", "2024-06-01", "10:30", "11:30");

        calendar.addEvent(e1);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            calendar.addEvent(e2);
        });

        assertTrue(ex.getMessage().contains("conflicts"));
    }

    @Test
    void testInvalidTime() {
        Event e = new Event("Bad", "2024-06-01", "12:00", "11:00");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            calendar.addEvent(e);
        });

        assertTrue(ex.getMessage().contains("Start time must be before end time"));
    }

    @Test
    void testRemoveEvent() {
        Event e = new Event("Meeting", "2024-06-01", "10:00", "11:00");
        calendar.addEvent(e);
        calendar.removeEvent(e);

        assertEquals(0, calendar.getAllEvents().size());
    }

    @Test
    void testGetEventsOnDate() {
        calendar.addEvent(new Event("A", "2024-06-01", "10:00", "11:00"));
        calendar.addEvent(new Event("B", "2024-06-01", "12:00", "13:00"));
        calendar.addEvent(new Event("C", "2024-06-02", "18:00", "19:00"));

        assertEquals(2, calendar.getEventsOnDate("2024-06-01").size());
    }
}