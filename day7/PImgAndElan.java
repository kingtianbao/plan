package myjavademo.day7;

public class PImgAndElan {
    public boolean pImagAndElan(pImg p1,Elan e1){
        boolean peflag = false;
        int p1x = p1.px;
        int p1y = p1.py;
        int p2x = p1.px + p1.bImg.getWidth(null);
        int p3y = p1.py + p1.bImg.getHeight(null);
        //分别取敌机的坐标
        int e1x=e1.ex;
        int e1y=e1.ey;
        int e2x=e1.ex+e1.eImg.getWidth(null);
        int e3y=e1.ey+e1.eImg.getHeight(null);
        if (p1x>=e1x&&p1y>=e1y&&p2x<=e2x&&p3y<=e3y){
            //判断已经碰撞,改变beflag的状态
            peflag=true;
        }
        return peflag;
    }
}
