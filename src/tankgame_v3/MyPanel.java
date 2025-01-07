package tankgame_v3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/*
* Game Panel
* */
//为了不断重绘子弹, 需要将MyPanel当做线程
public class MyPanel extends JPanel implements KeyListener, Runnable {
    // Define tanks
    Hero hero = null;
    Vector<Enemy> enemies= new Vector<>();
    //定一个存放Node的Vector, 用于恢复上局
    Vector<Node > nodes= new Vector<>();
    int enemiesSize = 3;


    public MyPanel(String key) {
        nodes = Recorder.getNodesAndEnemeyTankRec();
        // 将MyPanel的enemy设置给recorder的enemyTanks
        Recorder.setEnemyTanks(enemies);
        hero = new Hero(600, 100); // define the hero: (100,100)

        switch(key){
            case "1":
                for (int i = 0; i < enemiesSize; i++) {
                    //创建敌人的坦克
                    Enemy enemy = new Enemy(100 * (i + 1) , 0);
                    //设置方向
                    enemy.setDirect(2);
                    //启动敌人随机移动
                    new Thread(enemy).start();
                    //给敌人初始化加入子弹
                    Shot shot = new Shot(enemy.getX() + 20, enemy.getY()+60,enemy.getDirect());
                    enemy.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    enemies.add(enemy);
                }

                break;
            case "2"://继续上局
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建敌人的坦克
                    Enemy enemy = new Enemy(node.getX(),node.getY());
                    //设置方向
                    enemy.setDirect(node.getDirect());
                    //启动敌人随机移动
                    new Thread(enemy).start();
                    //给敌人初始化加入子弹
                    Shot shot = new Shot(enemy.getX() + 20, enemy.getY()+60,enemy.getDirect());
                    enemy.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    enemies.add(enemy);
                }
                break;

        }}


    // 编写方法,显示击毁坦克的信息
    public void showInfow(Graphics g) {
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 20);
        g.setFont(font);

        g.drawString("你已经击毁了",1020,30);
        drawTank(1020,60,g,0,1);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // default: black
        showInfow(g);
        // tank
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(),0);
        }


//        // bullet
//        if(hero.shot != null && hero.shot.isLive != false) {
//            g.setColor(Color.cyan); // 设置英雄坦克颜色为蓝色
//            g.draw3DRect(hero.shot.x, hero.shot.y, 4,4,false);
//        }
        // 将子弹遍历取出
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if(shot != null && shot.isLive) {
        g.setColor(Color.cyan); // 设置英雄坦克颜色为蓝色
          g.draw3DRect(shot.x,shot.y, 4,4,false);
        } else {
                // 如果子弹已经死掉, 拿掉子弹
                hero.shots.remove(shot);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            //判断当前的坦克是否活着
            if(enemy.isLive){
            drawTank(enemy.getX(),enemy.getY(),g,enemy.getDirect(),1);}
            //子弹
            for(int j = 0; j < enemy.shots.size(); j++) {
                //取出子弹
                Shot shot = enemy.shots.get(j);
                //绘制
                if(shot.isLive) {
                    g.setColor(Color.red); // 设置敌人坦克颜色为红色
                    g.draw3DRect(shot.x, shot.y, 4,4,false);
                } else {
                    //移除子弹
                    enemy.shots.remove(shot);
                }

            }
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
    public void hitEnemy() {
        //            //判断是否有坦克被击中
//            if(hero.shot != null && hero.shot.isLive ){
//                for(int i = 0; i<enemies.size(); i++) {
//                    Enemy enemy = enemies.get(i);
//                    hitTank(hero.shot, enemy);
//                }
//            }
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);

            if (shot != null && shot.isLive) {
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    hitTank(shot, enemy);
                }
            }
        }
    }

    //判断敌方坦克是否击中
    public void hitHero() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            //遍历所有子弹
            for (int j = 0; j < enemy.shots.size(); j++) {
                Shot shot = enemy.shots.get(j);
                if(hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }

        }
    }

    // 编写方法, 判断子弹是否击中敌人
    public void hitTank(Shot s, Tank enemy){
        switch (enemy.getDirect()) {
            case 0:
            case 2:
                if(s.x>enemy.getX() && s.x < (enemy.getX()+40)
                && s.y>enemy.getY()&&s.y<enemy.getY()+60){
                    s.isLive = false;
                    enemy.isLive = false;
                    enemies.remove(enemy);
                    // 当我放击毁一个敌人坦克, 更改数据
                    if(enemy instanceof Enemy) {
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
            case 1:
            case 3:
                if(s.x>enemy.getX() && s.x < (enemy.getX()+60)
                        && s.y>enemy.getY()&&s.y<enemy.getY()+40){
                    s.isLive = false;
                    enemy.isLive = false;
                    enemies.remove(enemy);
                }
                break;
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
            if(hero.getY() > 0) {
                hero.moveUp();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if(hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if(hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if(hero.getX() >0) {
                hero.moveLeft();
            }
        }

        // 发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
            // 判断是否发子弹 发射一颗子弹
//            if(hero.shot == null || hero.shot.isLive == false){
//            hero.shotEnemyTank();}
            // 发射多颗子弹
            hero.shotEnemyTank();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒自己重绘
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitHero();
            hitEnemy();
            this.repaint();
        }
    }
}
