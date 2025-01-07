package draw;

import javax.swing.*;
import java.awt.*;

/**
 * How to draw circle
 * Practice file
 * */


public class DrawCircle extends JFrame{ //extends JFrame -> contains canvas
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();

    }
    public DrawCircle() { // constructor
        // Initialize the panel
        mp = new MyPanel();
        //put the canvas into the frame
        this.add(mp);
        //set the size of the canvas
        this.setSize(500, 500);
        this.setVisible(true); // can be seen
    }
}

// define a panel, extend JPanel class.
class MyPanel extends JPanel {
    // MyPanel instance -> canvas
    // Graphics g -> pen : offer many functions to draw on canvas
    @Override
    public void paint(Graphics g) { // draw function
        super.paint(g); // remain: Invoking a method from the parent class using super and initialize

        g.drawOval(100, 100, 100, 100);

        // many graphs
        //line
        g.drawLine(10, 10, 100, 100);
        //rectangle
        g.drawRect(100, 100, 100, 100);
        g.setColor(Color.RED);
        g.fillRect(100, 100, 100, 100);

        //image
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("test.png"));
        g.drawImage(image, 300, 30,260,379,this);

        //text
        g.setColor(Color.BLUE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("Hello World", 100, 100);
    }
}