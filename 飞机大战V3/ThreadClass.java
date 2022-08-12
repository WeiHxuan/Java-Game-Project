package com.cxd.threadGame210124.V3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;

public class ThreadClass extends Thread{
	private ArrayList<FlyObject> fos=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> mps=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> enemys=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> explotions=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> props=new ArrayList<FlyObject>();
	public Graphics g;
	private int len;
	private int score;
	private Boolean gameOver=false;
	private int pickProp;
	public Boolean gameRest=false;
	public static String fileAddress="image_threadgame/";//定义图片存放地址
	
	HashMap<Integer,String> hm=new HashMap<Integer,String>();
	
	public ThreadClass(Graphics g,ArrayList<FlyObject> fos){
		this.g=g;
		this.fos=fos;
		
		//初始化参数
		initialize();
	}
	
	public ThreadClass(Graphics g,ArrayList<FlyObject> fos,ArrayList<FlyObject> mps){
		this.g=g;
		this.fos=fos;
		this.mps=mps;
		
		//初始化参数
		initialize();
	}
	
	//游戏暂停/开始
	public void on_off(){
		gameRest=!gameRest;
	}
	
	public void run(){
		while(true){
			if(gameRest==false){
				System.out.println("len is "+len);
				//判断游戏是否结束
				judgeGameOver(g);
				if(gameOver==true){
					break;
				}
				//创建缓存
				BufferedImage bufImg=new BufferedImage(1200,1200,BufferedImage.TYPE_INT_ARGB);
				//获取缓存上的画布
				Graphics bufg=bufImg.getGraphics();
				
				//刷新背景
				FlyObject background=new FlyObject(new Vector(0,0),null,null,"背景.png");
				background.drawFO(bufg);
				
	
				len++;//计数器（用于刷新怪物和宝箱）
				//刷新怪物
				generateEnemy();
				
				//子弹、怪物运动
				for(int i=0;i<fos.size();i++){
					FlyObject fo=fos.get(i);
					fo.drawFO(bufg);
					fo.move();
				}
				for(int i=0;i<enemys.size();i++){
					FlyObject en=enemys.get(i);
					en.drawFO(bufg);
					en.move();
				}
				//不断发射子弹
				generateBullet();
				
				//判断是否击中目标
				judgeAttack(bufg);
				
				//绘制爆炸效果
				drawExplo(bufg);
				
				//生成宝箱
				generateProp();
				//绘制宝箱
				drawProp(bufg);
				
				//我机运动
				if(mps.size()-1>0){
					mps.get(mps.size()-1).drawFO(bufg);
				}
	
				//刷新分数
				refreshScore(bufg);
				
				//画暂停键
				draw_on_off(bufg);
				
//				System.out.println("画完一幅图");
				g.drawImage(bufImg,0,0,null);
			}else{
				//画暂停键
				draw_on_off(g);
			}
				
			//系统暂停运行一段时间
			try{
				Thread.sleep(30);
			}catch(Exception ef){}
			
		}
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
	
	//给开关加图片
	public void draw_on_off(Graphics g_on_off){
		if(gameRest==false){
			ImageIcon imgic=new ImageIcon(fileAddress+"off.png"); 
			Image img=imgic.getImage();
			g_on_off.drawImage(img, 40,910,null);
		}else{
			ImageIcon imgic=new ImageIcon(fileAddress+"on.png"); 
			Image img=imgic.getImage();
			g_on_off.drawImage(img, 40,910,null);
		}
	}
	
	//不断发射子弹
	public void generateBullet(){
		if(pickProp==0){
			if(len%5==0){
		    	for(int i=0;i<4;i++){
		    		Vector location_fo=new Vector(mps.get(mps.size()-1).location.x,mps.get(mps.size()-1).location.y+20*i);
		        	Vector speed_fo=new Vector(100,0);
		        	Vector acce_fo=new Vector(0,0);
		        	FlyObject fo=new FlyObject(location_fo,speed_fo,acce_fo,"子弹.png",1);
		        	fos.add(fo);
		    	}
			}
		}else if(pickProp>0){
        	pickProp--;
			if(len%5==0){
		    	for(int i=0;i<8;i++){
		    		Vector location_fo=new Vector(mps.get(mps.size()-1).location.x,mps.get(mps.size()-1).location.y+40*i-150);
		        	Vector speed_fo=new Vector(100,0);
		        	Vector acce_fo=new Vector(0,0);
		        	FlyObject fo=new FlyObject(location_fo,speed_fo,acce_fo,"子弹2.png",5);
		        	fos.add(fo);
		    	}
			}
		}
	}
	
	//生成怪物
	public void generateEnemy(){
		//隔一段时间刷新出一个怪物
		if(len%20==0){
			Random ran=new Random();
			int loc_y=ran.nextInt(900)+100;
			int spd_x=-ran.nextInt(10)-10;
			Vector location=new Vector(1200,loc_y);
			Vector speed=new Vector(spd_x,0);
			Vector acce=new Vector(0,0);
			FlyObject enemy=new FlyObject(location,speed,acce,"怪物.png",5);
			enemys.add(enemy);
		}
		if(len%80==0){
			Random ran=new Random();
			int loc_y=ran.nextInt(900)+100;
			int spd_x=-ran.nextInt(5)-5;
			Vector location=new Vector(1200,loc_y);
			Vector speed=new Vector(spd_x,0);
			Vector acce=new Vector(0,0);
			FlyObject enemy=new FlyObject(location,speed,acce,"怪物2.png",20);
			enemys.add(enemy);
		}
	}
	
	//生成宝箱
	public void generateProp(){
		if(len%300==0){
			Random ran=new Random();
			int loc_x=ran.nextInt(900)+100;
			int loc_y=ran.nextInt(900)+100;
			Vector location=new Vector(loc_x,loc_y);
			Vector speed=new Vector(0,0);
			Vector acce=new Vector(0,0);
			FlyObject prop=new FlyObject(location,speed,acce,"宝箱.png",100);
			props.add(prop);
		}
	}
	
	//绘制宝箱
	public void drawProp(Graphics bfug_prop){
		for(int i=0;i<props.size();i++){
			props.get(i).drawFO(bfug_prop);
			props.get(i).HP--;
			
			if(props.get(i).imgName.equals("宝箱.png")){

				ImageIcon imgicon=new ImageIcon("宝箱.png");
				props.get(i).img=imgicon.getImage();
			}
			
			if(props.get(i).HP==0){
				props.remove(i);
			}
		}
	}
	
	//判断游戏是否结束
	public void judgeGameOver(Graphics g_judgeGameOver){
		for(int i=0;i<enemys.size();i++){
			FlyObject en=enemys.get(i);
			
			FlyObject mp=mps.get(mps.size()-1);
			
			//暂时不知道这一段为什么偶尔会出错
			try{
				int mp_x=mp.location.x;
				int mp_y=mp.location.y;
				int en_x=en.location.x;
				int en_y=en.location.y;
			}catch(Exception NullPointer){
				break;
			}
			
			int mp_x=mp.location.x;
			int mp_y=mp.location.y;
			int en_x=en.location.x;
			int en_y=en.location.y;
			
			int distance_mp_en=(int)Math.sqrt(Math.pow((mp_x-en_x),2)+Math.pow((mp_y-en_y),2));
			if(distance_mp_en<=60){
				ImageIcon imgicon_gamover=new ImageIcon(fileAddress+"gameover.png");
				Image img_gamover=imgicon_gamover.getImage();
				g_judgeGameOver.drawImage(img_gamover,0,0,null);
				gameOver=true;
			}
		}
	}
	
	//判断子弹是否击中怪物、怪物是否触碰我机、是否拾得宝箱
	public void judgeAttack(Graphics bufg_judgeAttack){
		//判断子弹是否击中怪物、怪物是否触碰我机
		for(int i=0;i<enemys.size();i++){
			FlyObject en=enemys.get(i);
			for(int j=0;j<fos.size();j++){
				FlyObject fo=fos.get(j);
				int fo_x=fo.location.x;
				int fo_y=fo.location.y;
				int en_x2=en.location.x;
				int en_y2=en.location.y;
				
				int distance_fo_en=(int)Math.sqrt(Math.pow((fo_x-en_x2),2)+Math.pow((fo_y-en_y2),2));
				if(distance_fo_en<=50){
					//如果两颗子弹同时打中一个怪物，那就销毁两个子弹，但后面那一个不计入分数
					try{
						en.HP-=fo.HP;
						explosion(fos.get(j));
						fos.remove(j);
						if(en.HP<=0){
							//这里可以加一个爆炸效果↓
							explosion(enemys.get(i));
							//这里如果直接用enemys.remove(i)会导致循坏for(int j=0;j<fos.size();j++)继续执行，误删其他元素
							enemys.get(i).img=null;
							//把怪物图片去除（每次画图就不画该怪物了），然后把它移出屏幕
							enemys.get(i).location=new Vector(-1000,0);
							if(en.imgName.equals(fileAddress+"怪物.png")){
								score+=10;
							}else if(en.imgName.equals(fileAddress+"怪物2.png")){
								score+=50;
							}
						}
					}catch(Exception IndexOutOfBounds){
						fos.remove(j);
					}
				}
				
			}
		}
		
		//判断是否拾得宝箱
		for(int i=0;i<props.size();i++){
			FlyObject en=props.get(i);
			FlyObject mp=mps.get(mps.size()-1);
			int mp_x=mp.location.x;
			int mp_y=mp.location.y;
			int pp_x=en.location.x;
			int pp_y=en.location.y;
			
			if(Math.abs(mp_x-pp_x)<=60&Math.abs(mp_y-pp_y)<=60){
				props.remove(i);
				pickProp+=100;
			}
		}
	}
	
	//刷新分数、道具点数
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
		
		bufg_score.drawImage(img_score, 600,50,null);
		bufg_score.drawImage(img5, 840,50,null);
		bufg_score.drawImage(img4, 900,50,null);
		bufg_score.drawImage(img3, 960,50,null);
		bufg_score.drawImage(img2, 1020,50,null);
		bufg_score.drawImage(img1, 1080,50,null);
		
		//刷新道具点数
		int number_prop_3=pickProp/100;
		int number_prop_2=(pickProp-number_prop_3*100)/10;
		int number_prop_1=pickProp-number_prop_3*100-number_prop_2*10;
		
		ImageIcon imgicon_prop_3=new ImageIcon(hm.get(number_prop_3));
		Image img_prop_3=imgicon_prop_3.getImage();
		ImageIcon imgicon_prop_2=new ImageIcon(hm.get(number_prop_2));
		Image img_prop_2=imgicon_prop_2.getImage();
		ImageIcon imgicon_prop_1=new ImageIcon(hm.get(number_prop_1));
		Image img_prop_1=imgicon_prop_1.getImage();
		bufg_score.drawImage(img_prop_3, 50,50,null);
		bufg_score.drawImage(img_prop_2, 110,50,null);
		bufg_score.drawImage(img_prop_1, 170,50,null);
	}
	
	//爆炸动效
	public void explosion(FlyObject flo){
		if(flo.imgName.equals(fileAddress+"怪物.png")|flo.imgName.equals(fileAddress+"怪物2.png")){
			int x_explo=flo.location.x;
			int y_explo=flo.location.y;
			Vector location=new Vector(x_explo,y_explo);
			FlyObject explo=new FlyObject(location,null,null,"爆炸_1.png",10);
			explotions.add(explo);
		}if(flo.imgName.equals(fileAddress+"子弹.png")|flo.imgName.equals(fileAddress+"子弹2.png")){
			int x_explo=flo.location.x;
			int y_explo=flo.location.y;
			Vector location=new Vector(x_explo,y_explo);
			FlyObject explo=new FlyObject(location,null,null,"爆炸_子弹_2.png",5);
			explotions.add(explo);
		}
	}
	
	//绘制爆炸动效
	public void drawExplo(Graphics bufg_explotion){
		for(int i=0;i<explotions.size();i++){
			explotions.get(i).drawFO(bufg_explotion);
			explotions.get(i).HP--;
			
			if(explotions.get(i).imgName.equals(fileAddress+"爆炸_1.png")){

				ImageIcon imgicon=new ImageIcon(fileAddress+"爆炸_"+((explotions.get(i).HP%3)+1)+".png");
				explotions.get(i).img=imgicon.getImage();
			}
			
			if(explotions.get(i).HP==0){
				explotions.remove(i);
			}
		}
	}
	
}
