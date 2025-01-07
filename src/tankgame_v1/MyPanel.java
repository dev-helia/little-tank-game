package tankgame_v1;

import javax.swing.*;
import java.awt.*;

/*
* Game Panel
* */
public class MyPanel extends JPanel {
    // Define tanks
    Hero hero = null;
    public MyPanel() {
        hero = new Hero(100, 100); // define the hero: (100,100)
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // default: black

        // tank
        drawTank(hero.getX(), hero.getY(), g, 0,0);
    }
    // tank function

    /** // /** shortcuts
     *
     * @param x the x coordinate of the top-left corner
     * @param y the y coordinate of the top-left corner
     * @param g the pen
     * @param direct direction of the tank
     * @param type the type of the tank
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // set tank's color
        switch (type) {
            case 0: //me
                g.setColor(Color.cyan);
                break;
            case 1: // enemy
                g.setColor(Color.red);
                break;
        }
        // set direction
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false); // left wheel
                g.fill3DRect(x + 30, y, 10, 60, false); // right wheel
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // body
                g.fillOval(x + 10, y + 20, 20, 20); // head
                g.drawLine(x + 20, y, x+20, y + 20); // battery

            default:
                System.out.println("Not yet...");
        }
    }
}
