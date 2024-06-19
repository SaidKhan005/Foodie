import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealPanel extends JPanel {
    private JTextField foodField;
    private JTextField calorieField;
    private DefaultListModel<String> listModel;
    private JList<String> foodList;
    private JLabel totalCaloriesLabel;
    private int totalCalories = 0;

    public MealPanel() {
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Food:"));
        foodField = new JTextField();
        inputPanel.add(foodField);

        inputPanel.add(new JLabel("Calories:"));
        calorieField = new JTextField();
        inputPanel.add(calorieField);

        JButton addButton = new JButton("Add");
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        // List panel
        listModel = new DefaultListModel<>();
        foodList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(foodList);
        add(listScrollPane, BorderLayout.CENTER);

        // Total calories panel
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());

        totalCaloriesLabel = new JLabel("Total Calories: 0");
        totalPanel.add(totalCaloriesLabel, BorderLayout.CENTER);

        add(totalPanel, BorderLayout.SOUTH);

        // Add button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFoodItem();
            }
        });

        // Delete button action listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFoodItem();
            }
        });
    }

    private void addFoodItem() {
        String food = foodField.getText();
        String calorieText = calorieField.getText();
        if (!food.isEmpty() && !calorieText.isEmpty()) {
            try {
                int calories = Integer.parseInt(calorieText);
                listModel.addElement(food + " - " + calories + " calories");
                foodField.setText("");
                calorieField.setText("");
                updateTotalCalories();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for calories.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both food and calorie values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFoodItem() {
        int selectedIndex = foodList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            updateTotalCalories();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a food item to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotalCalories() {
        totalCalories = 0;
        for (int i = 0; i < listModel.getSize(); i++) {
            String item = listModel.getElementAt(i);
            String[] parts = item.split(" - ");
            if (parts.length == 2) {
                String caloriePart = parts[1].split(" ")[0];
                try {
                    totalCalories += Integer.parseInt(caloriePart);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        totalCaloriesLabel.setText("Total Calories: " + totalCalories);
    }

    public int getTotalCalories() {
        return totalCalories;
    }
}
