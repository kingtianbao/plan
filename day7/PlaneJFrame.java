package myjavademo.day7;

import javax.swing.*;
import java.awt.*;

public class PlaneJFrame extends JFrame {
    int width=400,height=600;
    public PlaneJFrame(){
        this.setTitle("飞机大战");
        int pw=Toolkit.getDefaultToolkit().getScreenSize().width;
        int ph=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((pw-width)/2,(ph-height)/2,width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        PlaneJPanel pjp=new PlaneJPanel();
        this.add(pjp);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new PlaneJFrame();
    }

}
