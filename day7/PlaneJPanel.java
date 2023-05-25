package myjavademo.day7;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaneJPanel extends JPanel implements Runnable, MouseMotionListener {
    //定义背景图变量
    Image bg;
    //定义背景图的坐标
    int x=0,y=-5400;
    //定义多线程对象
    Thread t;
    //定义英雄机图片对象
    Image pImg;
    //定义英雄机的坐标
    int px=100,py=500;
    //定义炮弹图片对象
    Image bImg;
    //定义一个炮弹集合
    List<Bullet> bullets=new ArrayList<>();
    //定义一个计数器
    int count=0;
    //定义一个敌机的集合
    List<Elan> elans=new ArrayList<>();
    //定义一个敌机公用的对象
    Elan elan;
    //定义一个得分变量
    int score=0;
    //通过构造方法完成初始化
    public PlaneJPanel(){
        //加载背景图
        try {
            bg= ImageIO.read(new FileInputStream("plane/bk.jpg"));
            pImg=ImageIO.read(new FileInputStream("plane/hero.GIF"));
            bImg=ImageIO.read(new FileInputStream("plane/bullet.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //实例化多线程对象并起动
        t=new Thread(this);
        t.start();
        //给鼠标添加监听
        addMouseMotionListener(this);
    }
    //添加paint方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画背景图
        g.drawImage(bg,x,y,null);
        //画英雄机
        g.drawImage(pImg,px,py,null);
        //画炮弹
        for (int i=0;i<bullets.size();i++){
            //拆箱取出每个炮弹
            Bullet bullet=bullets.get(i);
            //调用这个炮弹画的方法
            bullet.drawBullet(g);
        }
//        //打一个点:看一下炮弹出了上边界是否删了
//        System.out.println(bullets.size());
        //画敌机
        for (Elan elan:elans){
            elan.drawElan(g);
        }
        //设置一个得分颜色
        g.setColor(Color.red);
        g.drawString("得分:"+score,350,50);
    }

    @Override
    public void run() {
        while (true){
            //1更改坐标
            y++;
            if (y>=0){
                y=-5400;
            }
            //炮弹的移动
            for (int i=0;i<bullets.size();i++){
                //拆箱取出每个炮弹
                Bullet bullet=bullets.get(i);
                //调用这个炮弹移动方法
                bullet.moveBullet();
                if (bullet.flag){//在炮弹移动时，判断是否出了上边界
                    bullets.remove(i);
                    break;
                }
            }
            //敌机的移动
            for (int i=0;i<elans.size();i++){
                elans.get(i).moveElan();
                if (elans.get(i).eflag){
                    elans.remove(i);
                    break;
                }
            }
            //让计数器进行加1运算
            count++;
            //创建炮弹的对象
            if (count%20==0){
                Bullet bullet=new Bullet(bImg,px+pImg.getWidth(null)/2-bImg.getWidth(null)/2,py);
                //将炮弹对象加入炮弹集合中
                bullets.add(bullet);
            }
            //判断count不能超过最大范围
            if (count>=100){
                count=0;
            }
            if (count%30==0){
                int num=(int)(Math.random()*5+2);
                //创建敌机对象
                elan=new Elan(new ImageIcon("plane/e"+num+".png").getImage(),(int)(Math.random()*550),-50,num,num);
                //将敌机对象加入敌机集合中
                elans.add(elan);
            }
            //通过双重for循环完成遍历
            for (Bullet bullet:bullets){
                for (Elan elan:elans){
                    //创建比对类对象并实例化
                    BulletAndElan bae=new BulletAndElan();
                    //寂静义一个boolean变量用于接收碰撞判断后取得返回值
                    boolean flag;
                    //调用比对方法
                    flag=bae.bulletAndElan(bullet,elan);
                    //进行判断方法调用后返回的值
                    if (flag){
                        //取得分
                        score+=elan.score;
                        //将敌机和炮弹的状态进行更改
                        elan.eflag=true;
                        bullet.flag=true;
                    }
                }
            }
            //2重绘
            repaint();
            //3休眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //获取鼠标的坐标值给英雄机px和py进行赋值
        px=e.getX()-pImg.getWidth(null)/2;
        py=e.getY()-pImg.getHeight(null)/2;
    }
}