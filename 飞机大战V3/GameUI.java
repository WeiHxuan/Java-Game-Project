package com.cxd.catchballgame210126.V3;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameUI extends JFrame{
	public Graphics g;
	public ArrayList<FlyObject> fos=new ArrayList<>();
	public ArrayList<FlyObject> mps=new ArrayList<>();
	public static String fileAddress="image_catchballgame/";
	
	public static void main(String[]args){
		GameUI gui=new GameUI();
		gui.showFrame();
	}
	
	public void showFrame(){
		this.setSize(900, 900);
		this.setTitle("catch ball game");
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		
		g=this.getGraphics();
		
		ThreadClass thc=new ThreadClass(g,fos,mps);
		thc.start();
		
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				int xe=e.getX();
				int ye=e.getY();
				if(30<xe&xe<130&760<ye&ye<860){
					thc.on_off();
				}
				if(thc.gameOver){
					if(e.getX()>340&e.getX()<540&e.getY()>630&e.getY()<710){
						thc.life=3;
						thc.fos.removeAll(fos);
						thc.mps.removeAll(mps);
						thc.score=0;
						thc.gameOver=false;
					}
				}
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			
		});
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
			}
			public void mouseMoved(MouseEvent e) {
				Vector location=new Vector(e.getX()-50,800);
				mps.add(new FlyObject(location,null,null,"½Ó¶¹ÈË¿ª.png"));
				
			}
			
		});
		
	}
}
