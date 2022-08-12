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
	public static String fileAddress="image_threadgame/";//����ͼƬ��ŵ�ַ
	
	HashMap<Integer,String> hm=new HashMap<Integer,String>();
	
	public ThreadClass(Graphics g,ArrayList<FlyObject> fos){
		this.g=g;
		this.fos=fos;
		
		//��ʼ������
		initialize();
	}
	
	public ThreadClass(Graphics g,ArrayList<FlyObject> fos,ArrayList<FlyObject> mps){
		this.g=g;
		this.fos=fos;
		this.mps=mps;
		
		//��ʼ������
		initialize();
	}
	
	//��Ϸ��ͣ/��ʼ
	public void on_off(){
		gameRest=!gameRest;
	}
	
	public void run(){
		while(true){
			if(gameRest==false){
				System.out.println("len is "+len);
				//�ж���Ϸ�Ƿ����
				judgeGameOver(g);
				if(gameOver==true){
					break;
				}
				//��������
				BufferedImage bufImg=new BufferedImage(1200,1200,BufferedImage.TYPE_INT_ARGB);
				//��ȡ�����ϵĻ���
				Graphics bufg=bufImg.getGraphics();
				
				//ˢ�±���
				FlyObject background=new FlyObject(new Vector(0,0),null,null,"����.png");
				background.drawFO(bufg);
				
	
				len++;//������������ˢ�¹���ͱ��䣩
				//ˢ�¹���
				generateEnemy();
				
				//�ӵ��������˶�
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
				//���Ϸ����ӵ�
				generateBullet();
				
				//�ж��Ƿ����Ŀ��
				judgeAttack(bufg);
				
				//���Ʊ�ըЧ��
				drawExplo(bufg);
				
				//���ɱ���
				generateProp();
				//���Ʊ���
				drawProp(bufg);
				
				//�һ��˶�
				if(mps.size()-1>0){
					mps.get(mps.size()-1).drawFO(bufg);
				}
	
				//ˢ�·���
				refreshScore(bufg);
				
				//����ͣ��
				draw_on_off(bufg);
				
//				System.out.println("����һ��ͼ");
				g.drawImage(bufImg,0,0,null);
			}else{
				//����ͣ��
				draw_on_off(g);
			}
				
			//ϵͳ��ͣ����һ��ʱ��
			try{
				Thread.sleep(30);
			}catch(Exception ef){}
			
		}
	}
	
	//��ʼ������
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
	
	//�����ؼ�ͼƬ
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
	
	//���Ϸ����ӵ�
	public void generateBullet(){
		if(pickProp==0){
			if(len%5==0){
		    	for(int i=0;i<4;i++){
		    		Vector location_fo=new Vector(mps.get(mps.size()-1).location.x,mps.get(mps.size()-1).location.y+20*i);
		        	Vector speed_fo=new Vector(100,0);
		        	Vector acce_fo=new Vector(0,0);
		        	FlyObject fo=new FlyObject(location_fo,speed_fo,acce_fo,"�ӵ�.png",1);
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
		        	FlyObject fo=new FlyObject(location_fo,speed_fo,acce_fo,"�ӵ�2.png",5);
		        	fos.add(fo);
		    	}
			}
		}
	}
	
	//���ɹ���
	public void generateEnemy(){
		//��һ��ʱ��ˢ�³�һ������
		if(len%20==0){
			Random ran=new Random();
			int loc_y=ran.nextInt(900)+100;
			int spd_x=-ran.nextInt(10)-10;
			Vector location=new Vector(1200,loc_y);
			Vector speed=new Vector(spd_x,0);
			Vector acce=new Vector(0,0);
			FlyObject enemy=new FlyObject(location,speed,acce,"����.png",5);
			enemys.add(enemy);
		}
		if(len%80==0){
			Random ran=new Random();
			int loc_y=ran.nextInt(900)+100;
			int spd_x=-ran.nextInt(5)-5;
			Vector location=new Vector(1200,loc_y);
			Vector speed=new Vector(spd_x,0);
			Vector acce=new Vector(0,0);
			FlyObject enemy=new FlyObject(location,speed,acce,"����2.png",20);
			enemys.add(enemy);
		}
	}
	
	//���ɱ���
	public void generateProp(){
		if(len%300==0){
			Random ran=new Random();
			int loc_x=ran.nextInt(900)+100;
			int loc_y=ran.nextInt(900)+100;
			Vector location=new Vector(loc_x,loc_y);
			Vector speed=new Vector(0,0);
			Vector acce=new Vector(0,0);
			FlyObject prop=new FlyObject(location,speed,acce,"����.png",100);
			props.add(prop);
		}
	}
	
	//���Ʊ���
	public void drawProp(Graphics bfug_prop){
		for(int i=0;i<props.size();i++){
			props.get(i).drawFO(bfug_prop);
			props.get(i).HP--;
			
			if(props.get(i).imgName.equals("����.png")){

				ImageIcon imgicon=new ImageIcon("����.png");
				props.get(i).img=imgicon.getImage();
			}
			
			if(props.get(i).HP==0){
				props.remove(i);
			}
		}
	}
	
	//�ж���Ϸ�Ƿ����
	public void judgeGameOver(Graphics g_judgeGameOver){
		for(int i=0;i<enemys.size();i++){
			FlyObject en=enemys.get(i);
			
			FlyObject mp=mps.get(mps.size()-1);
			
			//��ʱ��֪����һ��Ϊʲôż�������
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
	
	//�ж��ӵ��Ƿ���й�������Ƿ����һ����Ƿ�ʰ�ñ���
	public void judgeAttack(Graphics bufg_judgeAttack){
		//�ж��ӵ��Ƿ���й�������Ƿ����һ�
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
					//��������ӵ�ͬʱ����һ������Ǿ����������ӵ�����������һ�����������
					try{
						en.HP-=fo.HP;
						explosion(fos.get(j));
						fos.remove(j);
						if(en.HP<=0){
							//������Լ�һ����ըЧ����
							explosion(enemys.get(i));
							//�������ֱ����enemys.remove(i)�ᵼ��ѭ��for(int j=0;j<fos.size();j++)����ִ�У���ɾ����Ԫ��
							enemys.get(i).img=null;
							//�ѹ���ͼƬȥ����ÿ�λ�ͼ�Ͳ����ù����ˣ���Ȼ������Ƴ���Ļ
							enemys.get(i).location=new Vector(-1000,0);
							if(en.imgName.equals(fileAddress+"����.png")){
								score+=10;
							}else if(en.imgName.equals(fileAddress+"����2.png")){
								score+=50;
							}
						}
					}catch(Exception IndexOutOfBounds){
						fos.remove(j);
					}
				}
				
			}
		}
		
		//�ж��Ƿ�ʰ�ñ���
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
	
	//ˢ�·��������ߵ���
	public void refreshScore(Graphics bufg_score){
		//ˢ�·���
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
		
		//ˢ�µ��ߵ���
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
	
	//��ը��Ч
	public void explosion(FlyObject flo){
		if(flo.imgName.equals(fileAddress+"����.png")|flo.imgName.equals(fileAddress+"����2.png")){
			int x_explo=flo.location.x;
			int y_explo=flo.location.y;
			Vector location=new Vector(x_explo,y_explo);
			FlyObject explo=new FlyObject(location,null,null,"��ը_1.png",10);
			explotions.add(explo);
		}if(flo.imgName.equals(fileAddress+"�ӵ�.png")|flo.imgName.equals(fileAddress+"�ӵ�2.png")){
			int x_explo=flo.location.x;
			int y_explo=flo.location.y;
			Vector location=new Vector(x_explo,y_explo);
			FlyObject explo=new FlyObject(location,null,null,"��ը_�ӵ�_2.png",5);
			explotions.add(explo);
		}
	}
	
	//���Ʊ�ը��Ч
	public void drawExplo(Graphics bufg_explotion){
		for(int i=0;i<explotions.size();i++){
			explotions.get(i).drawFO(bufg_explotion);
			explotions.get(i).HP--;
			
			if(explotions.get(i).imgName.equals(fileAddress+"��ը_1.png")){

				ImageIcon imgicon=new ImageIcon(fileAddress+"��ը_"+((explotions.get(i).HP%3)+1)+".png");
				explotions.get(i).img=imgicon.getImage();
			}
			
			if(explotions.get(i).HP==0){
				explotions.remove(i);
			}
		}
	}
	
}
