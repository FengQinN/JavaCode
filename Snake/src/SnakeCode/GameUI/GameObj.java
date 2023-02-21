package SnakeCode.GameUI;

import java.awt.*;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:18
 * * explain:
 **/
public class GameObj {
    //图片
    Image img;
    //坐标
    int x;
    int y;
    //小方块的宽高
    int width = 30;
    int height = 30;
    //窗口类的引用
    GameFrame gameFrame;
    //构造器
    public GameObj() {
    }

    public GameObj(Image img, int x, int y, GameFrame gameFrame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.gameFrame = gameFrame;
    }

    public GameObj(Image img, int x, int y, int width, int height, GameFrame gameFrame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameFrame = gameFrame;
    }

    //get and set
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    //定义物体绘制方法
    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
