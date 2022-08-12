package com.cxd.threadGame210124.V3;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class FlyObject {
	
	public Vector location;//初始位置
	public Vector speed;//速度
	public Vector acce;//加速度
	public static String fileAddress="image_threadgame/";//定义图片存放地址
	
	HashMap<String,Vector> hm=new HashMap<String,Vector>();
	
	//创建飞行物图片
	public String imgName;
	public Image img;
	public int HP;
	
	//没有图片的飞行物
	public FlyObject(Vector location,Vector speed,Vector acce){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		
		//初始化
		initialize();
	}
	
	//有图片的飞行物
	public FlyObject(Vector location,Vector speed,Vector acce,String imgName){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		
		this.imgName=fileAddress+imgName;
		ImageIcon imgicon=new ImageIcon(this.imgName);
		img=imgicon.getImage();
		
		//初始化
		initialize();
	}
	
	//有图片、有血量的飞行物
	public FlyObject(Vector location,Vector speed,Vector acce,String imgName,int HP){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		this.HP=HP;
		
		this.imgName=fileAddress+imgName;
		ImageIcon imgicon=new ImageIcon(this.imgName);
		img=imgicon.getImage();
		
		//初始化
		initialize();
	}
	
	//初始化参数
	public void initialize(){
		//定义将每种飞行物坐标定为中心需要移动的距离
		hm.put(fileAddress+"我机.png",new Vector(50,50));
		hm.put(fileAddress+"子弹.png",new Vector(17,10));
		hm.put(fileAddress+"子弹2.png",new Vector(17,10));
		hm.put(fileAddress+"背景.png", new Vector(0,0));
		hm.put(fileAddress+"怪物.png", new Vector(50,50));
		hm.put(fileAddress+"怪物2.png", new Vector(50,50));
		hm.put(fileAddress+"爆炸_1.png", new Vector(50,50));
		hm.put(fileAddress+"爆炸_2.png", new Vector(50,50));
		hm.put(fileAddress+"爆炸_3.png", new Vector(50,50));
		hm.put(fileAddress+"爆炸_子弹_1.png",new Vector(10,10));
		hm.put(fileAddress+"爆炸_子弹_2.png",new Vector(10,10));
		hm.put(fileAddress+"宝箱.png", new Vector(50,50));
	}
	
	public void move(){
		speed.add(acce);
		location.add(speed);
	}
	
	public void drawFO(Graphics g){
		if(imgName!=null){
//			System.out.println(imgName);
			int deviationx=hm.get(imgName).x;
			int deviationy=hm.get(imgName).y;
			g.drawImage(img,location.x-deviationx, location.y-deviationy,null);
		}else{
			g.fillOval(location.x, location.y,10,10);
		}
	}

	
}
