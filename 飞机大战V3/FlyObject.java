package com.cxd.threadGame210124.V3;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class FlyObject {
	
	public Vector location;//��ʼλ��
	public Vector speed;//�ٶ�
	public Vector acce;//���ٶ�
	public static String fileAddress="image_threadgame/";//����ͼƬ��ŵ�ַ
	
	HashMap<String,Vector> hm=new HashMap<String,Vector>();
	
	//����������ͼƬ
	public String imgName;
	public Image img;
	public int HP;
	
	//û��ͼƬ�ķ�����
	public FlyObject(Vector location,Vector speed,Vector acce){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		
		//��ʼ��
		initialize();
	}
	
	//��ͼƬ�ķ�����
	public FlyObject(Vector location,Vector speed,Vector acce,String imgName){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		
		this.imgName=fileAddress+imgName;
		ImageIcon imgicon=new ImageIcon(this.imgName);
		img=imgicon.getImage();
		
		//��ʼ��
		initialize();
	}
	
	//��ͼƬ����Ѫ���ķ�����
	public FlyObject(Vector location,Vector speed,Vector acce,String imgName,int HP){
		this.location=location;
		this.speed=speed;
		this.acce=acce;
		this.HP=HP;
		
		this.imgName=fileAddress+imgName;
		ImageIcon imgicon=new ImageIcon(this.imgName);
		img=imgicon.getImage();
		
		//��ʼ��
		initialize();
	}
	
	//��ʼ������
	public void initialize(){
		//���彫ÿ�ַ��������궨Ϊ������Ҫ�ƶ��ľ���
		hm.put(fileAddress+"�һ�.png",new Vector(50,50));
		hm.put(fileAddress+"�ӵ�.png",new Vector(17,10));
		hm.put(fileAddress+"�ӵ�2.png",new Vector(17,10));
		hm.put(fileAddress+"����.png", new Vector(0,0));
		hm.put(fileAddress+"����.png", new Vector(50,50));
		hm.put(fileAddress+"����2.png", new Vector(50,50));
		hm.put(fileAddress+"��ը_1.png", new Vector(50,50));
		hm.put(fileAddress+"��ը_2.png", new Vector(50,50));
		hm.put(fileAddress+"��ը_3.png", new Vector(50,50));
		hm.put(fileAddress+"��ը_�ӵ�_1.png",new Vector(10,10));
		hm.put(fileAddress+"��ը_�ӵ�_2.png",new Vector(10,10));
		hm.put(fileAddress+"����.png", new Vector(50,50));
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
