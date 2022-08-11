package edu.ygc.qf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Struct;

public class Bird {
    //声明当前小鸟的图片
    public BufferedImage img;
    //声明小鸟的所有的图片
    public BufferedImage[] images=new BufferedImage[8];
    public int x,y;
    //定义当前小鸟图片的下标
    private int index=0;
    private int speed=10;
    private float g=6.8f;
    private float t=0.4f;

    public Bird() throws IOException {
        for(int i=0;i<8;i++){
            images[i]=ImageIO.read(getClass().getResource(i+".png"));
        }
        //设置默认图片为第一张
        img=images[0];
        x=100;
        y=220;
    }

    public void fly(){
        index++;
        //通过取余数保证下标一直是0-7
        img=images[index%8];
    }

    public void down(){
        int s=(int)(speed*t+g*t*t/2);
        y=y+s;
        speed=(int)(speed+g*t);
    }

    public void up(){
        speed=speed-20;
    }

    /*碰到顶部*/
    public boolean hitSky(){
        if(y<=0){
            //碰到了，返回true
            return true;
        }
        //没有碰到返回false
        return false;

    }
    /*碰到底部*/
    public boolean hitGround(){
        int height=img.getHeight();
        //当小鸟的y值加上小鸟的高度 大于等于500
        if(y+height>=500){
            return true;
        }
        return false;
    }
    /*碰到柱子*/
    public boolean hitColumn(Cloumn c){
        int height=img.getHeight();
        int width=img.getWidth();
        /*水平方向可以有交叉的条件*/
        if(c.x-width<=x && x<=c.x+c.width){
            //竖直方向上坐标有交叉
            if(y<=c.y+c.height/2-72  ||  y>=c.y+c.height/2+72-height){
                return true;
            }
        }
        return  false;
    }


}
