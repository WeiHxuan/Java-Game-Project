package com.cxd.threadGame210124.V3;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Listener implements ActionListener,MouseListener,MouseMotionListener{
	private ArrayList<FlyObject> fos=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> mps=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> enemys=new ArrayList<FlyObject>();
	private Boolean mousePosition=true;
	public static String fileAddress="image_threadgame/";//定义图片存放地址
	
	public Listener(){
		
	}
	
	public Listener(ArrayList<FlyObject> fos){
		this.fos=fos;
	}
	
	public Listener(ArrayList<FlyObject> fos,ArrayList<FlyObject> mps){
		this.fos=fos;
		this.mps=mps;
	}
	
	public void actionPerformed(ActionEvent e){
	}
	
	public void mouseClicked(MouseEvent e){
	}
    public void mousePressed(MouseEvent e){
    	
    }
     
    public void mouseReleased(MouseEvent e){
//		System.out.println(gameRest);
    	Vector location=new Vector(e.getX(),e.getY());
    	Vector speed=new Vector(10,0);
    	Vector acce=new Vector(0,0);
    	
    	FlyObject fo=new FlyObject(location,speed,acce,"子弹.png");
    	fos.add(fo);
     }
     
    public void mouseEntered(MouseEvent e){
     }
     
    public void mouseExited(MouseEvent e){
    }
    
    public void mouseDragged(MouseEvent e){
    	
    }
    public void mouseMoved(MouseEvent e){
    	Vector location=new Vector(e.getX(),e.getY());
    	FlyObject mp=new FlyObject(location,null,null,"我机.png");
    	mps.add(mp);
    	
    }

}
