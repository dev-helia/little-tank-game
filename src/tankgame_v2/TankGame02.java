package tankgame_v2;

import javax.swing.*;

public class TankGame02 extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame02 tankGame01 = new TankGame02();
    }
    public TankGame02() {
        mp = new MyPanel();
        this.add(mp);
        this.setTitle("Tank Game");
        this.setSize(1000,750);
        this.addKeyListener(mp); // add listener
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
