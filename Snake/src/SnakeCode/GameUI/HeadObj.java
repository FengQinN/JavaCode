package SnakeCode.GameUI;

import SnakeCode.GameUtils.Constant;
import SnakeCode.GameUtils.GameImgUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:18
 * * explain:
 **/
public class HeadObj extends GameObj{
    //蛇最后一个身体的坐标
    Integer newX = null;
    Integer newY = null;
    //下一关通关得分
    static int scoreNext = 10;
    //方向 up down left right 默认为右
    private  String direction = "right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public HeadObj(Image img, int x, int y, GameFrame gameFrame) {
        super(img, x, y, gameFrame);
        //键盘监听事件
        this.gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }
    //控制移动方向 W A S D 控制
    public void changeDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                if(getDirection() != "right"){
                    setDirection("left");
                    img = GameImgUtils.leftImg;
                }
                break;
            case KeyEvent.VK_D:
                if(getDirection() != "left"){
                    setDirection("right");
                    img = GameImgUtils.rightImg;
                }
                break;
            case KeyEvent.VK_W:
                if(getDirection() != "down"){
                    setDirection("up");
                    img = GameImgUtils.upImg;
                }
                break;
            case KeyEvent.VK_S:
                if(getDirection() != "up"){
                    setDirection("down");
                    img = GameImgUtils.downImg;
                }
                break;
            default:
                break;
        }
    }
    //蛇头和蛇身的移动
    public void moveBody(){
        //蛇身移动
        List<BodyObj> bodyObjList = this.gameFrame.bodyObjList;
        bodyObjList.get(0).setX(this.getX());
        bodyObjList.get(0).setY(this.getY());
        for (int i = bodyObjList.size() - 1; i >= 1 ; i--) {
            bodyObjList.get(i).setX(bodyObjList.get(i - 1).getX());
            bodyObjList.get(i).setY(bodyObjList.get(i - 1).getY());
            //蛇头与身体的判断
            if (this.getX() == bodyObjList.get(i).getX() && this.getY() == bodyObjList.get(i).getY() && i!=1){
                //游戏失败
                GameFrame.state = 3;
            }
        }

        //蛇头移动
        switch (getDirection()){
            case "up":
                y-=height;
                break;
            case "down":
                y+=height;
                break;
            case "left":
                x-=width;
                break;
            case "right":
                x+=width;
            default:
                break;
        }
    }
    //越界处理
    public void snakeCross(){
        if (x < 0){
            setX(570);
        }else if(x > 570){
            setX(0);
        }else if(y > 570){
            setY(0);
        }else if(y < 30){
            setY(570);
        }
    }
    //蛇吃食物
    public void eatFood(){
        newX = null;
        newY = null;
        FoodObj foodObj = this.gameFrame.foodObj;
        if(this.getX() == foodObj.getX() && this.getY() == foodObj.getY()){
            this.gameFrame.foodObj = foodObj.getFood();
            BodyObj lastBody = this.gameFrame.bodyObjList.get(this.gameFrame.bodyObjList.size() - 1);
            newX = lastBody.getX();
            newY = lastBody.getY();
            //每吃一个食物分数加1
            this.gameFrame.score++;
        }
        nextLevel();
    }
    //通关后下一关
    //通关判断
    public void nextLevel(){
        if (this.gameFrame.score >= scoreNext && Constant.getInGameDiff() > 50){
            GameFrame.state = 4;
            Constant.setInGameDiff(Constant.getInGameDiff() - 50);
            scoreNext+=5;
        }else if(Constant.getInGameDiff() == 50){
            GameFrame.state = 6;
        }
    }
    //身体添加
    public void  addBody(){
        if (newX != null && newY != null) {
            this.gameFrame.bodyObjList.add(new BodyObj(GameImgUtils.bodyImg,newX,newY,this.gameFrame));
        }
    }
    @Override
    public void render(Graphics g) {
        super.render(g);
        //蛇吃食物
        this.eatFood();
        //移动
        this.moveBody();
        //身体添加
        addBody();
        //越界处理
        this.snakeCross();
    }
}
