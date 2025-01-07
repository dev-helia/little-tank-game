package event;

// Demonstrate the ball moving up, down, left and right by keyboard control

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame{
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setTitle("Ball Move");
        this.setSize(800, 600);
        //JFrame
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

// panel
class MyPanel extends JPanel implements KeyListener { // Listen
    //set variable
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 30, 30);
    }
    // input
    @Override
    public void keyTyped(KeyEvent e) {

    }
    // press key
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char)e.getKeyChar() + "被按下...");
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        }
        // let panel repaint
        this.repaint();
    }
    // release key
    @Override
    public void keyReleased(KeyEvent e) {

    }
}