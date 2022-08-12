package com.cxd.linecargame210127.V2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ThreadClass extends Thread{
	public LineBall lb_blue;
	public LineBall lb_red;
	public Graphics g;
	public volatile Boolean gameRest=false;//用上了volatile，但是它是怎么起作用的？
	public volatile Boolean gameStart=false;
	public String fileAddress="image_lineballgame/";
	
	public int flag1=0;
	public int flag2=0;
	public int flag3=0;
	public int flag4=0;
	
	
	public ThreadClass(){
		
	}
	
	public ThreadClass(Graphics g,LineBall lb_blue,LineBall lb_red){
		this.lb_blue=lb_blue;
		this.lb_red=lb_red;
		this.g=g;
	}
	
	public void run(){
		while(true){
			if(gameStart){
				if(!gameRest){
					draw_just_once(2);
					lb_blue.judge_gameover();
					lb_red.judge_gameover();
					if(!lb_blue.gameOver&!lb_red.gameOver){
						lb_blue.imgName="蓝_4.png";
						lb_blue.drawLB(g);
						lb_blue.move();
						lb_red.imgName="红_4.png";
						lb_red.drawLB(g);
						lb_red.move();
					}else if(lb_blue.gameOver){
//						System.out.println("blue gameover!");
						draw_just_once(3);
						
					}else if(lb_red.gameOver){
//						System.out.println("red gameover!");
						draw_just_once(4);
					}
					
					 try{
						 Thread.sleep(50);
					 }catch(Exception ef){}
				}else{
					//画暂停界面
				}
				
			}else{
				//画开始界面
				draw_just_once(1);
			}
		}
	}
	
	//只画一次图片
	public void draw_just_once(int type){
		if(type==1&flag1==0){
			ImageIcon imgic=new ImageIcon(fileAddress+"游戏背景_2.png");
			Image img=imgic.getImage();
			g.drawImage(img, 0,0,null);

			ImageIcon imgic2=new ImageIcon(fileAddress+"开始游戏.png");
			Image img2=imgic2.getImage();
			g.drawImage(img2, 0,0,null);
			
			flag1++;
		}else if (type==2&flag2==0){
			ImageIcon imgic=new ImageIcon(fileAddress+"游戏背景_2.png");
			Image img=imgic.getImage();
			g.drawImage(img, 0,0,null);
			

			//画边界
			ImageIcon imgic2=new ImageIcon(fileAddress+"墙_2.png");
			Image img2=imgic2.getImage();
			for(int i=0;i<142;i++){
				g.drawImage(img2,45+5*i,65,null);
				g.drawImage(img2,45+5*i,770,null);
				g.drawImage(img2,45,65+5*i,null);
				g.drawImage(img2,750,65+5*i,null);
			}
			
			g.setColor(new Color(200,200,200));
			for(int i=0;i<70;i++){
				g.drawLine(50, 70+10*i, 750, 70+10*i);
				g.drawLine(50+10*i, 70, 50+10*i,770);
			}

//			ImageIcon imgic2=new ImageIcon(fileAddress+"二零墙.png");
//			Image img2=imgic2.getImage();
//			for(int i=0;i<720;i+=20){
//				g.drawImage(img2,45+i,50,null);
//				g.drawImage(img2,45+i,770,null);
//				g.drawImage(img2,30,65+i,null);
//				g.drawImage(img2,750,65+i,null);
//			}
			
			flag2++;
		}else if (type==3&flag3==0){
			ImageIcon imgic=new ImageIcon(fileAddress+"蓝败.png");
			Image img=imgic.getImage();
			g.drawImage(img, 0,0,null);
		}else if (type==4&flag4==0){
			ImageIcon imgic=new ImageIcon(fileAddress+"红败.png");
			Image img=imgic.getImage();
			g.drawImage(img, 0,0,null);
		}
	}
	
	public void on_off(){
		gameRest=!gameRest;
	}
}
