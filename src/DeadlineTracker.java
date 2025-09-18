import java.util.*;

class Assignment {
    String title;
    Date dueDate;

    Assignment(String title, Date dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return title + " (Due: " + dueDate + ")";
    }
}

public class DeadlineTracker {
    static Scanner scanner = new Scanner(System.in);
    static List<Assignment> assignments = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n Deadline Tracker");
            System.out.println("1. Add Assignment");
            System.out.println("2. View Assignments");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addAssignment();
                    break;
                case 2:
                    viewAssignments();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addAssignment() {
        System.out.print("Enter assignment title: ");
        String title = scanner.nextLine();

        System.out.print("Enter due date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();

        try {
            Date dueDate = java.sql.Date.valueOf(dateStr);
            assignments.add(new Assignment(title, dueDate));
            System.out.println("Assignment added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }

    private static void viewAssignments() {
        if (assignments.isEmpty()) {
            System.out.println("📭 No assignments added yet.");
            return;
        }

        assignments.sort(Comparator.comparing(a -> a.dueDate));
        System.out.println("\n📖 Assignments:");
        for (Assignment a : assignments) {
            System.out.println("- " + a);
        }
    }
}
