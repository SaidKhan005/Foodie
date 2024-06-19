import javax.swing.*;
import java.awt.*;

public class CalorieTrackerFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JLabel totalCaloriesLabel;

    public CalorieTrackerFrame() {
        setTitle("Calorie Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Breakfast", new MealPanel());
        tabbedPane.addTab("Lunch", new MealPanel());
        tabbedPane.addTab("Dinner", new MealPanel());
        tabbedPane.addTab("Snacks", new MealPanel());

        add(tabbedPane, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());

        totalCaloriesLabel = new JLabel("Total Calories: 0");
        totalPanel.add(totalCaloriesLabel, BorderLayout.CENTER);

        JButton calculateTotalButton = new JButton("Calculate Overall Total");
        totalPanel.add(calculateTotalButton, BorderLayout.EAST);

        add(totalPanel, BorderLayout.SOUTH);

        calculateTotalButton.addActionListener(e -> calculateOverallTotalCalories());
    }

    private void calculateOverallTotalCalories() {
        int overallTotalCalories = 0;
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            MealPanel panel = (MealPanel) tabbedPane.getComponentAt(i);
            overallTotalCalories += panel.getTotalCalories();
        }
        totalCaloriesLabel.setText("Total Calories: " + overallTotalCalories);
    }
}
