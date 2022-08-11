package edu.ygc.qf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 创建一个对象，作为地面
 */
public class Ground {
    public  BufferedImage img;
    public  int x;
    public  int y;

    public Ground(){
        try {
            x=0;
            y=500;
            img= ImageIO.read(getClass().getResource("ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        x--;
        if(x==-100){
            x=0;
        }
    }

}
