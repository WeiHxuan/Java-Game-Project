package com.cxd.catchballgame210126.V3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;

public class ThreadClass extends Thread{
	public ArrayList<FlyObject> fos;
	public ArrayList<FlyObject> mps;
	public ArrayList<FlyObject> explotions=new ArrayList<FlyObject>();
	public Graphics g;
	public Graphics bufg;
	public int len;
	public int score;
	public int rewardTime;
	public Boolean pause=false;
	public int pauseTime;
	public FlyObject mp_pause;
	public Boolean gameRest=false;
	public int life=3;
	public Boolean gameOver=false;
	public static String fileAddress="image_catchballgame/";
	HashMap<Integer,String> hm=new HashMap<Integer,String>();
	
	public String restart_game="重新开始_1.png";
	
	
	public ThreadClass(){
		initialize();
	}
	
	public ThreadClass(Graphics g,ArrayList<FlyObject> fos,ArrayList<FlyObject> mps){
		this.fos=fos;
		this.g=g;
		this.mps=mps;
		initialize();
	}
	
	//初始化参数
	public void initialize(){
		hm.put(0,fileAddress+"0.png");
		hm.put(1,fileAddress+"1.png");
		hm.put(2,fileAddress+"2.png");
		hm.put(3,fileAddress+"3.png");
		hm.put(4,fileAddress+"4.png");
		hm.put(5,fileAddress+"5.png");
		hm.put(6,fileAddress+"6.png");
		hm.put(7,fileAddress+"7.png");
		hm.put(8,fileAddress+"8.png");
		hm.put(9,fileAddress+"9.png");
	}
	
	//游戏暂停/开始
	public void on_off(){
		gameRest=!gameRest;
	}
	
	public void run(){
		while(true){
			if(!gameOver){
				if(gameRest==false){
					//创建缓冲区
					BufferedImage bufimg=new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
					bufg=bufimg.getGraphics();
					
					ImageIcon imgico=new ImageIcon(fileAddress+"背景.png");
					Image img=imgico.getImage();
					bufg.drawImage(img,0,0,null);
					
					len++;
					
					generateDrop();
					
					judgeCatchOrNot();
					
					draw_mp_fo();
					
					drawExplo(bufg);
					
					refreshScore(bufg);
					
					draw_on_off(bufg);
	
					g.drawImage(bufimg, 0,0,null);
					
					clear();
				}else{
					draw_on_off(g);
				}
				try{
					Thread.sleep(30);
				}catch(Exception ef){}
			}else{
				ImageIcon imgicon_gamover=new ImageIcon(fileAddress+"gameover.png");
				Image img_gamover=imgicon_gamover.getImage();
				g.drawImage(img_gamover,0,-40,null);
				
				try{
					Thread.sleep(1000);
				}catch(Exception ef){}
				
				ImageIcon imgicon_restartgame=new ImageIcon(fileAddress+restart_game);
				Image img_restartgame=imgicon_restartgame.getImage();
				g.drawImage(img_restartgame,340,630,null);
			}
		}
	}
	
	//清理列表
	public void clearList(ArrayList<FlyObject> fos,int flag){
		int fos_size=fos.size();
		if(flag==0){
			for(int i=fos.size()-10;i>-1;i--){
				if(fos.get(i).location.y>1000){
					for(int j=0;j<i;j++){
						fos.remove(0);
					}
					break;
				}
			}
		}else if(flag==1){
			for(int i=0;i<fos_size-100;i++){
				fos.remove(0);
			}
		}
	}
	
	//清理缓存？
	public void clear(){
		if(len%500==0){
			System.out.println("清理前：");
			System.out.println("fos size is"+fos.size());
			System.out.println("mps size is"+mps.size());
			System.out.println("exps size is"+explotions.size());
			clearList(fos,0);
			clearList(mps,1);
			clearList(explotions,0);
			System.out.println("清理后：");
			System.out.println("fos size is"+fos.size());
			System.out.println("mps size is"+mps.size());
			System.out.println("exps size is"+explotions.size());
		}
	}
	
	//生成下落物
	public void generateDrop(){
		if(rewardTime>0){
			if(len%1==0){
				rewardTime--;
				Random ran=new Random();
				String soyType;
				if(ran.nextInt(2)==0){
					soyType="金币1.png";
				}else{
					soyType="金币1.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,soyType);
				fos.add(fo);
			}
		}else{
			if(len%10==0){
				Random ran=new Random();
				String soyType;
				if(ran.nextInt(2)==0){
					soyType="红豆.png";
				}else{
					soyType="蓝豆.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,1);
				
				FlyObject fo=new FlyObject(location,speed,acce,soyType);
				fos.add(fo);
			}
			if(len%35==0){
				Random ran=new Random();
				String presentType;
				if(ran.nextInt(2)==0){
					presentType="绿宝石.png";
				}else{
					presentType="绿宝石.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,presentType);
				fos.add(fo);
			}
			if(len%350==0){
				Random ran=new Random();
				String presentType;
				if(ran.nextInt(2)==0){
					presentType="礼物.png";
				}else{
					presentType="礼物.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,presentType);
				fos.add(fo);
			}
			if(len%53==0){
				Random ran=new Random();
				String presentType;
				if(ran.nextInt(2)==0){
					presentType="蜘蛛.png";
				}else{
					presentType="蜘蛛.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,presentType);
				fos.add(fo);
			}
			if(len%76==0){
				Random ran=new Random();
				String presentType;
				if(ran.nextInt(2)==0){
					presentType="炸弹.png";
				}else{
					presentType="炸弹.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,presentType);
				fos.add(fo);
			}
			if(len%400==0){
				Random ran=new Random();
				String presentType;
				if(ran.nextInt(2)==0){
					presentType="生命值.png";
				}else{
					presentType="生命值.png";
				}
				Vector location=new Vector(ran.nextInt(750)+50,50);
				Vector speed=new Vector(0,ran.nextInt(1)+10);
				Vector acce=new Vector(0,2);
				
				FlyObject fo=new FlyObject(location,speed,acce,presentType);
				fos.add(fo);
			}
		}
	}
	
	//绘制我机、掉落物
	public void draw_mp_fo(){
		for(int i=0;i<fos.size();i++){
			FlyObject fo=fos.get(i);
			fo.drawFO(bufg);
			fo.move();
		}
		if(pause==false){
			if(mps.size()-5>=0){
				FlyObject mp=mps.get(mps.size()-5);
				mp.drawFO(bufg);
			}
		}else{
			pauseTime--;
			mp_pause.drawFO(bufg);
			if(pauseTime==0){
				pause=false;
			}
		}
	}
	
	//判断是否接到小球
	public void judgeCatchOrNot(){
		if(pause==false){
			for(int i=0;i<fos.size();i++){
				//这里mps.get(mps.size()-数字)的数字尽量改大一些，否则容易出现空指针
				if(mps.size()-10>0){
					int mp_x=mps.get(mps.size()-10).location.x;
					int mp_y=mps.get(mps.size()-10).location.y;
					int fo_x=fos.get(i).location.x;
					int fo_y=fos.get(i).location.y;
					
					if(Math.abs(mp_x-fo_x+20)<60&Math.abs(mp_y-fo_y)<50){
						if(fos.get(i).imgName.equals(fileAddress+"蓝豆.png")){
							score+=10;
						}else if(fos.get(i).imgName.equals(fileAddress+"红豆.png")){
							score+=5;
						}else if(fos.get(i).imgName.equals(fileAddress+"绿宝石.png")){
							score+=20;
						}else if(fos.get(i).imgName.equals(fileAddress+"礼物.png")){
							rewardTime+=200;
						}else if(fos.get(i).imgName.equals(fileAddress+"金币1.png")){
							score+=10;
						}else if(fos.get(i).imgName.equals(fileAddress+"蜘蛛.png")){
							pause=true;
							pauseTime+=100;
							mp_pause=mps.get(mps.size()-6);
							mp_pause.imgName=fileAddress+"接豆人晕.png";
						}else if(fos.get(i).imgName.equals(fileAddress+"炸弹.png")){
							life-=1;
							explotion(fos.get(i));
						}else if(fos.get(i).imgName.equals(fileAddress+"生命值.png")){
							life+=1;
						}
						mps.get(mps.size()-5).imgName=fileAddress+"接豆人合.png";
						fos.remove(i);
					}
				}
			}
		}
	}
	
	//刷新分数、道具点数、生命值
	public void refreshScore(Graphics bufg_score){
		//刷新分数
		int number_5=score/10000;
		int number_4=(score-number_5*10000)/1000;
		int number_3=(score-number_5*10000-number_4*1000)/100;
		int number_2=(score-number_5*10000-number_4*1000-number_3*100)/10;
		int number_1=score-number_5*10000-number_4*1000-number_3*100-number_2*10;
		
		ImageIcon imgicon_score=new ImageIcon(fileAddress+"Score.png");
		Image img_score=imgicon_score.getImage();
		ImageIcon imgicon5=new ImageIcon(hm.get(number_5));
		Image img5=imgicon5.getImage();
		ImageIcon imgicon4=new ImageIcon(hm.get(number_4));
		Image img4=imgicon4.getImage();
		ImageIcon imgicon3=new ImageIcon(hm.get(number_3));
		Image img3=imgicon3.getImage();
		ImageIcon imgicon2=new ImageIcon(hm.get(number_2));
		Image img2=imgicon2.getImage();
		ImageIcon imgicon1=new ImageIcon(hm.get(number_1));
		Image img1=imgicon1.getImage();
		
		bufg_score.drawImage(img_score, 340,50,null);
		bufg_score.drawImage(img5, 590,50,null);
		bufg_score.drawImage(img4, 650,50,null);
		bufg_score.drawImage(img3, 710,50,null);
		bufg_score.drawImage(img2, 770,50,null);
		bufg_score.drawImage(img1, 830,50,null);

		//刷新道具点数(眩晕时间或金币时间)
		if(rewardTime>0){
			int number_prop_3=rewardTime/100;
			int number_prop_2=(rewardTime-number_prop_3*100)/10;
			int number_prop_1=rewardTime-number_prop_3*100-number_prop_2*10;
			
			ImageIcon imgicon_prop_3=new ImageIcon(hm.get(number_prop_3));
			Image img_prop_3=imgicon_prop_3.getImage();
			ImageIcon imgicon_prop_2=new ImageIcon(hm.get(number_prop_2));
			Image img_prop_2=imgicon_prop_2.getImage();
			ImageIcon imgicon_prop_1=new ImageIcon(hm.get(number_prop_1));
			Image img_prop_1=imgicon_prop_1.getImage();
			bufg_score.drawImage(img_prop_3, 50,150,null);
			bufg_score.drawImage(img_prop_2, 110,150,null);
			bufg_score.drawImage(img_prop_1, 170,150,null);

			ImageIcon imgicon_rewardTime=new ImageIcon(fileAddress+"金币时间.png");
			Image img_rewardTime=imgicon_rewardTime.getImage();
			bufg_score.drawImage(img_rewardTime, 50,50,null);
		}else if(pauseTime>0){
			int number_prop_3=pauseTime/100;
			int number_prop_2=(pauseTime-number_prop_3*100)/10;
			int number_prop_1=pauseTime-number_prop_3*100-number_prop_2*10;
			
			ImageIcon imgicon_prop_3=new ImageIcon(hm.get(number_prop_3));
			Image img_prop_3=imgicon_prop_3.getImage();
			ImageIcon imgicon_prop_2=new ImageIcon(hm.get(number_prop_2));
			Image img_prop_2=imgicon_prop_2.getImage();
			ImageIcon imgicon_prop_1=new ImageIcon(hm.get(number_prop_1));
			Image img_prop_1=imgicon_prop_1.getImage();
			bufg_score.drawImage(img_prop_3, 50,150,null);
			bufg_score.drawImage(img_prop_2, 110,150,null);
			bufg_score.drawImage(img_prop_1, 170,150,null);
			
			ImageIcon imgicon_pauseTime=new ImageIcon(fileAddress+"眩晕时间.png");
			Image img_pauseTime=imgicon_pauseTime.getImage();
			bufg_score.drawImage(img_pauseTime, 50,50,null);
		}
		
		
		ImageIcon imgicon_life=new ImageIcon(fileAddress+"生命值.png");
		Image img_life=imgicon_life.getImage();
		if(life>5){
			life=5;
			bufg_score.drawImage(img_life, 580,830,null);
			bufg_score.drawImage(img_life, 640,830,null);
			bufg_score.drawImage(img_life, 700,830,null);
			bufg_score.drawImage(img_life, 760,830,null);
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==5){
			bufg_score.drawImage(img_life, 580,830,null);
			bufg_score.drawImage(img_life, 640,830,null);
			bufg_score.drawImage(img_life, 700,830,null);
			bufg_score.drawImage(img_life, 760,830,null);
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==4){
			bufg_score.drawImage(img_life, 640,830,null);
			bufg_score.drawImage(img_life, 700,830,null);
			bufg_score.drawImage(img_life, 760,830,null);
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==3){
			bufg_score.drawImage(img_life, 700,830,null);
			bufg_score.drawImage(img_life, 760,830,null);
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==2){
			bufg_score.drawImage(img_life, 760,830,null);
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==1){
			bufg_score.drawImage(img_life, 820,830,null);
		}else if(life==0){
			gameOver=true;
		}
	}
	
	//给开关加图片
	public void draw_on_off(Graphics g_on_off){
		if(gameRest==false){
			ImageIcon imgic=new ImageIcon(fileAddress+"off.png"); 
			Image img=imgic.getImage();
			g_on_off.drawImage(img, 30,760,null);
		}else{
			ImageIcon imgic=new ImageIcon(fileAddress+"on.png"); 
			Image img=imgic.getImage();
			g_on_off.drawImage(img, 30,760,null);

			ImageIcon imgic2=new ImageIcon(fileAddress+"暂停中.png"); 
			Image img2=imgic2.getImage();
			g_on_off.drawImage(img2,0,30,null);
		}
	}
	

	//绘制爆炸动效
	public void drawExplo(Graphics bufg_explotion){
		for(int i=0;i<explotions.size();i++){
			explotions.get(i).drawFO(bufg_explotion);
			explotions.get(i).HP--;
			
			explotions.get(i).imgName=fileAddress+"爆炸_"+((explotions.get(i).HP%3)+1)+".png";
			
			if(explotions.get(i).HP==0){
				explotions.remove(i);
			}
		}
	}
	

	//爆炸动效
	public void explotion(FlyObject flo){
			int x_explo=flo.location.x;
			int y_explo=flo.location.y;
			Vector location=new Vector(x_explo,y_explo);
			FlyObject explo=new FlyObject(location,null,null,"爆炸_1.png",10);
			explotions.add(explo);
	}
	
}
