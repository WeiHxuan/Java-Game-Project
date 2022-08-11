package edu.ygc.qf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Cloumn {
    //代表柱子的个数
    private static  int count=0;
    public BufferedImage img;
    public int x,y;
    //柱子的宽度和高度
    public int width,height;
    //两根柱子的间距
    public int distance;

    public Cloumn() throws IOException {
        if(count>1){
            count=0;
        }
        img= ImageIO.read(getClass().getResource("column.png"));
        distance=220;
        //获取柱子的宽和高
        width=img.getWidth();
        height=img.getHeight();
        //设置柱子的x坐标，因为有两个，所以用count控制
        x=500+(distance+width)*count;
        count++;
        Random random=new Random();
        //设置两个极限是出来20，柱子的开口是144
        y=random.nextInt(316)+92-height/2;
        System.out.println(y);
    }

    public void move(){
        x--;
        if(x+width<=0){
            x=x+2*(width+220);
            Random random=new Random();
            //设置两个极限是出来20，柱子的开口是144
            y=random.nextInt(316)+92-height/2;
        }
    }




}
