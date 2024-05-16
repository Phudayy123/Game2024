package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Snake {
	Point foodLocation;	
	int length= 4;
	int []x;
	int []y;
	int score=0;
	
	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;
	
	int vector = Snake.GO_DOWN;	
    long t=0;
	
	public Snake() {
		x = new int [20];
		y = new int [20];
		
		x[0]=5;
		y[0]=4;
		x[1]=5;
		y[1]=3;
		x[2]=5;
		y[2]=2;		
		
		foodLocation = placeFood();
	}

	public void setVector(int v) {
		if(vector != -v)
		vector=v;
	}
	
	public boolean checkCollision() {
        for (int i = 1; i < length; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                return true; 
            }
        }
        return false;
    }
	
	public boolean checkFood(int x1, int y1) {
		for(int i=0;i<length;i++) if(x[i]== x1 && y[i]==y1) return true;
		return false;
	}
	
	public Point placeFood() {
		Random r = new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (checkFood(x, y));
      
        foodLocation = new Point(x, y);
        return foodLocation;
	}
	
	public void update() {
		if(System.currentTimeMillis()-t>150) {
			
			// eat food
			if (GameScreen.bg[x[0]][y[0]] == 2) {
	            length++;
	            score++;
	            GameScreen.bg[x[0]][y[0]] = 0;
	            foodLocation = placeFood();
	            GameScreen.bg[foodLocation.x][foodLocation.y] = 2;
	        }
					
			for(int i=length-1;i>0;i--) {
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
			
			if(vector == Snake.GO_UP) y[0]--;
			if(vector == Snake.GO_DOWN) y[0]++;
			if(vector == Snake.GO_LEFT) x[0]--;
			if(vector == Snake.GO_RIGHT) x[0]++;
			
			if(x[0]<0) x[0]=19;
			if(x[0]>19) x[0]=0;
			if(y[0]<0) y[0]=19;
			if(y[0]>19) y[0]=0;
			
			t=System.currentTimeMillis();
		}	
	}
	
	public void paintSnake(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x[0]*20+1, y[0]*20+1, 18, 18);
		
		g.setColor(Color.red);
		for(int i=1;i<length;i++) {
			g.fillRect(x[i]*20+1, y[i]*20+1, 18, 18);
		}
	}
}
