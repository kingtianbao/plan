package myjavademo.day7;

/**
 * 炮弹和敌机碰撞判断类
 */
public class BulletAndElan {
    //定义一个带返回值方法，用于判断是不碰撞
    public boolean bulletAndElan(Bullet b1,Elan e1){
        boolean beflag=false;
        //分别取炮弹的坐标
        int b1x=b1.bx;
        int b1y=b1.by;
        int b2x=b1.bx+b1.bImg.getWidth(null);
        int b3y=b1.by+b1.bImg.getHeight(null);
        //分别取敌机的坐标
        int e1x=e1.ex;
        int e1y=e1.ey;
        int e2x=e1.ex+e1.eImg.getWidth(null);
        int e3y=e1.ey+e1.eImg.getHeight(null);
        if (b1x>=e1x&&b1y>=e1y&&b2x<=e2x&&b3y<=e3y){
            //判断已经碰撞,改变beflag的状态
            beflag=true;
        }
        return beflag;
    }
}
