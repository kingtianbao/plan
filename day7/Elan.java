package myjavademo.day7;

import java.awt.*;

public class Elan {
    Image eImg;
    int ex,ey,speed,score;
    boolean eflag=false;


    public Elan(Image eImg, int ex, int ey, int speed, int score) {
        this.eImg = eImg;
        this.ex = ex;
        this.ey = ey;
        this.speed = speed;
        this.score = score;
    }

    public void drawElan(Graphics g){
    g.drawImage(eImg,ex,ey,null);
    }
    public void moveElan(){
        ey+=speed;
        if(ey>=600)
            eflag=true;
    }
}
