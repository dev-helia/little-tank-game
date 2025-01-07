package tankgame_v3;

import java.util.Vector;

public class Hero extends Tank {
    // 定义一个shot对象, 表示射击行为(线程)
    Shot shot = null;


    // 可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        // 最多五颗
        if(shots.size() == 5) return;
        // create shot
        /** 根据当前hero对象的位置和方向来创建shot 对象*/
        switch(this.getDirect()) { // hero的方向
            case 0: // up
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        // 把新建的子弹加入vector
        shots.add(shot);
        // 启动线程
        new Thread(shot).start();
    }
}
