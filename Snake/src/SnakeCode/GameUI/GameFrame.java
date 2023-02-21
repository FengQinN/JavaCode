package SnakeCode.GameUI;

import SnakeCode.GameStart.GameStart;
import SnakeCode.GameUtils.Constant;
import SnakeCode.GameUtils.GameImgUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:17
 * * explain:
 **/
public class GameFrame extends JFrame {
    //蛇头随机
    Random random = new Random();
    //分数
    public  int score = 0;
    //游戏状态 0.未开始 1.游戏中 2.暂停 3.失败 4.通关 5.失败后重启 6.全通关
    public static int state =0;
    //解决闪烁问题
    //定义双缓存图片
    Image offScreenImage =  null;
    //蛇头构建
    HeadObj headObj = new HeadObj(GameImgUtils.rightImg,random.nextInt(20) * 30, (random.nextInt(19)+1) * 30,this);
    //蛇身集合
    public List<BodyObj> bodyObjList = new ArrayList<>();
    //食物对象
    FoodObj foodObj =  new FoodObj().getFood();
    //构造器中初始化一些参数
    public GameFrame(){
        //窗口是否可见
        setVisible(true);
        //窗口的大小
        setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        //窗口的标题
        setTitle(Constant.FRAME_TITLE);
        //窗口的初始化位置
        setLocationRelativeTo(null );
        //窗口的大下不可改变
        setResizable(false);
        //窗口的关闭事件
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //蛇身初始化
        bodyObjList.add(new BodyObj(GameImgUtils.bodyImg,30,570,this));
        bodyObjList.add(new BodyObj(GameImgUtils.bodyImg,0,570,this));
        //图标设置
        this.setIconImage(new ImageIcon("images/aili.jpg").getImage());
        //添加游戏状态监听事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    switch (state){
                        case 0:
                        case 2:
                            //暂停
                            //未开始
                            state = 1;
                            break;
                        case 1:
                            //游戏中
                            state = 2;
                            repaint();
                            break;
                        case 3:
                            //失败
                            state = 5;
                            break;
                        case 4:
                            state = 0;

                        default:
                            break;
                    }
                }
            }
        });
        //蛇头移动
        moveHead();
    }
    //蛇头移动
    public void moveHead(){
        //蛇头移动
        while (true) {
            if (state == 1){
                //游戏中才被调用
                repaint();
            }
            //失败后重启
            if(state ==5 ){
                state = 0;
                resetGame();
            }
            //通关
            if(state == 4 ){
                state = 0;
                resetGame();
            }
            try {
                Thread.sleep(Constant.getInGameDiff());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //游戏重置方法
    void resetGame(){
        //关闭当前窗口
        this.dispose();
        //开启一个新窗口
        String[] args = {};
        GameStart.main(args);
    }
    //绘制开始提示语方法
    void prompt(Graphics g){
        //未开始
        if (state == 0){
            g.fillRect(195,240,270,70);
            GameImgUtils.drawWord(g,"按下空格开始",Color.LIGHT_GRAY,35,220,290);
        }
        //暂停
        if (state == 2){
            g.fillRect(195,240,270,70);
            GameImgUtils.drawWord(g,"游戏已暂停",Color.lightGray,35,240,290);
        }
        //暂停
        if (state == 3){
            g.fillRect(195,240,330,70);
            GameImgUtils.drawWord(g,"失败！按下空格重来",Color.lightGray,35,200,290);
        }
        //通关
        if (state == 4){
            g.fillRect(195,240,330,70);
            GameImgUtils.drawWord(g,"游戏已通关,继续游戏！",Color.ORANGE,25,210,280);
        }
        //通关
        if (state == 6){
            g.fillRect(195,240,270,70);
            GameImgUtils.drawWord(g,"游戏已通关！",Color.ORANGE,35,240,290);
        }
    }

    //网格绘制，重写paint()方法
    @Override
    public void paint(Graphics g) {
        //初始化双缓存图片
        if (offScreenImage == null){
            offScreenImage = this.createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        }
        //获取图片对应的Graphics对象
        Graphics gImage  = offScreenImage.getGraphics();
        //设置背景色,填充
        gImage.setColor(Color.GRAY);
        gImage.fillRect(0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        //绘制网格线
        gImage.setColor(Color.black);
        //图线绘制-行
        //图线绘制-列
        for (int i = 0; i <= 20; i++) {
            //行
            gImage.drawLine(0,i * 30,600,i *30);
            //列
            gImage.drawLine(i *30,0,i * 30,600);
        }
        //蛇身绘制
        for (int i = bodyObjList.size() - 1; i >= 0; i--) {
            bodyObjList.get(i).render(gImage);
        }
        //蛇头绘制
        headObj.render(gImage);
        //食物绘制
        foodObj.render(gImage);
        //分数绘制
        GameImgUtils.drawWord(gImage,"分数:" + score,Color.GREEN,30,650,300);
        //绘制提示语
        gImage.setColor(Color.DARK_GRAY);
        prompt(gImage);
        //将双缓存图片绘制到主窗口s
        g.drawImage(offScreenImage,0,0,null);
    }

}
