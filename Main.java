import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventCalendar calendar = new EventCalendar();

        System.out.println("Welcome to the Calendar App!");

        while (true) {
            System.out.println("\n1. Add Event");
            System.out.println("2. Remove Event");
            System.out.println("3. View Events on a Date");
            System.out.println("4. View All Events");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {

                    case "1":
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();

                        System.out.print("Start time (HH:MM): ");
                        String start = sc.nextLine();

                        System.out.print("End time (HH:MM): ");
                        String end = sc.nextLine();

                        calendar.addEvent(new Event(name, date, start, end));
                        System.out.println("Event added!");
                        break;

                    case "2":
                        System.out.print("Event name: ");
                        String removeName = sc.nextLine();

                        System.out.print("Date: ");
                        String removeDate = sc.nextLine();

                        Event toRemove = null;
                        for (Event e : calendar.getEventsOnDate(removeDate)) {
                            if (e.getName().equals(removeName)) {
                                toRemove = e;
                                break;
                            }
                        }

                        if (toRemove != null) {
                            calendar.removeEvent(toRemove);
                            System.out.println("Event removed!");
                        } else {
                            System.out.println("Event not found.");
                        }
                        break;

                    case "3":
                        System.out.print("Date: ");
                        String d = sc.nextLine();

                        for (Event e : calendar.getEventsOnDate(d)) {
                            System.out.println(e.getName() + " " +
                                    e.getStartTime() + " - " + e.getEndTime());
                        }
                        break;

                    case "4":
                        for (Event e : calendar.getAllEvents()) {
                            System.out.println(e.getName() + " on " +
                                    e.getDate() + " " +
                                    e.getStartTime() + " - " + e.getEndTime());
                        }
                        break;

                    case "5":
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}