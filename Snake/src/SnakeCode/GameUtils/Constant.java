package SnakeCode.GameUtils;

/**
 * * author:FengQin
 * * Date:2022/10/3 15:18
 * * explain:
 **/
public class Constant {
    //窗口的大小
    public static final int FRAME_WIDTH=800;
    public static final int FRAME_HEIGHT=600;
    //窗口的标题
    public  static final String FRAME_TITLE = "贪吃蛇 || 1.0";
    //游戏难度(线程休眠时间)
    public static long inGameDiff = 250;
    public static long getInGameDiff() {
        return inGameDiff;
    }
    public static void setInGameDiff(long inGameDiff) {
        Constant.inGameDiff = inGameDiff;
    }
}
