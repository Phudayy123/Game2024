package SnakeGame;

import java.awt.*;
import javax.swing.*;

import CSDL.People;
import CSDL.PeopleDAO;


public class GameScreen extends JPanel implements Runnable {	
	private static final long serialVersionUID = 1L;
	EntryForm entryForm;
	Snake snake;
	private String message = "";	
	public static int [][] bg = new int[20][20];
	Thread thread;
	
	public GameScreen() {		
		bg[10][10]=2;	// khởi tại food
		snake = new Snake();
		entryForm=new EntryForm();		
		thread = new Thread(this);
		thread.start();	
	}
	
	@Override
	public void run() {		
		
	}		
	public void GameOver() {
	    if (snake.checkCollision()) {
            String playerName = EntryForm.getPlayerName(); 
            PeopleDAO dao = new PeopleDAO();
            People player = dao.findByName(playerName);
            
            if (player != null && snake.score > player.getDiem()) {
                player.setDiem(snake.score);
                dao.update(player);
            } else if (player == null) {
                People newPlayer = new People(playerName, snake.score);
                dao.insert(newPlayer);
            }
                                           
            JFrame frame = new JFrame("Game Over");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(350, 200);
            frame.setLocationRelativeTo(null);	         	            
            frame.add(new GameOverScreen(snake));	            
            frame.setVisible(true);  
            
            // Đóng frame chứa GameScreen (frame hiện tại)
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (currentFrame != null ) {
        	currentFrame.setVisible(false);
        	currentFrame.dispose();     
            }
        
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    bg[i][j] = 0;
                }
            }      
        
	    }
	}
		
	public void paintBg(Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, 400, 400);
        for(int i=0;i<20;i++) {
            for(int j=0;j<20;j++) {
                if(bg[i][j]==2) {
                    g.setColor(Color.yellow);
                    g.fillOval(i*20+1, j*20+1, 16, 16);
                    g.setColor(Color.gray);
                }

            }
        }
	}		
	
	public void paint(Graphics g) {
		paintBg(g);
		snake.paintSnake(g);
		g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString(message, 60, 200);		
        
    }
	public void setMessage(String message) {
        this.message = message;
        repaint(); 
    }
}