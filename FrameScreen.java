package SnakeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FrameScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean gamePaused = false;
    private Timer gameTimer;
    GameScreen gameScreen; 
	
	public FrameScreen() {
		setSize(418,440);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		gameScreen = new GameScreen();
		add(gameScreen);
		setFocusable(true);
		
		gameTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!gamePaused && !gameScreen.snake.checkCollision()) {
                	
                    gameScreen.snake.update();
                    gameScreen.repaint();
                    gameScreen.GameOver();                  
                    
                }
            }
        });
        gameTimer.start();      
        addKeyListener(new handler());			
		setVisible(true);				
	}
	
	public void actionPerformed(ActionEvent evt) {
        if (!gamePaused) {
            gameScreen.snake.update();
            gameScreen.repaint();
        }
    }

	private class handler implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			 if(e.getKeyCode()== KeyEvent.VK_SPACE) {
				 gamePaused = !gamePaused;
	                if (gamePaused) {
	                    gameScreen.setMessage("PRESS SPACE TO PLAY GAME!");
	                } else {
	                    gameScreen.setMessage("");
	                }	                               
	        }
	        if(e.getKeyCode()==KeyEvent.VK_UP) {
	        	gameScreen.snake.setVector(Snake.GO_UP);
	        }
	        if(e.getKeyCode()==KeyEvent.VK_DOWN) {
	        	gameScreen.snake.setVector(Snake.GO_DOWN);
	        }
	        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
	        	gameScreen.snake.setVector(Snake.GO_LEFT);
	        }
	        if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
	        	gameScreen.snake.setVector(Snake.GO_RIGHT);
	        }
	       
	    }
		@Override
		public void keyReleased(KeyEvent e) {
			
		}	  
}
	
}