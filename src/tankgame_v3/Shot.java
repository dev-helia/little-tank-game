package tankgame_v3;

public class Shot implements Runnable {
    // the bullet
    int x;
    int y;
    int direct = 0;
    int speed = 2;
    boolean isLive = true;

    // constructor
    public Shot(int x, int y, int direct) {
        this.y = y;
        this.direct = direct;
        this.x = x;
    }

    @Override
    public void run() { // shot
        while (true) {
            // 线程休眠 -> 50 millisecond
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0: // up
                    y -= speed;
                    break;
                case 1:// right
                    x += speed;
                    break;
                case 2://down
                    y += speed;
                    break;
                case 3: // left
                    x -= speed;
                    break;
            }
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive == true)) {
                isLive = false;
                break;
            }
        }


    }
}
