import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {
    public static int WIDTH = 300;
    public static int HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private int DELAY = 140;

    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
	private int apple_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private boolean chuachoi = false;
    

   

	public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);

        ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(this.getClass().getResource("apple.png"));
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(this.getClass().getResource("head.png"));
        head = iih.getImage();

        setFocusable(true);
        initGame();
    }


    public void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z*10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {
        	intro(g);

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0)
                    g.drawImage(head, x[z], y[z], this);
                else g.drawImage(ball, x[z], y[z], this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {
            gameOver(g);
        }
    }


    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2,
                     (HEIGHT+120) / 2-20);
    }
    public void intro(Graphics g) {
    	String bien = "--------------------------------------------------------------------------------------------------------------------------------------------------------------";
        String msg = "Nhấn Enter để chơi";
        String normal = "Nhấn SHIFT để dừng";
        String map1 = "Nhấn 1 chơi bản đồ nhỏ";
        String map2 = "Nhấn 2 chơi bản đồ lớn";
        Font small = new Font("Helvetica", Font.BOLD, 10);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(bien, (WIDTH - metr.stringWidth(bien))/2,
                HEIGHT+20);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg))/2,
                     HEIGHT+35);
        g.drawString(normal, (WIDTH - metr.stringWidth(normal))/2,
                (HEIGHT+50));
        g.drawString(map1, (WIDTH - metr.stringWidth(map1))/2,
                HEIGHT+65);
        g.drawString(map2, (WIDTH - metr.stringWidth(map2))/2,
           (HEIGHT+80));
    }

    public void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }


    public void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) {
            x[0] -= DOT_SIZE;
        }

        if (right) {
            x[0] += DOT_SIZE;
        }

        if (up) {
            y[0] -= DOT_SIZE;
        }

        if (down) {
            y[0] += DOT_SIZE;
        }
    }


    public void checkCollision() {

          for (int z = dots; z > 0; z--) {

              if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                  inGame = false;
              }
          }

        if (y[0] > HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] > WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
    }

    public void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    public void actionPerformed(ActionEvent e) {
         if ((chuachoi)) {
        	 if (inGame) {
	            checkApple();
	            checkCollision();
	            move();
	        }

       
           repaint();
        }
    }


    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (chuachoi) {
            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
            }
            if (key == KeyEvent.VK_ENTER) {
            	chuachoi = true;
            }
            if (key == KeyEvent.VK_SHIFT) {
            	chuachoi = false;
            }
//            if(inGame==false) {
            if (key == KeyEvent.VK_1) {
            	WIDTH = 300;
            	HEIGHT = 300;
            	Snake.s1.setSize(WIDTH+30,HEIGHT+120);
//            	inGame = true;
//            	DELAY=140;
//            	//chuachoi=false;
//            	initGame();
//            	Snake.s1.add(new Board());
//            	//addKeyListener(new TAdapter());
            }
            if (key == KeyEvent.VK_2) {
            	WIDTH = 400;
            	HEIGHT = 400;
            	Snake.s1.setSize(WIDTH+30,HEIGHT+120);
//            	inGame = true;
//            	DELAY=500;
//            	//chuachoi=false;
//            	initGame();
//            	Snake.s1.add(new Board());
//            	//addKeyListener(new TAdapter());
            }
//            }
        
        }
    }
}