import java.util.Date;

public class Assignment {
    private String subject;
    private Date dueDate;

    public Assignment(String subject, Date dueDate) {
        this.subject = subject;
        this.dueDate = dueDate;
    }

    // Getters
    public String getSubject() {
        return subject;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Utility: Show assignment as string
    @Override
    public String toString() {
        return subject + " - " + dueDate;
    }
}
