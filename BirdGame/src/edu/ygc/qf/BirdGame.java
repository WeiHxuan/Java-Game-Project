package edu.ygc.qf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * 游戏主体部分
 */
public class BirdGame extends JPanel{
    //当前游戏状态
    private static int state=0;
    //三个游戏状态常量
    private final int READY=1;
    private final int RUNNING=2;
    private final int GAMEOVER=3;
    //声明游戏背景,准备界面
    private BufferedImage bg,start,over;
    //声明一个地面对象
    private Ground ground;
    private Bird bird;
    private Cloumn c1,c2;
    //记录分数
    private int score=0;


    public BirdGame(){
        try {
            //实例化地面对象
            ground=new Ground();
            bird=new Bird();
            c1=new Cloumn();
            c2=new Cloumn();
            //初始化游戏状态为准备状态
            state= READY;
            //根据图片名称找到图片，并读取
            bg= ImageIO.read(getClass().getResource("bg.png"));
            start=ImageIO.read(getClass().getResource("start.png"));
            over=ImageIO.read(getClass().getResource("gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        //绘制背景图片
        g.drawImage(bg,0,0,null);
        switch (state){
            case READY:
                score=0;
                g.drawImage(bird.img,bird.x,bird.y,null);
                g.drawImage(start,0,0,null);
                break;
            case RUNNING:
                g.drawImage(bird.img,bird.x,bird.y,null);
                g.drawImage(c1.img,c1.x,c1.y,null);
                g.drawImage(c2.img,c2.x,c2.y,null);
                break;
            case GAMEOVER:
                g.drawImage(over,0,0,null);
                break;
        }
        //设置一个字体
        Font font=new Font(Font.SANS_SERIF,Font.BOLD,40);
        g.setFont(font);
        g.setColor(Color.WHITE);
        //绘制文字
        g.drawString(score+"",60,40);
        //绘制地面
        g.drawImage(ground.img,ground.x,ground.y,null);
    }

    /*用来处理所有的动态效果*/
    public void action(){
        //添加鼠标监听
        MyMouseListener listener=new MyMouseListener();
        this.addMouseListener(listener);
        while(true){
            switch (state){
                case READY:
                    ground.move();
                    bird.fly();
                    break;
                case RUNNING:
                    ground.move();
                    bird.fly();
                    bird.down();
                    c1.move();
                    c2.move();
                    //判断如果发生碰撞，那么就改变游戏状态，进行下一个循环
                    if(bird.hitSky() || bird.hitGround() || bird.hitColumn(c1) || bird.hitColumn(c2)){
                        state=GAMEOVER;
                        //把得分存储一下
                        DBUtils dbUtils=DBUtils.getInstance();
                        try {
                            dbUtils.save(score,"王刚");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        continue;
                    }else if(bird.x==c1.x+c1.width || bird.x==c2.x+c2.width){
                        score++;
                    }
                    break;
                case GAMEOVER:
                    break;
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /*添加鼠标监听事件*/
    class MyMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           switch (state){
               case READY:

                   //当游戏结束过一次重新开始的时候，是需要重新设置柱子的
                   try {
                       if(c1==null){
                           c1=new Cloumn();
                       }
                       if(c2==null){
                           c2=new Cloumn();
                       }
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
                   //点击后状态变为RUNNING状态
                   state=RUNNING;
                   break;
               case RUNNING:
                   bird.up();
                   break;
               case GAMEOVER:
                   state=READY;
                   c1=null;
                   c2=null;
                   try {
                       bird=new Bird();
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
                   break;
           }
        }
    }


}
