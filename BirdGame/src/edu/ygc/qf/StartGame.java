package edu.ygc.qf;

import javax.swing.*;
import java.awt.*;

public class StartGame {

    public static void main(String[] args) {
        //创建一个窗体
        JFrame frame=new JFrame();
        //设置大小
        frame.setSize(440,650);
        //设置出现位置：可以设置窗体左上角那个点的坐标
        //frame.setLocation(300,100);
   /*     Point p=new Point(200,100);
        frame.setLocation(p);*/
        //不给任何参照，那么默认显示在屏幕中间
        frame.setLocationRelativeTo(null);
        // 设置关闭窗口，退出应用程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建画布
         BirdGame game=new BirdGame();
         //在窗体上添加画布
        frame.add(game);
        //设置窗体可见
        frame.setVisible(true);
        //调用动作方法
        game.action();

    }
}
