package SnakeCode.GameUI;

import SnakeCode.GameUtils.GameImgUtils;

import java.awt.*;
import java.util.Random;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:55
 * * explain:
 **/
public class FoodObj extends GameObj{
    //随机函数
    Random random = new Random();

    public FoodObj() {
        super();
    }
    //获取食物
    public FoodObj getFood(){
        return  new FoodObj(GameImgUtils.foodImg,random.nextInt(20) * 30, (random.nextInt(19)+1) * 30,this.gameFrame);
    }

    public FoodObj(Image img, int x, int y, GameFrame gameFrame) {
        super(img, x, y, gameFrame);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
