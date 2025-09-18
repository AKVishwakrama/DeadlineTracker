import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Assignment {
    String subject;
    Date dueDate;

    Assignment(String subject, Date dueDate) {
        this.subject = subject;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return subject + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dueDate);
    }
}

public class DeadlineTrackerUI extends JFrame {
    private DefaultListModel<Assignment> listModel;
    private JList<Assignment> assignmentList;
    private JTextField subjectField, dateField, timeField;

    private Timer timer = new Timer();

    public DeadlineTrackerUI() {
        setTitle("Deadline Tracker with Notifications");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        inputPanel.add(subjectField);

        inputPanel.add(new JLabel("Due Date (yyyy-MM-dd):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Due Time (HH:mm, 24h):"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        JButton addButton = new JButton("Add Assignment");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // List Panel
        listModel = new DefaultListModel<>();
        assignmentList = new JList<>(listModel);
        add(new JScrollPane(assignmentList), BorderLayout.CENTER);

        // Button Action
        addButton.addActionListener(e -> {
            try {
                String subject = subjectField.getText();
                String dateStr = dateField.getText() + " " + timeField.getText();

                Date dueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);

                Assignment newAssignment = new Assignment(subject, dueDate);
                listModel.addElement(newAssignment);

                // Sort the list
                List<Assignment> tempList = Collections.list(listModel.elements());
                tempList.sort(Comparator.comparing(a -> a.dueDate));
                listModel.clear();
                for (Assignment a : tempList) {
                    listModel.addElement(a);
                }

                scheduleReminder(newAssignment);

                subjectField.setText("");
                dateField.setText("");
                timeField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Format! Use yyyy-MM-dd and HH:mm.");
            }
        });
    }

    private void scheduleReminder(Assignment assignment) {
        long reminderTime = assignment.dueDate.getTime() - (6 * 60 * 60 * 1000); // 6 hours before

        if (reminderTime > System.currentTimeMillis()) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    showNotification("Reminder: " + assignment.subject,
                            "Due at " + new SimpleDateFormat("HH:mm, yyyy-MM-dd").format(assignment.dueDate));
                }
            }, new Date(reminderTime));
        }
    }

    private void showNotification(String title, String message) {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Deadline Tracker");
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip("Deadline Tracker");
                tray.add(trayIcon);
                trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DeadlineTrackerUI().setVisible(true);
        });
    }
}
