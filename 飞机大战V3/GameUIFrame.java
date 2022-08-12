package com.cxd.threadGame210124.V3;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameUIFrame extends JFrame{
	public Graphics g;
	public ArrayList<FlyObject> fos=new ArrayList<FlyObject>();
	public ArrayList<FlyObject> mps=new ArrayList<FlyObject>();
	private ArrayList<FlyObject> enemys=new ArrayList<FlyObject>();
	public Boolean gameRest=false;
	
	
	public static void main(String[]args){
		GameUIFrame gameui=new GameUIFrame();
		gameui.showFrame();
	}
	
	public void showFrame(){
		this.setTitle("Thread Game");
		this.setSize(1200, 1200);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(3);
		
		JButton jb1=new JButton("START/STOP");
//		this.add(jb1);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		//ªÒ»°ª≠≤º
		this.g=this.getGraphics();

		Listener lis=new Listener(fos,mps);
		
		
		this.addMouseListener(lis);
		this.addMouseMotionListener(lis);

		ThreadClass tc=new ThreadClass(g,fos,mps);
		tc.start();
		System.out.println("Thread is working!");
		
		this.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				int xe=e.getX();
				int ye=e.getY();
				if(40<xe&xe<140&910<ye&ye<1010){
					tc.on_off();
				}
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
		});
	}
	

}
