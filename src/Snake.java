

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;



public class Snake extends JFrame{
	public static Snake s1;
    public Snake(int width,int height) {
    	add(new Board());
        width = Board.WIDTH;
        height = Board.HEIGHT;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width+30, height+120);
        setLocationRelativeTo(null);
        setTitle("Snake");	
        setResizable(true);																																							
        setVisible(true);
    }
    public static void main(String[] args) {
    	s1 = new Snake(330,350);
    	
    }
  
   
    
}