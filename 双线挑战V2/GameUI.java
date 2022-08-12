package com.cxd.linecargame210127.V2;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GameUI extends JFrame{
	public Graphics g;
	public int len;
	public LineBall lb_blue=new LineBall(new Vector(0,0),new Vector(1,0));
	public LineBall lb_red=new LineBall(new Vector(69,69),new Vector(-1,0));
//	public int[][] chessBoard=new int [70][70];
	
	public static void main(String[]arguments){
		GameUI gui=new GameUI();
		gui.showFrame();
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
	
	
	public void showFrame(){
		PlayMusic p=new PlayMusic();
		p.music.play();
		p.music.loop();
		
		this.setSize(800, 820);
		this.setTitle("LineBall Game");
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
		g=this.getGraphics();
		

		final ThreadClass thc=new ThreadClass(g,lb_blue,lb_red);
		thc.start();
		
		class Listener implements ActionListener,MouseListener,MouseMotionListener,KeyListener{
			private GameUI gui;
			private int mu=0;
			
			public Listener (GameUI gui){
				this.gui=gui;
			}
			
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				String press=e.getKeyChar()+"";
				System.out.println(press+" is pressed!");
				
			}

			public void keyReleased(KeyEvent e) {
				int keyc=e.getKeyCode();
				System.out.println(keyc+" is released!");
				int speed=1;
				
				if(keyc==10){
					if(mu==0){
						p.music.stop();
						mu=1;
					}else if(mu==1){
						p.music.play();
						p.music.loop();
						mu=0;
					}
				}
				
				if(!lb_blue.gameOver&!lb_red.gameOver){
					if(thc.gameStart){
						if(keyc==32){//¿Õ¸ñ
							thc.on_off();
		//					System.out.println(thc.gameRest);
						}
						if(lb_blue.len!=0){
							if(lb_blue.speed.y==0){
								if(keyc==87){//w
									lb_blue.len=0;
									lb_blue.speed=new Vector(0,-speed);
								}
								if(keyc==83){//s
									lb_blue.len=0;
									lb_blue.speed=new Vector(0,speed);
								}
							}
							if(lb_blue.speed.x==0){
								if(keyc==65){//a
									lb_blue.len=0;
									lb_blue.speed=new Vector(-speed,0);
								}
								if(keyc==68){//d
									lb_blue.len=0;
									lb_blue.speed=new Vector(speed,0);
								}
							}
						}
		
						if(lb_red.len!=0){
							if(lb_red.speed.y==0){
								if(keyc==38){//¡ü
									lb_red.len=0;
									lb_red.speed=new Vector(0,-speed);
								}
								if(keyc==40){//¡ý
									lb_red.len=0;
									lb_red.speed=new Vector(0,speed);
								}
							}
							if(lb_red.speed.x==0){
								if(keyc==37){//¡û
									lb_red.len=0;
									lb_red.speed=new Vector(-speed,0);
								}
								if(keyc==39){//¡ú
									lb_red.len=0;
									lb_red.speed=new Vector(speed,0);
								}
							}
						}
					}else{
						if(keyc==32){//¿Õ¸ñ
							thc.gameStart=true;
						}
					}
				}else{
					if(keyc==32){//¿Õ¸ñ
						thc.flag2=0;
						thc.flag3=0;
						thc.flag4=0;
						
						LineBall.chessBoard=new int [70][70];
						lb_blue=new LineBall(new Vector(0,0),new Vector(1,0));
						lb_red=new LineBall(new Vector(69,69),new Vector(-1,0));
						
						thc.lb_blue=lb_blue;
						thc.lb_red=lb_red;
					}
				}
			}

			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
			public void actionPerformed(ActionEvent e) {
//				this.requestFocusInWindow();
//				System.out.println("button is pressed!");
			}
			
		}
		
		Listener lis=new Listener(this);
		this.addMouseListener(lis);
		this.addMouseMotionListener(lis);
		this.addKeyListener(lis);
		this.requestFocusInWindow();
		
	}
}
