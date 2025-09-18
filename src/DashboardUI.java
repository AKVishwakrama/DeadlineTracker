import javax.swing.*;
import java.awt.*;

public class DashboardUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("📅 Assignment Deadline Tracker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLayout(new BorderLayout());

            // Sidebar Panel
            JPanel sidebar = new JPanel();
            sidebar.setLayout(new GridLayout(6, 1, 10, 10));
            sidebar.setBackground(new Color(40, 40, 70));

            JButton addTaskBtn = new JButton("➕ Add Task");
            JButton viewTaskBtn = new JButton("📂 View Tasks");
            JButton settingsBtn = new JButton("⚙ Settings");
            JButton exitBtn = new JButton("🚪 Exit");

            addTaskBtn.setBackground(new Color(70, 130, 180));
            addTaskBtn.setForeground(Color.WHITE);
            viewTaskBtn.setBackground(new Color(70, 130, 180));
            viewTaskBtn.setForeground(Color.WHITE);
            settingsBtn.setBackground(new Color(70, 130, 180));
            settingsBtn.setForeground(Color.WHITE);
            exitBtn.setBackground(new Color(178, 34, 34));
            exitBtn.setForeground(Color.WHITE);

            sidebar.add(addTaskBtn);
            sidebar.add(viewTaskBtn);
            sidebar.add(settingsBtn);
            sidebar.add(exitBtn);

            // Main Content Panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            JLabel title = new JLabel("📋 Dashboard - Upcoming Assignments", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 18));
            mainPanel.add(title, BorderLayout.NORTH);

            // Assignment List
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Math Homework - 2025-09-20 18:00");
            listModel.addElement("Science Project - 2025-09-22 09:00");

            JList<String> assignmentList = new JList<>(listModel);
            assignmentList.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(assignmentList);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // Notification Label
            JLabel notificationLabel = new JLabel("🔔 Notifications: Reminders 6h before deadline", JLabel.CENTER);
            notificationLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            mainPanel.add(notificationLabel, BorderLayout.SOUTH);

            // Add panels to Frame
            frame.add(sidebar, BorderLayout.WEST);
            frame.add(mainPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
