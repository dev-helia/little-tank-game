package tankgame_v3;

import java.io.*;
import java.util.Vector;

public class Recorder {

    //定义变量, 记录我方击毁敌人
    private static int allEnemyTankNum = 0;
    //定义io对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src/tankgame_v3/record.txt";

    // 定义一个Node的Vector 用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();

    //增加一个方法, 用于读取recordFile的恢复信息
    public static Vector<Node> getNodesAndEnemeyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node( Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[3]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    private static Vector<Enemy> enemyTanks = null;
    // 定义vector, 指向myPanel对象的敌人坦克vector
    public static void setEnemyTanks(Vector<Enemy> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    // 增加方法, 当游戏退出时, 将allEnemyTankNum 保存到record File
    // 保存地方坦克的坐标和方向

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            // 遍历敌人坦克的vector, 然后根据情况保存
            // 定义一个属性, 然后通过set方法得到敌人的vector
            for(int i = 0; i < enemyTanks.size(); i++) {
                // 取出敌人tank
                Enemy enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive) {
                    // 保存该tank的信息
                    String record = enemyTank.getX() + " "  + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bw.write(record+"\r\n");


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }
    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    // 当我方坦克击毁一个敌人坦克, 就++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }


}
