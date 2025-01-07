package tankgame_v3;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame03 extends JFrame {


    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        TankGame03 tankGame01 = new TankGame03();
    }

    public TankGame03() {
        System.out.println("请输入选择:1是新游戏, 2是恢复上局游戏");
        String key = scanner.next();
        mp = new MyPanel(key);
        //将mp放入thread 启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setTitle("Tank Game");
        this.setSize(1000,750);
        this.addKeyListener(mp); // add listener
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //再JFrame中增加监听关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到关闭窗口");
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
