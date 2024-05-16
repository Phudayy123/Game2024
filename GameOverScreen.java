package SnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameOverScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	private Snake sn1;
    
	public GameOverScreen(Snake snake) {	
	    this.sn1 = snake;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    JPanel textPanel = new JPanel() {	        
			private static final long serialVersionUID = 1L;

			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.setColor(Color.BLACK);
	            Font font = new Font("Arial", Font.BOLD, 16);
	            g.setFont(font);
	            String line1 = "Game over";
	            String line2 = "Your score: " + sn1.score;
	            FontMetrics metrics = g.getFontMetrics(font);
	            int line1Width = metrics.stringWidth(line1);
	            int line2Width = metrics.stringWidth(line2);
	            int x = (getWidth() - Math.max(line1Width, line2Width)) / 2;
	            int lineSpacing = metrics.getHeight();
	            int y1 = getHeight() / 2 - lineSpacing / 2; 
	            int y2 = getHeight() / 2 + lineSpacing / 2; 
	            g.drawString(line1, x, y1);
	            g.drawString(line2, x, y2);
	        }
	    };
	    textPanel.setPreferredSize(new Dimension(350, 150));  
	    add(textPanel);
	    button();  
    }
	
	public void button() {
		JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JButton gameOverButton = new JButton("Quay lại màn hình chính");
	    buttonPanel.add(gameOverButton);
	    add(buttonPanel, BorderLayout.SOUTH);
	    
	    gameOverButton.addActionListener(new ActionListener() {	    	
	        @Override
			public void actionPerformed(ActionEvent e) {				
				new MainMenu();					
				// Đóng GameOverScreen
                JFrame topLevelAncestor = (JFrame) SwingUtilities.getWindowAncestor(GameOverScreen.this);
                if (topLevelAncestor != null) {
                    topLevelAncestor.dispose();
                }
			}
		});
	}
}
