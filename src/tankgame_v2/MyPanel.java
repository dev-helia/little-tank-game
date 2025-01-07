package tankgame_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/*
* Game Panel
* */
public class MyPanel extends JPanel implements KeyListener {
    // Define tanks
    Hero hero = null;
    Vector<Enemy> enemies= new Vector<>();
    int enemiesSize = 3;
    public MyPanel() {
        hero = new Hero(100, 100); // define the hero: (100,100)
        for (int i = 0; i < enemiesSize; i++) {
            Enemy enemy = new Enemy(100 * (i + 1) , 0);
            enemy.setDirect(2);
            enemies.add(enemy);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // default: black

        // tank
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(),0);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            drawTank(enemy.getX(),enemy.getY(),g,enemy.getDirect(),1);
        }
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
        // set direction and draw the different tanks
        // 0 up 1 right 2 down 3 left
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false); // left wheel
                g.fill3DRect(x + 30, y, 10, 60, false); // right wheel
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // body
                g.fillOval(x + 10, y + 20, 20, 20); // head
                g.drawLine(x + 20, y, x+20, y + 20); // battery
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false); // left wheel
                g.fill3DRect(x, y + 30, 60, 10, false); // right wheel
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // body
                g.fillOval(x + 20, y + 10, 20, 20); // head
                g.drawLine(x + 30, y + 20, x+60, y + 20); // battery
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false); // left wheel
                g.fill3DRect(x + 30, y, 10, 60, false); // right wheel
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // body
                g.fillOval(x + 10, y + 20, 20, 20); // head
                g.drawLine(x + 20, y + 30, x+20, y + 60); // battery
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false); // left wheel
                g.fill3DRect(x, y + 30, 60, 10, false); // right wheel
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // body
                g.fillOval(x + 20, y + 10, 20, 20); // head
                g.drawLine(x + 30, y + 20, x, y + 20); // battery
                break;

            default:
                System.out.println("Not yet...");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //wdsa
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
