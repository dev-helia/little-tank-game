package tankgame_v3;

import java.util.Vector;

public class Enemy extends Tank implements Runnable {
    // Vector 保存多个shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while(true) {
            // 判断
            // 如果shots.size() == 0 创建放入并启动
            if(isLive && shots.size() < 1) {
                Shot s = null;
                switch(getDirect()){
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                }
                shots.add(s);
                new Thread(s).start();
            }
            switch(getDirect()){
                case 0:
                    // 让坦克多走几步
                    for(int i = 0; i < 6; i++){
                        if(getY() > 0){
                        moveUp();}
                        //休眠一下
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    // 让坦克多走几步
                    for(int i = 0; i < 6; i++){
                        if (getX() + 60 < 1000){moveRight();}

                        //休眠一下
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    // 让坦克多走几步
                    for(int i = 0; i < 6; i++){
                        if(getY() + 60 <  750){moveDown();}

                        //休眠一下
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    // 让坦克多走几步
                    for(int i = 0; i < 6; i++){
                        if(getX() >0){moveLeft();}

                        //休眠一下
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            //随机方向
            setDirect((int)(Math.random() * 4));
            //写并发必须启动和退出
            if(!isLive){
                break;
            }

        }
    }
}
