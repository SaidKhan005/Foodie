import javax.swing.*;

public class CalorieTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalorieTrackerFrame().setVisible(true);
        });
    }
}