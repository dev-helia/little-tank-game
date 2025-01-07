package tankgame_v1;

import javax.swing.*;

public class TankGame01 extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setTitle("Tank Game");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
