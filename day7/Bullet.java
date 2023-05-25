package myjavademo.day7;

import java.awt.*;

public class Bullet {
    Image bImg;
    int bx,by;
    int speed=1;
    boolean flag =false;
    public Bullet(Image bImg,int bx,int by){
        this.bImg=bImg;
        this.bx=bx;
        this.by=by;
    }
    public void drawBullet(Graphics g){
        g.drawImage(bImg,bx,by,null);
    }
    public void moveBullet(){
        by-=speed;
        if (by<=-bImg.getHeight(null)){
            flag=true;
        }
    }
}
