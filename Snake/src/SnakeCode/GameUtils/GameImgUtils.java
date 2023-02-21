package SnakeCode.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:18
 * * explain:
 **/
public class GameImgUtils {
    public static Image upImg = new ImageIcon("images/up.png").getImage();
    public static Image downImg = new ImageIcon("images/down.png").getImage();
    public static Image leftImg = new ImageIcon("images/left.png").getImage();
    public static Image rightImg = new ImageIcon("images/right.png").getImage();
    //身体
    public static Image bodyImg = new ImageIcon("images/body.png").getImage();
    //食物
    public static Image foodImg = new ImageIcon("images/food.png").getImage();
    //文字绘制
    public static void drawWord(Graphics g,String str,Color color,int size,int x,int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}
