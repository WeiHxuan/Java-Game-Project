package com.cxd.catchballgame210126.V3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FlyObject {
	public Vector location,speed,acce;
	public String imgName;
	public int HP;
	public static String fileAddress="image_catchballgame/";
	
	public Image img;
	
	
	public FlyObject(Vector location,Vector speed,Vector acce){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
	}
	
	public FlyObject(Vector location,Vector speed,Vector acce,String imgName){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		this.imgName=fileAddress+imgName;
		
		ImageIcon imgico=new ImageIcon(imgName);
		img=imgico.getImage();
	}
	

	public FlyObject(Vector location,Vector speed,Vector acce,String imgName,int HP){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		this.imgName=fileAddress+imgName;
		this.HP=HP;
		
		ImageIcon imgico=new ImageIcon(imgName);
		img=imgico.getImage();
	}
	
	public void move(){
		speed.x+=acce.x;
		speed.y+=acce.y;
		location.x+=speed.x;
		location.y+=speed.y;
	}
	
	public void drawFO(Graphics g){
		if(imgName!=null){
			ImageIcon imgico=new ImageIcon(imgName);
			img=imgico.getImage();
			g.drawImage(img,location.x, location.y,null);
		}else{
			g.fillOval(location.x, location.y, 30, 30);
		}
	}
		

}
