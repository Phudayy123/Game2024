package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class GameInstructions {
    public GameInstructions() {
        JFrame frame = new JFrame("Game Instructions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));      

        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 

        JLabel instructionLabel = new JLabel("Game Instructions");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructionLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); 

        String[] instructions = {
            "Move Forward: Up Arrow",
            "Move Backward: Down Arrow",
            "Move Left: Left Arrow",
            "Move Right: Right Arrow",
            "Pause: Space"
        };

        for (String instruction : instructions) {
            JLabel label = new JLabel(instruction);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT); 
            panel.add(label);           
            panel.add(Box.createRigidArea(new Dimension(0, 5))); 
        }        

        frame.add(panel, BorderLayout.CENTER);     
        frame.setVisible(true);
    }
}

